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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openspcoop2.utils.TipiDatabase;

public class Configuration {
	public static String DETTAGLI_ENCODE = null;
	public static String ERRORI_ENCODE = null;
	public static String TABELLE_ENCODE = null;
	public static String DB_TYPE = null;
	
	public static String IGRUE,INBOX,EMAIL_SMTP,OUTBOX,DBJNDI,CONTESTO,EMAIL_RECEIVER,EMAIL_SENDER, ATTUAZIONE_ENCODE, CHECK_FILE;
	public static boolean CONTESTO_HISTORY = true, EMAIL_NOTIFICATION_EVT = true, EMAIL_NOTIFICATION_ERR = true,  EMAIL_NOTIFICATION_ESITI = true, CANCELLA_ESITI_SALVATI = false, CANCELLA_EVENTI_SALVATI = false, IS_SHOW_SQL=false;
	public static String PDusername,PDpassword;
	public static URL PDurl_Trasmissione,PDurl_Tipologie,PDurl_RichiestaOperazione,PDurl_GestioneEventi,PDurl_EsitoElaborazione;
	public static int ESITI_DELAY = 30,EVENTI_DELAY = 30, TABELLE_DELAY =30, SPEDIZIONE_DELAY=30, SPEDIZIONE_POOL_SIZE=1, ESITI_POOL_SIZE=1, ATTESA_CONFERMA=1;
	public static String ADMINPWD;
	public void initialize(String config, Logger log) throws Exception{

		try {
			Properties readerConfig = new Properties();
			InputStream in = getClass().getClassLoader().getResourceAsStream(config);
			readerConfig.load(in);
			in.close();
			
			IGRUE = readerConfig.getProperty("igrue.folder");
			if(IGRUE == null){
				log.error("igrue.folder non specificato");
				throw new Exception("igrue.folder non specificato");
			} else {
				IGRUE = IGRUE.trim();
				if(!IGRUE.endsWith("/")) IGRUE = IGRUE + "/";
			}
			
			INBOX = readerConfig.getProperty("igrue.folder.inbox");
			if(INBOX == null){
				log.error("igrue.folder.inbox non specificato");
				throw new Exception("igrue.folder.inbox non specificato");
			} else {
				INBOX = INBOX.trim();
				if(!INBOX.endsWith("/")) INBOX = INBOX + "/";
			}
			
			OUTBOX = readerConfig.getProperty("igrue.folder.outbox");
			if(OUTBOX == null){
				log.error("igrue.folder.outbox non specificato");
				throw new Exception("igrue.folder.outbox non specificato");
			} else {
				OUTBOX = OUTBOX.trim();
				if(!OUTBOX.endsWith("/")) OUTBOX = OUTBOX + "/";
			}
			
			CONTESTO = readerConfig.getProperty("igrue.folder.contesto");
			if(CONTESTO == null){
				log.error("igrue.folder.contesto non specificato");
				throw new Exception("igrue.folder.contesto non specificato");
			} else {
				CONTESTO = CONTESTO.trim();
				if(!CONTESTO.endsWith("/")) CONTESTO = CONTESTO + "/";
			}
			
			ADMINPWD = readerConfig.getProperty("igrue.admin.pwd");
			if(ADMINPWD == null){
				log.error("igrue.admin.pwd non specificato");
				throw new Exception("igrue.admin.pwd non specificato");
			} else {
				ADMINPWD = ADMINPWD.trim();
			}
			
			String CONTESTO_HISTORY_VALUE = readerConfig.getProperty("igrue.contesto.history");
			if(CONTESTO_HISTORY_VALUE == null){
				log.warn("igrue.contesto.history non specificato, uso default true");
				CONTESTO_HISTORY = true;
			} else {
				CONTESTO_HISTORY =  Boolean.parseBoolean(CONTESTO_HISTORY_VALUE.trim());
			}
			
			String EMAIL_NOTIFICATION_EVT_VALUE = readerConfig.getProperty("igrue.email.notification.event");
			if(EMAIL_NOTIFICATION_EVT_VALUE == null){
				log.warn("igrue.email.notification.event non specificato, uso default true");
				EMAIL_NOTIFICATION_EVT = true;
			} else {
				EMAIL_NOTIFICATION_EVT =  Boolean.parseBoolean(EMAIL_NOTIFICATION_EVT_VALUE.trim());
			}
			
			String EMAIL_NOTIFICATION_ESITI_VALUE = readerConfig.getProperty("igrue.email.notification.esiti");
			if(EMAIL_NOTIFICATION_ESITI_VALUE == null){
				log.warn("igrue.email.notification.esiti non specificato, uso default true");
				EMAIL_NOTIFICATION_ESITI = true;
			} else {
				EMAIL_NOTIFICATION_ESITI =  Boolean.parseBoolean(EMAIL_NOTIFICATION_ESITI_VALUE.trim());
			}
			
			String EMAIL_NOTIFICATION_ERR_VALUE = readerConfig.getProperty("igrue.email.notification.error");
			if(EMAIL_NOTIFICATION_ERR_VALUE == null){
				log.warn("igrue.email.notification.error non specificato, uso default true");
				EMAIL_NOTIFICATION_ERR = true;
			} else {
				EMAIL_NOTIFICATION_ERR =  Boolean.parseBoolean(EMAIL_NOTIFICATION_ERR_VALUE.trim());
			}
			
			String CANCELLA_ESITI_SALVATI_VALUE = readerConfig.getProperty("igrue.esiti.cancella");
			if(CANCELLA_ESITI_SALVATI_VALUE == null){
				log.warn("igrue.esiti.cancella non specificato, uso default false");
				CANCELLA_ESITI_SALVATI = false;
			} else {
				CANCELLA_ESITI_SALVATI =  Boolean.parseBoolean(CANCELLA_ESITI_SALVATI_VALUE.trim());
			}
			
			String IS_SHOW_SQL_VALUE = readerConfig.getProperty("igrue.db.showsql");
			if(IS_SHOW_SQL_VALUE == null){
				log.warn("igrue.jdbc.showsql non specificato, uso default false");
				IS_SHOW_SQL = false;
			} else {
				IS_SHOW_SQL =  Boolean.parseBoolean(IS_SHOW_SQL_VALUE.trim());
			}
			
			String CANCELLA_EVENTI_SALVATI_VALUE = readerConfig.getProperty("igrue.eventi.cancella");
			if(CANCELLA_EVENTI_SALVATI_VALUE == null){
				log.warn("igrue.eventi.cancella non specificato, uso default false");
				CANCELLA_EVENTI_SALVATI = false;
			} else {
				CANCELLA_EVENTI_SALVATI =  Boolean.parseBoolean(CANCELLA_EVENTI_SALVATI_VALUE.trim());
			}
			
			DBJNDI = readerConfig.getProperty("igrue.db.jndiName");
			if(DBJNDI == null){
				log.error("igrue.db.jndiName non specificato");
				throw new Exception("igrue.db.jndiName non specificato");
			} else {
				DBJNDI = DBJNDI.trim();
			}
			
			String PDurl_Trasmissione_string = readerConfig.getProperty("igrue.pd.url.trasmissione");
			if(PDurl_Trasmissione_string == null){
				log.error("igrue.pd.url.trasmissione non specificato");
				throw new Exception("igrue.pd.url.trasmissione non specificato");
			} else {
				try {
					PDurl_Trasmissione_string = PDurl_Trasmissione_string.trim();
					PDurl_Trasmissione = new URL(PDurl_Trasmissione_string);
				} catch (MalformedURLException e) {
					throw new Exception("igrue.pd.url.trasmissione specificato non e' una URL valida");
				}
			}
			
			String PDurl_Tipologie_string = readerConfig.getProperty("igrue.pd.url.tipologie");
			if(PDurl_Tipologie_string == null){
				log.error("igrue.pd.url.tipologie non specificato");
				throw new Exception("igrue.pd.url.tipologie non specificato");
			} else {
				try {
					PDurl_Tipologie_string = PDurl_Tipologie_string.trim();
					PDurl_Tipologie = new URL(PDurl_Tipologie_string);
				} catch (MalformedURLException e) {
					throw new Exception("igrue.pd.url.tipologie specificato non e' una URL valida");
				}
			}
			
			String PDurl_RichiestaOperazione_string = readerConfig.getProperty("igrue.pd.url.richiestaoperazione");
			if(PDurl_RichiestaOperazione_string == null){
				log.error("igrue.pd.url.richiestaoperazione non specificato");
				throw new Exception("igrue.pd.url.richiestaoperazione non specificato");
			} else {
				try {
					PDurl_RichiestaOperazione_string = PDurl_RichiestaOperazione_string.trim();
					PDurl_RichiestaOperazione = new URL(PDurl_RichiestaOperazione_string);
				} catch (MalformedURLException e) {
					throw new Exception("igrue.pd.url.richiestaoperazione specificato non e' una URL valida");
				}
			}
			
			String PDurl_GestioneEventi_string = readerConfig.getProperty("igrue.pd.url.gestioneeventi");
			if(PDurl_GestioneEventi_string == null){
				log.error("igrue.pd.url.gestioneeventi non specificato");
				throw new Exception("igrue.pd.url.gestioneeventi non specificato");
			} else {
				try {
					PDurl_GestioneEventi_string = PDurl_GestioneEventi_string.trim();
					PDurl_GestioneEventi = new URL(PDurl_GestioneEventi_string);
				} catch (MalformedURLException e) {
					throw new Exception("igrue.pd.url.gestioneeventi specificato non e' una URL valida");
				}
			}
			
			String PDurl_EsitoElaborazione_string = readerConfig.getProperty("igrue.pd.url.esitoelaborazione");
			if(PDurl_EsitoElaborazione_string == null){
				log.error("igrue.pd.url.esitoelaborazione non specificato");
				throw new Exception("igrue.pd.url.esitoelaborazione non specificato");
			} else {
				try {
					PDurl_EsitoElaborazione_string = PDurl_EsitoElaborazione_string.trim();
					PDurl_EsitoElaborazione = new URL(PDurl_EsitoElaborazione_string);
				} catch (MalformedURLException e) {
					throw new Exception("igrue.pd.url.esitoelaborazione specificato non e' una URL valida");
				}
			}
			
			PDusername = readerConfig.getProperty("igrue.pd.username");
			if(PDusername == null){
				log.error("igrue.pd.username non specificato");
				throw new Exception("igrue.pd.username non specificato");
			} else {
				PDusername = PDusername.trim();
			}
			
			
			
			if(EMAIL_NOTIFICATION_EVT || EMAIL_NOTIFICATION_ERR || EMAIL_NOTIFICATION_ESITI){
				EMAIL_SMTP = readerConfig.getProperty("igrue.email.smtp");
				if(EMAIL_SMTP == null){
					log.error("igrue.email.smtp non specificato");
					throw new Exception("igrue.email.smtp non specificato");
				} else if(EMAIL_SMTP != null){
					EMAIL_SMTP = EMAIL_SMTP.trim();
				}
				
				
				EMAIL_SENDER = readerConfig.getProperty("igrue.email.sender");
				if(EMAIL_SENDER == null){
					log.error("igrue.email.sender non specificato");
					throw new Exception("igrue.email.sender non specificato");
				} else {
					EMAIL_SENDER = EMAIL_SENDER.trim();
				}
				
				EMAIL_RECEIVER = readerConfig.getProperty("igrue.email.receiver");
				if(EMAIL_RECEIVER == null){
					log.error("igrue.email.receiver non specificato");
					throw new Exception("igrue.email.receiver non specificato");
				} else {
					EMAIL_RECEIVER = EMAIL_RECEIVER.trim();
				}
			}
			
			
			PDpassword = readerConfig.getProperty("igrue.pd.password");
			if(PDpassword == null){
				log.error("igrue.pd.password non specificato");
				throw new Exception("igrue.pd.password non specificato");
			} else {
				PDpassword = PDpassword.trim();
			}
			
			try{
				ESITI_DELAY = Integer.parseInt(readerConfig.getProperty("igrue.esiti.delay").trim());
			}
			catch(NumberFormatException e){
				ESITI_DELAY = 30;
				log.warn("igrue.esiti.delay non e' un valore numerico");
			}
			catch(Throwable e){
				ESITI_DELAY = 30;
				log.warn("igrue.esiti.delay non specificato, valore default 30 min.");
			}
			
			
			try{
				EVENTI_DELAY = Integer.parseInt(readerConfig.getProperty("igrue.eventi.delay").trim());
			}
			catch(NumberFormatException e){
				EVENTI_DELAY = 30;
				log.warn("igrue.eventi.delay non e' un valore numerico");
			}
			catch(Throwable e){
				EVENTI_DELAY = 30;
				log.warn("igrue.eventi.delay non specificato, valore default 30 min.");
			}
			
			try{
				TABELLE_DELAY = Integer.parseInt(readerConfig.getProperty("igrue.tabelle.delay").trim());
			}
			catch(NumberFormatException e){
				TABELLE_DELAY = 30;
				log.warn("igrue.tabelle.delay non e' un valore numerico");
			}
			catch(Throwable e){
				TABELLE_DELAY = 30;
				log.warn("igrue.tabelle.delay non specificato, valore default 30 min.");
			}
			
			try{
				SPEDIZIONE_DELAY = Integer.parseInt(readerConfig.getProperty("igrue.spedizione.delay").trim());
			}
			catch(NumberFormatException e){
				SPEDIZIONE_DELAY = 30;
				log.warn("igrue.spedizione.delay non e' un valore numerico");
			}
			catch(Throwable e){
				SPEDIZIONE_DELAY = 30;
				log.warn("igrue.spedizione.delay non specificato, valore default 30 min.");
			}
			
			try{
				SPEDIZIONE_POOL_SIZE = Integer.parseInt(readerConfig.getProperty("igrue.spedizione.poolsize").trim());
			}
			catch(NumberFormatException e){
				SPEDIZIONE_POOL_SIZE = 1;
				log.warn("igrue.spedizione.poolsize non e' un valore numerico, valore default 1.");
			}
			catch(Throwable e){
				SPEDIZIONE_POOL_SIZE = 1;
				log.warn("igrue.spedizione.poolsize non specificato, valore default 1.");
			}
			
			try{
				ESITI_POOL_SIZE = Integer.parseInt(readerConfig.getProperty("igrue.esiti.poolsize").trim());
			}
			catch(NumberFormatException e){
				ESITI_POOL_SIZE = 1;
				log.warn("igrue.esiti.poolsize non e' un valore numerico, valore default 1.");
			}
			catch(Throwable e){
				ESITI_POOL_SIZE = 1;
				log.warn("igrue.esiti.poolsize non specificato, valore default 1.");
			}
			
			try{
				ATTESA_CONFERMA = Integer.parseInt(readerConfig.getProperty("igrue.spedizione.attesa").trim());
			}
			catch(NumberFormatException e){
				ATTESA_CONFERMA = 1;
				log.warn("igrue.spedizione.attesa non e' un valore numerico, valore default 1.");
			}
			catch(Throwable e){
				ATTESA_CONFERMA = 1;
				log.warn("igrue.spedizione.attesa non specificato, valore default 1.");
			}
			
			
			try{
				ATTUAZIONE_ENCODE = readerConfig.getProperty("igrue.datiattuazione.encode").trim();
			}
			catch(Throwable e){
				ATTUAZIONE_ENCODE = "UTF-8";
				log.warn("igrue.datiattuazione.encode non specificato, valore default UTF-8.");
			}
			
			try{
				ERRORI_ENCODE = readerConfig.getProperty("igrue.datierrori.encode").trim();
			}
			catch(Throwable e){
				ERRORI_ENCODE = "UTF-8";
				log.warn("igrue.datierrori.encode non specificato, valore default UTF-8.");
			}
			
			try{
				TABELLE_ENCODE = readerConfig.getProperty("igrue.datitabelle.encode").trim();
			}
			catch(Throwable e){
				TABELLE_ENCODE = "UTF-8";
				log.warn("igrue.datitabelle.encode non specificato, valore default UTF-8.");
			}
			
			try{
				DETTAGLI_ENCODE = readerConfig.getProperty("igrue.datidettagli.encode").trim();
			}
			catch(Throwable e){
				DETTAGLI_ENCODE = "UTF-8";
				log.warn("igrue.datidettagli.encode non specificato, valore default UTF-8.");
			}
			
			try{
				DB_TYPE = readerConfig.getProperty("igrue.db.databasetype").trim();
			}
			catch(Throwable e){
				DB_TYPE = TipiDatabase.POSTGRESQL.getNome();
				log.warn("igrue.db.databasetype non specificato, valore default "+TipiDatabase.POSTGRESQL.getNome());
			}
			
			try{
				CHECK_FILE = readerConfig.getProperty("igrue.check.file").trim();
				File checkfile = new File(CHECK_FILE);
				if (!checkfile.exists()) {
					checkfile.createNewFile();
				}
				if(!checkfile.canRead() || !checkfile.canWrite()) {
					throw new IOException("Non si hanno i diritti di lettura o scrittura sul file");
				}
			}
			catch(IOException e){
				log.warn("igrue.check.file specificato, ma non e' stato possibile aprirlo. Non sara' memorizzato lo stato dei componenti del Proxy IGRUE: " + e);
				CHECK_FILE = null;
			}
			catch(Throwable e){
				log.warn("igrue.check.file non specificato, non sara' memorizzato lo stato dei componenti del Proxy IGRUE.");
				CHECK_FILE = null;
			}
			
		} catch (Exception e) {
			log.error("Errore durante il caricamento della configurazione ["+ config + "]: ", e);
			
			throw new Exception("Errore durante il caricamento della configurazione ["+ config + "]: " + e);
		}
	}

}
