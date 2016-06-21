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
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.govmix.proxy.igrue.web.ejb.MailTemplate;
import org.govmix.proxy.igrue.web.ejb.MailTemplateId;
import org.govmix.proxy.igrue.web.service.IMailTemplate;
import org.openspcoop2.generic_project.exception.ServiceException;

@SuppressWarnings("serial")
public class AvvisiEmailDataModel extends BaseDataModel<MailTemplateId, MailTemplate, IMailTemplate> {

	private static Logger log = Logger.getLogger(AvvisiEmailDataModel.class);
	
	//@Override
	public void update() {
		
		super.update();
		
		//get the template code modificato
		Map<String, String> requestParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String modifiedCode = requestParam.get("template_code");
		
		if(modifiedCode!=null && !"".equals(modifiedCode)){
			MailTemplate mt = this.wrappedData.get(modifiedCode);
			try {
				this.getDataProvider().store(mt);
			} catch (ServiceException e) {}
		}
	}
	
	//@Override
	public int getRowCount() {
		if(this.rowCount==null){
			try {
				this.rowCount = this.getDataProvider().totalCount();
			} catch (ServiceException e) {
				return 0;
			} 
		}
		return this.rowCount;
	}

	//@Override
	public Object getRowData() {
		
		if(this.currentPk==null)
			return null;
		else{
			MailTemplate t = this.wrappedData.get(this.currentPk);
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
				for (MailTemplateId key : this.wrappedKeys) {
					setRowKey(key);
					visitor.process(context, key, argument);
				}
			}else{
				int start = ((SequenceRange)range).getFirstRow();
				int limit = ((SequenceRange)range).getRows();
				
				this.wrappedKeys = new ArrayList<MailTemplateId>();
				List<MailTemplate> list = this.getDataProvider().findAll(start, limit);
				for (MailTemplate mt : list) {
					
					MailTemplateId key = new MailTemplateId();
					key.setTemplateCode(mt.getTemplateCode());
					IdUtente utente = new IdUtente();
					utente.setIdAmministrazione(mt.getIdAmministrazione());
					utente.setIdSistema(mt.getIdSistema());
					key.setUtente(utente);
					this.wrappedData.put(key, mt);
					this.wrappedKeys.add(key);
					visitor.process(context, key, argument);
				}
			}
		}catch (Exception e) {
			log.error(e,e);
		}
	}

}
