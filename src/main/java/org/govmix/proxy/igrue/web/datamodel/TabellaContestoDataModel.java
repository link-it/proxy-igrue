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
import org.govmix.proxy.igrue.web.ejb.IdTabellacontesto;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.govmix.proxy.igrue.web.ejb.Tabellacontesto;
import org.govmix.proxy.igrue.web.service.ITabellacontesto;
import org.openspcoop2.generic_project.exception.ServiceException;

public class TabellaContestoDataModel extends BaseDataModel<IdTabellacontesto, Tabellacontesto, ITabellacontesto> {

	
	private static final long serialVersionUID = -5840960340275319070L;
	private static Logger log = Logger.getLogger(TabellaContestoDataModel.class);
	
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
			Tabellacontesto t = this.wrappedData.get(this.currentPk);
			if(t==null){
				try {
					t = this.getDataProvider().findById(this.currentPk);
				} catch (ServiceException e) {}
			}
			return t;
		}
	}

	//@Override
	public void walk(FacesContext context, DataVisitor visitor, Range range,
			Object argument) throws IOException {
		try{
			if(this.detached){
				for (IdTabellacontesto nometabella : this.wrappedKeys) {
					setRowKey(nometabella);
					visitor.process(context, nometabella, argument);
				}
			}else{
				int start = ((SequenceRange)range).getFirstRow();
				int limit = ((SequenceRange)range).getRows();
				
				this.wrappedKeys = new ArrayList<IdTabellacontesto>();
				List<Tabellacontesto> list = this.getDataProvider().findAll(start, limit);
				for (Tabellacontesto tabellacontesto : list) {
					IdTabellacontesto id = new IdTabellacontesto();
					id.setNometabella(tabellacontesto.getNometabella());
					IdUtente utente = new IdUtente();
					utente.setIdAmministrazione(tabellacontesto.getIdAmministrazione());
					utente.setIdSistema(tabellacontesto.getIdSistema());
					id.setUtente(utente);
					this.wrappedData.put(id, tabellacontesto);
					this.wrappedKeys.add(id);
					visitor.process(context, id, argument);
				}
			}
		}catch (Exception e) {
			log.error(e,e);
		}
	}	
}
