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

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.govmix.proxy.igrue.web.dto.EventoDTO;
import org.govmix.proxy.igrue.web.service.IEvento;
import org.openspcoop2.generic_project.exception.ServiceException;

public class EventiBean extends BaseBean<EventoDTO, Integer>{

	private List<SelectItem> descrizioni;
	
	public List<SelectItem> getDescrizioni() throws ServiceException{
		
		if(this.descrizioni!=null)
			return this.descrizioni;
		
		this.descrizioni=new ArrayList<SelectItem>();
		
		IEvento s = (IEvento) this.service;
		List<String> list = s.getDescrizioni();
		
		for (String descr : list) {
			this.descrizioni.add(new SelectItem(descr));
		}
		
		return this.descrizioni;
	}
	
}
