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

import org.govmix.proxy.igrue.web.ejb.MailTrace;

import org.openspcoop2.generic_project.beans.AbstractModel;
import org.openspcoop2.generic_project.beans.IField;
import org.openspcoop2.generic_project.beans.Field;
import org.openspcoop2.generic_project.beans.ComplexField;


/**     
 * Model MailTrace 
 *
 * @author Giovanni Bussu (bussu@link.it)
 * @author $Author$
 * @version $Rev$, $Date$
 */
public class MailTraceModel extends AbstractModel<MailTrace> {

	public MailTraceModel(){
	
		super();
	
		this.MAIL_TEMPLATE = new org.govmix.proxy.igrue.web.ejb.model.MailTemplateIdModel(new Field("mail-template",org.govmix.proxy.igrue.web.ejb.MailTemplateId.class,"mail-trace",MailTrace.class));
		this.TEMPLATE_CODE = new Field("template_code",java.lang.String.class,"mail-trace",MailTrace.class);
		this.ID_AMMINISTRAZIONE = new Field("id_amministrazione",java.lang.String.class,"mail-trace",MailTrace.class);
		this.ID_SISTEMA = new Field("id_sistema",java.lang.Integer.class,"mail-trace",MailTrace.class);
		this.SUBJECT = new Field("subject",java.lang.String.class,"mail-trace",MailTrace.class);
		this.CONTENT = new Field("content",java.lang.String.class,"mail-trace",MailTrace.class);
		this.RECEIVERS = new Field("receivers",java.lang.String.class,"mail-trace",MailTrace.class);
		this.TIME = new Field("time",java.sql.Timestamp.class,"mail-trace",MailTrace.class);
	
	}
	
	public MailTraceModel(IField father){
	
		super(father);
	
		this.MAIL_TEMPLATE = new org.govmix.proxy.igrue.web.ejb.model.MailTemplateIdModel(new ComplexField(father,"mail-template",org.govmix.proxy.igrue.web.ejb.MailTemplateId.class,"mail-trace",MailTrace.class));
		this.TEMPLATE_CODE = new ComplexField(father,"template_code",java.lang.String.class,"mail-trace",MailTrace.class);
		this.ID_AMMINISTRAZIONE = new ComplexField(father,"id_amministrazione",java.lang.String.class,"mail-trace",MailTrace.class);
		this.ID_SISTEMA = new ComplexField(father,"id_sistema",java.lang.Integer.class,"mail-trace",MailTrace.class);
		this.SUBJECT = new ComplexField(father,"subject",java.lang.String.class,"mail-trace",MailTrace.class);
		this.CONTENT = new ComplexField(father,"content",java.lang.String.class,"mail-trace",MailTrace.class);
		this.RECEIVERS = new ComplexField(father,"receivers",java.lang.String.class,"mail-trace",MailTrace.class);
		this.TIME = new ComplexField(father,"time",java.sql.Timestamp.class,"mail-trace",MailTrace.class);
	
	}
	
	

	public org.govmix.proxy.igrue.web.ejb.model.MailTemplateIdModel MAIL_TEMPLATE = null;
	 
	public IField TEMPLATE_CODE = null;
	 
	public IField ID_AMMINISTRAZIONE = null;
	 
	public IField ID_SISTEMA = null;
	 
	public IField SUBJECT = null;
	 
	public IField CONTENT = null;
	 
	public IField RECEIVERS = null;
	 
	public IField TIME = null;
	 

	//@Override
	public Class<MailTrace> getModeledClass(){
		return MailTrace.class;
	}

}