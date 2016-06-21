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
package org.govmix.proxy.igrue.web.ejb.dao.jdbc.converter;

import org.openspcoop2.generic_project.beans.IField;
import org.openspcoop2.generic_project.beans.IModel;
import org.openspcoop2.generic_project.exception.ExpressionException;
import org.openspcoop2.generic_project.expression.impl.sql.AbstractSQLFieldConverter;

import org.govmix.proxy.igrue.web.ejb.Ticket;


public class TicketFieldConverter extends AbstractSQLFieldConverter {

	//@Override
	public String toColumn(IField field,boolean returnAlias,boolean appendTablePrefix) throws ExpressionException {
		
		// In the case of columns with alias, using parameter returnAlias​​, 
		// it is possible to drive the choice whether to return only the alias or 
		// the full definition of the column containing the alias
		
		if(field.equals(Ticket.model().TRASMISSIONE.FILE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".file";
			}else{
				return "file";
			}
		}
		if(field.equals(Ticket.model().TRASMISSIONE.UTENTE.ID_AMMINISTRAZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_amministrazione";
			}else{
				return "id_amministrazione";
			}
		}
		if(field.equals(Ticket.model().TRASMISSIONE.UTENTE.ID_SISTEMA)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_sistema";
			}else{
				return "id_sistema";
			}
		}
		if(field.equals(Ticket.model().FILE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".file";
			}else{
				return "file";
			}
		}
		if(field.equals(Ticket.model().ID_AMMINISTRAZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_amministrazione";
			}else{
				return "id_amministrazione";
			}
		}
		if(field.equals(Ticket.model().ID_SISTEMA)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_sistema";
			}else{
				return "id_sistema";
			}
		}
		if(field.equals(Ticket.model().DATAASSEGNAZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".dataassegnazione";
			}else{
				return "dataassegnazione";
			}
		}
		if(field.equals(Ticket.model().DATAFINETRASMISSIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".datafinetrasmissione";
			}else{
				return "datafinetrasmissione";
			}
		}
		if(field.equals(Ticket.model().FILERICEVUTO)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".filericevuto";
			}else{
				return "filericevuto";
			}
		}
		if(field.equals(Ticket.model().IDTICKET)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".idticket";
			}else{
				return "idticket";
			}
		}


		else{
			throw new ExpressionException("Field ["+field.toString()+"] not supported by converter.toColumn: "+this.getClass().getName());
		}
		
	}
	
	//@Override
	public String toTable(IField field,boolean returnAlias) throws ExpressionException {
		
		// In the case of table with alias, using parameter returnAlias​​, 
		// it is possible to drive the choice whether to return only the alias or 
		// the full definition of the table containing the alias
		
		if(field.equals(Ticket.model().TRASMISSIONE.FILE)){
			return this.toTable(Ticket.model().TRASMISSIONE, returnAlias);
		}
		if(field.equals(Ticket.model().TRASMISSIONE.UTENTE.ID_AMMINISTRAZIONE)){
			return this.toTable(Ticket.model().TRASMISSIONE.UTENTE, returnAlias);
		}
		if(field.equals(Ticket.model().TRASMISSIONE.UTENTE.ID_SISTEMA)){
			return this.toTable(Ticket.model().TRASMISSIONE.UTENTE, returnAlias);
		}
		if(field.equals(Ticket.model().FILE)){
			return this.toTable(Ticket.model(), returnAlias);
		}
		if(field.equals(Ticket.model().ID_AMMINISTRAZIONE)){
			return this.toTable(Ticket.model(), returnAlias);
		}
		if(field.equals(Ticket.model().ID_SISTEMA)){
			return this.toTable(Ticket.model(), returnAlias);
		}
		if(field.equals(Ticket.model().DATAASSEGNAZIONE)){
			return this.toTable(Ticket.model(), returnAlias);
		}
		if(field.equals(Ticket.model().DATAFINETRASMISSIONE)){
			return this.toTable(Ticket.model(), returnAlias);
		}
		if(field.equals(Ticket.model().FILERICEVUTO)){
			return this.toTable(Ticket.model(), returnAlias);
		}
		if(field.equals(Ticket.model().IDTICKET)){
			return this.toTable(Ticket.model(), returnAlias);
		}


		else{
			throw new ExpressionException("Field ["+field.toString()+"] not supported by converter.toTable: "+this.getClass().getName());
		}
		
	}

	//@Override
	public String toTable(IModel<?> model,boolean returnAlias) throws ExpressionException {
		
		// In the case of table with alias, using parameter returnAlias​​, 
		// it is possible to drive the choice whether to return only the alias or 
		// the full definition of the table containing the alias
		
		if(model.equals(Ticket.model())){
			return "tickets";
		}
		if(model.equals(Ticket.model().TRASMISSIONE)){
			return "trasmissioni";
		}
		if(model.equals(Ticket.model().TRASMISSIONE.UTENTE)){
			return "utenti";
		}


		else{
			throw new ExpressionException("Model ["+model.toString()+"] not supported by converter.toTable: "+this.getClass().getName());
		}
		
	}

}
