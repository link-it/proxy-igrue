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

import org.govmix.proxy.igrue.web.ejb.Tabellacontesto;


public class TabellacontestoFieldConverter extends AbstractSQLFieldConverter {

	//@Override
	public String toColumn(IField field,boolean returnAlias,boolean appendTablePrefix) throws ExpressionException {
		
		// In the case of columns with alias, using parameter returnAlias​​, 
		// it is possible to drive the choice whether to return only the alias or 
		// the full definition of the column containing the alias
		
		if(field.equals(Tabellacontesto.model().UTENTE.ID_AMMINISTRAZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_amministrazione";
			}else{
				return "id_amministrazione";
			}
		}
		if(field.equals(Tabellacontesto.model().UTENTE.ID_SISTEMA)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_sistema";
			}else{
				return "id_sistema";
			}
		}
		if(field.equals(Tabellacontesto.model().ID_AMMINISTRAZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_amministrazione";
			}else{
				return "id_amministrazione";
			}
		}
		if(field.equals(Tabellacontesto.model().ID_SISTEMA)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_sistema";
			}else{
				return "id_sistema";
			}
		}
		if(field.equals(Tabellacontesto.model().CHECKSUM)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".checksum";
			}else{
				return "checksum";
			}
		}
		if(field.equals(Tabellacontesto.model().DATAAGGIORNAMENTO)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".dataaggiornamento";
			}else{
				return "dataaggiornamento";
			}
		}
		if(field.equals(Tabellacontesto.model().NOMETABELLA)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".nometabella";
			}else{
				return "nometabella";
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
		
		if(field.equals(Tabellacontesto.model().UTENTE.ID_AMMINISTRAZIONE)){
			return this.toTable(Tabellacontesto.model().UTENTE, returnAlias);
		}
		if(field.equals(Tabellacontesto.model().UTENTE.ID_SISTEMA)){
			return this.toTable(Tabellacontesto.model().UTENTE, returnAlias);
		}
		if(field.equals(Tabellacontesto.model().ID_AMMINISTRAZIONE)){
			return this.toTable(Tabellacontesto.model(), returnAlias);
		}
		if(field.equals(Tabellacontesto.model().ID_SISTEMA)){
			return this.toTable(Tabellacontesto.model(), returnAlias);
		}
		if(field.equals(Tabellacontesto.model().CHECKSUM)){
			return this.toTable(Tabellacontesto.model(), returnAlias);
		}
		if(field.equals(Tabellacontesto.model().DATAAGGIORNAMENTO)){
			return this.toTable(Tabellacontesto.model(), returnAlias);
		}
		if(field.equals(Tabellacontesto.model().NOMETABELLA)){
			return this.toTable(Tabellacontesto.model(), returnAlias);
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
		
		if(model.equals(Tabellacontesto.model())){
			return "tabellecontesto";
		}
		if(model.equals(Tabellacontesto.model().UTENTE)){
			return "utenti";
		}


		else{
			throw new ExpressionException("Model ["+model.toString()+"] not supported by converter.toTable: "+this.getClass().getName());
		}
		
	}

}
