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

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.Configuration;
import org.govmix.proxy.igrue.managers.LogManager;
import org.govmix.proxy.igrue.web.ejb.Evento;
import org.govmix.proxy.igrue.web.ejb.IdTrasmissione;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.govmix.proxy.igrue.web.ejb.Ticket;
import org.govmix.proxy.igrue.web.ejb.TicketId;
import org.govmix.proxy.igrue.web.ejb.dao.IEsitoServiceSearch;
import org.govmix.proxy.igrue.web.ejb.dao.IEventoServiceSearch;
import org.govmix.proxy.igrue.web.ejb.dao.ITicketServiceSearch;
import org.govmix.proxy.igrue.web.ejb.utils.UtentiUtilities;
import org.govmix.proxy.igrue.web.service.ServiceManagerFactory;
import org.openspcoop2.generic_project.exception.NotFoundException;
import org.openspcoop2.generic_project.expression.IExpression;
import org.openspcoop2.generic_project.expression.IPaginatedExpression;



public class RaccoltaEsiti implements Runnable {
	/** Variabile di stop del thread */
	private boolean stop = false;
	private boolean isAlive = true;


	private ExecutorService executorService;
	private static Vector<Esito> poolData = new Vector<Esito>();
	private RaccoltaEsitiThread[] treads;

	private static Logger log;

	public RaccoltaEsiti(){
		log = LogManager.getLoggerRaccoltaEsiti();

		executorService = Executors.newFixedThreadPool(Configuration.ESITI_POOL_SIZE);
		poolData = new Vector<Esito>();
		treads = new RaccoltaEsitiThread[Configuration.ESITI_POOL_SIZE];

		for(int i=0; i<Configuration.ESITI_POOL_SIZE; i++){
			treads[i] = new RaccoltaEsitiThread(i);
			executorService.execute(treads[i]);
		}

	}

	//@Override
	public void run() {

		while(stop==false){

			log.info("[WorkerManager] Verifico gli esiti delle trasmissioni...");

			try{
				List<IdUtente> utenti = UtentiUtilities.getLstUtenti(log);
				
				for(IdUtente u : utenti) {
					IEsitoServiceSearch esitiService = ServiceManagerFactory.getServiceManager().getEsitoServiceSearch();
					IEventoServiceSearch eventiService = ServiceManagerFactory.getServiceManager().getEventoServiceSearch();
					ITicketServiceSearch ricercaTicket = ServiceManagerFactory.getServiceManager().getTicketServiceSearch();
					IPaginatedExpression exp = esitiService.newPaginatedExpression();
					
					
					
					IPaginatedExpression exp1 = esitiService.newPaginatedExpression();
					exp1.notEquals(org.govmix.proxy.igrue.web.ejb.Esito.model().STATISTICHEELABORAZIONI, 0).or();
					exp1.notEquals(org.govmix.proxy.igrue.web.ejb.Esito.model().STATISTICHESCARTI, 0).or();
					exp1.notEquals(org.govmix.proxy.igrue.web.ejb.Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTO, 0).or();
					exp1.notEquals(org.govmix.proxy.igrue.web.ejb.Esito.model().LOGDEGLIERRORI, 0);
					exp.and().equals(org.govmix.proxy.igrue.web.ejb.Esito.model().ID_AMMINISTRAZIONE, u.getIdAmministrazione())
					.and().equals(org.govmix.proxy.igrue.web.ejb.Esito.model().ID_SISTEMA, u.getIdSistema())
					.or(exp1);
					
					
					List<org.govmix.proxy.igrue.web.ejb.Esito> lst = esitiService.findAll(exp);
	
					for(org.govmix.proxy.igrue.web.ejb.Esito e : lst) {
	
						TicketId tid = new TicketId();
						IdTrasmissione trasmissione = new IdTrasmissione();
						trasmissione.setFile(e.getFile());
	
						IdUtente utente = new IdUtente();
						utente.setIdAmministrazione(e.getIdAmministrazione());
						utente.setIdSistema(e.getIdSistema());
	
						trasmissione.setUtente(utente);
						tid.setTrasmissione(trasmissione);
						Ticket t = null;
						try{
							t = ricercaTicket.get(tid);
						} catch (NotFoundException nfe) {
						} 
	
						if(t == null) {
							continue;
						}
						
						
						// Controllo se la trasmissione e' stata scartata
						
						IExpression countEventoScarto = eventiService.newExpression();
						countEventoScarto.equals(Evento.model().EVENTTYPE_CODE, new Integer(2)).and()
						.equals(Evento.model().OWNER_IDAMMINISTRAZIONE, t.getIdAmministrazione()).and()
						.equals(Evento.model().OWNER_IDSISTEMA, t.getIdSistema()).and()
						.equals(Evento.model().PARAMETERID, t.getIdticket());
	
						Long count = eventiService.count(countEventoScarto).longValue();
						
						if(count>0){
							continue;
						}
						
						//Verifico se per la trasmissione in questione e' stato ricevuto l'evento elaborato dal MEF
						
						IExpression countEventiElaborazione = eventiService.newExpression();
						countEventiElaborazione.between(Evento.model().EVENTTYPE_CODE, new Integer(3), new Integer(6)).and()
						.equals(Evento.model().OWNER_IDAMMINISTRAZIONE, t.getIdAmministrazione()).and()
						.equals(Evento.model().OWNER_IDSISTEMA, t.getIdSistema()).and()
						.equals(Evento.model().PARAMETERID, t.getIdticket());
	
						count = eventiService.count(countEventiElaborazione).longValue();
						
						if(count>0){
							log.info("[WorkerManager] [" + e.getFile() + "] Aggiunto a verifica esiti.");
							poolData.add(new Esito(e.getFile(), e.getIdAmministrazione(), e.getIdSistema(), e.getStatisticheelaborazioni(),e.getStatistichescarti(),e.getEsitoelaborazioneperanagraficadiriferimento(),e.getLogdeglierrori()));
						} else {
							log.warn("[WorkerManager] [" + e.getFile() + "] Non e' stato ancora ricevuto l'evento di elaborazione per questa trasmissione.");
						}
					}
				}
			}
			catch (Exception sqle){
				log.error("[WorkerManager] Errore durante la gestione degli esiti.", sqle);
			}

			try {
				Thread.sleep(Configuration.ESITI_DELAY * 1000);
			} catch (Exception e) {}
			try {
				while(poolData.size()>0) try {Thread.sleep(5000);} catch (Exception ee) {}
			} catch (Exception e) {}
		}

		isAlive = false;
	}


	public void killMe(){

		for(int i =0; i<treads.length;i++){
			treads[i].killMe();
		}

		while(true){
			boolean terminati = true;
			for(int i =0; i<treads.length;i++){
				if(treads[i].check()) {
					log.info("[WorkerManager] Thread " + i + " in attesa di terminazione");
					terminati = false;
				}
			}
			if(!terminati){
				try { Thread.sleep(5000); } catch (InterruptedException e) {}
			}
			else{ 
				this.stop = true; 
				return;
			}
		}

	}

	public boolean check() {
		return this.isAlive;
	}

	public class Esito implements Comparable<Esito>{
		int file, statisticheElaborazioni,statisticheScarti,esitoElaborazionePerAnagraficaDiRiferimento,logDegliErrori;
		IdUtente utente;
		boolean assegnato;

		public Esito(int file, String idAmministrazione, int idSistema, int statisticheElaborazioni, int statisticheScarti, int esitoElaborazionePerAnagraficaDiRiferimento, int logDegliErrori){
			this.utente = new IdUtente();

			this.utente.setIdAmministrazione(idAmministrazione);
			this.utente.setIdSistema(idSistema);

			this.file = file;
			this.statisticheElaborazioni = statisticheElaborazioni;
			this.statisticheScarti = statisticheScarti;
			this.esitoElaborazionePerAnagraficaDiRiferimento = esitoElaborazionePerAnagraficaDiRiferimento;
			this.logDegliErrori = logDegliErrori;
			this.assegnato = false;
		}
		public int compareTo(Esito o) {
			if(this.file<o.file) return -1;
			else if(this.file>o.file) return +1;
			else return 0;
		}
		public boolean isAssegnato(){return assegnato;}
		public void assegnato(){assegnato = true;}
	}

	public static Esito getEsito(){
		synchronized(poolData){
			Integer file = null;
			for(int i=0;i<poolData.size();i++){
				if(!poolData.get(i).isAssegnato()){
					file = poolData.get(i).file;
					poolData.get(i).assegnato();
					log.debug("[WorkerManager] File [" + file + "] concesso in gestione ad un worker. " + poolData.size() + " file ancora da gestire.");
					return poolData.get(i);
				}
			}
			return null;
		}
	}

	public static void dropEsito(Esito i){
		synchronized(poolData){
			for(int x=0;x<poolData.size();x++){
				if(poolData.get(x).file == i.file) poolData.remove(x);
			}
		}
	}
}





