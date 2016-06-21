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
import org.govmix.proxy.igrue.web.ejb.ProprietaEventiId;
import org.openspcoop2.generic_project.beans.NonNegativeNumber;
import org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject;
import org.openspcoop2.generic_project.exception.NotFoundException;
import org.openspcoop2.generic_project.exception.NotImplementedException;
import org.openspcoop2.generic_project.exception.ServiceException;
import org.openspcoop2.generic_project.dao.jdbc.JDBCExpression;
import org.openspcoop2.generic_project.dao.jdbc.JDBCPaginatedExpression;
import org.openspcoop2.generic_project.dao.jdbc.JDBCServiceManagerProperties;
import org.govmix.proxy.igrue.web.ejb.ProprietaEvento;
import org.govmix.proxy.igrue.web.ejb.dao.jdbc.JDBCServiceManager;


public class JDBCProprietaEventoServiceImpl extends JDBCProprietaEventoServiceSearchImpl
	implements IJDBCServiceCRUDWithId<ProprietaEvento, ProprietaEventiId, JDBCServiceManager> {

	//@Override
	public void create(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, ProprietaEvento proprietaEvento) throws NotImplementedException,ServiceException,Exception {

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		


		// Object proprietaEvento
		sqlQueryObjectInsert.addInsertTable(this.getProprietaEventoFieldConverter().toTable(ProprietaEvento.model()));
		sqlQueryObjectInsert.addInsertField(this.getProprietaEventoFieldConverter().toColumn(ProprietaEvento.model().PROPERTY_ID,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getProprietaEventoFieldConverter().toColumn(ProprietaEvento.model().PROPERTY_KEY,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getProprietaEventoFieldConverter().toColumn(ProprietaEvento.model().PROPERTY_VALUE,false),"?");

		// Insert proprietaEvento
		String insertSql = sqlQueryObjectInsert.createSQLInsert();
		jdbcUtilities.execute(insertSql, jdbcProperties.isShowSql(), 
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(proprietaEvento.getPropertyId(),ProprietaEvento.model().PROPERTY_ID.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(proprietaEvento.getPropertyKey(),ProprietaEvento.model().PROPERTY_KEY.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(proprietaEvento.getPropertyValue(),ProprietaEvento.model().PROPERTY_VALUE.getFieldType())
		);

		
	}

	//@Override
	public void update(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, ProprietaEventiId oldId, ProprietaEvento proprietaEvento) throws NotFoundException, NotImplementedException, ServiceException, Exception {
		

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObjectInsert.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectGet = sqlQueryObjectDelete.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectUpdate = sqlQueryObjectGet.newSQLQueryObject();
				
		// Object proprietaEvento
		sqlQueryObjectUpdate.setANDLogicOperator(true);
		sqlQueryObjectUpdate.addUpdateTable(this.getProprietaEventoFieldConverter().toTable(ProprietaEvento.model()));
		boolean isUpdate_proprietaEvento = true;
		java.util.List<JDBCObject> lstObjects_proprietaEvento = new java.util.ArrayList<JDBCObject>();
		sqlQueryObjectUpdate.addUpdateField(this.getProprietaEventoFieldConverter().toColumn(ProprietaEvento.model().PROPERTY_ID,false), "?");
		lstObjects_proprietaEvento.add(new JDBCObject(proprietaEvento.getPropertyId(), ProprietaEvento.model().PROPERTY_ID.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getProprietaEventoFieldConverter().toColumn(ProprietaEvento.model().PROPERTY_KEY,false), "?");
		lstObjects_proprietaEvento.add(new JDBCObject(proprietaEvento.getPropertyKey(), ProprietaEvento.model().PROPERTY_KEY.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getProprietaEventoFieldConverter().toColumn(ProprietaEvento.model().PROPERTY_VALUE,false), "?");
		lstObjects_proprietaEvento.add(new JDBCObject(proprietaEvento.getPropertyValue(), ProprietaEvento.model().PROPERTY_VALUE.getFieldType()));
		sqlQueryObjectUpdate.addWhereCondition(this.getProprietaEventoFieldConverter().toColumn(ProprietaEvento.model().PROPERTY_ID,false)+"=?");
		lstObjects_proprietaEvento.add(new JDBCObject(oldId.getPropertyId(), ProprietaEvento.model().PROPERTY_ID.getFieldType()));
		sqlQueryObjectUpdate.addWhereCondition(this.getProprietaEventoFieldConverter().toColumn(ProprietaEvento.model().PROPERTY_KEY,false)+"=?");
		lstObjects_proprietaEvento.add(new JDBCObject(oldId.getPropertyKey(), ProprietaEvento.model().PROPERTY_KEY.getFieldType()));

		if(isUpdate_proprietaEvento) {
			// Update proprietaEvento
			jdbcUtilities.executeUpdate(sqlQueryObjectUpdate.createSQLUpdate(), jdbcProperties.isShowSql(), 
				lstObjects_proprietaEvento.toArray(new JDBCObject[]{}));
		}

	}	
	
	//@Override
	public void updateOrCreate(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, ProprietaEventiId oldId, ProprietaEvento proprietaEvento) throws NotImplementedException,ServiceException,Exception {
	
		if(this.exists(jdbcProperties, log, connection, sqlQueryObject, oldId)) {
			this.update(jdbcProperties, log, connection, sqlQueryObject, oldId, proprietaEvento);
		} else {
			this.create(jdbcProperties, log, connection, sqlQueryObject, proprietaEvento);
		}
		
	}
	
	//@Override
	public void delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, ProprietaEvento proprietaEvento) throws NotImplementedException,ServiceException,Exception {
		
		ProprietaEventiId idObject = new ProprietaEventiId();
		idObject.setPropertyId(proprietaEvento.getPropertyId());
		idObject.setPropertyKey(proprietaEvento.getPropertyKey());

		this._delete(jdbcProperties, log, connection, sqlQueryObject, idObject);

	}

	private void _delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Object id) throws NotImplementedException,ServiceException,Exception {
		
		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObject.newSQLQueryObject();
		
		ProprietaEventiId idProprietaEventi = this.getIdFromObject(id);

		// Object proprietaEvento
		sqlQueryObjectDelete.setANDLogicOperator(true);
		sqlQueryObjectDelete.addDeleteTable(this.getProprietaEventoFieldConverter().toTable(ProprietaEvento.model()));
		sqlQueryObjectDelete.addWhereCondition(this.getProprietaEventoFieldConverter().toColumn(ProprietaEvento.model().PROPERTY_ID,true)+"=?");
		sqlQueryObjectDelete.addWhereCondition(this.getProprietaEventoFieldConverter().toColumn(ProprietaEvento.model().PROPERTY_KEY,true)+"=?");

		// Delete proprietaEvento
		jdbcUtilities.execute(sqlQueryObjectDelete.createSQLDelete(), jdbcProperties.isShowSql(), 
				new JDBCObject(idProprietaEventi.getPropertyId(),idProprietaEventi.getPropertyId().getClass()),
				new JDBCObject(idProprietaEventi.getPropertyKey(),idProprietaEventi.getPropertyKey().getClass()));
	}

	//@Override
	public void deleteById(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, ProprietaEventiId idProprietaEvento) throws NotImplementedException,ServiceException,Exception {

		Object id = null;
		try{
			id = this.findIdProprietaEvento(jdbcProperties, log, connection, sqlQueryObject, idProprietaEvento,true);
		}catch(NotFoundException notFound){
			return;
		}			
		this._delete(jdbcProperties, log, connection, sqlQueryObject, id);
		
	}
	
	//@Override
	public NonNegativeNumber deleteAll(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject) throws NotImplementedException,ServiceException,Exception {
		
		return this.deleteAll(jdbcProperties, log, connection, sqlQueryObject, new JDBCExpression(this.getProprietaEventoFieldConverter()));

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
