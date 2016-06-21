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
package org.govmix.proxy.igrue.web.mbean;



import java.util.Date;

import it.mef.csp.webservices.dto.CodiceProceduraAttivazione;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.web.service.IRichiestaCodiceAttivazione;

public class CodiceAttivazioneBean {

	private static Logger log = Logger.getLogger(CodiceAttivazioneBean.class);
	private IRichiestaCodiceAttivazione service;
	
	public void setService(IRichiestaCodiceAttivazione service) {
		this.service = service;
	}
	
	private CodiceProceduraAttivazione codice;

	public CodiceProceduraAttivazione getCodice() {	
		return codice;
	}

	public void setCodice(CodiceProceduraAttivazione codice) {
		this.codice = codice;
	}
	
	public String richiediCodice(){
		try{
			this.codice = this.service.getCodiceProceduraAttivazione();
		}catch (Exception e) {
			log.error(e,e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}
	
	public Date getDataAssegnazione(){
		return this.codice.getDataAssegnazione().toGregorianCalendar().getTime();
	}
}
