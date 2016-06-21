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


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.Configuration;
import org.govmix.proxy.igrue.Constants;
import org.govmix.proxy.igrue.managers.LogManager;
import org.govmix.proxy.igrue.managers.MailManager;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.govmix.proxy.igrue.web.ejb.Trasmissione;
import org.govmix.proxy.igrue.web.ejb.dao.ITrasmissioneServiceSearch;
import org.govmix.proxy.igrue.web.ejb.utils.UtentiUtilities;
import org.govmix.proxy.igrue.web.service.ServiceManagerFactory;
import org.openspcoop2.generic_project.expression.IPaginatedExpression;

public class SpedizioneDati implements Runnable{
	/** Variabile di stop del thread */
	private boolean stop = false, isAlive = true;
	private static Logger log;

	private ExecutorService executorService;
	private static Vector<Spedizione> poolData; 
	private SpedizioneDatiThread[] treads;

	public SpedizioneDati(){
		log = LogManager.getLoggerSpedizioneDati();
		poolData = new Vector<Spedizione>();
		executorService = Executors.newFixedThreadPool(Configuration.SPEDIZIONE_POOL_SIZE);
		treads = new SpedizioneDatiThread[Configuration.SPEDIZIONE_POOL_SIZE];
		
		for(int i=0; i<Configuration.SPEDIZIONE_POOL_SIZE; i++){
			treads[i] = new SpedizioneDatiThread(i);
		}
		
	}

	public void run() {
		log.info("[WorkerManager] Modulo Spedizione Dati avviato con " + Configuration.SPEDIZIONE_POOL_SIZE + " worker threads");
		while(stop==false){

			List<IdUtente> utenti = new ArrayList<IdUtente>();
			try{
				log.debug("[WorkerManager] Recupero lista utenti:");
				utenti = UtentiUtilities.getLstUtenti(log);
				for(IdUtente utente : utenti) {
					log.debug("[WorkerManager]\t" + utente.getIdAmministrazione() + utente.getIdSistema());
				}
			} catch(Exception e) {
				log.error("[WorkerManager] Impossibile recuperare la lista degli utenti.", e);
				LogManager.getLoggerASConsole().error("Impossibile recuperare la lista degli utenti." ,e);
				MailManager.postMail(null,"INV_FIL_02",new Exception("Impossibile recuperare la lista degli utenti: " + e));
			}


			for(IdUtente utente : utenti) {
				log.debug("[WorkerManager] Ricerca file di attualizzazione da spedire per l'utente " + utente.getIdAmministrazione() + utente.getIdSistema());
				updatePoolData(utente);
			}
			
			for(int i=0; i<Configuration.SPEDIZIONE_POOL_SIZE; i++){
				executorService.execute(treads[i]);
			}
			
			while(poolData.size()>0 || isWorkerAlive()) { 
				log.debug("[WorkerManager] Trasmissioni in gestione. Prolungo attesa per 5 secondi");
				try { 
					Thread.sleep(5000); 
				} catch (Throwable e) {
					log.error(e,e);
				}
			}

			try { 
				log.debug("[WorkerManager] Gestione file terminata. Attesa per " + Configuration.SPEDIZIONE_DELAY + " secondi");
				for(int i=0 ; i < Configuration.SPEDIZIONE_DELAY ; i++){
					Thread.sleep(1000); 
				}
			} catch (Throwable e) {
				log.error(e,e);
			}
		}
		isAlive = false;
	}
	
	private boolean isWorkerAlive() {
		for(int i=0; i<Configuration.SPEDIZIONE_POOL_SIZE; i++){
			if(treads[i].check()) return true;
		}
		return false;
	}

	private void updatePoolData(IdUtente utente){
		String outDirName = UtentiUtilities.getOutboxDir(utente);
		log.debug("[WorkerManager] Verifico la presenza di file nella cartella di out: ["+outDirName+"]");
		File dir = new File(outDirName);
		if(!dir.exists()){
			log.error("[WorkerManager] Errore durante la ricerca dei file da trasmettere: " + outDirName + " non esiste");
			return;
		}
		File[] files = dir.listFiles();
		
		log.debug("[WorkerManager] Trovati [" + files.length +  "] file da gestire");
		
		for(File file : files){
			if(!file.getName().endsWith(".zip")) {
				log.warn("[WorkerManager] Il nome del file non rispetta le specifiche " + outDirName + file.getName());
				continue; 
			}
			if(file.getName().startsWith("WEB")) {
				log.warn("[WorkerManager] File in gestione dalla Web Console " + outDirName + file.getName());
				continue; 
			}
			String fileString = file.getName().substring(0, file.getName().length() -4 );
			log.debug("[WorkerManager] Aggiunto in lista il file [" + fileString + "].");
			poolData.add(new Spedizione(fileString, utente));
		}

		// prendo le trasmissioni per cui e' stato creato il contesto, ma ancora non iviato il file
		try{
			log.debug("[WorkerManager] Cerco file presi in gestione ma con trasmissione incompleta");
			
			ITrasmissioneServiceSearch trasmissioneSearch = ServiceManagerFactory.getServiceManager().getTrasmissioneServiceSearch();
			IPaginatedExpression exp = trasmissioneSearch.newPaginatedExpression();
			exp.equals(Trasmissione.model().ID_AMMINISTRAZIONE, utente.getIdAmministrazione())
			.and().equals(Trasmissione.model().ID_SISTEMA, utente.getIdSistema())
			.and().notEquals(Trasmissione.model().ESITOULTIMOINVIO, Constants.EsitoInvioSuccesso)
			.and().notEquals(Trasmissione.model().ESITOULTIMOINVIO, Constants.FileGiaInviato);
			java.util.List<Trasmissione> lst = trasmissioneSearch.findAll(exp);

			for(Trasmissione trasmissione : lst) {
				String file = String.valueOf(trasmissione.getFile());
				log.debug("Aggiunto in lista il file [" + file + "].");
				IdUtente u = new IdUtente();
				u.setIdAmministrazione(trasmissione.getIdAmministrazione());
				u.setIdSistema(trasmissione.getIdSistema());
				poolData.add(new Spedizione(file, u));
			}
		}
		catch (Exception e) {}
	}

	public void killMe(){

		while(true){
			boolean terminati = true;
			for(int i =0; i<treads.length;i++){
				if(treads[i].check()) {
					log.info("[WorkerManager] Worker_" + i + " in attesa di terminazione");
					terminati = false;
				}
			}
			if(!terminati){
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					log.error("Errore durante la sleep del thread" , e);
				}
			} else{
				this.stop = true;
				return;
			}
		}

	}

	public boolean check() {
		return this.isAlive;
	}

	public static Spedizione getSpedizione(){
		synchronized(poolData){
			Spedizione file = null;
			for(int i=0;i<poolData.size();i++){
				if(!poolData.get(i).isAssegnato()){
					file = poolData.get(i);
					poolData.get(i).assegnato();
					log.debug("[WorkerManager] File [" + file.getFile() + "] concesso in gestione. " + poolData.size() + " file ancora da gestire.");
					return file;
				}
			}
			return null;
		}
	}

	public static void dropFile(Spedizione i){
		synchronized(poolData){ 
			for(int x=0;x<poolData.size();x++){
				if(poolData.get(x).equals(i)){
					poolData.remove(x);
				}
			}
		} 
	}

	public long addSpedizione(Spedizione spedizione) {
		poolData.add(spedizione);
		while(poolData.contains(spedizione)) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {}
		}
		return spedizione.getIdentificativo();
	}

}





