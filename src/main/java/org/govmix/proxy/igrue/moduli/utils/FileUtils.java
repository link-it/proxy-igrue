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
package org.govmix.proxy.igrue.moduli.utils;


import it.mef.csp.webservices.dto.EsitoOperazione;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipFile;

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.Configuration;
import org.govmix.proxy.igrue.managers.DBManager;
import org.govmix.proxy.igrue.managers.Trasmissione;
import org.govmix.proxy.igrue.web.ejb.Esito;
import org.govmix.proxy.igrue.web.ejb.IdEsito;
import org.govmix.proxy.igrue.web.ejb.IdTabellacontesto;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.govmix.proxy.igrue.web.ejb.Tabellacontesto;
import org.govmix.proxy.igrue.web.ejb.dao.IEsitoService;
import org.govmix.proxy.igrue.web.ejb.dao.ITabellacontestoService;
import org.govmix.proxy.igrue.web.ejb.dao.jdbc.JDBCServiceManager;
import org.govmix.proxy.igrue.web.ejb.dao.jdbc.converter.EsitoFieldConverter;
import org.govmix.proxy.igrue.web.ejb.utils.UtentiUtilities;
import org.govmix.proxy.igrue.web.service.ServiceManagerFactory;
import org.openspcoop2.generic_project.beans.IField;
import org.openspcoop2.generic_project.exception.NotFoundException;

public class FileUtils {

	/**
	 * Copia un file.
	 * 
	 * @param inputFile
	 * @param outputFile
	 * @throws IOException
	 */
	public static void copy(String inputFile, String outputFile) throws IOException{ 
		InputStream in = new FileInputStream(inputFile);
		FileOutputStream out = new FileOutputStream(outputFile);
		byte[]bytes = new byte[1024];
		int letti = 0;
		while( (letti=in.read(bytes)) != -1 ){
			out.write(bytes, 0, letti);
		}
		out.flush();
		in.close();
		out.close();
	}
	
	/**
	 * Crea il contesto su filesystem per Igrue:
	 * 
	 * - codice
	 *    |- codice.zip
	 *    |- RISULTATI
	 *    |- EVENTI 
	 * 
	 * @param file
	 * @throws Exception
	 */
	public static void makeContext(Trasmissione trasmissione, IdUtente utente) throws Exception{
		
		//Creo le cartelle necessarie:
		int codice = trasmissione.getFileId();
		String file = trasmissione.getFile();
		if(!(new File(UtentiUtilities.getOutboxDir(utente) + file +".zip")).exists()) return;
    	File p = new File(UtentiUtilities.getInboxDir(utente) + codice);
    	File newZip = new File(UtentiUtilities.getInboxDir(utente) + codice + "/" + codice + ".zip");
    	File risultati = new File(UtentiUtilities.getInboxDir(utente) + codice + "/RISULTATI");
    	File eventi = new File(UtentiUtilities.getInboxDir(utente) + codice + "/EVENTI");
    	
    	
    	if(!p.exists()) if(!p.mkdir()) throw new Exception("Impossibile creare la cartella " + p.getAbsolutePath());
    	if(!risultati.exists()) if(!risultati.mkdir()) throw new Exception("Impossibile creare la cartella " + risultati.getAbsolutePath());
    	if(!eventi.exists()) if(!eventi.mkdir()) throw new Exception("Impossibile creare la cartella " + eventi.getAbsolutePath());
    	
    	copy(UtentiUtilities.getOutboxDir(utente) + file +".zip", newZip.getAbsolutePath());
    	cleanOutbox(file, utente);
	}
	
	
	public static void cleanOutbox(String file, IdUtente utente) {
    	File p = new File(UtentiUtilities.getOutboxDir(utente) + file + ".zip");
    	if(p.exists()) p.delete(); 
	}
	
	
	public static boolean saveEsito(IdEsito id, IField tipoEsitoField, byte[] att0001,EsitoOperazione esitoOperazione,Logger log){
		
		DBManager dbManager = DBManager.getInstance();
		Connection con = null;

		int file = id.getTrasmissione().getFile();
		
		try{
			String tipoEsito = new EsitoFieldConverter().toColumn(tipoEsitoField, false, false);

			con = dbManager.getConnection();
			
			JDBCServiceManager serviceManager = new JDBCServiceManager(con, ServiceManagerFactory.getJDBCProperties(false));
			
			IEsitoService esitoService = serviceManager.getEsitoService();
			Esito esito = esitoService.get(id);

			populateEsito(esito, tipoEsitoField, esitoOperazione.getCodiceEsito(),  esitoOperazione.getDescrizioneEsito() + " - " + esitoOperazione.getDettaglio());
			
			esitoService.update(id, esito);
			
			File esitoInfo = new File(UtentiUtilities.getInboxDir(id.getTrasmissione().getUtente()) + file + "/RISULTATI/" + tipoEsito + ".txt");
			FileOutputStream fos = null;
			try {
				esitoInfo.createNewFile();
				fos = new FileOutputStream(esitoInfo);
				fos.write(("[" + esitoOperazione.getCodiceEsito() + "] " + esitoOperazione.getDescrizioneEsito() + " - " + esitoOperazione.getDettaglio()).getBytes());
				fos.close();
			} catch (IOException e) {
				log.error("Impossibile creare il file "+ UtentiUtilities.getInboxDir() + file + "/RISULTATI/" + tipoEsito + ".txt", e);
				con.rollback();
				return false;
			}
			
			// Scrivo l'esito su File Sysyem
			if( att0001 == null || att0001.length == 0 ){
				log.debug("["+file+"] Nessun esito ricevuto.");
			} else{
				log.info("["+file+"] Salvataggio Esito. Size: " + att0001.length);
				File esitoFile = new File(UtentiUtilities.getInboxDir(id.getTrasmissione().getUtente()) + file + "/RISULTATI/" + tipoEsito + ".zip");
				try {
					esitoFile.createNewFile();
					fos = new FileOutputStream(esitoFile);
					fos.write(att0001);
					fos.close();
				} catch (IOException e) {
					log.error("Impossibile creare il file "+ UtentiUtilities.getInboxDir() + file + "/RISULTATI/" + tipoEsito + ".zip");
					con.rollback();
					return false;
				}
			}
			con.commit();
		} catch (Exception sqle){
			log.error("["+file+"] Impossibile aggiornare la tabella degli esiti", sqle);
			try{
				con.rollback();
			} catch(SQLException e){}
			return false;
		}
		finally{
			dbManager.releaseConnection(con);
		}
		return true;
	}
	
	
	private static void populateEsito(Esito esito, IField tipoEsitoField, int codiceEsito, String descrizioneEsito) throws Exception {

		if(tipoEsitoField.equals(Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTO)) {
			esito.setEsitoelaborazioneperanagraficadiriferimento(codiceEsito);
			esito.setEsitoelaborazioneperanagraficadiriferimentodescrizione(descrizioneEsito);
		} else if(tipoEsitoField.equals(Esito.model().STATISTICHEELABORAZIONI)) {
			esito.setStatisticheelaborazioni(codiceEsito);
			esito.setStatisticheelaborazionidescrizione(descrizioneEsito);
		} else if(tipoEsitoField.equals(Esito.model().STATISTICHESCARTI)) {
			esito.setStatistichescarti(codiceEsito);
			esito.setStatistichescartidescrizione(descrizioneEsito);
		} else if(tipoEsitoField.equals(Esito.model().LOGDEGLIERRORI)) {
			esito.setLogdeglierrori(codiceEsito);
			esito.setLogdeglierroridescrizione(descrizioneEsito);
		} else throw new Exception("Esito ["+tipoEsitoField+"] non riconosciuto");
	}

	public static boolean saveContesto(IdTabellacontesto id, byte[] dati,Long oldChecksum,Logger log){
		Long newChecksum = new Long(-1);
		File tmp = null;
		String nomeTabella = id.getNometabella() + "(idAmm: "+id.getUtente().getIdAmministrazione()+"; idSis: "+id.getUtente().getIdSistema()+")";
		String filename = UtentiUtilities.getContestoDir(id.getUtente()) + id.getNometabella();
		if(dati!=null && dati.length>0){
			ZipFile zip = null;
			try{
				tmp = new File(filename + ".zip.tmp");
				FileOutputStream fos = null;
				tmp.createNewFile();
				fos = new FileOutputStream(tmp);
				fos.write(dati);
				fos.close();
				zip = new ZipFile(tmp);
				newChecksum = zip.getEntry("content.txt").getCrc();
			}
			catch(Exception e){
				log.error("Impossibile verificare il checksum");
				log.debug(e,e);
				return false;
			} finally {
				if(zip != null) try { zip.close(); } catch (IOException e) { }
			}
		} else {
			log.warn("["+nomeTabella+"] Ricevuta tabella vuota.");
		}
		log.debug("["+nomeTabella+"] Ricevuti dati. Checksum " + newChecksum);
		DBManager dbManager = DBManager.getInstance();
		Connection con = null;
		String d = null;
		try{
			con = dbManager.getConnection();
			JDBCServiceManager serviceManager = new JDBCServiceManager(con, ServiceManagerFactory.getJDBCProperties(false));

			ITabellacontestoService tabellecontestoService = serviceManager.getTabellacontestoService();

			Tabellacontesto tabellacontesto = null;
			try{
				tabellacontesto = tabellecontestoService.get(id);
			} catch(NotFoundException e) {
				log.error("["+nomeTabella+"] Tabelle di Contesto non presente in database!.");
				return false;
			}

			if(oldChecksum==null){
				oldChecksum = tabellacontesto.getChecksum();
			}

			log.debug(oldChecksum + " = " + newChecksum);
			if(oldChecksum.compareTo(newChecksum) == 0){
				log.debug("["+nomeTabella+"] Tabelle di Contesto gia' aggiornate.");
				tmp.renameTo(new File(filename + ".zip"));

				// Aggiorno la data di aggiornamento.
				
				tabellacontesto.setDataaggiornamento(new java.util.Date());
				tabellecontestoService.update(id, tabellacontesto);
				con.commit();
				return true;
			}

			// Aggiorno data di aggiornamento e checksum

			tabellacontesto.setDataaggiornamento(new java.util.Date());
			tabellacontesto.setChecksum(newChecksum);
			tabellecontestoService.update(id, tabellacontesto);
			
			
			log.debug("["+nomeTabella+"] Tabelle di Contesto piu' aggiornate. Procedo al salvataggio");

			// Scrivo l'esito su File Sysyem
			if( dati==null || dati.length == 0 ){
				log.info("["+nomeTabella+"] Nessun dato ricevuto.");
			} else {
				log.debug("["+nomeTabella+"] Richiesta di salvataggio tabella. Size: " + dati.length);
				tmp.renameTo(new File(filename + ".zip"));
				if(Configuration.CONTESTO_HISTORY){
					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
					Date data = new Date();
					d = formatter.format(data); 
					File tabella = new File(filename + ".zip." + d);
					tabella.createNewFile();
					FileOutputStream fos = new FileOutputStream(tabella);
					fos.write(dati);
					fos.close();
				}
			}
			
			log.info("["+nomeTabella+"] Tabelle di Contesto aggiornate.");
			con.commit();
		}
		catch (IOException ioe) {
			log.error("Impossibile creare il file "+ filename + ".zip" + d);
			try{
				con.rollback();
			} catch(SQLException e){}
		}
		catch (Exception sqle){
			log.error("["+nomeTabella+"] Impossibile aggiornare la tabella di contesto: " + sqle);
			try{
				con.rollback();
			} catch(SQLException e){}
			return false;
		}
		finally{
			dbManager.releaseConnection(con);
		}
		return true;
	}
}
