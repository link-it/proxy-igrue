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

import org.govmix.proxy.igrue.web.ejb.Tipievento;
import org.govmix.proxy.igrue.web.ejb.dao.jdbc.JDBCServiceManager;


public class JDBCTipieventoServiceImpl extends JDBCTipieventoServiceSearchImpl
	implements IJDBCServiceCRUDWithoutId<Tipievento, JDBCServiceManager> {

	//@Override
	public void create(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Tipievento tipievento) throws NotImplementedException,ServiceException,Exception {

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		


		// Object tipievento
		sqlQueryObjectInsert.addInsertTable(this.getTipieventoFieldConverter().toTable(Tipievento.model()));
		sqlQueryObjectInsert.addInsertField(this.getTipieventoFieldConverter().toColumn(Tipievento.model().CODE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getTipieventoFieldConverter().toColumn(Tipievento.model().DESCRIPTION,false),"?");

		// Insert tipievento
		String insertSql = sqlQueryObjectInsert.createSQLInsert();
		jdbcUtilities.execute(insertSql, jdbcProperties.isShowSql(), 
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(tipievento.getCode(),Tipievento.model().CODE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(tipievento.getDescription(),Tipievento.model().DESCRIPTION.getFieldType())
		);

		
	}

	//@Override
	public void update(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Tipievento tipievento) throws NotFoundException, NotImplementedException, ServiceException, Exception {
		

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObjectInsert.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectGet = sqlQueryObjectDelete.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectUpdate = sqlQueryObjectGet.newSQLQueryObject();
				

		// Object tipievento
		sqlQueryObjectUpdate.setANDLogicOperator(true);
		sqlQueryObjectUpdate.addUpdateTable(this.getTipieventoFieldConverter().toTable(Tipievento.model()));
		boolean isUpdate_tipievento = true;
		java.util.List<JDBCObject> lstObjects_tipievento = new java.util.ArrayList<JDBCObject>();
		sqlQueryObjectUpdate.addUpdateField(this.getTipieventoFieldConverter().toColumn(Tipievento.model().DESCRIPTION,false), "?");
		lstObjects_tipievento.add(new JDBCObject(tipievento.getDescription(), Tipievento.model().DESCRIPTION.getFieldType()));
		sqlQueryObjectUpdate.addWhereCondition(this.getTipieventoFieldConverter().toColumn(Tipievento.model().CODE,false)+"?");
		lstObjects_tipievento.add(new JDBCObject(tipievento.getCode(), Tipievento.model().CODE.getFieldType()));

		if(isUpdate_tipievento) {
			// Update tipievento
			jdbcUtilities.executeUpdate(sqlQueryObjectUpdate.createSQLUpdate(), jdbcProperties.isShowSql(), 
				lstObjects_tipievento.toArray(new JDBCObject[]{}));
		}

	}	
	
	//@Override
	public void updateOrCreate(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Tipievento tipievento) throws NotImplementedException,ServiceException,Exception {
	
		Object idObject = tipievento.getCode();

		if(this._exists(jdbcProperties, log, connection, sqlQueryObject, idObject)) {
			this.update(jdbcProperties, log, connection, sqlQueryObject, tipievento);
		} else {
			this.create(jdbcProperties, log, connection, sqlQueryObject, tipievento);
		}

	}
	
	//@Override
	public void delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Tipievento tipievento) throws NotImplementedException,ServiceException,Exception {
		
		Object idObject = tipievento.getCode();

		this._delete(jdbcProperties, log, connection, sqlQueryObject, idObject);

	}

	private void _delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Object id) throws NotImplementedException,ServiceException,Exception {
		
		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObject.newSQLQueryObject();
		
		Integer code = this.getIdFromObject(id);

		// Object tipievento
		sqlQueryObjectDelete.setANDLogicOperator(true);
		sqlQueryObjectDelete.addDeleteTable(this.getTipieventoFieldConverter().toTable(Tipievento.model()));
		sqlQueryObjectDelete.addWhereCondition(this.getTipieventoFieldConverter().toColumn(Tipievento.model().CODE,false)+"=?");

		// Delete tipievento
		jdbcUtilities.execute(sqlQueryObjectDelete.createSQLDelete(), jdbcProperties.isShowSql(), 
			new JDBCObject(code,code.getClass()));
	}

	
	//@Override
	public NonNegativeNumber deleteAll(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject) throws NotImplementedException,ServiceException,Exception {
		
		return this.deleteAll(jdbcProperties, log, connection, sqlQueryObject, new JDBCExpression(this.getTipieventoFieldConverter()));

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
