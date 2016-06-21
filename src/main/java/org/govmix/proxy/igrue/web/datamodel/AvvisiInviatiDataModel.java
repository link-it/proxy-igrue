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
package org.govmix.proxy.igrue.web.datamodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.govmix.proxy.igrue.web.ejb.MailTemplateId;
import org.govmix.proxy.igrue.web.ejb.MailTrace;
import org.govmix.proxy.igrue.web.ejb.MailTraceId;
import org.govmix.proxy.igrue.web.service.IMailTrace;
import org.openspcoop2.generic_project.exception.ServiceException;

@SuppressWarnings("serial")
public class AvvisiInviatiDataModel extends BaseDataModel<MailTraceId, MailTrace, IMailTrace> {

	private static Logger log = Logger.getLogger(AvvisiInviatiDataModel.class);
	
	//@Override
	public int getRowCount() {
		if(this.rowCount==null){
			try {
				this.rowCount = this.getDataProvider().totalCount();
			} catch (ServiceException e) {
				this.rowCount = 0;
			} 
		}
		return this.rowCount;
	}

	//@Override
	public Object getRowData() {
		
		if(this.currentPk==null)
			return null;
		else{
			MailTrace t = this.wrappedData.get(this.currentPk);
			if(t==null){
				try {
					t=this.getDataProvider().findById(this.currentPk);
					this.wrappedData.put(this.currentPk, t);
				} catch (ServiceException e) {}
			}
			return t;
		}
	}

	//@Override
	public void walk(FacesContext context, DataVisitor visitor, Range range,
			Object argument) throws IOException {
		try{
			if(detached){
				for (MailTraceId key : this.wrappedKeys) {
					setRowKey(key);
					visitor.process(context, key, argument);
				}
			}else{
				int start = ((SequenceRange)range).getFirstRow();
				int limit = ((SequenceRange)range).getRows();
				
				this.wrappedKeys = new ArrayList<MailTraceId>();
				List<MailTrace> list = this.getDataProvider().findAll(start, limit);
				if(list != null && !list.isEmpty()) {
					for (MailTrace mt : list) {
						MailTraceId id = new MailTraceId();
						id.setContent(mt.getContent());
						id.setReceivers(mt.getReceivers());
						id.setSubject(mt.getSubject());
						
						MailTemplateId templateId = new MailTemplateId();
						templateId.setTemplateCode(mt.getTemplateCode());
						
						IdUtente utente = new IdUtente();
						utente.setIdAmministrazione(mt.getIdAmministrazione());
						utente.setIdSistema(mt.getIdSistema());
						
						templateId.setUtente(utente);
						id.setTemplateId(templateId);
						
						id.setTime(mt.getTime());
						this.wrappedData.put(id, mt);
						this.wrappedKeys.add(id);
						visitor.process(context, id, argument);
					}
				}
			}
		}catch (Exception e) {
			log.error(e,e);
		}
	}

}
