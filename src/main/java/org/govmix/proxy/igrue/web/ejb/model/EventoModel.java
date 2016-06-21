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
package org.govmix.proxy.igrue.web.ejb.model;

import org.govmix.proxy.igrue.web.ejb.Evento;

import org.openspcoop2.generic_project.beans.AbstractModel;
import org.openspcoop2.generic_project.beans.IField;
import org.openspcoop2.generic_project.beans.Field;
import org.openspcoop2.generic_project.beans.ComplexField;


/**     
 * Model Evento 
 *
 * @author Giovanni Bussu (bussu@link.it)
 * @author $Author$
 * @version $Rev$, $Date$
 */
public class EventoModel extends AbstractModel<Evento> {

	public EventoModel(){
	
		super();
	
		this.ENDDATE = new Field("enddate",java.util.Date.class,"evento",Evento.class);
		this.EVENTTYPE_CODE = new Field("eventtype_code",java.lang.Integer.class,"evento",Evento.class);
		this.EVENTTYPE_DESCRIPTION = new Field("eventtype_description",java.lang.String.class,"evento",Evento.class);
		this.EVENT_ID = new Field("event_id",java.lang.Integer.class,"evento",Evento.class);
		this.OWNER_DESCRIPTION = new Field("owner_description",java.lang.String.class,"evento",Evento.class);
		this.OWNER_IDAMMINISTRAZIONE = new Field("owner_idamministrazione",java.lang.String.class,"evento",Evento.class);
		this.OWNER_IDSISTEMA = new Field("owner_idsistema",java.lang.Integer.class,"evento",Evento.class);
		this.PARAMETERID = new Field("parameterid",java.lang.String.class,"evento",Evento.class);
		this.REASON = new Field("reason",java.lang.String.class,"evento",Evento.class);
		this.STARTDATE = new Field("startdate",java.util.Date.class,"evento",Evento.class);
		this.TICKET_ID = new org.govmix.proxy.igrue.web.ejb.model.TicketIdModel(new Field("ticket-id",org.govmix.proxy.igrue.web.ejb.TicketId.class,"evento",Evento.class));
	
	}
	
	public EventoModel(IField father){
	
		super(father);
	
		this.ENDDATE = new ComplexField(father,"enddate",java.util.Date.class,"evento",Evento.class);
		this.EVENTTYPE_CODE = new ComplexField(father,"eventtype_code",java.lang.Integer.class,"evento",Evento.class);
		this.EVENTTYPE_DESCRIPTION = new ComplexField(father,"eventtype_description",java.lang.String.class,"evento",Evento.class);
		this.EVENT_ID = new ComplexField(father,"event_id",java.lang.Integer.class,"evento",Evento.class);
		this.OWNER_DESCRIPTION = new ComplexField(father,"owner_description",java.lang.String.class,"evento",Evento.class);
		this.OWNER_IDAMMINISTRAZIONE = new ComplexField(father,"owner_idamministrazione",java.lang.String.class,"evento",Evento.class);
		this.OWNER_IDSISTEMA = new ComplexField(father,"owner_idsistema",java.lang.Integer.class,"evento",Evento.class);
		this.PARAMETERID = new ComplexField(father,"parameterid",java.lang.String.class,"evento",Evento.class);
		this.REASON = new ComplexField(father,"reason",java.lang.String.class,"evento",Evento.class);
		this.STARTDATE = new ComplexField(father,"startdate",java.util.Date.class,"evento",Evento.class);
		this.TICKET_ID = new org.govmix.proxy.igrue.web.ejb.model.TicketIdModel(new ComplexField(father,"ticket-id",org.govmix.proxy.igrue.web.ejb.TicketId.class,"evento",Evento.class));
	
	}
	
	

	public IField ENDDATE = null;
	 
	public IField EVENTTYPE_CODE = null;
	 
	public IField EVENTTYPE_DESCRIPTION = null;
	 
	public IField EVENT_ID = null;
	 
	public IField OWNER_DESCRIPTION = null;
	 
	public IField OWNER_IDAMMINISTRAZIONE = null;
	 
	public IField OWNER_IDSISTEMA = null;
	 
	public IField PARAMETERID = null;
	 
	public IField REASON = null;
	 
	public IField STARTDATE = null;
	 
	public org.govmix.proxy.igrue.web.ejb.model.TicketIdModel TICKET_ID = null;
	 

	//@Override
	public Class<Evento> getModeledClass(){
		return Evento.class;
	}

}