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

import org.govmix.proxy.igrue.web.ejb.Ticket;

import org.openspcoop2.generic_project.beans.AbstractModel;
import org.openspcoop2.generic_project.beans.IField;
import org.openspcoop2.generic_project.beans.Field;
import org.openspcoop2.generic_project.beans.ComplexField;


/**     
 * Model Ticket 
 *
 * @author Giovanni Bussu (bussu@link.it)
 * @author $Author$
 * @version $Rev$, $Date$
 */
public class TicketModel extends AbstractModel<Ticket> {

	public TicketModel(){
	
		super();
	
		this.TRASMISSIONE = new org.govmix.proxy.igrue.web.ejb.model.IdTrasmissioneModel(new Field("trasmissione",org.govmix.proxy.igrue.web.ejb.IdTrasmissione.class,"ticket",Ticket.class));
		this.FILE = new Field("file",java.lang.Integer.class,"ticket",Ticket.class);
		this.ID_AMMINISTRAZIONE = new Field("id_amministrazione",java.lang.String.class,"ticket",Ticket.class);
		this.ID_SISTEMA = new Field("id_sistema",java.lang.Integer.class,"ticket",Ticket.class);
		this.DATAASSEGNAZIONE = new Field("dataassegnazione",java.util.Date.class,"ticket",Ticket.class);
		this.DATAFINETRASMISSIONE = new Field("datafinetrasmissione",java.util.Date.class,"ticket",Ticket.class);
		this.FILERICEVUTO = new Field("filericevuto",boolean.class,"ticket",Ticket.class);
		this.IDTICKET = new Field("idticket",java.lang.String.class,"ticket",Ticket.class);
	
	}
	
	public TicketModel(IField father){
	
		super(father);
	
		this.TRASMISSIONE = new org.govmix.proxy.igrue.web.ejb.model.IdTrasmissioneModel(new ComplexField(father,"trasmissione",org.govmix.proxy.igrue.web.ejb.IdTrasmissione.class,"ticket",Ticket.class));
		this.FILE = new ComplexField(father,"file",java.lang.Integer.class,"ticket",Ticket.class);
		this.ID_AMMINISTRAZIONE = new ComplexField(father,"id_amministrazione",java.lang.String.class,"ticket",Ticket.class);
		this.ID_SISTEMA = new ComplexField(father,"id_sistema",java.lang.Integer.class,"ticket",Ticket.class);
		this.DATAASSEGNAZIONE = new ComplexField(father,"dataassegnazione",java.util.Date.class,"ticket",Ticket.class);
		this.DATAFINETRASMISSIONE = new ComplexField(father,"datafinetrasmissione",java.util.Date.class,"ticket",Ticket.class);
		this.FILERICEVUTO = new ComplexField(father,"filericevuto",boolean.class,"ticket",Ticket.class);
		this.IDTICKET = new ComplexField(father,"idticket",java.lang.String.class,"ticket",Ticket.class);
	
	}
	
	

	public org.govmix.proxy.igrue.web.ejb.model.IdTrasmissioneModel TRASMISSIONE = null;
	 
	public IField FILE = null;
	 
	public IField ID_AMMINISTRAZIONE = null;
	 
	public IField ID_SISTEMA = null;
	 
	public IField DATAASSEGNAZIONE = null;
	 
	public IField DATAFINETRASMISSIONE = null;
	 
	public IField FILERICEVUTO = null;
	 
	public IField IDTICKET = null;
	 

	//@Override
	public Class<Ticket> getModeledClass(){
		return Ticket.class;
	}

}