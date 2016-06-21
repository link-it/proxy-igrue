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

import it.mef.csp.webservices.dto.Credenziali;
import it.mef.csp.webservices.dto.Ticket;
import it.mef.csp.webservices.messages.trasmissione.PrenotazioneTrasmissioneIn;
import it.mef.csp.webservices.messages.trasmissione.PrenotazioneTrasmissioneOut;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.web.ejb.IdTrasmissione;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.govmix.proxy.igrue.web.ejb.TicketId;
import org.govmix.proxy.igrue.web.ejb.dao.ITicketService;
import org.govmix.proxy.igrue.web.ejb.utils.UtentiUtilities;
import org.govmix.proxy.igrue.web.service.ServiceManagerFactory;
import org.govmix.proxy.igrue.ws.IgrueUtils;
import org.openspcoop2.generic_project.exception.ServiceException;



public class TicketManager {
	
	private Ticket T;
	private Logger log;
	private int file;
	private IdTrasmissione id;
	private int wid;
	/**
	 * Cerca il ticket della trasmissione. 
	 * In prima istanza cerca su database.
	 * Se non e' stato ancora registrato un ticket per la trasmissione,
	 * ne richiede uno al sistema centrale Igrue e lo memorizza su database
	 * 
	 * Lancia eccezioni se:
	 * non riesce ad accedere al db
	 * non riesce a contattare Igrue.
	 * 
	 * @return ticket
	 * @throws Exception
	 */
	
	
	public TicketManager(IdTrasmissione id, int wid, Logger log) throws Exception{
		this.log = log;
		this.file = id.getFile();
		this.id = id;
		this.wid = wid;
		T = new Ticket();
		try{
			if(getFromDB()){
				return;
			}
		}
		catch(Exception e){
			MailManager.postMail(id.getUtente(), "RIC_TCK_02", e);
			throw new Exception("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+file+"] Errore durante il recupero del ticket su database: " + e,e);
		}
		
		// Ticket non in db, lo richiedo ad Igrue
		// Se c'e' un errore con il database o con il ws, ritorno null. 
		try{
			if(getFromWS(UtentiUtilities.getCredenziali(id.getUtente()))) {
				saveToDB();
				String fileName = UtentiUtilities.getInboxDir(id.getUtente()) + file;
				File info = new File(fileName + "/INFO.txt");
				FileOutputStream fos = new FileOutputStream(info);
				fos.write(("Ticket assegnato: " + Long.toString(this.T.getIdTicket())).getBytes());
				fos.close();
			}
			
		}
		catch(SQLException e){
			MailManager.postMail(id.getUtente(), "RIC_TCK_02",e);
			throw new Exception("["+file+"] Errore durante il salvataggio del ticket su database: " + e);
		}
		catch(Exception e){
			MailManager.postMail(id.getUtente(), "RIC_TCK_01",e);
			CheckManager.updateCheck(CheckManager.checkTicket, 02, log);
			throw new Exception("["+file+"] Errore nella richiesta del ticket al sistema centrale Igrue.", e);
		}
		if(this.T == null){
			MailManager.postMail(id.getUtente(), "RIC_TCK_04",new Exception("["+file+"] Impossibile recuperare il ticket."));
			throw new Exception("["+file+"] Impossibile recuperare il ticket.");
		}
	}
	
	/**
	 * Ricerca e restituisce un ticket in base all'id della trasmissione. Null se non esiste.
	 *  
	 * @param idtrasmissione 
	 * @return ticket
	 * @throws SQLException
	 * @throws DatatypeConfigurationException
	 */
	private boolean getFromDB() throws ServiceException {
		
		log.debug("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+file+"] Ricerco ticket su database");
		
		//cerco il ticket in database
		try{
			ITicketService ticketService = ServiceManagerFactory.getServiceManager().getTicketService();
			
			TicketId id = new TicketId();
			IdTrasmissione trasmissione = new IdTrasmissione();
			trasmissione.setFile(file);
			trasmissione.setUtente(this.id.getUtente());
			
			id.setTrasmissione(trasmissione);
			
			
			if(ticketService.exists(id)) {
				log.debug("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+file+"] Trovato ticket su database");
				org.govmix.proxy.igrue.web.ejb.Ticket ticket = ticketService.get(id);
				GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
				Date dataAssegnazione =  ticket.getDataassegnazione()!= null ? new java.sql.Date(ticket.getDataassegnazione().getTime()) : null;
				if(dataAssegnazione!=null){
					gc.setTime(dataAssegnazione);
					this.T.setDataAssegnazione(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
				}
				Date dataFineTrasmissione = ticket.getDatafinetrasmissione() != null ? new java.sql.Date(ticket.getDatafinetrasmissione().getTime()) : null;
				if(dataFineTrasmissione!=null){
					gc.setTime(dataFineTrasmissione);
					this.T.setDataFineTrasmissione(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
				}
				this.T.setFileRicevuto(ticket.getFilericevuto());
				this.T.setIdAmministrazione(ticket.getIdAmministrazione());
				this.T.setIdSistema(ticket.getIdSistema());
				this.T.setIdTicket(Long.parseLong(ticket.getIdticket()));
				return true;
			} else {
				log.debug("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+file+"] Ticket su database non trovato.");
				return false;
			}
		} catch(Exception e) {
			log.debug("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]Errore durante la ricerca del ticket associato al file["+file+"]", e);
			throw new ServiceException("Errore durante la ricerca del ticket associato al file["+file+"]", e);
		}
	}
	
	/**
	 * Salva il ticket nel database con l'identificativo passato. Lancia un'eccezione SQL se il ticket esiste gia per quell'id.
	 * 
	 * @param file
	 * @param ticket
	 * @throws SQLException
	 * @throws DatatypeConfigurationException
	 */
	private void saveToDB() throws ServiceException {
		log.info("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+file+"] Salvataggio del ticket ("+this.T.getIdTicket()+") su database");
		try{
			ITicketService ticketService = ServiceManagerFactory.getServiceManager().getTicketService();
			
			org.govmix.proxy.igrue.web.ejb.Ticket ticket = new org.govmix.proxy.igrue.web.ejb.Ticket();
			
			ticket.setFile(file);
			ticket.setIdAmministrazione(this.T.getIdAmministrazione());
			ticket.setIdSistema(this.T.getIdSistema());
			ticket.setDataassegnazione(new Date(this.T.getDataAssegnazione().toGregorianCalendar().getTimeInMillis()));
			ticket.setIdticket(Long.toString(this.T.getIdTicket()));
			
			ticketService.create(ticket);

		}
		catch(Exception e){
			log.error("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+file+"] Errore in salvataggio ticket ("+this.T.getIdTicket()+")",e);
			throw new ServiceException("["+file+"] Errore in salvataggio ticket ("+this.T.getIdTicket()+")",e);		}
	}
	
	private boolean getFromWS(Credenziali c) throws Exception{
		log.debug("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+file+"] Richiedo ticket al Sistema Centrale IGRUE");
		PrenotazioneTrasmissioneIn in = new PrenotazioneTrasmissioneIn();
		in.setCredenziali(c);
		IgrueUtils igrueUtils = new IgrueUtils();
		it.eng.csp.webservices.Trasmissione client = igrueUtils.getTrasmissioneClient();
		PrenotazioneTrasmissioneOut out;
		try {
			out = client.prenotazioneTrasmissione(in);
		} catch (Exception e) {
			log.error("Errore del servizio centrale IGRUE" , e);
			igrueUtils.logHttpResponse((BindingProvider) client, log);
			throw e;
		}
		if(out.getEsitoOperazione().getCodiceEsito() == 0) {
			log.info("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+file+"] Ticket (" + out.getTicket().getIdTicket() + ") ricevuto con esito " + out.getEsitoOperazione().getCodiceEsito() + ": " + out.getEsitoOperazione().getDettaglio() + " - " + out.getEsitoOperazione().getDescrizioneEsito());
			CheckManager.updateCheck(CheckManager.checkTicket, 0, log);
		} else {
			log.error("[" + ((wid==Integer.MAX_VALUE) ? "WebUI" : "Worker_" + wid )+"]["+file+"] Richiesta Ticket fallita con esito " + out.getEsitoOperazione().getCodiceEsito() + ": " + out.getEsitoOperazione().getDettaglio() + " - " + out.getEsitoOperazione().getDescrizioneEsito());
			CheckManager.updateCheck(CheckManager.checkTicket, 1, log);
			IdUtente u = new IdUtente();
			u.setIdAmministrazione(c.getIdAmministrazione());
			u.setIdSistema(c.getIdSistema());
			MailManager.postMail(u, "RIC_TCK_03",out.getEsitoOperazione());
			throw new Exception("Richiesta Ticket fallita con esito " + out.getEsitoOperazione().getCodiceEsito() + ": " + out.getEsitoOperazione().getDettaglio() + " - " + out.getEsitoOperazione().getDescrizioneEsito());
		}
		return((this.T = out.getTicket()) != null);
	}
	
	public Ticket getTicket(){
		return this.T;
	}

	
}
