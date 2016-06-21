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
package org.govmix.proxy.igrue.ws.http;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.Configuration;

import org.apache.commons.codec.binary.Base64;

public class PD_Invoker {
	static String usernamePD;
	static String passwordPD;
	static String soapAction = "";
	Logger log;
	public PD_Invoker(Logger log) throws MalformedURLException{
		usernamePD = Configuration.PDusername;
		passwordPD = Configuration.PDpassword;
		this.log = log;
	}

	public static void copy(InputStream in, OutputStream out)
	throws IOException {
		synchronized (in) {
			synchronized (out) {
				byte[] buffer = new byte[256];
				while (true) {
					int bytesRead = in.read(buffer);
					if (bytesRead == -1) break;
					out.write(buffer, 0, bytesRead);
				}
			}
		}
	}

	public SOAPMessage send(byte[] attachment, SOAPMessage request, URL url) throws Exception{
		MimeHeaders mhs = request.getMimeHeaders();

		/* Autenticazione */
		if(usernamePD != null && passwordPD!= null){
			Base64 base64 = new Base64();
			String authorization = new String(base64.encode((usernamePD + ":" + passwordPD).getBytes()));
			mhs.addHeader("Authorization", "Basic " + authorization);
		}
		mhs.addHeader("SOAPAction", "Igrue");

		/*
		 * Se c'e' l'attachment lo metto nel messaggio
		 */

		if(attachment!=null){
			AttachmentPart apATT0001 = request.createAttachmentPart();
			apATT0001.setDataHandler(new DataHandler(new ByteArrayDataSource(attachment, "application/octet-stream"))); 
			apATT0001.setContentId("<ATT0001>");
			request.addAttachmentPart(apATT0001);
		}

		/*
		 * Invio il messaggio
		 */

		SOAPConnectionFactory connF = SOAPConnectionFactory.newInstance();
		SOAPConnection conn = connF.createConnection();
		SOAPMessage response = null;
		try{
			response = conn.call(request, url);
			if(log.getLevel() == Level.DEBUG){
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				response.writeTo(baos);
				log.debug(baos.toString());
			}
		}
		catch(Exception e){
			String err = null;
			try{
				if((err = e.getCause().getCause().toString()) == null) err = e.getCause().toString();
				throw new Exception(err);
			}
			catch(NullPointerException npe){
				throw new Exception("Ricevuta risposta vuota.",npe);
			}
			
		}

		return response;
	}

}
