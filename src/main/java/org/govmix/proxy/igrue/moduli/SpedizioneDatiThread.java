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


import it.mef.csp.webservices.dto.Ticket;

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.managers.CheckManager;
import org.govmix.proxy.igrue.managers.LogManager;
import org.govmix.proxy.igrue.managers.MailManager;
import org.govmix.proxy.igrue.managers.Trasmissione;
import org.govmix.proxy.igrue.moduli.Spedizione;
import org.govmix.proxy.igrue.moduli.utils.FileUtils;



public class SpedizioneDatiThread implements Runnable{
	private boolean isAlive = true;
	private int id;
	public SpedizioneDatiThread(int i){
		this.id = i;
	}
	
	public void run() {
		Logger log = LogManager.getLoggerSpedizioneDati();
		log.debug("[Worker_"+id+"] Avviato...");
		isAlive = true;
		String file;
		Spedizione spedizione;
		while((spedizione = SpedizioneDati.getSpedizione()) != null){
			file = spedizione.getFile();
			log.info("[Worker_"+id+"] [" + spedizione.getUtente().getIdAmministrazione() + "." + spedizione.getUtente().getIdSistema() + "]["+file+"] Inizio gestione trasmissione");
			Trasmissione trasmissione;
			try {
				log.info("[Worker_"+id+"] [" + spedizione.getUtente().getIdAmministrazione() + "." + spedizione.getUtente().getIdSistema() + "]["+file+"] Creazione della trasmissione");
			    trasmissione = new Trasmissione(spedizione, id);
			} catch (Exception e) {
				log.error("[Worker_"+id+"] [" + spedizione.getUtente().getIdAmministrazione() + "." + spedizione.getUtente().getIdSistema() + "]["+file+"] Errore nella creazione della Trasmissione: " +e,e);
				SpedizioneDati.dropFile(spedizione);
				MailManager.postMail(spedizione.getUtente(), "INV_FIL_06",e);
				continue;
			}
		    	
	    	//Creo il contesto
			log.info("[Worker_"+id+"] [" + spedizione.getUtente().getIdAmministrazione() + "." + spedizione.getUtente().getIdSistema() + "]["+file+"] Creazione del contesto");
	    	try{FileUtils.makeContext(trasmissione, spedizione.utente);}
	    	catch(Exception e){
	    		log.error("[Worker_"+id+"] [" + spedizione.getUtente().getIdAmministrazione() + "." + spedizione.getUtente().getIdSistema() + "]["+file+"] Errore nella creazione del contesto: " + e,e);
	    		SpedizioneDati.dropFile(spedizione);
	    		MailManager.postMail(spedizione.getUtente(), "INV_FIL_03",e);
	    		continue;
			}
		    
	    	log.info("[Worker_"+id+"] [" + spedizione.getUtente().getIdAmministrazione() + "." + spedizione.getUtente().getIdSistema() + "]["+file+"] Richiesta del ticket");
	    	Ticket ticket = null;
	    	try {
				ticket = trasmissione.getTicket();
				CheckManager.updateCheck(CheckManager.checkTicket, 0, log);
			} catch (Exception e) {
				log.error("[Worker_"+id+"] [" + spedizione.getUtente().getIdAmministrazione() + "." + spedizione.getUtente().getIdSistema() + "]["+file+"] Errore nel recupero del ticket : " + e,e);
				SpedizioneDati.dropFile(spedizione);
				continue;
			}
	    	
	    	log.info("[Worker_"+id+"] [" + spedizione.getUtente().getIdAmministrazione() + "." + spedizione.getUtente().getIdSistema() + "]["+file+"] Spedizione al sistema centrale");
			try{
				if(trasmissione.inviaFile(ticket, spedizione.utente, id))
					CheckManager.updateCheck(CheckManager.checkSpedizione, 0, log);
				else {
					CheckManager.updateCheck(CheckManager.checkSpedizione, 2, log);
				}
			} catch(Exception e) {
				log.error("[Worker_"+id+"] [" + spedizione.getUtente().getIdAmministrazione() + "." + spedizione.getUtente().getIdSistema() + "]["+file+"] Errore nell'invio del file al sistema centrale Igrue",e);
				SpedizioneDati.dropFile(spedizione);
				CheckManager.updateCheck(CheckManager.checkSpedizione, 2, log);
				continue;
			}
			SpedizioneDati.dropFile(spedizione);
			
		}
		log.debug("[Worker_"+id+"] Nessun file da gestire. Worker terminato.");
		isAlive = false;
    }

	public boolean check() {
		return this.isAlive;
	}
	
}





