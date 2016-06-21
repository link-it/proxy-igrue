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

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogManager {

	private static Logger loggerASConsole = null;
	private static Logger loggerRaccoltaEventi = null;
	private static Logger loggerSpedizioneDati = null;
	private static Logger loggerRaccoltaEsiti = null;
	private static Logger loggerRaccoltaTabelleContesto = null;
	private static Logger loggerDummyServer = null;
	/**
	 * Il Metodo si occupa di inizializzare il sistema di Logger 
	 *
	 */
	public static boolean initialize(String properties){
		try{
			PropertyConfigurator.configure(LogManager.class.getResource(properties));
			LogManager.loggerRaccoltaEventi = Logger.getLogger("igrue.raccoltaEventi");
			LogManager.loggerRaccoltaEventi = Logger.getLogger("igrue.raccoltaEventi");
			LogManager.loggerSpedizioneDati = Logger.getLogger("igrue.spedizioneDati");
			LogManager.loggerRaccoltaEsiti = Logger.getLogger("igrue.raccoltaEsiti");
			LogManager.loggerDummyServer = Logger.getLogger("igrue.dummyServer");
			LogManager.loggerRaccoltaTabelleContesto = Logger.getLogger("igrue.raccoltaTabelleContesto");
			LogManager.loggerASConsole = Logger.getLogger("igrue");
			LogManager.loggerASConsole.info("Sistema di logging correttamente inizializzato.");
					
			return true;
		}catch(Exception e){
			LogManager.loggerASConsole.error("Riscontrato errore durante l'inizializzazione del sistema di logging di IGRUE: " + e.getMessage());
			return false;
		}
	}

	
	
	public static Logger getLoggerASConsole() {
		return LogManager.loggerASConsole;
	}
	
	public static Logger getLoggerRaccoltaEventi() {
		return LogManager.loggerRaccoltaEventi;
	}
	
	public static Logger getLoggerSpedizioneDati() {
		return LogManager.loggerSpedizioneDati;
	}
	
	public static Logger getLoggerRaccoltaEsiti() {
		return LogManager.loggerRaccoltaEsiti;
	}
	
	public static Logger getLoggerRaccoltaTabelleContesto() {
		return LogManager.loggerRaccoltaTabelleContesto;
	}
	
	public static Logger getLoggerDummyServer() {
		return LogManager.loggerDummyServer;
	}
}
