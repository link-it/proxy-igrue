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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.Configuration;

public class CheckManager {
	public static File checkfile = null;
	
	public static final String checkProxy = "igrue.check.proxy";
	
	public static final String checkEsiti = "igrue.check.esiti";
	public static final String checkEventi = "igrue.check.eventi";
	public static final String checkTicket = "igrue.check.ticket";
	public static final String checkTabelle = "igrue.check.tabelle";
	public static final String checkSpedizione = "igrue.check.spedizione";
	public static final String checkCodice = "igrue.check.codice";
	
	
	public CheckManager() throws Exception {
		if (Configuration.CHECK_FILE == null) throw new Exception ("Check file non configurato.");
		CheckManager.checkfile = new File(Configuration.CHECK_FILE);
		if (!CheckManager.checkfile.exists()) {
			CheckManager.checkfile.createNewFile();
			FileOutputStream fos = new FileOutputStream(CheckManager.checkfile);
			Properties properties = new Properties();
			properties.setProperty(checkEsiti, "0");
			properties.setProperty(checkEventi, "0");
			properties.setProperty(checkTicket, "0");
			properties.setProperty(checkTabelle, "0");
			properties.setProperty(checkSpedizione, "0");
			properties.setProperty(checkCodice, "0");
			properties.store(fos, "Check dei servizi del Sistema Centrale Igrue");
			fos.close();
		}
	}
	
	public static void updateCheck(String check, int status, Logger log) {
		if (CheckManager.checkfile == null) { return; }
		if (log!=null) log.debug("Aggiorno lo status del check " + check + "=" + status);
		try {
			FileInputStream fis = new FileInputStream(CheckManager.checkfile);
			Properties properties = new Properties();
			properties.load(fis);
			fis.close();
			
			properties.setProperty(check, status + "");
			properties.setProperty(check+".time", new Date().getTime()+"");
			
			FileOutputStream fos = new FileOutputStream(CheckManager.checkfile);
			properties.store(fos, "Check dei servizi del Sistema Centrale Igrue");
			fos.close();
		} catch (IOException ioe) {
			if( log!=null) log.error("Impossibile aggiornare lo status del check",ioe);
		}
	}
}
