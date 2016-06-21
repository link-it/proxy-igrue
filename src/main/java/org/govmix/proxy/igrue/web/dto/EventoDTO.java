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
package org.govmix.proxy.igrue.web.dto;

import org.govmix.proxy.igrue.web.ejb.Evento;
import org.govmix.proxy.igrue.web.ejb.Tipievento;

@SuppressWarnings("serial")
public class EventoDTO extends Evento{

	protected String descrizione;
	
	public EventoDTO() {
	}
	
	public EventoDTO(Evento e, Tipievento te) {
		this.setDescrizione(te.getDescription());
		this.setEventId(e.getEventId());
		this.setEnddate(e.getEnddate());
		this.setEventtypeCode(e.getEventtypeCode());
		this.setEventtypeDescription(e.getEventtypeDescription());
		this.setOwnerDescription(e.getOwnerDescription());
		this.setOwnerIdamministrazione(e.getOwnerIdamministrazione());
		this.setOwnerIdsistema(e.getOwnerIdsistema());
		this.setReason(e.getReason());
		this.setStartdate(e.getStartdate());
		this.setParameterid(e.getParameterid());
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizione() {
		return descrizione;
	}
	
	}
