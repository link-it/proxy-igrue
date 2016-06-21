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
package org.govmix.proxy.igrue.web.ejb.dao.jdbc;

import java.sql.Connection;

import org.openspcoop2.utils.sql.ISQLQueryObject;
import org.apache.log4j.Logger;
import org.openspcoop2.generic_project.dao.jdbc.IJDBCServiceCRUDWithId;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.govmix.proxy.igrue.web.ejb.MailTemplateId;
import org.openspcoop2.generic_project.beans.NonNegativeNumber;
import org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject;
import org.openspcoop2.generic_project.exception.NotFoundException;
import org.openspcoop2.generic_project.exception.NotImplementedException;
import org.openspcoop2.generic_project.exception.ServiceException;
import org.openspcoop2.generic_project.dao.jdbc.JDBCExpression;
import org.openspcoop2.generic_project.dao.jdbc.JDBCPaginatedExpression;
import org.openspcoop2.generic_project.dao.jdbc.JDBCServiceManagerProperties;
import org.govmix.proxy.igrue.web.ejb.MailTemplate;
import org.govmix.proxy.igrue.web.ejb.dao.jdbc.JDBCServiceManager;


public class JDBCMailTemplateServiceImpl extends JDBCMailTemplateServiceSearchImpl
	implements IJDBCServiceCRUDWithId<MailTemplate, MailTemplateId, JDBCServiceManager> {

	//@Override
	public void create(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, MailTemplate mailTemplate) throws NotImplementedException,ServiceException,Exception {

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		


		// Object mailTemplate
		sqlQueryObjectInsert.addInsertTable(this.getMailTemplateFieldConverter().toTable(MailTemplate.model()));
		sqlQueryObjectInsert.addInsertField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().TEMPLATE_CODE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().MAIL_OGGETTO,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().TEMPLATE_TXT,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().LAST_SEND,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().FLAG_SEND_SN,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().MAIL_MITTENTE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().MAIL_DESTINATARIO,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().MAIL_CC,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().ID_AMMINISTRAZIONE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().ID_SISTEMA,false),"?");

		// Insert mailTemplate
		String insertSql = sqlQueryObjectInsert.createSQLInsert();
		jdbcUtilities.execute(insertSql, jdbcProperties.isShowSql(), 
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(mailTemplate.getTemplateCode(),MailTemplate.model().TEMPLATE_CODE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(mailTemplate.getMailOggetto(),MailTemplate.model().MAIL_OGGETTO.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(mailTemplate.getTemplateTxt(),MailTemplate.model().TEMPLATE_TXT.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(mailTemplate.getLastSend(),MailTemplate.model().LAST_SEND.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(mailTemplate.getFlagSendSn(),MailTemplate.model().FLAG_SEND_SN.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(mailTemplate.getMailMittente(),MailTemplate.model().MAIL_MITTENTE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(mailTemplate.getMailDestinatario(),MailTemplate.model().MAIL_DESTINATARIO.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(mailTemplate.getMailCc(),MailTemplate.model().MAIL_CC.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(mailTemplate.getIdAmministrazione(),MailTemplate.model().ID_AMMINISTRAZIONE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(mailTemplate.getIdSistema(),MailTemplate.model().ID_SISTEMA.getFieldType())
		);

		
	}

	//@Override
	public void update(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, MailTemplateId oldId, MailTemplate mailTemplate) throws NotFoundException, NotImplementedException, ServiceException, Exception {
		

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObjectInsert.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectGet = sqlQueryObjectDelete.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectUpdate = sqlQueryObjectGet.newSQLQueryObject();
				
		// Object mailTemplate
		sqlQueryObjectUpdate.setANDLogicOperator(true);
		sqlQueryObjectUpdate.addUpdateTable(this.getMailTemplateFieldConverter().toTable(MailTemplate.model()));
		boolean isUpdate_mailTemplate = true;
		java.util.List<JDBCObject> lstObjects_mailTemplate = new java.util.ArrayList<JDBCObject>();
		sqlQueryObjectUpdate.addUpdateField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().TEMPLATE_CODE,false), "?");
		lstObjects_mailTemplate.add(new JDBCObject(mailTemplate.getTemplateCode(), MailTemplate.model().TEMPLATE_CODE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().MAIL_OGGETTO,false), "?");
		lstObjects_mailTemplate.add(new JDBCObject(mailTemplate.getMailOggetto(), MailTemplate.model().MAIL_OGGETTO.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().TEMPLATE_TXT,false), "?");
		lstObjects_mailTemplate.add(new JDBCObject(mailTemplate.getTemplateTxt(), MailTemplate.model().TEMPLATE_TXT.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().LAST_SEND,false), "?");
		lstObjects_mailTemplate.add(new JDBCObject(mailTemplate.getLastSend(), MailTemplate.model().LAST_SEND.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().FLAG_SEND_SN,false), "?");
		lstObjects_mailTemplate.add(new JDBCObject(mailTemplate.getFlagSendSn(), MailTemplate.model().FLAG_SEND_SN.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().MAIL_MITTENTE,false), "?");
		lstObjects_mailTemplate.add(new JDBCObject(mailTemplate.getMailMittente(), MailTemplate.model().MAIL_MITTENTE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().MAIL_DESTINATARIO,false), "?");
		lstObjects_mailTemplate.add(new JDBCObject(mailTemplate.getMailDestinatario(), MailTemplate.model().MAIL_DESTINATARIO.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().MAIL_CC,false), "?");
		lstObjects_mailTemplate.add(new JDBCObject(mailTemplate.getMailCc(), MailTemplate.model().MAIL_CC.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().ID_AMMINISTRAZIONE,false), "?");
		lstObjects_mailTemplate.add(new JDBCObject(mailTemplate.getIdAmministrazione(), MailTemplate.model().ID_AMMINISTRAZIONE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().ID_SISTEMA,false), "?");
		lstObjects_mailTemplate.add(new JDBCObject(mailTemplate.getIdSistema(), MailTemplate.model().ID_SISTEMA.getFieldType()));
		
		sqlQueryObjectUpdate.addWhereCondition(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().TEMPLATE_CODE,true)+"=?");
		lstObjects_mailTemplate.add(new JDBCObject(oldId.getTemplateCode(), oldId.getTemplateCode().getClass()));
		sqlQueryObjectUpdate.addWhereCondition(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().ID_AMMINISTRAZIONE,true)+"=?");
		lstObjects_mailTemplate.add(new JDBCObject(oldId.getUtente().getIdAmministrazione(), oldId.getUtente().getIdAmministrazione().getClass()));
		sqlQueryObjectUpdate.addWhereCondition(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().ID_SISTEMA,true)+"=?");
		lstObjects_mailTemplate.add(new JDBCObject(oldId.getUtente().getIdSistema(), oldId.getUtente().getIdSistema().getClass()));

		if(isUpdate_mailTemplate) {
			// Update mailTemplate
			jdbcUtilities.executeUpdate(sqlQueryObjectUpdate.createSQLUpdate(), jdbcProperties.isShowSql(), 
				lstObjects_mailTemplate.toArray(new JDBCObject[]{}));
		}

	}	
	
	//@Override
	public void updateOrCreate(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, MailTemplateId oldId, MailTemplate mailTemplate) throws NotImplementedException,ServiceException,Exception {
	
		if(this.exists(jdbcProperties, log, connection, sqlQueryObject, oldId)) {
			this.update(jdbcProperties, log, connection, sqlQueryObject, oldId, mailTemplate);
		} else {
			this.create(jdbcProperties, log, connection, sqlQueryObject, mailTemplate);
		}
		
	}
	
	//@Override
	public void delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, MailTemplate mailTemplate) throws NotImplementedException,ServiceException,Exception {
		
		MailTemplateId idObject = new MailTemplateId();

		idObject.setTemplateCode(mailTemplate.getTemplateCode());
		IdUtente utente = new IdUtente();
		utente.setIdAmministrazione(mailTemplate.getIdAmministrazione());
		utente.setIdSistema(mailTemplate.getIdSistema());
		idObject.setUtente(utente);
		
		this._delete(jdbcProperties, log, connection, sqlQueryObject, idObject);

	}

	private void _delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Object id) throws NotImplementedException,ServiceException,Exception {
		
		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObject.newSQLQueryObject();
		
		MailTemplateId idMailTemplate = this.getIdFromObject(id);

		// Object mailTemplate
		sqlQueryObjectDelete.setANDLogicOperator(true);
		sqlQueryObjectDelete.addDeleteTable(this.getMailTemplateFieldConverter().toTable(MailTemplate.model()));
		sqlQueryObjectDelete.addWhereCondition(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().TEMPLATE_CODE,true)+"=?");
		sqlQueryObjectDelete.addWhereCondition(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().ID_AMMINISTRAZIONE,true)+"=?");
		sqlQueryObjectDelete.addWhereCondition(this.getMailTemplateFieldConverter().toColumn(MailTemplate.model().ID_SISTEMA,true)+"=?");


		// Delete mailTemplate
		jdbcUtilities.execute(sqlQueryObjectDelete.createSQLDelete(), jdbcProperties.isShowSql(), 
				new JDBCObject(idMailTemplate.getTemplateCode(), idMailTemplate.getTemplateCode().getClass()),
				new JDBCObject(idMailTemplate.getUtente().getIdAmministrazione(), idMailTemplate.getUtente().getIdAmministrazione().getClass()),
				new JDBCObject(idMailTemplate.getUtente().getIdSistema(), idMailTemplate.getUtente().getIdSistema().getClass())
				);
	}

	//@Override
	public void deleteById(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, MailTemplateId idMailTemplate) throws NotImplementedException,ServiceException,Exception {

		Object id = null;
		try{
			id = this.findIdMailTemplate(jdbcProperties, log, connection, sqlQueryObject, idMailTemplate,true);
		}catch(NotFoundException notFound){
			return;
		}			
		this._delete(jdbcProperties, log, connection, sqlQueryObject, id);
		
	}
	
	//@Override
	public NonNegativeNumber deleteAll(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject) throws NotImplementedException,ServiceException,Exception {
		
		return this.deleteAll(jdbcProperties, log, connection, sqlQueryObject, new JDBCExpression(this.getMailTemplateFieldConverter()));

	}

	//@Override
	public NonNegativeNumber deleteAll(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, JDBCExpression expression) throws NotImplementedException, ServiceException,Exception {

		java.util.List<Object> lst = this._findAllObjectIds(jdbcProperties, log, connection, sqlQueryObject, new JDBCPaginatedExpression(expression));
		
		for(Object id : lst) {
			this._delete(jdbcProperties, log, connection, sqlQueryObject, id);
		}
		
		return new NonNegativeNumber(lst.size());
	
	}



	// -- DB
	
	//@Override
	public void deleteById(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, long tableId) throws ServiceException, NotImplementedException, Exception {
		throw new NotImplementedException("Table without long id column PK");
	}
}
