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

import org.govmix.proxy.igrue.web.ejb.MailTemplate;

import org.openspcoop2.generic_project.beans.AbstractModel;
import org.openspcoop2.generic_project.beans.IField;
import org.openspcoop2.generic_project.beans.Field;
import org.openspcoop2.generic_project.beans.ComplexField;


/**     
 * Model MailTemplate 
 *
 * @author Giovanni Bussu (bussu@link.it)
 * @author $Author$
 * @version $Rev$, $Date$
 */
public class MailTemplateModel extends AbstractModel<MailTemplate> {

	public MailTemplateModel(){
	
		super();
	
		this.TEMPLATE_CODE = new Field("template_code",java.lang.String.class,"mail-template",MailTemplate.class);
		this.ID_AMMINISTRAZIONE = new Field("id_amministrazione",java.lang.String.class,"mail-template",MailTemplate.class);
		this.ID_SISTEMA = new Field("id_sistema",java.lang.Integer.class,"mail-template",MailTemplate.class);
		this.MAIL_OGGETTO = new Field("mail_oggetto",java.lang.String.class,"mail-template",MailTemplate.class);
		this.TEMPLATE_TXT = new Field("template_txt",java.lang.String.class,"mail-template",MailTemplate.class);
		this.LAST_SEND = new Field("last_send",java.lang.String.class,"mail-template",MailTemplate.class);
		this.FLAG_SEND_SN = new Field("flag_send_sn",boolean.class,"mail-template",MailTemplate.class);
		this.MAIL_MITTENTE = new Field("mail_mittente",java.lang.String.class,"mail-template",MailTemplate.class);
		this.MAIL_DESTINATARIO = new Field("mail_destinatario",java.lang.String.class,"mail-template",MailTemplate.class);
		this.MAIL_CC = new Field("mail_cc",java.lang.String.class,"mail-template",MailTemplate.class);
		this.UTENTE = new org.govmix.proxy.igrue.web.ejb.model.IdUtenteModel(new Field("utente",org.govmix.proxy.igrue.web.ejb.IdUtente.class,"mail-template",MailTemplate.class));
	
	}
	
	public MailTemplateModel(IField father){
	
		super(father);
	
		this.TEMPLATE_CODE = new ComplexField(father,"template_code",java.lang.String.class,"mail-template",MailTemplate.class);
		this.ID_AMMINISTRAZIONE = new ComplexField(father,"id_amministrazione",java.lang.String.class,"mail-template",MailTemplate.class);
		this.ID_SISTEMA = new ComplexField(father,"id_sistema",java.lang.Integer.class,"mail-template",MailTemplate.class);
		this.MAIL_OGGETTO = new ComplexField(father,"mail_oggetto",java.lang.String.class,"mail-template",MailTemplate.class);
		this.TEMPLATE_TXT = new ComplexField(father,"template_txt",java.lang.String.class,"mail-template",MailTemplate.class);
		this.LAST_SEND = new ComplexField(father,"last_send",java.lang.String.class,"mail-template",MailTemplate.class);
		this.FLAG_SEND_SN = new ComplexField(father,"flag_send_sn",boolean.class,"mail-template",MailTemplate.class);
		this.MAIL_MITTENTE = new ComplexField(father,"mail_mittente",java.lang.String.class,"mail-template",MailTemplate.class);
		this.MAIL_DESTINATARIO = new ComplexField(father,"mail_destinatario",java.lang.String.class,"mail-template",MailTemplate.class);
		this.MAIL_CC = new ComplexField(father,"mail_cc",java.lang.String.class,"mail-template",MailTemplate.class);
		this.UTENTE = new org.govmix.proxy.igrue.web.ejb.model.IdUtenteModel(new ComplexField(father,"utente",org.govmix.proxy.igrue.web.ejb.IdUtente.class,"mail-template",MailTemplate.class));
	
	}
	
	

	public IField TEMPLATE_CODE = null;
	 
	public IField ID_AMMINISTRAZIONE = null;
	 
	public IField ID_SISTEMA = null;
	 
	public IField MAIL_OGGETTO = null;
	 
	public IField TEMPLATE_TXT = null;
	 
	public IField LAST_SEND = null;
	 
	public IField FLAG_SEND_SN = null;
	 
	public IField MAIL_MITTENTE = null;
	 
	public IField MAIL_DESTINATARIO = null;
	 
	public IField MAIL_CC = null;
	 
	public org.govmix.proxy.igrue.web.ejb.model.IdUtenteModel UTENTE = null;
	 

	//@Override
	public Class<MailTemplate> getModeledClass(){
		return MailTemplate.class;
	}

}