/*
 * ProxyIgrue - Reimplementazione free del Sender IGRUE del MEF 
 * http://igrue.gov4j.it
 * 
 * Copyright (c) 2009-2015 Link.it srl (http://link.it). 
 * Copyright (c) 2009 Provincia Autonoma di Bolzano (http://www.provincia.bz.it/). 
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.govmix.proxy.igrue.managers;

import it.mef.csp.webservices.dto.Ticket;
import it.mef.csp.webservices.messages.trasmissione.Content;
import it.mef.csp.webservices.messages.trasmissione.InviaFileIn;
import it.mef.csp.webservices.messages.trasmissione.InviaFileOut;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.Constants;
import org.govmix.proxy.igrue.moduli.Spedizione;
import org.govmix.proxy.igrue.moduli.utils.FileUtils;
import org.govmix.proxy.igrue.web.ejb.Esito;
import org.govmix.proxy.igrue.web.ejb.IdTrasmissione;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.govmix.proxy.igrue.web.ejb.dao.IEsitoService;
import org.govmix.proxy.igrue.web.ejb.dao.ITrasmissioneService;
import org.govmix.proxy.igrue.web.ejb.dao.jdbc.JDBCServiceManager;
import org.govmix.proxy.igrue.web.ejb.utils.UtentiUtilities;
import org.govmix.proxy.igrue.web.service.ServiceManagerFactory;
import org.govmix.proxy.igrue.ws.IgrueUtils;
import org.openspcoop2.generic_project.exception.MultipleResultException;
import org.openspcoop2.generic_project.exception.NotFoundException;
import org.openspcoop2.generic_project.exception.NotImplementedException;
import org.openspcoop2.generic_project.exception.ServiceException;



public class Trasmissione {
	private Logger log = LogManager.getLoggerSpedizioneDati();
	private String file;
	private IdTrasmissione idTrasmissione;
	private int fileId;
	private Timestamp dataUltimoInvio;
	private int esitoUltimoInvio = Integer.MAX_VALUE;
	private int wid;
	
	
	public Trasmissione(Spedizione spedizione) throws SQLException, ServiceException, NotImplementedException, MultipleResultException, NotFoundException {
		this(spedizione, 0);
	}

	
	public Trasmissione(Spedizione spedizione, int wid) throws SQLException, ServiceException, NotImplementedException, MultipleResultException, NotFoundException {

		this.wid = wid;
		this.file = spedizione.getFile();

		if(this.file != null && !(new File(UtentiUtilities.getOutboxDir(spedizione.getUtente()) + spedizione.getFile() +".zip")).exists()){
			fileId = Integer.parseInt(file);
		}
		else {
			//Ottengo l'identificativo del messaggio, ovvero il next in db
			DBManager dbManager = DBManager.getInstance();
			Connection con = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				con = dbManager.getConnection();
				stmt = con.prepareStatement("SELECT nextval('sq_trasmissioni')");
				rs = stmt.executeQuery();
				if ( rs.next() ) {  
					fileId = rs.getInt(1);  
					if(this.file == null) {
						
					}
				} else {
					throw new SQLException("La sequence 'sq_trasmissioni' non ha ritornato il nextval.");
				}
			} finally {
				if(rs != null) try { rs.close(); } catch (Exception e) {}
				if(stmt != null) try { stmt.close(); } catch (Exception e) {}
				if(con != null) try { con.close(); } catch (Exception e) {}
			}
		}

		spedizione.setIdentificativo(fileId);
		
		// Controllo se esiste gia una trasmissione in db
		DBManager dbManager = DBManager.getInstance();
		Connection con = null;

		try{
			log.debug("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+fileId+"] Controllo l'esistenza della trasmissione ");
			con = dbManager.getConnection();
			con.setAutoCommit(false);

			JDBCServiceManager serviceManager = new JDBCServiceManager(con, ServiceManagerFactory.getJDBCProperties(false));  
			ITrasmissioneService trasmissioneService = serviceManager.getTrasmissioneService();
			idTrasmissione = new IdTrasmissione();
			idTrasmissione.setFile(fileId);
			idTrasmissione.setUtente(spedizione.getUtente());


			if(trasmissioneService.exists(idTrasmissione)){

				org.govmix.proxy.igrue.web.ejb.Trasmissione t = trasmissioneService.get(idTrasmissione);

				//La trasmissione esiste. 
				log.debug("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+fileId+"] Trovata trasmissione su database.");
				dataUltimoInvio = new Timestamp(t.getDataultimoinvio() != null ? t.getDataultimoinvio().getTime() : 0);
				esitoUltimoInvio = t.getEsitoultimoinvio();
			}
			else{
				//la trasmissione non esiste. Creo il record per la gestione dell'invio dei dati di attuazione
				log.debug("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+fileId+"] Creazione trasmissione del file " + file + " su database");

				org.govmix.proxy.igrue.web.ejb.Trasmissione t = new org.govmix.proxy.igrue.web.ejb.Trasmissione();

				t.setFile(idTrasmissione.getFile());
				t.setIdAmministrazione(idTrasmissione.getUtente().getIdAmministrazione());
				t.setIdSistema(idTrasmissione.getUtente().getIdSistema());
				t.setEsitoultimoinvio(Integer.MAX_VALUE);
				
				trasmissioneService.create(t);

				IEsitoService esitoCRUD = serviceManager.getEsitoService();

				Esito esito = new Esito();

				esito.setFile(idTrasmissione.getFile());
				esito.setIdAmministrazione(idTrasmissione.getUtente().getIdAmministrazione());
				esito.setIdSistema(idTrasmissione.getUtente().getIdSistema());
				esito.setStatisticheelaborazioni(Integer.MAX_VALUE);
				esito.setStatistichescarti(Integer.MAX_VALUE);
				esito.setEsitoelaborazioneperanagraficadiriferimento(Integer.MAX_VALUE);
				esito.setLogdeglierrori(Integer.MAX_VALUE);
				esito.setDataultimocontrollo(new java.util.Date(0));

				esitoCRUD.create(esito);

				con.commit();

			}
		} catch (SQLException sqle){
			LogManager.getLoggerSpedizioneDati().error("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+file+"] Errore durante la creazione/ricerca di una trasmissione:" + sqle);
			try{
				con.rollback();
			} catch(Exception e){}
			throw sqle;
		} finally {
			dbManager.releaseConnection(con);
		}
	}

	public String getFile(){
		return file;
	}

	public int getFileId(){
		return fileId;
	}
	public Ticket getTicket() throws Exception{
		TicketManager tm = new TicketManager(idTrasmissione, this.wid, log);
		return tm.getTicket();
	}


	/**
	 * Invia il file al sistema centrale con il ticket della trasmissione.
	 * 
	 * Lancia eccezioni se:
	 * ha problemi di IO con il file
	 * ha problemi a contattare Igrue
	 * 
	 * @param filePath
	 * @throws Exception
	 */
	public boolean inviaFile(Ticket t, IdUtente utente, int wid) {
		// Controllo che il file non sia gia' stato inviato con successo
		if(esitoUltimoInvio == Constants.EsitoInvioSuccesso || esitoUltimoInvio == Constants.FileGiaInviato){
			//Messaggio gia inviato con successo. Proseguo senza reinviare.
			log.debug("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"][" + utente.getIdAmministrazione() + "." + utente.getIdSistema() + "]["+file+"] File gia' inviato il " + dataUltimoInvio);
			FileUtils.cleanOutbox(file, utente);
			return true;
		}

		// Messaggio da inviare
		IgrueUtils igrueUtils = null;
		String filepath = UtentiUtilities.getInboxDir(utente) + fileId + "/"+ fileId + ".zip";
		log.debug("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+fileId+"] Richiesto l'invio del file " + filepath);
		File file = new File(filepath);
		InviaFileOut esito = null;
		DataHandler dataHandler = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			dataHandler = new DataHandler(new ByteArrayDataSource(fis, "application/zip"));
		} catch (Exception e) {
			MailManager.postMail(utente, "INV_FIL_03",e);
			log.error("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+file+"] Errore durante la lettura del file " + file.getAbsolutePath(),e);
			return false;
		}
		it.eng.csp.webservices.Trasmissione client = null;
		try {
			InviaFileIn fileIn = new InviaFileIn();
			fileIn.setTicket(t);
			fileIn.setCredenziali(UtentiUtilities.getCredenziali(utente));
			igrueUtils=new IgrueUtils();
			client = igrueUtils.getTrasmissioneClient();
			@SuppressWarnings("unchecked")
			Map<String, DataHandler> attachments = (Map<String, DataHandler>)((BindingProvider)client).getRequestContext().get(MessageContext.OUTBOUND_MESSAGE_ATTACHMENTS);
			if(attachments == null) attachments = new HashMap<String, DataHandler>();
			String id = "ATT0001";
			attachments.put(id, dataHandler);
			((BindingProvider)client).getRequestContext().put(MessageContext.OUTBOUND_MESSAGE_ATTACHMENTS, attachments);
			Content content = new Content();
			content.setHref("cid:" + id);
			esito = client.inviaFile(fileIn, content);
		} catch (Exception e) {
			log.error("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+file+"] Errore nella spedizione dei dati di attuazione: " + e,e);
			igrueUtils.logHttpResponse((BindingProvider)client, log);
			MailManager.postMail(utente, "INV_FIL_01",e);
			return false;
		} 

		if(esito != null){
			log.info("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+fileId+"] Dati di attuazione inviati con esito " + esito.getEsitoOperazione().getCodiceEsito() + ": " + esito.getEsitoOperazione().getDescrizioneEsito() + " - " + esito.getEsitoOperazione().getDettaglio());
		}else{
			log.error("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+fileId+"] Dati di attuazione inviati ma nessun esito restituito (??)");
			MailManager.postMail(utente, "INV_FIL_06",new Exception("["+file+"] Dati di attuazione inviati ma nessun esito restituito"));
			return false;
		}
		DBManager dbManager = DBManager.getInstance();
		Connection con = null;

		// registro l'esito su database
		try{

			con = dbManager.getConnection();
			con.setAutoCommit(false);

			JDBCServiceManager serviceManager = new JDBCServiceManager(con, ServiceManagerFactory.getJDBCProperties(false));
			ITrasmissioneService trasmissioneService = serviceManager.getTrasmissioneService();

			IdTrasmissione idTrasmissione = new IdTrasmissione();
			
			idTrasmissione.setFile(fileId);
			idTrasmissione.setUtente(utente);

			org.govmix.proxy.igrue.web.ejb.Trasmissione trasmissione = trasmissioneService.get(idTrasmissione);

			trasmissione.setDataultimoinvio(new java.util.Date());
			trasmissione.setEsitoultimoinvio(esito.getEsitoOperazione().getCodiceEsito());
			trasmissione.setEsitoultimoinviodescrizione(esito.getEsitoOperazione().getDescrizioneEsito() + " - " + esito.getEsitoOperazione().getDettaglio());

			trasmissioneService.update(idTrasmissione, trasmissione);

			con.commit();
		} catch (Exception sqle){
			log.error("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+fileId+"] SQLException durante la creazione/ricerca di una trasmissione");
			try{con.rollback();} catch(SQLException e){}
			MailManager.postMail(utente, "INV_FIL_05",sqle);
			return false;
		} finally {
			dbManager.releaseConnection(con);
		}

		//Controllo l'esito dell'invio
		if(esito.getEsitoOperazione().getCodiceEsito() == Constants.EsitoInvioSuccesso){
			String txt = "";
			if (this.file != null) txt += "\nNome file originale: " + this.file + ".zip";
			txt += "\n\nIdentificativo Trasmissione: " + fileId + "\n";
			MailManager.postMail(utente, "INV_FIL_09",txt);
		}
		else if(esito.getEsitoOperazione().getCodiceEsito() == Constants.FileGiaInviato){
			String txt = "";
			if (this.file != null) txt += "\nNome file originale: " + this.file + ".zip";
			txt += "\n\n"+ esito.getEsitoOperazione().getDettaglio() +"\nIdentificativo Trasmissione: " + fileId + "\n";
			MailManager.postMail(utente, "INV_FIL_10",txt);
		}
		else{
			MailManager.postMail(utente, "INV_FIL_08",esito.getEsitoOperazione());
		}

		return false;
	}

	public static void notifica(IdTrasmissione idTrasmissione){
		
		DBManager dbManager = DBManager.getInstance();
		Connection con = null;
		// registro l'esito su database
		try{

			con = dbManager.getConnection();
			con.setAutoCommit(false);

			JDBCServiceManager serviceManager = new JDBCServiceManager(con, ServiceManagerFactory.getJDBCProperties(false));
			ITrasmissioneService trasmissioneService = serviceManager.getTrasmissioneService();

			org.govmix.proxy.igrue.web.ejb.Trasmissione trasmissione = trasmissioneService.get(idTrasmissione);

			trasmissione.setNotificato(true);

			trasmissioneService.update(idTrasmissione, trasmissione);
		} catch (Exception sqle){
			try{con.rollback();} catch(SQLException e){}
			MailManager.postMail(idTrasmissione.getUtente(), "INV_FIL_05", sqle);
			return;
		} finally {
			dbManager.releaseConnection(con);
		}
	}
}

