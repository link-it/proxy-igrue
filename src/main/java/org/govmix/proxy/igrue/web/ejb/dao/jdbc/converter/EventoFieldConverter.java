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
import org.govmix.proxy.igrue.web.ejb.Evento;
import org.govmix.proxy.igrue.web.ejb.Tipievento;


public class EventoFieldConverter extends AbstractSQLFieldConverter {

	//@Override
	public String toColumn(IField field,boolean returnAlias,boolean appendTablePrefix) throws ExpressionException {
		
		// In the case of columns with alias, using parameter returnAlias​​, 
		// it is possible to drive the choice whether to return only the alias or 
		// the full definition of the column containing the alias
		
		if(field.equals(Evento.model().ENDDATE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".enddate";
			}else{
				return "enddate";
			}
		}
		if(field.equals(Evento.model().EVENTTYPE_CODE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".eventtype_code";
			}else{
				return "eventtype_code";
			}
		}
		if(field.equals(Evento.model().EVENTTYPE_DESCRIPTION)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".eventtype_description";
			}else{
				return "eventtype_description";
			}
		}
		if(field.equals(Evento.model().EVENT_ID)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id";
			}else{
				return "id";
			}
		}
		if(field.equals(Evento.model().OWNER_DESCRIPTION)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".owner_description";
			}else{
				return "owner_description";
			}
		}
		if(field.equals(Evento.model().OWNER_IDAMMINISTRAZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".owner_idamministrazione";
			}else{
				return "owner_idamministrazione";
			}
		}
		if(field.equals(Evento.model().OWNER_IDSISTEMA)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".owner_idsistema";
			}else{
				return "owner_idsistema";
			}
		}
		if(field.equals(Evento.model().PARAMETERID)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".parameterid";
			}else{
				return "parameterid";
			}
		}
		if(field.equals(Evento.model().REASON)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".reason";
			}else{
				return "reason";
			}
		}
		if(field.equals(Evento.model().STARTDATE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".startdate";
			}else{
				return "startdate";
			}
		}
		if(field.equals(Evento.model().TICKET_ID.TRASMISSIONE.FILE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".file";
			}else{
				return "file";
			}
		}
		if(field.equals(Evento.model().TICKET_ID.TRASMISSIONE.UTENTE.ID_AMMINISTRAZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_amministrazione";
			}else{
				return "id_amministrazione";
			}
		}
		if(field.equals(Evento.model().TICKET_ID.TRASMISSIONE.UTENTE.ID_SISTEMA)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_sistema";
			}else{
				return "id_sistema";
			}
		}

		if(field.equals(Tipievento.model().DESCRIPTION)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".description";
			}else{
				return "description";
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
		
		if(field.equals(Evento.model().ENDDATE)){
			return this.toTable(Evento.model(), returnAlias);
		}
		if(field.equals(Evento.model().EVENTTYPE_CODE)){
			return this.toTable(Evento.model(), returnAlias);
		}
		if(field.equals(Evento.model().EVENTTYPE_DESCRIPTION)){
			return this.toTable(Evento.model(), returnAlias);
		}
		if(field.equals(Evento.model().EVENT_ID)){
			return this.toTable(Evento.model(), returnAlias);
		}
		if(field.equals(Evento.model().OWNER_DESCRIPTION)){
			return this.toTable(Evento.model(), returnAlias);
		}
		if(field.equals(Evento.model().OWNER_IDAMMINISTRAZIONE)){
			return this.toTable(Evento.model(), returnAlias);
		}
		if(field.equals(Evento.model().OWNER_IDSISTEMA)){
			return this.toTable(Evento.model(), returnAlias);
		}
		if(field.equals(Evento.model().PARAMETERID)){
			return this.toTable(Evento.model(), returnAlias);
		}
		if(field.equals(Evento.model().REASON)){
			return this.toTable(Evento.model(), returnAlias);
		}
		if(field.equals(Evento.model().STARTDATE)){
			return this.toTable(Evento.model(), returnAlias);
		}
		if(field.equals(Evento.model().TICKET_ID.TRASMISSIONE.FILE)){
			return this.toTable(Evento.model().TICKET_ID.TRASMISSIONE, returnAlias);
		}
		if(field.equals(Evento.model().TICKET_ID.TRASMISSIONE.UTENTE.ID_AMMINISTRAZIONE)){
			return this.toTable(Evento.model().TICKET_ID.TRASMISSIONE.UTENTE, returnAlias);
		}
		if(field.equals(Evento.model().TICKET_ID.TRASMISSIONE.UTENTE.ID_SISTEMA)){
			return this.toTable(Evento.model().TICKET_ID.TRASMISSIONE.UTENTE, returnAlias);
		}
		if(field.equals(Tipievento.model().DESCRIPTION)){
			return new TipieventoFieldConverter().toTable(Tipievento.model().DESCRIPTION, returnAlias);
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
		
		if(model.equals(Evento.model())){
			return "eventi";
		}
		if(model.equals(Evento.model().TICKET_ID)){
			return "tickets";
		}
		if(model.equals(Evento.model().TICKET_ID.TRASMISSIONE)){
			return "trasmissioni";
		}
		if(model.equals(Evento.model().TICKET_ID.TRASMISSIONE.UTENTE)){
			return "utenti";
		}


		else{
			throw new ExpressionException("Model ["+model.toString()+"] not supported by converter.toTable: "+this.getClass().getName());
		}
		
	}

}
