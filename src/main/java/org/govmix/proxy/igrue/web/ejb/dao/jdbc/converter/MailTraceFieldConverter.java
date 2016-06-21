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

import org.govmix.proxy.igrue.web.ejb.MailTrace;


public class MailTraceFieldConverter extends AbstractSQLFieldConverter {

	//@Override
	public String toColumn(IField field,boolean returnAlias,boolean appendTablePrefix) throws ExpressionException {
		
		// In the case of columns with alias, using parameter returnAlias​​, 
		// it is possible to drive the choice whether to return only the alias or 
		// the full definition of the column containing the alias
		
		if(field.equals(MailTrace.model().MAIL_TEMPLATE.TEMPLATE_CODE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".template_code";
			}else{
				return "template_code";
			}
		}
		if(field.equals(MailTrace.model().MAIL_TEMPLATE.UTENTE.ID_AMMINISTRAZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_amministrazione";
			}else{
				return "id_amministrazione";
			}
		}
		if(field.equals(MailTrace.model().MAIL_TEMPLATE.UTENTE.ID_SISTEMA)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_sistema";
			}else{
				return "id_sistema";
			}
		}
		if(field.equals(MailTrace.model().TEMPLATE_CODE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".template_code";
			}else{
				return "template_code";
			}
		}
		if(field.equals(MailTrace.model().ID_AMMINISTRAZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_amministrazione";
			}else{
				return "id_amministrazione";
			}
		}
		if(field.equals(MailTrace.model().ID_SISTEMA)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_sistema";
			}else{
				return "id_sistema";
			}
		}
		if(field.equals(MailTrace.model().SUBJECT)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".subject";
			}else{
				return "subject";
			}
		}
		if(field.equals(MailTrace.model().CONTENT)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".content";
			}else{
				return "content";
			}
		}
		if(field.equals(MailTrace.model().RECEIVERS)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".receivers";
			}else{
				return "receivers";
			}
		}
		if(field.equals(MailTrace.model().TIME)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".time";
			}else{
				return "time";
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
		
		if(field.equals(MailTrace.model().MAIL_TEMPLATE.TEMPLATE_CODE)){
			return this.toTable(MailTrace.model().MAIL_TEMPLATE, returnAlias);
		}
		if(field.equals(MailTrace.model().MAIL_TEMPLATE.UTENTE.ID_AMMINISTRAZIONE)){
			return this.toTable(MailTrace.model().MAIL_TEMPLATE.UTENTE, returnAlias);
		}
		if(field.equals(MailTrace.model().MAIL_TEMPLATE.UTENTE.ID_SISTEMA)){
			return this.toTable(MailTrace.model().MAIL_TEMPLATE.UTENTE, returnAlias);
		}
		if(field.equals(MailTrace.model().TEMPLATE_CODE)){
			return this.toTable(MailTrace.model(), returnAlias);
		}
		if(field.equals(MailTrace.model().ID_AMMINISTRAZIONE)){
			return this.toTable(MailTrace.model(), returnAlias);
		}
		if(field.equals(MailTrace.model().ID_SISTEMA)){
			return this.toTable(MailTrace.model(), returnAlias);
		}
		if(field.equals(MailTrace.model().SUBJECT)){
			return this.toTable(MailTrace.model(), returnAlias);
		}
		if(field.equals(MailTrace.model().CONTENT)){
			return this.toTable(MailTrace.model(), returnAlias);
		}
		if(field.equals(MailTrace.model().RECEIVERS)){
			return this.toTable(MailTrace.model(), returnAlias);
		}
		if(field.equals(MailTrace.model().TIME)){
			return this.toTable(MailTrace.model(), returnAlias);
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
		
		if(model.equals(MailTrace.model())){
			return "mail_trace";
		}
		if(model.equals(MailTrace.model().MAIL_TEMPLATE)){
			return "mail_template";
		}
		if(model.equals(MailTrace.model().MAIL_TEMPLATE.UTENTE)){
			return "utenti";
		}


		else{
			throw new ExpressionException("Model ["+model.toString()+"] not supported by converter.toTable: "+this.getClass().getName());
		}
		
	}

}
