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

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.web.core.Utils;
import org.govmix.proxy.igrue.web.service.IService;

public class BaseBean<T,K> {

	protected IService<T, K> service;
	protected T selectedElement;
	protected Map<T, Boolean> selectedIds = new HashMap<T, Boolean>();
	protected ArrayList<T> toRemove;
	private static Logger log = Logger.getLogger(BaseBean.class);
	
	public void setService(IService<T, K> service) {
		this.service = service;
	}
	
	@SuppressWarnings("unchecked")
	public T getSelectedElement(){
		if(this.selectedElement==null){
			try{
				ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
				this.selectedElement = ((Class<T>)parameterizedType.getActualTypeArguments()[0]).newInstance();
			}catch (Exception e) {
				log.error("errore cercando di istanziare il selectedElement ", e);
			}
		}
		return this.selectedElement;
	}
	
	public void setSelectedElement(T selectedElement) {
		this.selectedElement = selectedElement;
	}
	
	public Map<T, Boolean> getSelectedIds() {
		return selectedIds;
	}
	
	public void setSelectedIds(Map<T, Boolean> selectedIds) {
		this.selectedIds = selectedIds;
	}
	
//	public EntityManager getEntityManager(){
//		return this.service.getEntityManager();
//	}
	
	public String delete(){
		toRemove = new ArrayList<T>();
		Iterator<T> it = this.selectedIds.keySet().iterator();
		while (it.hasNext()) {
			T elem = (T) it.next();
			if(this.selectedIds.get(elem).booleanValue()){
				this.toRemove.add(elem);
				it.remove();
			}
		}
				
		for (T elem : toRemove) {
			try{
				this.service.delete(elem);
			}catch (Exception e) {
				FacesContext ctx = FacesContext.getCurrentInstance();
				String m = Utils.getMessageFromResourceBundle(ctx.getApplication().getMessageBundle(), "DELETE_ERROR", new String[]{elem.toString()}, ctx.getViewRoot().getLocale());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(m,e.getLocalizedMessage()));
			}
			
		}
		
		
		return null;
	}
	
}
