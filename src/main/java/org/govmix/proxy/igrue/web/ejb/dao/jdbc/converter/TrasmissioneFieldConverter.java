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

import org.govmix.proxy.igrue.web.ejb.Trasmissione;


public class TrasmissioneFieldConverter extends AbstractSQLFieldConverter {

	//@Override
	public String toColumn(IField field,boolean returnAlias,boolean appendTablePrefix) throws ExpressionException {
		
		// In the case of columns with alias, using parameter returnAlias​​, 
		// it is possible to drive the choice whether to return only the alias or 
		// the full definition of the column containing the alias
		
		if(field.equals(Trasmissione.model().UTENTE.ID_AMMINISTRAZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_amministrazione";
			}else{
				return "id_amministrazione";
			}
		}
		if(field.equals(Trasmissione.model().UTENTE.ID_SISTEMA)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_sistema";
			}else{
				return "id_sistema";
			}
		}
		if(field.equals(Trasmissione.model().FILE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".file";
			}else{
				return "file";
			}
		}
		if(field.equals(Trasmissione.model().ID_AMMINISTRAZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_amministrazione";
			}else{
				return "id_amministrazione";
			}
		}
		if(field.equals(Trasmissione.model().ID_SISTEMA)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_sistema";
			}else{
				return "id_sistema";
			}
		}
		if(field.equals(Trasmissione.model().DATAULTIMOINVIO)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".dataultimoinvio";
			}else{
				return "dataultimoinvio";
			}
		}
		if(field.equals(Trasmissione.model().ESITOULTIMOINVIO)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".esitoultimoinvio";
			}else{
				return "esitoultimoinvio";
			}
		}
		if(field.equals(Trasmissione.model().ESITOULTIMOINVIODESCRIZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".esitoultimoinviodescrizione";
			}else{
				return "esitoultimoinviodescrizione";
			}
		}
		if(field.equals(Trasmissione.model().NOTIFICATO)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".notificato";
			}else{
				return "notificato";
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
		
		if(field.equals(Trasmissione.model().UTENTE.ID_AMMINISTRAZIONE)){
			return this.toTable(Trasmissione.model().UTENTE, returnAlias);
		}
		if(field.equals(Trasmissione.model().UTENTE.ID_SISTEMA)){
			return this.toTable(Trasmissione.model().UTENTE, returnAlias);
		}
		if(field.equals(Trasmissione.model().FILE)){
			return this.toTable(Trasmissione.model(), returnAlias);
		}
		if(field.equals(Trasmissione.model().ID_AMMINISTRAZIONE)){
			return this.toTable(Trasmissione.model(), returnAlias);
		}
		if(field.equals(Trasmissione.model().ID_SISTEMA)){
			return this.toTable(Trasmissione.model(), returnAlias);
		}
		if(field.equals(Trasmissione.model().DATAULTIMOINVIO)){
			return this.toTable(Trasmissione.model(), returnAlias);
		}
		if(field.equals(Trasmissione.model().ESITOULTIMOINVIO)){
			return this.toTable(Trasmissione.model(), returnAlias);
		}
		if(field.equals(Trasmissione.model().ESITOULTIMOINVIODESCRIZIONE)){
			return this.toTable(Trasmissione.model(), returnAlias);
		}
		if(field.equals(Trasmissione.model().NOTIFICATO)){
			return this.toTable(Trasmissione.model(), returnAlias);
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
		
		if(model.equals(Trasmissione.model())){
			return "trasmissioni";
		}
		if(model.equals(Trasmissione.model().UTENTE)){
			return "utenti";
		}


		else{
			throw new ExpressionException("Model ["+model.toString()+"] not supported by converter.toTable: "+this.getClass().getName());
		}
		
	}

}
