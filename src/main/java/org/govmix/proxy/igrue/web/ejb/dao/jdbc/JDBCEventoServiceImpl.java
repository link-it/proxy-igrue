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

import org.openspcoop2.generic_project.dao.jdbc.IJDBCServiceCRUDWithoutId;
import org.openspcoop2.generic_project.beans.NonNegativeNumber;

import org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject;
import org.openspcoop2.generic_project.exception.NotFoundException;
import org.openspcoop2.generic_project.exception.NotImplementedException;
import org.openspcoop2.generic_project.exception.ServiceException;
import org.openspcoop2.generic_project.dao.jdbc.JDBCExpression;
import org.openspcoop2.generic_project.dao.jdbc.JDBCPaginatedExpression;

import org.openspcoop2.generic_project.dao.jdbc.JDBCServiceManagerProperties;

import org.govmix.proxy.igrue.web.ejb.Evento;
import org.govmix.proxy.igrue.web.ejb.dao.jdbc.JDBCServiceManager;


public class JDBCEventoServiceImpl extends JDBCEventoServiceSearchImpl
	implements IJDBCServiceCRUDWithoutId<Evento, JDBCServiceManager> {

	//@Override
	public void create(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Evento evento) throws NotImplementedException,ServiceException,Exception {

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		


		// Object evento
		sqlQueryObjectInsert.addInsertTable(this.getEventoFieldConverter().toTable(Evento.model()));
		sqlQueryObjectInsert.addInsertField(this.getEventoFieldConverter().toColumn(Evento.model().ENDDATE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEventoFieldConverter().toColumn(Evento.model().EVENTTYPE_CODE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEventoFieldConverter().toColumn(Evento.model().EVENTTYPE_DESCRIPTION,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEventoFieldConverter().toColumn(Evento.model().EVENT_ID,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEventoFieldConverter().toColumn(Evento.model().OWNER_DESCRIPTION,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEventoFieldConverter().toColumn(Evento.model().OWNER_IDAMMINISTRAZIONE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEventoFieldConverter().toColumn(Evento.model().OWNER_IDSISTEMA,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEventoFieldConverter().toColumn(Evento.model().PARAMETERID,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEventoFieldConverter().toColumn(Evento.model().REASON,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEventoFieldConverter().toColumn(Evento.model().STARTDATE,false),"?");

		// Insert evento
		String insertSql = sqlQueryObjectInsert.createSQLInsert();
		jdbcUtilities.execute(insertSql, jdbcProperties.isShowSql(), 
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(evento.getEnddate(),Evento.model().ENDDATE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(evento.getEventtypeCode(),Evento.model().EVENTTYPE_CODE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(evento.getEventtypeDescription(),Evento.model().EVENTTYPE_DESCRIPTION.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(evento.getEventId(),Evento.model().EVENT_ID.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(evento.getOwnerDescription(),Evento.model().OWNER_DESCRIPTION.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(evento.getOwnerIdamministrazione(),Evento.model().OWNER_IDAMMINISTRAZIONE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(evento.getOwnerIdsistema(),Evento.model().OWNER_IDSISTEMA.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(evento.getParameterid(),Evento.model().PARAMETERID.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(evento.getReason(),Evento.model().REASON.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(evento.getStartdate(),Evento.model().STARTDATE.getFieldType())
		);

		
	}

	//@Override
	public void update(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Evento evento) throws NotFoundException, NotImplementedException, ServiceException, Exception {
		

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObjectInsert.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectGet = sqlQueryObjectDelete.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectUpdate = sqlQueryObjectGet.newSQLQueryObject();
				

		// Object evento
		sqlQueryObjectUpdate.setANDLogicOperator(true);
		sqlQueryObjectUpdate.addUpdateTable(this.getEventoFieldConverter().toTable(Evento.model()));
		boolean isUpdate_evento = true;
		java.util.List<JDBCObject> lstObjects_evento = new java.util.ArrayList<JDBCObject>();
		sqlQueryObjectUpdate.addUpdateField(this.getEventoFieldConverter().toColumn(Evento.model().ENDDATE,false), "?");
		lstObjects_evento.add(new JDBCObject(evento.getEnddate(), Evento.model().ENDDATE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEventoFieldConverter().toColumn(Evento.model().EVENTTYPE_CODE,false), "?");
		lstObjects_evento.add(new JDBCObject(evento.getEventtypeCode(), Evento.model().EVENTTYPE_CODE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEventoFieldConverter().toColumn(Evento.model().EVENTTYPE_DESCRIPTION,false), "?");
		lstObjects_evento.add(new JDBCObject(evento.getEventtypeDescription(), Evento.model().EVENTTYPE_DESCRIPTION.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEventoFieldConverter().toColumn(Evento.model().EVENT_ID,false), "?");
		lstObjects_evento.add(new JDBCObject(evento.getEventId(), Evento.model().EVENT_ID.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEventoFieldConverter().toColumn(Evento.model().OWNER_DESCRIPTION,false), "?");
		lstObjects_evento.add(new JDBCObject(evento.getOwnerDescription(), Evento.model().OWNER_DESCRIPTION.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEventoFieldConverter().toColumn(Evento.model().OWNER_IDAMMINISTRAZIONE,false), "?");
		lstObjects_evento.add(new JDBCObject(evento.getOwnerIdamministrazione(), Evento.model().OWNER_IDAMMINISTRAZIONE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEventoFieldConverter().toColumn(Evento.model().OWNER_IDSISTEMA,false), "?");
		lstObjects_evento.add(new JDBCObject(evento.getOwnerIdsistema(), Evento.model().OWNER_IDSISTEMA.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEventoFieldConverter().toColumn(Evento.model().PARAMETERID,false), "?");
		lstObjects_evento.add(new JDBCObject(evento.getParameterid(), Evento.model().PARAMETERID.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEventoFieldConverter().toColumn(Evento.model().REASON,false), "?");
		lstObjects_evento.add(new JDBCObject(evento.getReason(), Evento.model().REASON.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEventoFieldConverter().toColumn(Evento.model().STARTDATE,false), "?");
		lstObjects_evento.add(new JDBCObject(evento.getStartdate(), Evento.model().STARTDATE.getFieldType()));
		
		sqlQueryObjectUpdate.addWhereCondition(this.getEventoFieldConverter().toColumn(Evento.model().EVENT_ID,false)+"=?");
		lstObjects_evento.add(new JDBCObject(evento.getEventId(), Evento.model().EVENT_ID.getFieldType()));

		if(isUpdate_evento) {
			// Update evento
			jdbcUtilities.executeUpdate(sqlQueryObjectUpdate.createSQLUpdate(), jdbcProperties.isShowSql(), 
				lstObjects_evento.toArray(new JDBCObject[]{}));
		}


	}	
	
	//@Override
	public void updateOrCreate(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Evento evento) throws NotImplementedException,ServiceException,Exception {
	
		Object idObject = evento.getEventId();

		if(this._exists(jdbcProperties, log, connection, sqlQueryObject, idObject)) {
			this.update(jdbcProperties, log, connection, sqlQueryObject, evento);
		} else {
			this.create(jdbcProperties, log, connection, sqlQueryObject, evento);
		}

	}
	
	//@Override
	public void delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Evento evento) throws NotImplementedException,ServiceException,Exception {
		
		Object idObject = evento.getEventId();
		this._delete(jdbcProperties, log, connection, sqlQueryObject, idObject);

	}

	private void _delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Object id) throws NotImplementedException,ServiceException,Exception {
		
		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObject.newSQLQueryObject();
		
		Integer idInt = this.getIdFromObject(id);

		// Object evento
		sqlQueryObjectDelete.setANDLogicOperator(true);
		sqlQueryObjectDelete.addDeleteTable(this.getEventoFieldConverter().toTable(Evento.model()));
		sqlQueryObjectDelete.addWhereCondition(this.getEventoFieldConverter().toColumn(Evento.model().EVENT_ID,true)+"=?");

		// Delete evento
		jdbcUtilities.execute(sqlQueryObjectDelete.createSQLDelete(), jdbcProperties.isShowSql(), 
			new JDBCObject(idInt,idInt.getClass()));
	}

	
	//@Override
	public NonNegativeNumber deleteAll(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject) throws NotImplementedException,ServiceException,Exception {
		
		return this.deleteAll(jdbcProperties, log, connection, sqlQueryObject, new JDBCExpression(this.getEventoFieldConverter()));

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
