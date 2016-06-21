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
package org.govmix.proxy.igrue.web.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.Configuration;
import org.govmix.proxy.igrue.web.ejb.utils.UtentiUtilities;

public class Utils {
	private static Logger log = Logger.getLogger(Utils.class);
	
	public static List<IDettaglio> getDettagli(String filename){
		try {
			log.debug("getDettagli dal file " + filename);
			ZipFile zipFile = new ZipFile(filename);
			Enumeration<? extends ZipEntry> entries = zipFile.entries();

			// Prendo il primo (e unico?) elemento dello zip.
			ZipEntry entry = (ZipEntry)entries.nextElement();
			
			BufferedReader bReader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry),Configuration.DETTAGLI_ENCODE)); //"ISO-8859-1"
			String line = null;
			List<IDettaglio> dettagli = new ArrayList<IDettaglio>();
			while ((line = bReader.readLine()) != null) {
				log.debug("parsing: " + line);
				String[] parts = line.split("#");
				
				if(parts[0].compareTo("HH")==0 || parts[0].compareTo("FF")==0) {
					dettagli.add(new DettaglioHdrFtr(parts[0],Integer.parseInt(parts[1]),parts[2]));
				}
				else if(parts[0].compareTo("TIPOERRORE") == 0) {
					dettagli.add(new DettaglioErrore(parts[0],Integer.parseInt(parts[1]),parts[2]));
				}
				else if(parts[0].startsWith("ACQ_") | parts[0].compareTo("SCRT") == 0) {
					dettagli.add(new DettaglioStat(parts[0],Integer.parseInt(parts[1]),parts[2]));
				}
				else if(parts.length==3) {
					dettagli.add(new DettaglioStat(parts[0],Integer.parseInt(parts[1]),parts[2]));
				}
				else {
					dettagli.add(new DettaglioImpl(parts[0],Integer.parseInt(parts[1]),parts[2],parts[3]));
				}
 			}
			bReader.close();
			return dettagli;
		} 
		catch (IOException e) {
			log.error(e,e);
			return null;
		}
	}
	
	
	public static List<IDettaglioDatiContesto> getDettagliDatiContesto(int file){
		List<IDettaglioDatiContesto> dettagli = new ArrayList<IDettaglioDatiContesto>();
		try {
			log.debug("getDettagliDatiContesto dal file " + file);
			ZipFile zipFile = new ZipFile(UtentiUtilities.getInboxDir() + file + "/" + file + ".zip");
			Enumeration<? extends ZipEntry> entries = zipFile.entries();

			// Prendo il primo (e unico?) elemento dello zip.
			ZipEntry entry = (ZipEntry)entries.nextElement();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry),Configuration.ATTUAZIONE_ENCODE));
			String line = null;
			while ((line = bReader.readLine()) != null) {
				log.debug("parsing: " + line);
				String[] parts = line.split("#");
				
				if(parts[0].compareTo("HH")==0 || parts[0].compareTo("FF")==0) {
					continue;
				}
				else {
					dettagli.add(new DettaglioDatiContestoImpl(parts[0],parts[1],parts[2],parts[3]));
				}
 			}
			bReader.close();
		} 
		catch (IOException e) {
			log.error(e,e);
			return null;
		}
		
		
		
		try {
			//System.out.println("getDettagli dal file " + Configuration.INBOX + file + "/RISULTATI/logDegliErrori.zip");
			ZipFile zipFile = new ZipFile(UtentiUtilities.getInboxDir() + file + "/RISULTATI/logDegliErrori.zip");
			Enumeration<? extends ZipEntry> entries = zipFile.entries();

			// Prendo il primo (e unico?) elemento dello zip.
			
			ZipEntry entry = (ZipEntry)entries.nextElement();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry),Configuration.ERRORI_ENCODE));
			
			String line = null;
			HashMap<Integer,String> errori = new  HashMap<Integer,String>();
			while ((line = bReader.readLine()) != null) {
				//log.debug("parsing: " + line);
				String[] parts = line.split("#");
				
				if(parts[0].compareTo("HH")==0 || parts[0].compareTo("FF")==0) {
					continue;
				}
				else if(parts[0].compareTo("TIPOERRORE") == 0) {
					//System.out.println("Beccato il tipoerrore");
					String[] pvs = parts[2].split("\\|");
					for(int i = 0; i<pvs.length-1; i += 2){
						//System.out.println(Integer.parseInt(pvs[i]) + " : " + pvs[i+1]);
						errori.put(Integer.parseInt(pvs[i]),pvs[i+1]);
					}
				}
				
				else {
					String[] pvs = parts[3].split("\\|");
					for(int i = 0; i<pvs.length; i ++){
						if (pvs[i].compareTo("00") != 0){
							//System.out.println(parts[1] + ": trovato errore: [" + pvs[i] + "]. pos: " + i  );
							//System.out.println(errori.get(Integer.parseInt(pvs[i])));
							//System.out.println(dettagli.get(Integer.parseInt(parts[1])-1).getParteVariabile().get(i).getValore());
							dettagli.get(Integer.parseInt(parts[1])-1).getParteVariabile().get(i).setCausa(errori.get(Integer.parseInt(pvs[i])));
						}
					}
				}
 			}
			bReader.close();
		} 
		catch (Exception e) {
			
		}
		return dettagli;
	}
	
	public static List<IDettaglioTabella> getDettagliTabelle(String filename){
		try {
			log.debug("getDettagli dal file " + filename);
			ZipFile zipFile = new ZipFile(filename);
			Enumeration<? extends ZipEntry> entries = zipFile.entries();

			// Prendo il primo (e unico?) elemento dello zip.
			ZipEntry entry = (ZipEntry)entries.nextElement();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry),Configuration.TABELLE_ENCODE));
			
			String line = null;
			List<IDettaglioTabella> dettagli = new ArrayList<IDettaglioTabella>();
			while ((line = bReader.readLine()) != null) {
				log.debug("parsing: " + line);
				String[] parts = line.split("#");
				if(parts.length < 1) continue;
				if(parts[0].compareTo("HH") == 0 || parts[0].compareTo("FF") == 0) { continue; }
				
				if(filename.contains("Complessivo")){
					dettagli.add(new DettaglioGenericoTabellaImpl(parts[0],parts[1],parts[2]));
					continue;
				}
				if(parts[0].compareTo("T2") == 0){
					dettagli.add(new DettaglioT2Impl(parts[0],parts[1],parts[2]));
					continue;
				}
				if(parts[0].compareTo("T9") == 0){
					dettagli.add(new DettaglioT9Impl(parts[0],parts[1],parts[2]));
					continue;
				}
				if(parts[0].compareTo("T12") == 0){
					dettagli.add(new DettaglioT12Impl(parts[0],parts[1],parts[2]));
					continue;
				}
				if(parts[0].compareTo("T23") == 0){
					dettagli.add(new DettaglioT23Impl(parts[0],parts[1],parts[2]));
					continue;
				}
				if(parts[0].compareTo("T26") == 0){
					dettagli.add(new DettaglioT26Impl(parts[0],parts[1],parts[2]));
					continue;
				}
				if(parts[0].compareTo("T27") == 0){
					dettagli.add(new DettaglioT27Impl(parts[0],parts[1],parts[2]));
					continue;
				}
				if(parts[0].compareTo("T31") == 0){
					dettagli.add(new DettaglioT31Impl(parts[0],parts[1],parts[2]));
					continue;
				}
				if(parts[0].compareTo("T32") == 0){
					dettagli.add(new DettaglioT32Impl(parts[0],parts[1],parts[2]));
					continue;
				}
				if(parts[0].compareTo("T33") == 0){
					dettagli.add(new DettaglioT33Impl(parts[0],parts[1],parts[2]));
					continue;
				}
				if(parts[0].compareTo("T34") == 0){
					dettagli.add(new DettaglioT34Impl(parts[0],parts[1],parts[2]));
					continue;
				}
				if(parts[0].compareTo("T35") == 0){
					dettagli.add(new DettaglioT35Impl(parts[0],parts[1],parts[2]));
					continue;
				}
				if(parts[0].compareTo("T48") == 0){
					dettagli.add(new DettaglioT48Impl(parts[0],parts[1],parts[2]));
					continue;
				}
				if(parts[0].compareTo("T54") == 0){
					dettagli.add(new DettaglioT54Impl(parts[0],parts[1],parts[2]));
					continue;
				}
				dettagli.add(new DettaglioTabellaImpl(parts[0],parts[1],parts[2]));
 			}
			zipFile.close();
			bReader.close();
			return dettagli;
		} 
		catch (IOException e) {
			log.error(e,e);
			return null;
		}
	}
	
	public static List<String> getTitoli(String key){
		List<String> titoli = new ArrayList<String>();
		//titoli.add("Tabella");
		titoli.add("#");
		if(key.compareTo("T0") == 0){
			titoli.add("Cod. operazione");
			titoli.add("Descr. operazione");
			return titoli;
		}
		if(key.compareTo("T1") == 0){
			titoli.add("Cod. aiuto");
			titoli.add("Descr. aiuto");
			return titoli;
		}
		if(key.compareTo("T2") == 0){
			titoli.add("[Cod] Descr. Priorita");
			titoli.add("[Cod] Descr. obiettivo generale");
			titoli.add("[Cod] Descr. obiettivo specifico");
			return titoli;
		}
		if(key.compareTo("T3") == 0){
			titoli.add("Cod. settore");
			titoli.add("Descr. settore");
			return titoli;
		}
		if(key.compareTo("T4") == 0){
			titoli.add("Cod. tema");
			titoli.add("Descr. tema");
			return titoli;
		}
		if(key.compareTo("T5") == 0){
			titoli.add("Cod. ATECO");
			titoli.add("Descr. ATECO");
			return titoli;
		}
		if(key.compareTo("T6") == 0){
			titoli.add("Cod. attivita economica");
			titoli.add("Descr. attivita economica");
			return titoli;
		}
		if(key.compareTo("T7") == 0){
			titoli.add("Cod. dim. territoriale");
			titoli.add("Descr. dim. territoriale");
			return titoli;
		}
		if(key.compareTo("T8") == 0){
			titoli.add("Cod. tipologia finanziamento");
			titoli.add("Descr. tipologia finanziamento");
			return titoli;
		}
		if(key.compareTo("T9") == 0){
			titoli.add("[Cod] Descr. tipologia di complessita");
			titoli.add("Cod. prog.");
			titoli.add("Descr. progetto complesso");
			return titoli;
		}
		if(key.compareTo("T10") == 0){
			titoli.add("Cod. CCI");
			titoli.add("Cod. grande prog.");
			titoli.add("Descr. Grande progetto");
			return titoli;
		}
		if(key.compareTo("T11") == 0){
			titoli.add("Cod. intesa");
			titoli.add("Descr. Intesa");
			return titoli;
		}
		if(key.compareTo("T12") == 0){
			titoli.add("[Cod] Descr. QNS");
			titoli.add("[Cod] Descr. indicatore");
			return titoli;
		}
		if(key.compareTo("T13") == 0){
			titoli.add("Cod. CCI");
			titoli.add("Descr. programma");
			titoli.add("Cod. obiettivo");
			titoli.add("Descr. obiettivo");
			titoli.add("Fondo comunitario");
			return titoli;
		}
		if(key.compareTo("T14") == 0){
			titoli.add("Cod. CCI");
			titoli.add("Cod. Asse");
			titoli.add("Descr. Asse");
			return titoli;
		}
		if(key.compareTo("T15") == 0){
			titoli.add("Cod. CCI");
			titoli.add("Cod. Asse");
			titoli.add("Cod. Obiettivo");
			titoli.add("Descr. Obiettivo");
			return titoli;
		}
		if(key.compareTo("T16") == 0){
			titoli.add("Cod. FAS");
			titoli.add("Descr. programma FAS");
			return titoli;
		}
		if(key.compareTo("T17") == 0){
			titoli.add("Cod. FAS");
			titoli.add("Cod. Linea");
			titoli.add("Descr. Linea di intervento");
			return titoli;
		}
		if(key.compareTo("T18") == 0){
			titoli.add("Cod. FAS");
			titoli.add("Cod. Linea");
			titoli.add("Cod. Azione");
			titoli.add("Descr. Azione");
			return titoli;
		}
		if(key.compareTo("T19") == 0){
			titoli.add("Cod. POC");
			titoli.add("Descr. programma POC");
			return titoli;
		}
		if(key.compareTo("T20") == 0){
			titoli.add("Cod. POC");
			titoli.add("Cod. Indicatore");
			titoli.add("Descr. Indicatore di risultato");
			return titoli;
		}
		if(key.compareTo("T21") == 0){
			titoli.add("Descr. tipo strumento");
			titoli.add("Cod. strumento");
			titoli.add("Descr. strumento");
			titoli.add("Den. responsabile");
			titoli.add("Data approvazione");
			return titoli;
		}
		if(key.compareTo("T22") == 0){
			titoli.add("Settore");
			titoli.add("Descr. Settore");
			titoli.add("Attivita");
			titoli.add("Descr. Attivita");
			return titoli;
		}
		if(key.compareTo("T23") == 0){
			titoli.add("[Cod] Den. Reg.");
			titoli.add("[Cod] Den. Prov.");
			titoli.add("[Cod] Den. Comune");
			titoli.add("NUTS 1");
			titoli.add("NUTS 2");
			titoli.add("NUTS 3");
			return titoli;
		}
		if(key.compareTo("T24") == 0){
			titoli.add("Cod.");
			titoli.add("Descr. vulnerabilita");
			return titoli;
		}
		if(key.compareTo("T25") == 0){
			titoli.add("Fonte");
			titoli.add("Descr. fonte");
			return titoli;
		}
		if(key.compareTo("T26") == 0){
			titoli.add("Cod - Tipo");
			titoli.add("Descr. Norma");
			titoli.add("Numero - Anno norma");
			return titoli;
		}
		if(key.compareTo("T27") == 0){
			titoli.add("Cod.");
			titoli.add("Numero");
			titoli.add("Anno");
			titoli.add("Tipo");
			titoli.add("Descr. quota");
			return titoli;
		}
		if(key.compareTo("T28") == 0){
			titoli.add("Tipo");
			titoli.add("Codice");
			titoli.add("Descr. Voce");
			return titoli;
		}
		if(key.compareTo("T29") == 0){
			titoli.add("Cod.");
			titoli.add("Descr. Voce");
			return titoli;
		}
		if(key.compareTo("T30") == 0){
			titoli.add("Cod.");
			titoli.add("Descr. Casuale");
			return titoli;
		}
		if(key.compareTo("T31") == 0){
			titoli.add("[Cod.] Descr. Settore");
			titoli.add("[Cod.] Descr. SottoSettore");
			titoli.add("[Cod.] Descr. Categoria");
			titoli.add("[Cod.] Descr. Natura");
			titoli.add("[Cod.] Descr. Tipologia");
			titoli.add("[Cod.] Denom. Indicatore");
			titoli.add("[Cod.] Descr. Misura");
			titoli.add("Destinatari");
			return titoli;
		}
		if(key.compareTo("T32") == 0){
			titoli.add("[Cod.] Descr. Natura");
			titoli.add("[Cod.] Descr. Tipologia");
			titoli.add("[Cod.] Denom. Indicatore");
			titoli.add("[Cod.] Descr. Misura");
			return titoli;
		}
		if(key.compareTo("T33") == 0){
			titoli.add("Cod. Programma");
			titoli.add("[Cod.] Denom. Indicatore");
			titoli.add("[Cod.] Descr. Misura");
			titoli.add("Destinatari");
			return titoli;
		}
		if(key.compareTo("T34") == 0){
			titoli.add("[Cod.] Descr. Criterio");
			titoli.add("[Cod.] Descr. Dettaglio");
			return titoli;
		}
		if(key.compareTo("T35") == 0){
			titoli.add("[Cod.] Descr. Operazione");
			titoli.add("[Cod.] Descr. Iter");
			titoli.add("[Cod.] Descr. Fase");
			return titoli;
		}
		if(key.compareTo("T36") == 0){
			titoli.add("Cod.");
			titoli.add("Descr. Motivo");
			return titoli;
		}
		if(key.compareTo("T37") == 0){
			titoli.add("Cod.");
			titoli.add("Descr.");
			return titoli;
		}
		if(key.compareTo("T38") == 0){
			titoli.add("Forma Giuridica");
			titoli.add("Descr. Forma Giuridica");
			return titoli;
		}
		if(key.compareTo("T39") == 0){
			titoli.add("Cod. Ruolo");
			titoli.add("Descr. Ruolo");
			return titoli;
		}
		if(key.compareTo("T40") == 0){
			titoli.add("Cod. Dimensione");
			titoli.add("Descr. Dimensione");
			return titoli;
		}
		if(key.compareTo("T41") == 0){
			titoli.add("Cod. Classe");
			titoli.add("Descr. Classe");
			return titoli;
		}
		if(key.compareTo("T42") == 0){
			titoli.add("Cod. Paese");
			titoli.add("Descr. Paese");
			return titoli;
		}
		if(key.compareTo("T43") == 0){
			titoli.add("Cod. Titolo");
			titoli.add("Descr. Titolo");
			return titoli;
		}
		if(key.compareTo("T44") == 0){
			titoli.add("Cod.");
			titoli.add("Descr. Condizione Occupazionale");
			return titoli;
		}
		if(key.compareTo("T45") == 0){
			titoli.add("Cod.");
			titoli.add("Descr. Tipo Lavoro");
			return titoli;
		}
		if(key.compareTo("T46") == 0){
			titoli.add("Cod.");
			titoli.add("Descr. Tipo Contratto");
			return titoli;
		}
		if(key.compareTo("T47") == 0){
			titoli.add("Cod.");
			titoli.add("Descr. Tipologia Procedura Aggiudicazione");
			return titoli;
		}
		if(key.compareTo("T48") == 0){
			titoli.add("Cod. Tip. Proc. Aggiudicazione");
			titoli.add("[Cod.] Descr. Step");
			return titoli;
		}
		if(key.compareTo("T49") == 0){
			titoli.add("Cod.");
			titoli.add("Descr.");
			return titoli;
		}
		if(key.compareTo("T50") == 0){
			titoli.add("Cod. Tip. Proc. Aggiudicazione");
			titoli.add("Descr. Step");
			return titoli;
		}
		if(key.compareTo("T51") == 0){
			titoli.add("Cod. Soggetto");
			titoli.add("Descr. Soggetto");
			return titoli;
		}
		if(key.compareTo("T52") == 0){
			titoli.add("Cod. Step");
			titoli.add("Descr. Soggetto");
			return titoli;
		}
		if(key.compareTo("T53") == 0){
			titoli.add("Cod.");
			titoli.add("Descr.");
			return titoli;
		}
		if(key.compareTo("T54") == 0){
			titoli.add("Cod. Proc. Att.");
			titoli.add("Cod. Prog.");
			titoli.add("Tipo Proc.");
			titoli.add("Descr.");
			titoli.add("[Cod. Tipo] Descr. Responsabile");
			titoli.add("Importo");
			titoli.add("Cancellazione");
			return titoli;
		}
		titoli.add("Parte Variabile");
		return titoli;
	}
	
	public static String getTitolo(String key){

		if(key.equals("T0")){
			return "Tipo Operazione";
		}
		if(key.equals("T1")){
			return "Tipo Aiuto";
		}
		if(key.equals("T2")){
			return "Obiettivo Specifico QSN";
		}
		if(key.equals("T3")){
			return "Settore CPT";
		}
		if(key.equals("T4")){
			return "Tema Prioritario";
		}
		if(key.equals("T5")){
			return "Ateco 2007";
		}
		if(key.equals("T6")){
			return "Attivita economica";
		}
		if(key.equals("T7")){
			return "Dimensione Territoriale";
		}
		if(key.equals("T8")){
			return "Tipologia Finanziamento";
		}
		if(key.equals("T9")){
			return "Progetto Complesso";
		}
		if(key.equals("T10")){
			return "Grande Progetto";
		}
		if(key.equals("T11")){
			return "Intesa";
		}
		if(key.equals("T12")){
			return "Indicatori di risultato DSN";
		}
		if(key.equals("T13")){
			return "Programma Operativo";
		}
		if(key.equals("T14")){
			return "Asse";
		}
		if(key.equals("T15")){
			return "Obiettivo Operativo";
		}
		if(key.equals("T16")){
			return "Programma FAS";
		}
		if(key.equals("T17")){
			return "Linea FAS";
		}
		if(key.equals("T18")){
			return "Azione FAS";
		}
		if(key.equals("T19")){
			return "Programma POC";
		}
		if(key.equals("T20")){
			return "Indicatori di risultato di programma";
		}
		if(key.equals("T21")){
			return "Strumento attuativo";
		}
		if(key.equals("T22")){
			return "Codici orfeo";
		}
		if(key.equals("T23")){
			return "Localizzazione geografica";
		}
		if(key.equals("T24")){
			return "Gruppo Vulnerabile";
		}
		if(key.equals("T24")){
			return "Gruppo Vulnerabile";
		}
		if(key.equals("T25")){
			return "Fonti Finanziarie";
		}
		if(key.equals("T26")){
			return "Norme";
		}
		if(key.equals("T27")){
			return "Delibere";
		}
		if(key.equals("T28")){
			return "Voci di Spesa";
		}
		if(key.equals("T29")){
			return "Codici Gestionali";
		}
		if(key.equals("T30")){
			return "Casuale Pagamenti";
		}
		if(key.equals("T31")){
			return "Indicatori Core";
		}
		if(key.equals("T32")){
			return "Indicatori Occupazionali";
		}
		if(key.equals("T33")){
			return "Indicatori di Programma";
		}
		if(key.equals("T34")){
			return "Criteri e Dettagli";
		}
		if(key.equals("T35")){
			return "Fasi Procedurali";
		}
		if(key.equals("T36")){
			return "Motivo";
		}
		if(key.equals("T37")){
			return "Motivo Scostamento";
		}
		if(key.equals("T38")){
			return "Forma Giuridica";
		}
		if(key.equals("T39")){
			return "Ruolo";
		}
		if(key.equals("T40")){
			return "Dimensione";
		}
		if(key.equals("T41")){
			return "Classe Addetti";
		}
		if(key.equals("T42")){
			return "Cittadinanza";
		}
		if(key.equals("T43")){
			return "Titolo Studio";
		}
		if(key.equals("T44")){
			return "Condizione Occupazionale";
		}
		if(key.equals("T45")){
			return "Tipo Lavoro";
		}
		if(key.equals("T46")){
			return "Tipo Contratto";
		}
		if(key.equals("T47")){
			return "Tipo Procedure Aggiudicazione";
		}
		if(key.equals("T48")){
			return "Step Procedure di Aggiudicazione";
		}
		if(key.equals("T49")){
			return "Motivo Scostamento Procedura di Aggiudicazione";
		}
		if(key.equals("T50")){
			return "Tipo Procedure di Attivazione";
		}
		if(key.equals("T51")){
			return "Responsabile Procedura";
		}
		if(key.equals("T52")){
			return "Step Procedure di Attivazione";
		}
		if(key.equals("T53")){
			return "Motivo Scostamento Procedura di Attivazione";
		}
		if(key.equals("T54")){
			return "Procedura di attivazione";
		}

		return "-";
	}

	public static final void copyInputStream(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int len;

		while((len = in.read(buffer)) >= 0)
			out.write(buffer, 0, len);

		in.close();
		out.close();
	}
}
