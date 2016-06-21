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


import it.mef.csp.webservices.dto.EsitoOperazione;
import it.mef.csp.webservices.dto.EventWS;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.govmix.proxy.igrue.Configuration;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.govmix.proxy.igrue.web.ejb.MailTemplate;
import org.govmix.proxy.igrue.web.ejb.MailTemplateId;
import org.govmix.proxy.igrue.web.ejb.MailTrace;
import org.govmix.proxy.igrue.web.ejb.Tipievento;
import org.govmix.proxy.igrue.web.ejb.dao.IMailTemplateServiceSearch;
import org.govmix.proxy.igrue.web.ejb.dao.IMailTraceService;
import org.govmix.proxy.igrue.web.ejb.dao.ITipieventoServiceSearch;
import org.govmix.proxy.igrue.web.ejb.utils.UtentiUtilities;
import org.govmix.proxy.igrue.web.service.ServiceManagerFactory;
import org.openspcoop2.generic_project.expression.IPaginatedExpression;

public class MailManager {

	private static SimpleDateFormat formatter = new SimpleDateFormat("DD-MMM-yyyy hh:mm");  

	public static void postMail(IdUtente utente, String template_code,Exception e){
		postMail(utente, template_code,makeContent(e,null), Configuration.EMAIL_NOTIFICATION_ERR);
	}
	public static void postMail(IdUtente utente, String template_code, EsitoOperazione esito) {
		postMail(utente, template_code,makeContent(null,esito), Configuration.EMAIL_NOTIFICATION_ESITI);
	}
	public static void postMail(EventWS evento, int file) {
		IdUtente idUtente = new IdUtente();
		idUtente.setIdAmministrazione(evento.getOwner().getIdAmministrazione());
		idUtente.setIdSistema(evento.getOwner().getIdSistema());
		postMail(idUtente,"RIC_EVN_0" + evento.getEventType().getCode(),makeContent(evento,file), Configuration.EMAIL_NOTIFICATION_EVT);
	}
	
	public static void postMail(IdUtente idUtente, String template_code,String content_contrib) {
		postMail(idUtente, template_code, content_contrib, Configuration.EMAIL_NOTIFICATION_EVT);
	}

	private static void postMail(IdUtente idUtente, String template_code,String content_contrib, boolean filter) {
		if(!filter) return;
		
		String dest,cc, obj,content;
		boolean send, bcc=true;
		// Recupero il template da DB
		//		Connection con = null;
		//		ResultSet rs = null;
		//		PreparedStatement pstm = null;
		
		
		List<IdUtente> utenti = new ArrayList<IdUtente>();
		
		if(idUtente!=null) {
			utenti.add(idUtente);
		} else {
			try {
				utenti = UtentiUtilities.getLstUtenti(null);
			} catch (Exception e) {
				LogManager.getLoggerASConsole().error("Impossibile reperire la lista degli utenti",e);
				return;
			}
		}
		
		for(IdUtente utente : utenti) {
			try{
				//			con = DBManager.getInstance().getConnection();
				//			pstm = con.prepareStatement("SELECT * FROM mail_template WHERE template_code=?");
				//			pstm.setString(1, template_code);
				//			rs = pstm.executeQuery();

				IMailTemplateServiceSearch mailTemplateSearch = ServiceManagerFactory.getServiceManager().getMailTemplateServiceSearch();

				MailTemplateId id = new MailTemplateId();
				id.setTemplateCode(template_code);
				id.setUtente(utente);


				if(mailTemplateSearch.exists(id)){
					MailTemplate mt = mailTemplateSearch.get(id);

					dest = mt.getMailDestinatario();
					cc = mt.getMailCc();
					obj = mt.getMailOggetto();
					content = mt.getTemplateTxt();
					send = mt.getFlagSendSn();
				}
				else{
					dest = "";
					cc = "";
					obj = "Errore  ("+template_code+")";
					content = "Non e' stato trovato un template per questo errore ("+template_code+").\nInoltrare la seguente agli amministratori del sistema.\nGrazie.";
					send = true;
				}
			}
			catch(Exception ee){
				dest = "";
				cc = "";
				obj = "Errore  ("+template_code+")";
				content = "Errore nell'accesso a DB. Template non reperibile. ("+template_code+").\nInoltrare la seguente agli amministratori del sistema.\nGrazie.";
				send = true;
			}

			if(!send) return;
			if(dest.compareTo("")==0) {
				dest=Configuration.EMAIL_RECEIVER;
				bcc = false;
			}
			String[] recipients = dest.split(";");
			content += content_contrib;
			//Set the host smtp address
			Properties props = new Properties();
			props.put("mail.smtp.host", Configuration.EMAIL_SMTP);

			// create some properties and get the default Session
			Session session = Session.getDefaultInstance(props, null);
			// create a message
			Message msg = new MimeMessage(session);
			// set the from and to address
			try{
				InternetAddress addressFrom = new InternetAddress(Configuration.EMAIL_SENDER);
				msg.setFrom(addressFrom);
				InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
				for (int i = 0; i < recipients.length; i++){
					addressTo[i] = new InternetAddress(recipients[i]);
				}
				msg.setRecipients(Message.RecipientType.TO, addressTo);
				if(cc.compareTo("")!=0){
					String[] ccs = cc.split(";");
					InternetAddress[] addressCC = new InternetAddress[ccs.length]; 
					for (int i = 0; i < ccs.length; i++){
						addressCC[i] = new InternetAddress(ccs[i]);
					}
					msg.setRecipients(Message.RecipientType.CC, addressCC);
				}
				if(bcc){
					InternetAddress[] addressBCC = new InternetAddress[1]; 
					addressBCC[0] = new InternetAddress(Configuration.EMAIL_RECEIVER);
					msg.setRecipients(Message.RecipientType.BCC, addressBCC);
				}
				// Setting the Subject and Content Type
				msg.setSubject(obj);
				msg.setContent(content, "text/plain");
				Transport.send(msg);
				LogManager.getLoggerASConsole().info("Mail inviata: " + obj);

				/* Salvo la mail in database */


				IMailTraceService mailTraceCRUD = ServiceManagerFactory.getServiceManager().getMailTraceService();

				MailTrace mailTrace = new MailTrace();
				mailTrace.setTemplateCode(template_code);
				mailTrace.setSubject(obj);
				mailTrace.setContent(content);
				mailTrace.setReceivers(dest+";"+cc);
				mailTrace.setTime(new Date(System.currentTimeMillis()));
				mailTrace.setIdAmministrazione(utente.getIdAmministrazione());
				mailTrace.setIdSistema(utente.getIdSistema());
				mailTraceCRUD.create(mailTrace);
			} catch(Exception email){
				LogManager.getLoggerASConsole().error("Invio email fallito: ",email);
			}
		}

	}

	public static String makeContent(Exception e,EsitoOperazione esito){
		String msg = "";
		if(e != null){
			ByteArrayOutputStream stack = new ByteArrayOutputStream();
			PrintWriter s = new PrintWriter(stack);
			e.printStackTrace(s);
			s.close();

			msg += "\n\nStackTrace dell'eccezione rilevata:\n\n" + new String(stack.toByteArray());
		}

		if(esito != null){
			msg += "\n\nEsito ricevuto:" +
					"\n\tCodice Esito: " + esito.getCodiceEsito() +
					"\n\tDescrizione: " + esito.getDescrizioneEsito() +
					"\n\tDettaglio: " + esito.getDettaglio() +
					"\n\tData: " + formatter.format(esito.getTimeStamp().toGregorianCalendar().getTime());
		}
		return msg;
	}


	public static String makeContent(EventWS evento, int file){
		String msg = "";
		msg += "\n\nEvento ricevuto:" ;
		msg +=	"\n\tID: " + evento.getId() ;
		msg +=	"\n\tTrasmissione: " + file ;
		if(evento.getReason() != null)	msg +=	"\n\tRagione: " + evento.getReason() ;
		if(evento.getEventType() != null)	msg +=	"\n\tTipo Evento: (" + evento.getEventType().getCode() + ") " ;


		// Recupero la descrizione dell'evento
		try{
			//			
			//			con = DBManager.getInstance().getConnection();
			//			pstm = con.prepareStatement("SELECT * FROM tipievento WHERE code=?");
			//			pstm.setInt(1, evento.getEventType().getCode());
			//			rs = pstm.executeQuery();
			//			if(rs.next()){
			//				msg +=	rs.getString("description").trim();
			//			}

			ITipieventoServiceSearch tipiEventoSearch = ServiceManagerFactory.getServiceManager().getTipieventoServiceSearch();

			IPaginatedExpression exp = tipiEventoSearch.newPaginatedExpression();
			exp.equals(Tipievento.model().CODE, evento.getEventType().getCode());

			List<Tipievento> tipi = tipiEventoSearch.findAll(exp);

			if(tipi != null && tipi.size() > 0) {
				msg +=	tipi.get(0).getDescription();
			}

		}
		catch(Exception ee){}

		if(evento.getParameterId() != null)	msg +=	"\n\tTicket: " + evento.getParameterId() ;
		if(evento.getOwner() != null)	msg +=	"\n\tOwner: (" + evento.getOwner().getIdSistema() + ", " + evento.getOwner().getIdAmministrazione() + ") "; 
		if(evento.getOwner().getDescription() != null)	msg += evento.getOwner().getDescription()  ;
		if(evento.getStartDate() != null)	msg +=	"\n\tData inizio: " + formatter.format(evento.getStartDate().toGregorianCalendar().getTime()) ;
		if(evento.getEndDate() != null)	msg +=	"\n\tData fine: " + formatter.format(evento.getEndDate().toGregorianCalendar().getTime());
		return msg;
	}
}
