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
package org.govmix.proxy.igrue;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.managers.CheckManager;
import org.govmix.proxy.igrue.managers.DBManager;
import org.govmix.proxy.igrue.managers.LogManager;
import org.govmix.proxy.igrue.managers.MailManager;
import org.govmix.proxy.igrue.moduli.RaccoltaEsiti;
import org.govmix.proxy.igrue.moduli.RaccoltaEventi;
import org.govmix.proxy.igrue.moduli.RaccoltaTabelleContesto;
import org.govmix.proxy.igrue.moduli.Spedizione;
import org.govmix.proxy.igrue.moduli.SpedizioneDati;
import org.govmix.proxy.igrue.ws.IgrueUtils;

public class Startup implements ServletContextListener {
	
	private static SpedizioneDati spedizioneDati;
	private RaccoltaEsiti raccoltaEsiti;
	private RaccoltaEventi raccoltaEventi;
	private RaccoltaTabelleContesto raccoltaTabelleContesto;
	public static ServletContext servletContext;
	
	public void contextDestroyed(ServletContextEvent arg0) {
		if(spedizioneDati!=null) spedizioneDati.killMe();
		if(raccoltaEsiti!=null) raccoltaEsiti.killMe();
		if(raccoltaEventi!=null) raccoltaEventi.killMe();
		if(raccoltaTabelleContesto!=null) raccoltaTabelleContesto.killMe();
		
		CheckManager.updateCheck(CheckManager.checkProxy, 2, null);
		LogManager.getLoggerASConsole().info("Stop Moduli effettuato.");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		
		servletContext = arg0.getServletContext();
		
		LogManager.initialize("/properties/logger.log4j.properties");
		
		Logger log = LogManager.getLoggerASConsole();
		log.info("LogManager inizializzato");

		try{
			new Configuration().initialize("properties/igrue.properties",log);
		}
		catch(Exception e){
			log.error("Configurazione incompleta. ProxyIGRUE non avviato.",e);
			MailManager.postMail(null, "STR_SYS_01", e);
			CheckManager.updateCheck(CheckManager.checkProxy, 2, log);
			return; 
		}
		log.info("Configurazione caricata");
		
		log.warn("Workaroud \"Cannot create a secure XMLInputFactory\" ");
		System.setProperty("org.apache.cxf.stax.allowInsecureParser", Boolean.TRUE.toString());
		
		try{
			new IgrueUtils();
		} catch(Exception e){
			log.error("Impossibile avviare i WS Client",e);
			MailManager.postMail(null, "STR_SYS_01", e);
			CheckManager.updateCheck(CheckManager.checkProxy, 2, log);
			return; 
		}
		log.info("WS Client avviati.");
		
		
		try{
			new CheckManager();
			log.info("CheckManager inizializzato");
		} catch(Exception e){
			log.error("Check Manager inizializzato con errori.",e);
		}
		
		
		try {
			DBManager.initialize(Configuration.DBJNDI, null);
		} catch (Exception e) {
			log.error("Errore nell'inizializzazione del DBManager",e);
			MailManager.postMail(null, "STR_SYS_02",e);
			CheckManager.updateCheck(CheckManager.checkProxy, 2, log);
			return;
		}
		log.info("DBManager Inizializzato");
		
		CheckManager.updateCheck(CheckManager.checkProxy, 0, log);
		
		spedizioneDati = new SpedizioneDati();
		new Thread(spedizioneDati).start();
		log.info("Modulo SpedizioneDati avviato.");
		
		raccoltaEventi = new RaccoltaEventi();
		new Thread(raccoltaEventi).start();
		log.info("Modulo RaccoltaEventi avviato.");
		
		raccoltaEsiti = new RaccoltaEsiti();
		new Thread(raccoltaEsiti).start();
		log.info("Modulo RaccoltaEsiti avviato.");
		
		raccoltaTabelleContesto = new RaccoltaTabelleContesto();
		new Thread(raccoltaTabelleContesto).start();
		log.info("Modulo RaccoltaTabelleContesto avviato.");
	}
	
	public static long addTrasmissione(Spedizione spedizione) {
		return spedizioneDati.addSpedizione(spedizione);
	}
}
