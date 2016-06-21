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
import org.govmix.proxy.igrue.web.dto.EventoDTO;
import org.govmix.proxy.igrue.web.service.IEvento;
import org.openspcoop2.generic_project.exception.ServiceException;

public class EventiDataModel extends BaseDataModel<Integer, EventoDTO, IEvento> {

	private static final long serialVersionUID = 6418725575085622425L;
	private static Logger log = Logger.getLogger(EventiDataModel.class);
	
	//@Override
	public int getRowCount() {
		try {
			return this.getDataProvider().totalCount();
		} catch (ServiceException e) {
			return 0;
		}
	}

	//@Override
	public Object getRowData() {
		
		if(this.currentPk==null)
			return null;
		else{
			EventoDTO t = this.wrappedData.get(this.currentPk);
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
				for (Integer key : this.wrappedKeys) {
					setRowKey(key);
					visitor.process(context, key, argument);
				}
			}else{
				int start = ((SequenceRange)range).getFirstRow();
				int limit = ((SequenceRange)range).getRows();
				
				this.wrappedKeys = new ArrayList<Integer>();
				List<EventoDTO> list = this.getDataProvider().findAll(start, limit);
				for (EventoDTO evento : list) {
					this.wrappedData.put(evento.getEventId(), evento);
					this.wrappedKeys.add(evento.getEventId());
					visitor.process(context, evento.getEventId(), argument);
				}
			}
		}catch (Exception e) {
			log.error(e,e);
		}
	}

}
