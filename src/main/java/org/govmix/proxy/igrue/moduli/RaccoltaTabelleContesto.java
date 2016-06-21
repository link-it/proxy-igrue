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


import it.eng.csp.webservices.ArrayOfXsdNillableString;
import it.eng.csp.webservices.Tipologie;
import it.mef.csp.webservices.messages.tipologie.Content;
import it.mef.csp.webservices.messages.tipologie.AllDatiContestoIn;
import it.mef.csp.webservices.messages.tipologie.DatiContestoPerTipologiaIn;
import it.mef.csp.webservices.messages.tipologie.DatiContestoPerTipologiaOut;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.Configuration;
import org.govmix.proxy.igrue.managers.CheckManager;
import org.govmix.proxy.igrue.managers.DBManager;
import org.govmix.proxy.igrue.managers.LogManager;
import org.govmix.proxy.igrue.managers.MailManager;
import org.govmix.proxy.igrue.moduli.utils.FileUtils;
import org.govmix.proxy.igrue.web.ejb.IdTabellacontesto;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.govmix.proxy.igrue.web.ejb.utils.UtentiUtilities;
import org.govmix.proxy.igrue.ws.IgrueUtils;



public class RaccoltaTabelleContesto implements Runnable{
	private boolean stop = false;
	private boolean isAlive = true;
	
	public void run() {
		Logger log = LogManager.getLoggerRaccoltaTabelleContesto();
		
		IgrueUtils igrueUtils = null;
		
		try{
			igrueUtils = new IgrueUtils();
		} catch(Exception e) {
			log.error("Errore nell'avvio del sistema di raccolta tabelle di contesto. Impossibile instanziare i fruitori dei servizi MEF: " + e);
			LogManager.getLoggerASConsole().error("Errore nell'avvio del sistema di raccolta tabelle di contesto. Impossibile instanziare i fruitori dei servizi MEF: " + e,e);
			MailManager.postMail(null, "DAT_CNT_02",new Exception("Impossibile instanziare i fruitori dei servizi MEF"));
			stop = true;
			throw new RuntimeException("Impossibile instanziare i fruitori dei servizi MEF", e);
		}
		
		while(stop==false){
			
			List<IdUtente> utenti = new ArrayList<IdUtente>();
			try{
				utenti = UtentiUtilities.getLstUtenti(log);
			} catch(Exception e) {
				log.error("Impossibile recuperare la lista degli utenti: " + e);
				LogManager.getLoggerASConsole().error("Impossibile recuperare la lista degli utenti: " + e,e);
				MailManager.postMail(null,"GST_EVN_02",new Exception("Impossibile recuperare la lista degli utenti: " + e));
			}
			
			for(IdUtente utente : utenti) {
				log.debug("[" + utente.getIdAmministrazione() + "." + utente.getIdSistema() + "] Richiedo la Lista delle Tabelle di Contesto");
				DBManager dbManager = DBManager.getInstance();
				ResultSet rs = null;
				PreparedStatement pstm = null;
				Connection con = null;
				boolean aggiornaCompleta = false, aggiornaRichiesto = false;
	
				try{
					con = dbManager.getConnection();
					pstm = con.prepareStatement("SELECT A.nometabella,COALESCE(A.checksum,0) as checksum,A.updated FROM " +
							"( " +
							"SELECT c.nometabella,c.checksum,CAST('t' AS BOOLEAN) AS updated " +
							"FROM tabellecontesto c,eventi e " +
							"WHERE c.nometabella = e.parameterid AND c.dataaggiornamento < e.startdate AND id_amministrazione = ? AND id_sistema = ? GROUP BY nometabella,checksum " +
							") A " +
							"left JOIN " +
							"( " +
							"SELECT c.nometabella,c.checksum,'f' as updated " +
							"FROM tabellecontesto c,eventi e " +
							"WHERE c.nometabella = e.parameterid  GROUP BY nometabella,checksum " +
							") B " +
							"ON A.nometabella=B.nometabella " +
							"UNION " +
							"( " +
							"SELECT c.nometabella, COALESCE(c.checksum,0) as checksum, 'f' as updated " +
							"FROM tabellecontesto c " +
							"WHERE c.checksum is null AND id_amministrazione = ? AND id_sistema = ? GROUP BY nometabella,checksum )");
					
					pstm.setString(1, utente.getIdAmministrazione());
					pstm.setInt(2, utente.getIdSistema());
					pstm.setString(3, utente.getIdAmministrazione());
					pstm.setInt(4, utente.getIdSistema());
					
					// cerco le tabelle per cui e' c'e' un evento di aggiornamento posteriore alla data dell'ultimo download fatto per quella tabella
					rs = pstm.executeQuery();
					
					while(rs.next()){
	
						IdTabellacontesto idTab = new IdTabellacontesto();
						
						idTab.setUtente(utente);
						idTab.setNometabella(rs.getString("nometabella"));
	
						String nomeFile = UtentiUtilities.getContestoDir(utente) + idTab.getNometabella() + ".zip";
						
						
						if(!rs.getBoolean("updated") && new File(nomeFile).exists()) continue;
						
						aggiornaRichiesto = true;
						DatiContestoPerTipologiaIn request = new DatiContestoPerTipologiaIn();
						String id = idTab.getNometabella();
						ArrayOfXsdNillableString ids = new  ArrayOfXsdNillableString();
						ids.getString().add(id);
						request.setIdTipologieRichieste(ids);
						request.setCredenziali(UtentiUtilities.getCredenziali(utente));
						DatiContestoPerTipologiaOut datiContestoPerTipologiaOut = null;
						byte[] attachment = null;
						Tipologie client = null;
						try {
							client = igrueUtils.getTipologieClient();
							Holder<Content> content = new Holder<Content>();
							content.value = new Content();
							datiContestoPerTipologiaOut = client.datiContestoPerTipologia(request, content);
							attachment = igrueUtils.getAttachment((BindingProvider) client, content.value.getHref());
							CheckManager.updateCheck(CheckManager.checkTabelle, 0, log);
						} catch (Exception e) {
							log.error("[" + utente.getIdAmministrazione() + "." + utente.getIdSistema() + "] Riscontrato errore durante la richiesta dei dati di contesto per tipologia.",e);
							igrueUtils.logHttpResponse((BindingProvider) client, log);
							MailManager.postMail(utente, "DAT_CNT_04",e);
							CheckManager.updateCheck(CheckManager.checkTabelle, 2, log);
							continue;
						}
						log.info("[" + utente.getIdAmministrazione() + "." + utente.getIdSistema() + "] Ricevuti dati contesto "+id+" con esito ["
								+ datiContestoPerTipologiaOut.getEsitoOperazione().getCodiceEsito() + "] "
								+ datiContestoPerTipologiaOut.getEsitoOperazione().getDescrizioneEsito() + " - "
								+ datiContestoPerTipologiaOut.getEsitoOperazione().getDettaglio());
						if(datiContestoPerTipologiaOut.getEsitoOperazione().getCodiceEsito() == 0){
							aggiornaCompleta = true;
							if(FileUtils.saveContesto(idTab,attachment,rs.getLong("checksum"),log)){
								MailManager.postMail(utente, "DAT_CNT_06","Dati di contesto " + id + " aggiornati con successo.");
							}
						}
						else{
							MailManager.postMail(utente, "DAT_CNT_02",datiContestoPerTipologiaOut.getEsitoOperazione());
						}
					}
					CheckManager.updateCheck(CheckManager.checkTabelle, 0, log);
					if(aggiornaRichiesto && aggiornaCompleta) {
						log.info("[" + utente.getIdAmministrazione() + "." + utente.getIdSistema() + "] Aggiornamento eseguito");
					} else if(aggiornaRichiesto && !aggiornaCompleta) {
						log.error("[" + utente.getIdAmministrazione() + "." + utente.getIdSistema() + "] Aggiornamento fallito");
					} else {
						log.debug("[" + utente.getIdAmministrazione() + "." + utente.getIdSistema() + "] Nessun aggiornamento necessario");
					}
				}
				catch (SQLException sqle){
					log.error("[" + utente.getIdAmministrazione() + "." + utente.getIdSistema() + "] Errore durante l'aggiornamento delle tabelle di contesto:" + sqle, sqle);
					MailManager.postMail(utente, "DAT_CNT_01",sqle);
					CheckManager.updateCheck(CheckManager.checkTabelle, 2, log);
				}
				catch (Exception e){
					log.error("[" + utente.getIdAmministrazione() + "." + utente.getIdSistema() + "] Errore durante l'aggiornamento delle tabelle di contesto:" + e, e);
				}
				finally{
					try{ if(rs!=null) rs.close(); } catch(SQLException e){}
					try{ if(pstm!=null) pstm.close(); } catch(SQLException e){}
					dbManager.releaseConnection(con);
				}
				Tipologie client = null;
				try {
					if(aggiornaCompleta){
						byte[] attachment = null;
						
						client = igrueUtils.getTipologieClient();
						Holder<Content> content = new Holder<Content>();
						content.value = new Content();
						content.value.setHref("cid:content.zip");
						AllDatiContestoIn request = new AllDatiContestoIn();
						request.setCredenziali(UtentiUtilities.getCredenziali(utente));
						client.allDatiContesto(request, content);
						attachment = igrueUtils.getAttachment((BindingProvider) client, content.value.getHref());
						CheckManager.updateCheck(CheckManager.checkTabelle, 0, log);
						
						IdTabellacontesto idTab = new IdTabellacontesto();
						idTab.setUtente(utente);
						idTab.setNometabella("Complessivo");
						if(!FileUtils.saveContesto(idTab,attachment,null,log)){
							MailManager.postMail(utente, "DAT_CNT_03",new Exception("Impossibile salvare la tabella Complessivo"));
						}
					}
				}
				catch (Exception e) {
					log.error("Errore nel recupero della tabella complessiva: " + e,e);
					igrueUtils.logHttpResponse((BindingProvider) client, log);
					MailManager.postMail(utente, "DAT_CNT_04",e);
				}
			}		
			try {
				Thread.sleep(1000 * Configuration.TABELLE_DELAY);
			} catch (InterruptedException e) {}
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





