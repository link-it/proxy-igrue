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


import it.eng.csp.webservices.EsitoElaborazione;
import it.mef.csp.webservices.dto.Ticket;
import it.mef.csp.webservices.messages.esitoelaborazione.Content;
import it.mef.csp.webservices.messages.esitoelaborazione.EsitoAnagraficaRiferimentoIn;
import it.mef.csp.webservices.messages.esitoelaborazione.EsitoAnagraficaRiferimentoOut;
import it.mef.csp.webservices.messages.esitoelaborazione.LogErroriIn;
import it.mef.csp.webservices.messages.esitoelaborazione.LogErroriOut;
import it.mef.csp.webservices.messages.esitoelaborazione.StatisticheElaborazioniInAsAttachments;
import it.mef.csp.webservices.messages.esitoelaborazione.StatisticheElaborazioniOutAsAttachment;
import it.mef.csp.webservices.messages.esitoelaborazione.StatisticheScartiInAsAttachment;
import it.mef.csp.webservices.messages.esitoelaborazione.StatisticheScartiOutAsAttachment;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.Configuration;
import org.govmix.proxy.igrue.managers.CheckManager;
import org.govmix.proxy.igrue.managers.LogManager;
import org.govmix.proxy.igrue.managers.MailManager;
import org.govmix.proxy.igrue.managers.TicketManager;
import org.govmix.proxy.igrue.moduli.RaccoltaEsiti.Esito;
import org.govmix.proxy.igrue.moduli.utils.FileUtils;
import org.govmix.proxy.igrue.web.ejb.IdEsito;
import org.govmix.proxy.igrue.web.ejb.IdTrasmissione;
import org.govmix.proxy.igrue.web.ejb.dao.IEsitoService;
import org.govmix.proxy.igrue.web.ejb.utils.UtentiUtilities;
import org.govmix.proxy.igrue.web.service.ServiceManagerFactory;
import org.govmix.proxy.igrue.ws.IgrueUtils;

public class RaccoltaEsitiThread implements Runnable {
	private boolean stop = false, isAlive = true;
	private int id;
	public RaccoltaEsitiThread(int i){
		this.id = i;
	}

	public void run() {
		Logger log = LogManager.getLoggerRaccoltaEsiti();
		IgrueUtils igrueUtils = null;

		try{
			igrueUtils = new IgrueUtils();
		} catch(Exception e) {
			log.error("Errore nell'avvio del sistema di raccolta esiti. Impossibile instanziare i fruitori dei servizi MEF: " + e);
			LogManager.getLoggerASConsole().error("Errore nell'avvio del sistema di raccolta esiti. Impossibile instanziare i fruitori dei servizi MEF: " + e,e);
			MailManager.postMail(null, "SCR_RIS_02",new Exception("Impossibile instanziare i fruitori dei servizi MEF"));
			stop = true;
			throw new RuntimeException("Impossibile instanziare i fruitori dei servizi MEF", e);
		}
		Esito esito;
		while(!stop){

			if((esito = RaccoltaEsiti.getEsito()) == null){
				try {
					log.debug("[Worker_"+id+"]  Nessul esito da gestire.");
					for(int i=0; i<Configuration.SPEDIZIONE_DELAY; i++)
						Thread.sleep(1000);
					continue;
				} catch (InterruptedException e) {
					log.error("[Worker_"+id+"]  Errore durante la sleep del thread" , e);
				}
			}
			int file = esito.file;
			IdEsito idEsito = null;
			try{
				//La trasmissione esiste. 

				log.debug("[Worker_" + id +"]["+file+"] Cerco esiti. Aggiorno la data dell'ultimo tentativo.");

				IEsitoService esitoService = ServiceManagerFactory.getServiceManager().getEsitoService();
				idEsito = new IdEsito();
				IdTrasmissione idTrasmissione = new IdTrasmissione();

				idTrasmissione.setFile(file);
				idTrasmissione.setUtente(esito.utente);
				idEsito.setTrasmissione(idTrasmissione);
				org.govmix.proxy.igrue.web.ejb.Esito esitoDaDb = esitoService.get(idEsito);

				esitoDaDb.setDataultimocontrollo(new java.util.Date());
				esitoService.update(idEsito, esitoDaDb);
			}
			catch(Exception e){
				log.error("[Worker_" + id +"]["+file+"] Errore nella ricerca degli esiti: " + e,e);
				MailManager.postMail(esito.utente, "SCR_RIS_04", e);
				continue;
			}
			//Recupero il ticket
			Ticket t;
			try {
				IdTrasmissione idTrasmissione = new IdTrasmissione();

				idTrasmissione.setFile(file);
				idTrasmissione.setUtente(esito.utente);

				t = new TicketManager(idTrasmissione, id, log).getTicket();
				CheckManager.updateCheck(CheckManager.checkTicket, 0, log);
			} catch (Exception e) {
				log.error("[Worker_" + id +"]["+file+"] Errore durante il recupero del ticket",e);
				CheckManager.updateCheck(CheckManager.checkTicket, 2, log);
				RaccoltaEsiti.dropEsito(esito);
				continue;
			}

			//Controllo se StatisticheElaborazioni e' gia' stato ricevuto.
			if(esito.statisticheElaborazioni !=0){
				StatisticheElaborazioniInAsAttachments in = new StatisticheElaborazioniInAsAttachments();
				in.setTicket(t);

				byte[] attachment = null; 
				StatisticheElaborazioniOutAsAttachment out = null;
				try {
					in.setCredenziali(UtentiUtilities.getCredenziali(esito.utente));
					EsitoElaborazione client = igrueUtils.getEsitoElaborazioneClient();
					Holder<Content> content = new Holder<Content>();
					content.value = new Content();
					out = client.statisticheElaborazioniAsAttachment(in, content);
					CheckManager.updateCheck(CheckManager.checkEsiti, 0, log);
					log.info("[Worker_" + id +"]["+file+"] Richieste StatisticheElaborazioni con esito " + out.getEsitoOperazione().getCodiceEsito() + ": " + out.getEsitoOperazione().getDescrizioneEsito() + " - " + out.getEsitoOperazione().getDettaglio());
					if(out.getEsitoOperazione().getCodiceEsito() == 0) {
						if(content.value!=null)
							attachment = igrueUtils.getAttachment((BindingProvider) client, content.value.getHref());
						if(!FileUtils.saveEsito(idEsito, org.govmix.proxy.igrue.web.ejb.Esito.model().STATISTICHEELABORAZIONI,attachment,out.getEsitoOperazione(),log))
							MailManager.postMail(esito.utente, "SCR_RIS_02",new Exception("Impossibile salvare l'esito StatisticheElaborazioni")); 
					} else {
						MailManager.postMail(esito.utente, "SCR_RIS_05",out.getEsitoOperazione());
						throw new Exception(out.getEsitoOperazione().getDescrizioneEsito() + ": " + out.getEsitoOperazione().getDettaglio());
					}
				} catch(WebServiceException e){ 
					log.error("[Worker_" + id +"]["+file+"] Errore durante la richiesta delle StatisticheElaborazioni",e);
					CheckManager.updateCheck(CheckManager.checkEsiti, 2, log);
					MailManager.postMail(esito.utente, "SCR_RIS_01",e);  
				} catch(Exception e){ 
					log.error("[Worker_" + id +"]["+file+"] Errore durante la richiesta delle StatisticheElaborazioni",e);
					MailManager.postMail(esito.utente, "SCR_RIS_01",e);  
				}
			}

			//Controllo se StatisticheElaborazioni e' gia' stato ricevuto.
			if(esito.statisticheScarti !=0){
				byte[] attachment = null;
				StatisticheScartiOutAsAttachment out = null;
				StatisticheScartiInAsAttachment in = new StatisticheScartiInAsAttachment();
				in.setTicket(t);
				try{ 
					in.setCredenziali(UtentiUtilities.getCredenziali(esito.utente));
					EsitoElaborazione client = igrueUtils.getEsitoElaborazioneClient();
					Holder<Content> content = new Holder<Content>();
					content.value = new Content();
					out = client.statisticheScartiAsAttachment(in, content);
					CheckManager.updateCheck(CheckManager.checkEsiti, 0, log);
					log.info("[Worker_" + id +"]["+file+"] Richieste StatisticheElaborazioni con esito " + out.getEsitoOperazione().getCodiceEsito() + ": " + out.getEsitoOperazione().getDescrizioneEsito() + " - " + out.getEsitoOperazione().getDettaglio());
					if(out.getEsitoOperazione().getCodiceEsito() == 0) {
						if(content.value!=null)
							attachment = igrueUtils.getAttachment((BindingProvider) client, content.value.getHref());
						if(!FileUtils.saveEsito(idEsito,org.govmix.proxy.igrue.web.ejb.Esito.model().STATISTICHESCARTI,attachment,out.getEsitoOperazione(),log))
							MailManager.postMail(esito.utente, "SCR_RIS_02",new Exception("Impossibile salvare l'esito StatisticheScarti")); 
					} else {
						MailManager.postMail(esito.utente, "SCR_RIS_05",out.getEsitoOperazione());
						throw new Exception(out.getEsitoOperazione().getDescrizioneEsito() + ": " + out.getEsitoOperazione().getDettaglio());
					}
				} catch(WebServiceException e){ 
					log.error("[Worker_" + id +"]["+file+"] Errore durante la richiesta delle Statistiche Scarti",e);
					CheckManager.updateCheck(CheckManager.checkEsiti, 2, log);
					MailManager.postMail(esito.utente, "SCR_RIS_01",e);  
				} catch(Exception e) { 
					log.error("[Worker_" + id +"]["+file+"] Errore durante la richiesta delle Statistiche Scarti",e);
					MailManager.postMail(esito.utente, "SCR_RIS_01",e);  
				}
			}

			//Controllo se esitoElaborazionePerAnagraficaDiRiferimento e' gia' stato ricevuto.
			if(esito.esitoElaborazionePerAnagraficaDiRiferimento !=0){
				byte[] attachment = null;
				EsitoAnagraficaRiferimentoOut out = null;
				EsitoAnagraficaRiferimentoIn in = new EsitoAnagraficaRiferimentoIn();
				in.setTicket(t);
				try{ 
					in.setCredenziali(UtentiUtilities.getCredenziali(esito.utente));
					EsitoElaborazione client = igrueUtils.getEsitoElaborazioneClient();
					Holder<Content> content = new Holder<Content>();
					content.value = new Content();
					out = client.esitoAnagraficaRiferimento(in, content);
					CheckManager.updateCheck(CheckManager.checkEsiti, 0, log);
					log.info("[Worker_" + id +"]["+file+"] Richieste StatisticheElaborazioni con esito " + out.getEsitoOperazione().getCodiceEsito() + ": " + out.getEsitoOperazione().getDescrizioneEsito() + " - " + out.getEsitoOperazione().getDettaglio());
					if(out.getEsitoOperazione().getCodiceEsito() == 0) {
						if(content.value!=null)
							attachment = igrueUtils.getAttachment((BindingProvider) client, content.value.getHref());
						if(!FileUtils.saveEsito(idEsito,org.govmix.proxy.igrue.web.ejb.Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTO,attachment, out.getEsitoOperazione(),log))
							MailManager.postMail(esito.utente, "SCR_RIS_02",new Exception("Impossibile salvare l'esito esitoElaborazionePerAnagraficaDiRiferimento")); 
					} else {
						MailManager.postMail(esito.utente, "SCR_RIS_05",out.getEsitoOperazione());
						throw new Exception(out.getEsitoOperazione().getDescrizioneEsito() + ": " + out.getEsitoOperazione().getDettaglio());
					}
				} catch(WebServiceException e){ 
					log.error("[Worker_" + id +"]["+file+"] Errore durante la richiesta degli Esiti Elaborazione per Anagrafica di Riferimento",e);
					CheckManager.updateCheck(CheckManager.checkEsiti, 2, log);
					MailManager.postMail(esito.utente, "SCR_RIS_01",e);  
				} catch(Exception e){ 
					log.error("[Worker_" + id +"]["+file+"] Errore durante la richiesta degli Esiti Elaborazione per Anagrafica di Riferimento",e);
					MailManager.postMail(esito.utente, "SCR_RIS_01",e);   
				}
			}

			//Controllo se StatisticheElaborazioni e' gia' stato ricevuto.
			if(esito.logDegliErrori !=0){
				byte[] attachment = null;
				LogErroriOut out = null;
				LogErroriIn in = new LogErroriIn();
				in.setTicket(t);
				try{
					in.setCredenziali(UtentiUtilities.getCredenziali(esito.utente));
					EsitoElaborazione client = igrueUtils.getEsitoElaborazioneClient();
					Holder<Content> content = new Holder<Content>();
					content.value = new Content();
					out = client.logErrori(in, content);
					CheckManager.updateCheck(CheckManager.checkEsiti, 0, log);
					log.info("[Worker_" + id +"]["+file+"] Richieste StatisticheElaborazioni con esito " + out.getEsitoOperazione().getCodiceEsito() + ": " + out.getEsitoOperazione().getDescrizioneEsito() + " - " + out.getEsitoOperazione().getDettaglio());
					if(out.getEsitoOperazione().getCodiceEsito() == 0) {
						if(content.value!=null)
							attachment = igrueUtils.getAttachment((BindingProvider) client, content.value.getHref());
						if(!FileUtils.saveEsito(idEsito,org.govmix.proxy.igrue.web.ejb.Esito.model().LOGDEGLIERRORI, attachment, out.getEsitoOperazione(), log))
							MailManager.postMail(esito.utente, "SCR_RIS_02",new Exception("Impossibile salvare l'esito logDegliErrori")); 
					} else {
						MailManager.postMail(esito.utente, "SCR_RIS_05",out.getEsitoOperazione());
						throw new Exception(out.getEsitoOperazione().getDescrizioneEsito() + ": " + out.getEsitoOperazione().getDettaglio());
					}
				} catch(WebServiceException e){ 
					log.error("[Worker_" + id +"]["+file+"] Errore durante la richiesta del log degli Errori",e);
					CheckManager.updateCheck(CheckManager.checkEsiti, 2, log);
					MailManager.postMail(esito.utente, "SCR_RIS_01",e);  
				} catch(Exception e) { 
					log.error("[Worker_" + id +"]["+file+"] Errore durante la richiesta del log degli Errori",e);
					MailManager.postMail(esito.utente, "SCR_RIS_01",e);  
				}
			}
			RaccoltaEsiti.dropEsito(esito);
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