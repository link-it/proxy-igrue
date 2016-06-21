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
package org.govmix.proxy.igrue.moduli;


import it.eng.csp.webservices.ArrayOfXsdLong;
import it.eng.csp.webservices.GestioneEventi;
import it.mef.csp.webservices.dto.Credenziali;
import it.mef.csp.webservices.dto.EventWS;
import it.mef.csp.webservices.messages.gestioneeventi.CancellaIn;
import it.mef.csp.webservices.messages.gestioneeventi.CancellaOut;
import it.mef.csp.webservices.messages.gestioneeventi.ListaIn;
import it.mef.csp.webservices.messages.gestioneeventi.ListaOut;
import it.mef.csp.webservices.messages.gestioneeventi.ListaTipiIn;
import it.mef.csp.webservices.messages.gestioneeventi.ListaTipiOut;
import it.mef.system.event.EventType;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.Configuration;
import org.govmix.proxy.igrue.managers.CheckManager;
import org.govmix.proxy.igrue.managers.DBManager;
import org.govmix.proxy.igrue.managers.LogManager;
import org.govmix.proxy.igrue.managers.MailManager;
import org.govmix.proxy.igrue.managers.Trasmissione;
import org.govmix.proxy.igrue.web.ejb.IdTrasmissione;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.govmix.proxy.igrue.web.ejb.utils.UtentiUtilities;
import org.govmix.proxy.igrue.ws.IgrueUtils;



public class RaccoltaEventi implements Runnable {
	/** Variabile di stop del thread */
	private boolean stop = false;
	private boolean isAlive = true;
	
	//@Override
	public void run() {
		Logger log = LogManager.getLoggerRaccoltaEventi();
		try {
			for(IdUtente utente : UtentiUtilities.getLstUtenti(log)) {
				String contestoDir = UtentiUtilities.getContestoDir(utente);
				String inboxDir = UtentiUtilities.getInboxDir(utente);
				File c = new File(contestoDir);
				if(!c.isDirectory()) c.mkdir();
				File e = new File(contestoDir + "EVENTI");
				if(!e.isDirectory()) e.mkdir();
				File eo = new File(inboxDir + "EVENTI_ORFANI");
				if(!eo.isDirectory()) eo.mkdir();
			}
		} catch (Exception e) {
			log.error("Errore nell'avvio del sistema di raccolta eventi: " + e);
			LogManager.getLoggerASConsole().error("Errore nell'avvio del sistema di raccolta eventi: " + e,e);
			MailManager.postMail(null, "GST_EVN_02",e);
			stop = true;
		}
		
		IgrueUtils igrueUtils = null;
		
		try{
			igrueUtils = new IgrueUtils();
		} catch(Exception e) {
			log.error("Errore nell'avvio del sistema di raccolta eventi. Impossibile instanziare i fruitori dei servizi MEF: " + e);
			LogManager.getLoggerASConsole().error("Errore nell'avvio del sistema di raccolta eventi. Impossibile instanziare i fruitori dei servizi MEF: " + e,e);
			MailManager.postMail(null,"GST_EVN_02",new Exception("Impossibile instanziare i fruitori dei servizi MEF"));
			stop = true;
			throw new RuntimeException("Impossibile instanziare i fruitori dei servizi MEF", e);
		}
		
		while(stop==false){
			
			List<IdUtente> utenti = new ArrayList<IdUtente>();
			try{
				utenti = UtentiUtilities.getLstUtenti(log);
			} catch(Exception e) {
				log.error("Impossibile recuperare la lista degli utenti: " + e);
				LogManager.getLoggerASConsole().error("Impossibile recuperare la lista degli utenti: " + e,e);
				MailManager.postMail(null,"GST_EVN_02",new Exception("Impossibile recuperare la lista degli utenti: " + e));
			}
			
			for(IdUtente utente : utenti) {
				String prefix =  "[" + utente.getIdAmministrazione() + "." + utente.getIdSistema() + "] ";
				log.debug(prefix + "Richiedo la Lista delle Tipologie di Evento");
				GestioneEventi client = igrueUtils.getGestioneEventiClient();
				try {
					ListaTipiOut listaTipiOut = null;
					try { 
						ListaTipiIn in = new ListaTipiIn();
						Credenziali credenziali = UtentiUtilities.getCredenziali(utente);
						in.setCredenziali(credenziali);
						listaTipiOut = client.listaTipi(in);
					} catch(Exception e){
						MailManager.postMail(utente,  "GST_EVN_05",e);
						throw e;
					}
	
					if(listaTipiOut.getEsitoOperazione().getCodiceEsito()!=0){
						MailManager.postMail(utente,  "GST_EVN_07",listaTipiOut.getEsitoOperazione());
					}
					
					
					
					if(listaTipiOut.getEsitoOperazione().getCodiceEsito() == 0) {
						log.info(prefix + "Ricevuta lista dei tipi di evento con esito ["
								+ listaTipiOut.getEsitoOperazione().getCodiceEsito()
								+ "] "
								+ listaTipiOut.getEsitoOperazione()
										.getDescrizioneEsito() + " - "
								+ listaTipiOut.getEsitoOperazione().getDettaglio());
						CheckManager.updateCheck(CheckManager.checkEventi, 0, log);
					}
					else {
						log.error(prefix + "Ricevuta lista dei tipi di evento con esito ["
								+ listaTipiOut.getEsitoOperazione().getCodiceEsito()
								+ "] "
								+ listaTipiOut.getEsitoOperazione()
										.getDescrizioneEsito() + " - "
								+ listaTipiOut.getEsitoOperazione().getDettaglio());
					
						CheckManager.updateCheck(CheckManager.checkEventi, 1, log);
						continue;
					}
					EventType[] tipi = listaTipiOut.getTipiEvento().getTipiEvento().toArray(new EventType[1]);
					for (int i = 0; i < tipi.length; i++) {
						EventType evento = tipi[i];
						if (IgrueUtils.save(evento)) {
							log.debug(prefix + "Tipo Evento [" + evento.getCode() + "]: " + evento.getDescription() + " salvato.");
						} else {
							MailManager.postMail(utente, "GST_EVN_01",new Exception("Impossibile salvare il tipo evento [" + evento.getCode() + "]: " + evento.getDescription()));
							//log.debug(prefix + "Tipo Evento [" + evento.getCode() + "]: " + evento.getDescription() + " NON salvato.");
						}
					}
				}
	
				catch (Exception e) {
					log.error(prefix + "Errore nella gestione degli Eventi: " + e,e);
					CheckManager.updateCheck(CheckManager.checkEventi, 2, log);
				}
			
				log.debug(prefix + "Richiedo la lista degli eventi...");
				try {
					CancellaIn cancellaIn = new CancellaIn();
					ListaOut listaOut = null;
					List<Long> ids = new ArrayList<Long>();
					CancellaOut cancellaOut = null;
					
					try {
						ListaIn in = new ListaIn();
						in.setCredenziali(UtentiUtilities.getCredenziali(utente));
						listaOut = client.lista(in);
					}
					catch(Exception e){
						MailManager.postMail(utente, "GST_EVN_05",e);
						throw e;
					}
	
					EventWS[] eventi = listaOut.getEventi().getEventi().toArray(new EventWS[1]);
					CheckManager.updateCheck(CheckManager.checkEventi, 0, log);
					log.info(prefix + "Ricevuta lista di ("+ eventi.length +") eventi con esito [" + listaOut.getEsitoOperazione().getCodiceEsito() + "] " + listaOut.getEsitoOperazione().getDescrizioneEsito() + " - " + listaOut.getEsitoOperazione().getDettaglio());
					if(listaOut.getEsitoOperazione().getCodiceEsito()!=0){
						MailManager.postMail(utente, "GST_EVN_07",listaOut.getEsitoOperazione());
					}
					int salvati=0,nonsalvati=0;
					for(int i=0; i<eventi.length;i++){
						EventWS evento = eventi[i];
						if(IgrueUtils.save(evento, utente)) {
							//Gestione mail interna
							ids.add(new Long(evento.getId()));
							salvati++;
							//log.debug(prefix + "Evento "+evento.getId()+" salvato.");
						} else {
							nonsalvati++;
							log.debug(prefix + "Evento "+evento.getId()+" NON salvato.");
							MailManager.postMail(utente, "GST_EVN_01", new Exception("Impossibile salvare l'evento "+evento.getId()+"."));
						}
					}
					
					log.info(prefix + "Evento salvati:" + salvati);
					log.info(prefix + "Evento NON salvati:" + nonsalvati);
					
					if( !Configuration.CANCELLA_ESITI_SALVATI ) log.info("Cancellazione eventi dal Sistema Centrale IGRUE disabilitata.");
					
					if(ids.size() > 0 && Configuration.CANCELLA_ESITI_SALVATI){
						log.info(prefix + "Invio richiesta di cancellazione per gli eventi salvati");
						ArrayOfXsdLong lista = new ArrayOfXsdLong();
						lista.getEventIds().addAll(ids);
						cancellaIn.setEventIds(lista);
						cancellaIn.setCredenziali(UtentiUtilities.getCredenziali(utente));
						try {
							cancellaOut = igrueUtils.getGestioneEventiClient().cancella(cancellaIn);
						}
						catch(Exception e){
							MailManager.postMail(utente, "GST_EVN_06",e);
							throw e;
						}
						
						log.info(prefix + "Cancellazione eseguita con esito [" + cancellaOut.getEsitoOperazione().getCodiceEsito() + "] " + cancellaOut.getEsitoOperazione().getDescrizioneEsito() + " - " + cancellaOut.getEsitoOperazione().getDettaglio());
						if(cancellaOut.getEsitoOperazione().getCodiceEsito()!=0){
							MailManager.postMail(utente, "GST_EVN_04",cancellaOut.getEsitoOperazione());
						}
						
						if(cancellaOut.getDeleted() != null){
							Boolean[] cancelled = cancellaOut.getDeleted().getDeleted().toArray(new Boolean[1]);
							for(int i=0; i<ids.size();i++){
								log.info(ids.get(i) + ": " + cancelled[i]);
							}
						}
					}
				} catch (Exception e) {
					log.error(prefix + "Errore nella gestione degli Eventi: " + e,e);
					CheckManager.updateCheck(CheckManager.checkEventi, 2, log);
				}
			}
				
				
			// Controllo se ci sono trasmissioni che non hanno ricevuto eventi:
			log.debug("Ricerco trasmissioni non riscontrate");
			DBManager dbManager = DBManager.getInstance();
			ResultSet rs = null;
			PreparedStatement pstm = null;
			Connection con = null;

			try{								
				con = dbManager.getConnection();
				pstm = con.prepareStatement("select * from (select * from tickets t , trasmissioni r WHERE t.file = r.file AND t.id_amministrazione = r.id_amministrazione AND t.id_sistema = r.id_sistema AND NOT EXISTS ( select parameterid from eventi where parameterid = CAST(t.idticket AS VARCHAR))) r WHERE r.dataultimoinvio<? AND r.notificato='false'");
				// cerco le tabelle per cui e' c'e' un evento di aggiornamento posteriore alla data dell'ultimo download fatto per quella tabella
				pstm.setTimestamp(1, new Timestamp(new Date().getTime() - Configuration.ATTESA_CONFERMA * 3600 * 1000));
				rs = pstm.executeQuery();
				while(rs.next()){
					int file = rs.getInt("file");
					String idAmministrazione = rs.getString("id_amministrazione");
					int idSistema = rs.getInt("id_sistema");
					
					IdUtente utente = new IdUtente();
					utente.setIdAmministrazione(idAmministrazione);
					utente.setIdSistema(idSistema);
					
					log.warn("Non sono stati ricevuti eventi per la trasmissione " + file + " dopo " + Configuration.ATTESA_CONFERMA + " ore dall'ultima spedizione.");
					MailManager.postMail(utente, "INV_FIL_04", new Exception("Non sono stati ricevuti eventi per la trasmissione " + file + " dopo " + Configuration.ATTESA_CONFERMA + " ore dall'ultima spedizione."));

					IdTrasmissione id = new IdTrasmissione();
					id.setFile(file);
					id.setUtente(utente);
					
					Trasmissione.notifica(id);
				}
			}
			catch(Exception e){
				log.error("Riscontrato problema di connessione a database durante la ricerca di trasmissioni non riscontrate: " + e,e);
				MailManager.postMail(null, "GST_EVN_01",e);
			}
			finally{
				try{ if(rs!=null) rs.close(); } catch(SQLException e){}
				try{ if(pstm!=null) pstm.close(); } catch(SQLException e){}
				DBManager.getInstance().releaseConnection(con);
			}
			
		
			try {
				Thread.sleep(Configuration.EVENTI_DELAY * 1000);
			} catch (InterruptedException e) {}
		}

		isAlive = false;
	}
	
	public void killMe(){
		 this.stop = true;
	}

	public boolean check() {
		return this.isAlive;
	}
}





