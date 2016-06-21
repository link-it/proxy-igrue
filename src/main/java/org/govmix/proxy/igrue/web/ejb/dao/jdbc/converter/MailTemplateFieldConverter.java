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

import org.govmix.proxy.igrue.web.ejb.MailTemplate;


public class MailTemplateFieldConverter extends AbstractSQLFieldConverter {

	//@Override
	public String toColumn(IField field,boolean returnAlias,boolean appendTablePrefix) throws ExpressionException {
		
		// In the case of columns with alias, using parameter returnAlias​​, 
		// it is possible to drive the choice whether to return only the alias or 
		// the full definition of the column containing the alias
		
		if(field.equals(MailTemplate.model().TEMPLATE_CODE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".template_code";
			}else{
				return "template_code";
			}
		}
		if(field.equals(MailTemplate.model().ID_AMMINISTRAZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_amministrazione";
			}else{
				return "id_amministrazione";
			}
		}
		if(field.equals(MailTemplate.model().ID_SISTEMA)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_sistema";
			}else{
				return "id_sistema";
			}
		}
		if(field.equals(MailTemplate.model().MAIL_OGGETTO)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".mail_oggetto";
			}else{
				return "mail_oggetto";
			}
		}
		if(field.equals(MailTemplate.model().TEMPLATE_TXT)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".template_txt";
			}else{
				return "template_txt";
			}
		}
		if(field.equals(MailTemplate.model().LAST_SEND)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".last_send";
			}else{
				return "last_send";
			}
		}
		if(field.equals(MailTemplate.model().FLAG_SEND_SN)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".flag_send_sn";
			}else{
				return "flag_send_sn";
			}
		}
		if(field.equals(MailTemplate.model().MAIL_MITTENTE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".mail_mittente";
			}else{
				return "mail_mittente";
			}
		}
		if(field.equals(MailTemplate.model().MAIL_DESTINATARIO)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".mail_destinatario";
			}else{
				return "mail_destinatario";
			}
		}
		if(field.equals(MailTemplate.model().MAIL_CC)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".mail_cc";
			}else{
				return "mail_cc";
			}
		}
		if(field.equals(MailTemplate.model().UTENTE.ID_AMMINISTRAZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_amministrazione";
			}else{
				return "id_amministrazione";
			}
		}
		if(field.equals(MailTemplate.model().UTENTE.ID_SISTEMA)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_sistema";
			}else{
				return "id_sistema";
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
		
		if(field.equals(MailTemplate.model().TEMPLATE_CODE)){
			return this.toTable(MailTemplate.model(), returnAlias);
		}
		if(field.equals(MailTemplate.model().ID_AMMINISTRAZIONE)){
			return this.toTable(MailTemplate.model(), returnAlias);
		}
		if(field.equals(MailTemplate.model().ID_SISTEMA)){
			return this.toTable(MailTemplate.model(), returnAlias);
		}
		if(field.equals(MailTemplate.model().MAIL_OGGETTO)){
			return this.toTable(MailTemplate.model(), returnAlias);
		}
		if(field.equals(MailTemplate.model().TEMPLATE_TXT)){
			return this.toTable(MailTemplate.model(), returnAlias);
		}
		if(field.equals(MailTemplate.model().LAST_SEND)){
			return this.toTable(MailTemplate.model(), returnAlias);
		}
		if(field.equals(MailTemplate.model().FLAG_SEND_SN)){
			return this.toTable(MailTemplate.model(), returnAlias);
		}
		if(field.equals(MailTemplate.model().MAIL_MITTENTE)){
			return this.toTable(MailTemplate.model(), returnAlias);
		}
		if(field.equals(MailTemplate.model().MAIL_DESTINATARIO)){
			return this.toTable(MailTemplate.model(), returnAlias);
		}
		if(field.equals(MailTemplate.model().MAIL_CC)){
			return this.toTable(MailTemplate.model(), returnAlias);
		}
		if(field.equals(MailTemplate.model().UTENTE.ID_AMMINISTRAZIONE)){
			return this.toTable(MailTemplate.model().UTENTE, returnAlias);
		}
		if(field.equals(MailTemplate.model().UTENTE.ID_SISTEMA)){
			return this.toTable(MailTemplate.model().UTENTE, returnAlias);
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
		
		if(model.equals(MailTemplate.model())){
			return "mail_template";
		}
		if(model.equals(MailTemplate.model().UTENTE)){
			return "utenti";
		}


		else{
			throw new ExpressionException("Model ["+model.toString()+"] not supported by converter.toTable: "+this.getClass().getName());
		}
		
	}

}
