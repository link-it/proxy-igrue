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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.openspcoop2.utils.sql.ISQLQueryObject;
import org.apache.log4j.Logger;
import org.openspcoop2.generic_project.dao.jdbc.IJDBCServiceCRUDWithId;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.govmix.proxy.igrue.web.ejb.MailTemplateId;
import org.govmix.proxy.igrue.web.ejb.MailTraceId;
import org.openspcoop2.generic_project.beans.NonNegativeNumber;
import org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject;
import org.openspcoop2.generic_project.exception.NotFoundException;
import org.openspcoop2.generic_project.exception.NotImplementedException;
import org.openspcoop2.generic_project.exception.ServiceException;
import org.openspcoop2.generic_project.dao.jdbc.JDBCExpression;
import org.openspcoop2.generic_project.dao.jdbc.JDBCPaginatedExpression;
import org.openspcoop2.generic_project.dao.jdbc.JDBCServiceManagerProperties;
import org.govmix.proxy.igrue.web.ejb.MailTrace;
import org.govmix.proxy.igrue.web.ejb.dao.jdbc.JDBCServiceManager;


public class JDBCMailTraceServiceImpl extends JDBCMailTraceServiceSearchImpl
	implements IJDBCServiceCRUDWithId<MailTrace, MailTraceId, JDBCServiceManager> {

	//@Override
	public void create(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, MailTrace mailTrace) throws NotImplementedException,ServiceException,Exception {

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		


		// Object mailTrace
		sqlQueryObjectInsert.addInsertTable(this.getMailTraceFieldConverter().toTable(MailTrace.model()));
		sqlQueryObjectInsert.addInsertField(this.getMailTraceFieldConverter().toColumn(MailTrace.model().TEMPLATE_CODE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getMailTraceFieldConverter().toColumn(MailTrace.model().ID_AMMINISTRAZIONE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getMailTraceFieldConverter().toColumn(MailTrace.model().ID_SISTEMA,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getMailTraceFieldConverter().toColumn(MailTrace.model().SUBJECT,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getMailTraceFieldConverter().toColumn(MailTrace.model().CONTENT,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getMailTraceFieldConverter().toColumn(MailTrace.model().RECEIVERS,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getMailTraceFieldConverter().toColumn(MailTrace.model().TIME,false),"?");

		// Insert mailTrace
		String insertSql = sqlQueryObjectInsert.createSQLInsert();
		jdbcUtilities.execute(insertSql, jdbcProperties.isShowSql(), 
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(mailTrace.getTemplateCode(),MailTrace.model().TEMPLATE_CODE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(mailTrace.getIdAmministrazione(),MailTrace.model().ID_AMMINISTRAZIONE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(mailTrace.getIdSistema(),MailTrace.model().ID_SISTEMA.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(mailTrace.getSubject(),MailTrace.model().SUBJECT.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(mailTrace.getContent(),MailTrace.model().CONTENT.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(mailTrace.getReceivers(),MailTrace.model().RECEIVERS.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(new Timestamp(mailTrace.getTime().getTime()),MailTrace.model().TIME.getFieldType())
		);

		
	}

	//@Override
	public void update(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, MailTraceId oldId, MailTrace mailTrace) throws NotFoundException, NotImplementedException, ServiceException, Exception {
		

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObjectInsert.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectGet = sqlQueryObjectDelete.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectUpdate = sqlQueryObjectGet.newSQLQueryObject();
				
		// Object mailTrace
		sqlQueryObjectUpdate.setANDLogicOperator(true);
		sqlQueryObjectUpdate.addUpdateTable(this.getMailTraceFieldConverter().toTable(MailTrace.model()));
		boolean isUpdate_mailTrace = true;
		java.util.List<JDBCObject> lstObjects_mailTrace = new java.util.ArrayList<JDBCObject>();
		sqlQueryObjectUpdate.addUpdateField(this.getMailTraceFieldConverter().toColumn(MailTrace.model().TEMPLATE_CODE,false), "?");
		lstObjects_mailTrace.add(new JDBCObject(mailTrace.getTemplateCode(), MailTrace.model().TEMPLATE_CODE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getMailTraceFieldConverter().toColumn(MailTrace.model().ID_AMMINISTRAZIONE,false), "?");
		lstObjects_mailTrace.add(new JDBCObject(mailTrace.getIdAmministrazione(), MailTrace.model().ID_AMMINISTRAZIONE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getMailTraceFieldConverter().toColumn(MailTrace.model().ID_SISTEMA,false), "?");
		lstObjects_mailTrace.add(new JDBCObject(mailTrace.getIdSistema(), MailTrace.model().ID_SISTEMA.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getMailTraceFieldConverter().toColumn(MailTrace.model().SUBJECT,false), "?");
		lstObjects_mailTrace.add(new JDBCObject(mailTrace.getSubject(), MailTrace.model().SUBJECT.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getMailTraceFieldConverter().toColumn(MailTrace.model().CONTENT,false), "?");
		lstObjects_mailTrace.add(new JDBCObject(mailTrace.getContent(), MailTrace.model().CONTENT.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getMailTraceFieldConverter().toColumn(MailTrace.model().RECEIVERS,false), "?");
		lstObjects_mailTrace.add(new JDBCObject(mailTrace.getReceivers(), MailTrace.model().RECEIVERS.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getMailTraceFieldConverter().toColumn(MailTrace.model().TIME,false), "?");
		lstObjects_mailTrace.add(new JDBCObject(new Timestamp(mailTrace.getTime().getTime()), MailTrace.model().TIME.getFieldType()));
		
		sqlQueryObject.addWhereCondition(this.getMailTraceFieldConverter().toColumn(MailTrace.model().TEMPLATE_CODE,true)+"=?");
		lstObjects_mailTrace.add(new JDBCObject(oldId.getTemplateId().getTemplateCode(), MailTrace.model().TEMPLATE_CODE.getFieldType()));
		sqlQueryObject.addWhereCondition(this.getMailTraceFieldConverter().toColumn(MailTrace.model().ID_AMMINISTRAZIONE,true)+"=?");
		lstObjects_mailTrace.add(new JDBCObject(oldId.getTemplateId().getUtente().getIdAmministrazione(), MailTrace.model().ID_AMMINISTRAZIONE.getFieldType()));
		sqlQueryObject.addWhereCondition(this.getMailTraceFieldConverter().toColumn(MailTrace.model().ID_SISTEMA,true)+"=?");
		lstObjects_mailTrace.add(new JDBCObject(oldId.getTemplateId().getUtente().getIdSistema(), MailTrace.model().ID_SISTEMA.getFieldType()));
		sqlQueryObject.addWhereCondition(this.getMailTraceFieldConverter().toColumn(MailTrace.model().SUBJECT,true)+"=?");
		lstObjects_mailTrace.add(new JDBCObject(oldId.getSubject(), MailTrace.model().SUBJECT.getFieldType()));
		sqlQueryObject.addWhereCondition(this.getMailTraceFieldConverter().toColumn(MailTrace.model().CONTENT,true)+"=?");
		lstObjects_mailTrace.add(new JDBCObject(oldId.getContent(), MailTrace.model().CONTENT.getFieldType()));
		sqlQueryObject.addWhereCondition(this.getMailTraceFieldConverter().toColumn(MailTrace.model().RECEIVERS,true)+"=?");
		lstObjects_mailTrace.add(new JDBCObject(oldId.getReceivers(), MailTrace.model().RECEIVERS.getFieldType()));
		sqlQueryObject.addWhereCondition(this.getMailTraceFieldConverter().toColumn(MailTrace.model().TIME,true)+"=?");
		lstObjects_mailTrace.add(new JDBCObject(new Timestamp(oldId.getTime().getTime()), MailTrace.model().TIME.getFieldType()));

		if(isUpdate_mailTrace) {
			// Update mailTrace
			jdbcUtilities.executeUpdate(sqlQueryObjectUpdate.createSQLUpdate(), jdbcProperties.isShowSql(), 
				lstObjects_mailTrace.toArray(new JDBCObject[]{}));
		}

	}	
	
	//@Override
	public void updateOrCreate(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, MailTraceId oldId, MailTrace mailTrace) throws NotImplementedException,ServiceException,Exception {
	
		if(this.exists(jdbcProperties, log, connection, sqlQueryObject, oldId)) {
			this.update(jdbcProperties, log, connection, sqlQueryObject, oldId, mailTrace);
		} else {
			this.create(jdbcProperties, log, connection, sqlQueryObject, mailTrace);
		}
		
	}
	
	//@Override
	public void delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, MailTrace mailTrace) throws NotImplementedException,ServiceException,Exception {
		
		MailTraceId id = new MailTraceId();
		MailTemplateId templateId = new MailTemplateId();
		templateId.setTemplateCode(mailTrace.getTemplateCode());
		
		IdUtente utente = new IdUtente();
		utente.setIdAmministrazione(mailTrace.getIdAmministrazione());
		utente.setIdSistema(mailTrace.getIdSistema());
		
		templateId.setUtente(utente);
		
		id.setTemplateId(templateId);
		id.setSubject(mailTrace.getSubject());
		id.setContent(mailTrace.getContent());
		id.setReceivers(mailTrace.getReceivers());
		id.setTime(mailTrace.getTime());
    	this._delete(jdbcProperties, log, connection, sqlQueryObject, id);

	}

	private void _delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Object id) throws NotImplementedException,ServiceException,Exception {
		
		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObject.newSQLQueryObject();
		
		MailTraceId mailTraceId = this.getIdFromObject(id);

		// Object mailTrace
		sqlQueryObjectDelete.setANDLogicOperator(true);
		sqlQueryObjectDelete.addDeleteTable(this.getMailTraceFieldConverter().toTable(MailTrace.model()));
		sqlQueryObjectDelete.addWhereCondition(this.getMailTraceFieldConverter().toColumn(MailTrace.model().TEMPLATE_CODE,true)+"=?");
		List<JDBCObject> lstObjects_mailTrace = new ArrayList<JDBCObject>();
		lstObjects_mailTrace .add(new JDBCObject(mailTraceId.getTemplateId().getTemplateCode(), MailTrace.model().TEMPLATE_CODE.getFieldType()));
		sqlQueryObjectDelete.addWhereCondition(this.getMailTraceFieldConverter().toColumn(MailTrace.model().ID_AMMINISTRAZIONE,true)+"=?");
		lstObjects_mailTrace.add(new JDBCObject(mailTraceId.getTemplateId().getUtente().getIdAmministrazione(), MailTrace.model().ID_AMMINISTRAZIONE.getFieldType()));
		sqlQueryObjectDelete.addWhereCondition(this.getMailTraceFieldConverter().toColumn(MailTrace.model().ID_SISTEMA,true)+"=?");
		lstObjects_mailTrace.add(new JDBCObject(mailTraceId.getTemplateId().getUtente().getIdSistema(), MailTrace.model().ID_SISTEMA.getFieldType()));
		sqlQueryObjectDelete.addWhereCondition(this.getMailTraceFieldConverter().toColumn(MailTrace.model().SUBJECT,true)+"=?");
		lstObjects_mailTrace.add(new JDBCObject(mailTraceId.getSubject(), MailTrace.model().SUBJECT.getFieldType()));
		sqlQueryObjectDelete.addWhereCondition(this.getMailTraceFieldConverter().toColumn(MailTrace.model().CONTENT,true)+"=?");
		lstObjects_mailTrace.add(new JDBCObject(mailTraceId.getContent(), MailTrace.model().CONTENT.getFieldType()));
		sqlQueryObjectDelete.addWhereCondition(this.getMailTraceFieldConverter().toColumn(MailTrace.model().RECEIVERS,true)+"=?");
		lstObjects_mailTrace.add(new JDBCObject(mailTraceId.getReceivers(), MailTrace.model().RECEIVERS.getFieldType()));
		sqlQueryObjectDelete.addWhereCondition(this.getMailTraceFieldConverter().toColumn(MailTrace.model().TIME,true)+"=?");
		lstObjects_mailTrace.add(new JDBCObject(new Timestamp(mailTraceId.getTime().getTime()), MailTrace.model().TIME.getFieldType()));


		// Delete mailTrace
		jdbcUtilities.execute(sqlQueryObjectDelete.createSQLDelete(), jdbcProperties.isShowSql(), 
				lstObjects_mailTrace.toArray(new JDBCObject[]{}));

	}

	//@Override
	public void deleteById(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, MailTraceId idMailTrace) throws NotImplementedException,ServiceException,Exception {

		Object id = null;
		try{
			id = this.findIdMailTrace(jdbcProperties, log, connection, sqlQueryObject, idMailTrace,true);
		}catch(NotFoundException notFound){
			return;
		}			
		this._delete(jdbcProperties, log, connection, sqlQueryObject, id);
		
	}
	
	//@Override
	public NonNegativeNumber deleteAll(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject) throws NotImplementedException,ServiceException,Exception {
		
		return this.deleteAll(jdbcProperties, log, connection, sqlQueryObject, new JDBCExpression(this.getMailTraceFieldConverter()));

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
