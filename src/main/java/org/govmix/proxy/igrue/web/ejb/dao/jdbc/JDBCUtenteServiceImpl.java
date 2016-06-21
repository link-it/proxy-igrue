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
import org.openspcoop2.generic_project.beans.NonNegativeNumber;

import org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject;
import org.openspcoop2.generic_project.exception.NotFoundException;
import org.openspcoop2.generic_project.exception.NotImplementedException;
import org.openspcoop2.generic_project.exception.ServiceException;
import org.openspcoop2.generic_project.dao.jdbc.JDBCExpression;
import org.openspcoop2.generic_project.dao.jdbc.JDBCPaginatedExpression;

import org.openspcoop2.generic_project.dao.jdbc.JDBCServiceManagerProperties;

import org.govmix.proxy.igrue.web.ejb.Utente;
import org.govmix.proxy.igrue.web.ejb.dao.jdbc.JDBCServiceManager;


public class JDBCUtenteServiceImpl extends JDBCUtenteServiceSearchImpl
	implements IJDBCServiceCRUDWithId<Utente, IdUtente, JDBCServiceManager> {

	//@Override
	public void create(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Utente utente) throws NotImplementedException,ServiceException,Exception {

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		


		// Object utente
		sqlQueryObjectInsert.addInsertTable(this.getUtenteFieldConverter().toTable(Utente.model()));
		sqlQueryObjectInsert.addInsertField(this.getUtenteFieldConverter().toColumn(Utente.model().ID_AMMINISTRAZIONE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getUtenteFieldConverter().toColumn(Utente.model().ID_SISTEMA,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getUtenteFieldConverter().toColumn(Utente.model().PASSWORD,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getUtenteFieldConverter().toColumn(Utente.model().SIL,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getUtenteFieldConverter().toColumn(Utente.model().MEF_PASSWORD,false),"?");

		// Insert utente
		String insertSql = sqlQueryObjectInsert.createSQLInsert();
		jdbcUtilities.execute(insertSql, jdbcProperties.isShowSql(), 
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(utente.getIdAmministrazione(),Utente.model().ID_AMMINISTRAZIONE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(utente.getIdSistema(),Utente.model().ID_SISTEMA.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(utente.getPassword(),Utente.model().PASSWORD.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(utente.getSil(),Utente.model().SIL.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(utente.getMefPassword(),Utente.model().MEF_PASSWORD.getFieldType())
		);

		
	}

	//@Override
	public void update(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, IdUtente oldId, Utente utente) throws NotFoundException, NotImplementedException, ServiceException, Exception {
		

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObjectInsert.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectGet = sqlQueryObjectDelete.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectUpdate = sqlQueryObjectGet.newSQLQueryObject();
				
		// Object utente
		sqlQueryObjectUpdate.setANDLogicOperator(true);
		sqlQueryObjectUpdate.addUpdateTable(this.getUtenteFieldConverter().toTable(Utente.model()));
		boolean isUpdate_utente = true;
		java.util.List<JDBCObject> lstObjects_utente = new java.util.ArrayList<JDBCObject>();
		sqlQueryObjectUpdate.addUpdateField(this.getUtenteFieldConverter().toColumn(Utente.model().ID_AMMINISTRAZIONE,false), "?");
		lstObjects_utente.add(new JDBCObject(utente.getIdAmministrazione(), Utente.model().ID_AMMINISTRAZIONE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getUtenteFieldConverter().toColumn(Utente.model().ID_SISTEMA,false), "?");
		lstObjects_utente.add(new JDBCObject(utente.getIdSistema(), Utente.model().ID_SISTEMA.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getUtenteFieldConverter().toColumn(Utente.model().PASSWORD,false), "?");
		lstObjects_utente.add(new JDBCObject(utente.getPassword(), Utente.model().PASSWORD.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getUtenteFieldConverter().toColumn(Utente.model().SIL,false), "?");
		lstObjects_utente.add(new JDBCObject(utente.getSil(), Utente.model().SIL.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getUtenteFieldConverter().toColumn(Utente.model().MEF_PASSWORD,false), "?");
		lstObjects_utente.add(new JDBCObject(utente.getMefPassword(), Utente.model().MEF_PASSWORD.getFieldType()));

		sqlQueryObjectUpdate.addWhereCondition(this.getUtenteFieldConverter().toColumn(Utente.model().ID_AMMINISTRAZIONE,false)+"=?");
		lstObjects_utente.add(new JDBCObject(oldId.getIdAmministrazione(), oldId.getIdAmministrazione().getClass()));
		sqlQueryObjectUpdate.addWhereCondition(this.getUtenteFieldConverter().toColumn(Utente.model().ID_SISTEMA,false)+"=?");
		lstObjects_utente.add(new JDBCObject(oldId.getIdSistema(), oldId.getIdSistema().getClass()));

		if(isUpdate_utente) {
			// Update utente
			jdbcUtilities.executeUpdate(sqlQueryObjectUpdate.createSQLUpdate(), jdbcProperties.isShowSql(), 
				lstObjects_utente.toArray(new JDBCObject[]{}));
		}
	}	
	
	//@Override
	public void updateOrCreate(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, IdUtente oldId, Utente utente) throws NotImplementedException,ServiceException,Exception {
	
		if(this.exists(jdbcProperties, log, connection, sqlQueryObject, oldId)) {
			this.update(jdbcProperties, log, connection, sqlQueryObject, oldId, utente);
		} else {
			this.create(jdbcProperties, log, connection, sqlQueryObject, utente);
		}
		
	}
	
	//@Override
	public void delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Utente utente) throws NotImplementedException,ServiceException,Exception {
		
		IdUtente idObject = new IdUtente();
		idObject.setIdAmministrazione(utente.getIdAmministrazione());
		idObject.setIdSistema(utente.getIdSistema());

		this._delete(jdbcProperties, log, connection, sqlQueryObject, idObject);

	}

	private void _delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Object id) throws NotImplementedException,ServiceException,Exception {
		
		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObject.newSQLQueryObject();
		
		IdUtente idUtente = this.getIdFromObject(id);

		// Object utente
		sqlQueryObjectDelete.setANDLogicOperator(true);
		sqlQueryObjectDelete.addDeleteTable(this.getUtenteFieldConverter().toTable(Utente.model()));
		sqlQueryObjectDelete.addWhereCondition(this.getUtenteFieldConverter().toColumn(Utente.model().ID_AMMINISTRAZIONE,false)+"=?");
		sqlQueryObjectDelete.addWhereCondition(this.getUtenteFieldConverter().toColumn(Utente.model().ID_SISTEMA,false)+"=?");

		jdbcUtilities.execute(sqlQueryObjectDelete.createSQLDelete(), jdbcProperties.isShowSql(), 
				new JDBCObject(idUtente.getIdAmministrazione(), idUtente.getIdAmministrazione().getClass()),
				new JDBCObject(idUtente.getIdSistema(), idUtente.getIdSistema().getClass()));

	}

	//@Override
	public void deleteById(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, IdUtente idUtente) throws NotImplementedException,ServiceException,Exception {

		Object id = null;
		try{
			id = this.findIdUtente(jdbcProperties, log, connection, sqlQueryObject, idUtente,true);
		}catch(NotFoundException notFound){
			return;
		}			
		this._delete(jdbcProperties, log, connection, sqlQueryObject, id);
		
	}
	
	//@Override
	public NonNegativeNumber deleteAll(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject) throws NotImplementedException,ServiceException,Exception {
		
		return this.deleteAll(jdbcProperties, log, connection, sqlQueryObject, new JDBCExpression(this.getUtenteFieldConverter()));

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
