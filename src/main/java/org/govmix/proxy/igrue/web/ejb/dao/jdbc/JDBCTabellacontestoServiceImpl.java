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
import org.govmix.proxy.igrue.web.ejb.IdTabellacontesto;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.openspcoop2.generic_project.beans.NonNegativeNumber;
import org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject;
import org.openspcoop2.generic_project.exception.NotFoundException;
import org.openspcoop2.generic_project.exception.NotImplementedException;
import org.openspcoop2.generic_project.exception.ServiceException;
import org.openspcoop2.generic_project.dao.jdbc.JDBCExpression;
import org.openspcoop2.generic_project.dao.jdbc.JDBCPaginatedExpression;
import org.openspcoop2.generic_project.dao.jdbc.JDBCServiceManagerProperties;
import org.govmix.proxy.igrue.web.ejb.Tabellacontesto;
import org.govmix.proxy.igrue.web.ejb.dao.jdbc.JDBCServiceManager;


public class JDBCTabellacontestoServiceImpl extends JDBCTabellacontestoServiceSearchImpl
	implements IJDBCServiceCRUDWithId<Tabellacontesto, IdTabellacontesto, JDBCServiceManager> {

	//@Override
	public void create(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Tabellacontesto tabellacontesto) throws NotImplementedException,ServiceException,Exception {

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		


		// Object tabellacontesto
		sqlQueryObjectInsert.addInsertTable(this.getTabellacontestoFieldConverter().toTable(Tabellacontesto.model()));
		sqlQueryObjectInsert.addInsertField(this.getTabellacontestoFieldConverter().toColumn(Tabellacontesto.model().ID_AMMINISTRAZIONE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getTabellacontestoFieldConverter().toColumn(Tabellacontesto.model().ID_SISTEMA,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getTabellacontestoFieldConverter().toColumn(Tabellacontesto.model().CHECKSUM,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getTabellacontestoFieldConverter().toColumn(Tabellacontesto.model().DATAAGGIORNAMENTO,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getTabellacontestoFieldConverter().toColumn(Tabellacontesto.model().NOMETABELLA,false),"?");

		// Insert tabellacontesto
		String insertSql = sqlQueryObjectInsert.createSQLInsert();
		jdbcUtilities.execute(insertSql, jdbcProperties.isShowSql(), 
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(tabellacontesto.getIdAmministrazione(),Tabellacontesto.model().ID_AMMINISTRAZIONE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(tabellacontesto.getIdSistema(),Tabellacontesto.model().ID_SISTEMA.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(tabellacontesto.getChecksum(),Tabellacontesto.model().CHECKSUM.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(tabellacontesto.getDataaggiornamento(),Tabellacontesto.model().DATAAGGIORNAMENTO.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(tabellacontesto.getNometabella(),Tabellacontesto.model().NOMETABELLA.getFieldType())
		);

		
	}

	//@Override
	public void update(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, IdTabellacontesto oldId, Tabellacontesto tabellacontesto) throws NotFoundException, NotImplementedException, ServiceException, Exception {
		

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObjectInsert.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectGet = sqlQueryObjectDelete.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectUpdate = sqlQueryObjectGet.newSQLQueryObject();
				
		// Object tabellacontesto
		sqlQueryObjectUpdate.setANDLogicOperator(true);
		sqlQueryObjectUpdate.addUpdateTable(this.getTabellacontestoFieldConverter().toTable(Tabellacontesto.model()));
		boolean isUpdate_tabellacontesto = true;
		java.util.List<JDBCObject> lstObjects_tabellacontesto = new java.util.ArrayList<JDBCObject>();
		sqlQueryObjectUpdate.addUpdateField(this.getTabellacontestoFieldConverter().toColumn(Tabellacontesto.model().ID_AMMINISTRAZIONE,false), "?");
		lstObjects_tabellacontesto.add(new JDBCObject(tabellacontesto.getIdAmministrazione(), Tabellacontesto.model().ID_AMMINISTRAZIONE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getTabellacontestoFieldConverter().toColumn(Tabellacontesto.model().ID_SISTEMA,false), "?");
		lstObjects_tabellacontesto.add(new JDBCObject(tabellacontesto.getIdSistema(), Tabellacontesto.model().ID_SISTEMA.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getTabellacontestoFieldConverter().toColumn(Tabellacontesto.model().CHECKSUM,false), "?");
		lstObjects_tabellacontesto.add(new JDBCObject(tabellacontesto.getChecksum(), Tabellacontesto.model().CHECKSUM.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getTabellacontestoFieldConverter().toColumn(Tabellacontesto.model().DATAAGGIORNAMENTO,false), "?");
		lstObjects_tabellacontesto.add(new JDBCObject(tabellacontesto.getDataaggiornamento(), Tabellacontesto.model().DATAAGGIORNAMENTO.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getTabellacontestoFieldConverter().toColumn(Tabellacontesto.model().NOMETABELLA,false), "?");
		lstObjects_tabellacontesto.add(new JDBCObject(tabellacontesto.getNometabella(), Tabellacontesto.model().NOMETABELLA.getFieldType()));
		sqlQueryObjectUpdate.addWhereCondition(this.getTabellacontestoFieldConverter().toColumn(Tabellacontesto.model().NOMETABELLA,true)+"=?");
		lstObjects_tabellacontesto.add(new JDBCObject(oldId.getNometabella(), Tabellacontesto.model().NOMETABELLA.getFieldType()));
		sqlQueryObjectUpdate.addWhereCondition(this.getTabellacontestoFieldConverter().toColumn(Tabellacontesto.model().ID_AMMINISTRAZIONE,true)+"=?");
		lstObjects_tabellacontesto.add(new JDBCObject(oldId.getUtente().getIdAmministrazione(), Tabellacontesto.model().ID_AMMINISTRAZIONE.getFieldType()));
		sqlQueryObjectUpdate.addWhereCondition(this.getTabellacontestoFieldConverter().toColumn(Tabellacontesto.model().ID_SISTEMA,true)+"=?");
		lstObjects_tabellacontesto.add(new JDBCObject(oldId.getUtente().getIdSistema(), Tabellacontesto.model().ID_SISTEMA.getFieldType()));

		if(isUpdate_tabellacontesto) {
			// Update tabellacontesto
			jdbcUtilities.executeUpdate(sqlQueryObjectUpdate.createSQLUpdate(), jdbcProperties.isShowSql(), 
				lstObjects_tabellacontesto.toArray(new JDBCObject[]{}));
		}

	}	
	
	//@Override
	public void updateOrCreate(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, IdTabellacontesto oldId, Tabellacontesto tabellacontesto) throws NotImplementedException,ServiceException,Exception {
	
		if(this.exists(jdbcProperties, log, connection, sqlQueryObject, oldId)) {
			this.update(jdbcProperties, log, connection, sqlQueryObject, oldId, tabellacontesto);
		} else {
			this.create(jdbcProperties, log, connection, sqlQueryObject, tabellacontesto);
		}
		
	}
	
	//@Override
	public void delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Tabellacontesto tabellacontesto) throws NotImplementedException,ServiceException,Exception {
		
		IdTabellacontesto idObject = new IdTabellacontesto();
		idObject.setNometabella(tabellacontesto.getNometabella());
		IdUtente utente = new IdUtente();
		utente.setIdAmministrazione(tabellacontesto.getIdAmministrazione());
		utente.setIdSistema(tabellacontesto.getIdSistema());
		idObject.setUtente(utente);

		this._delete(jdbcProperties, log, connection, sqlQueryObject, idObject);
        
	}

	private void _delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Object objectId) throws NotImplementedException,ServiceException,Exception {
		
		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObject.newSQLQueryObject();

		IdTabellacontesto id = this.getIdFromObject(objectId);


		// Object tabellacontesto
		sqlQueryObjectDelete.setANDLogicOperator(true);
		sqlQueryObjectDelete.addDeleteTable(this.getTabellacontestoFieldConverter().toTable(Tabellacontesto.model()));
		sqlQueryObjectDelete.addWhereCondition(this.getTabellacontestoFieldConverter().toColumn(Tabellacontesto.model().NOMETABELLA,true)+"=?");
		sqlQueryObjectDelete.addWhereCondition(this.getTabellacontestoFieldConverter().toColumn(Tabellacontesto.model().ID_AMMINISTRAZIONE,true)+"=?");
		sqlQueryObjectDelete.addWhereCondition(this.getTabellacontestoFieldConverter().toColumn(Tabellacontesto.model().ID_SISTEMA,true)+"=?");

		// Delete tabellacontesto
		jdbcUtilities.execute(sqlQueryObjectDelete.createSQLDelete(), jdbcProperties.isShowSql(), 
				new JDBCObject(id.getNometabella(),id.getNometabella().getClass()),
				new JDBCObject(id.getUtente().getIdAmministrazione(),id.getUtente().getIdAmministrazione().getClass()),
				new JDBCObject(id.getUtente().getIdSistema(),id.getUtente().getIdSistema().getClass()));

	}

	//@Override
	public void deleteById(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, IdTabellacontesto idTabellacontesto) throws NotImplementedException,ServiceException,Exception {

		Object id = null;
		try{
			id = this.findIdTabellacontesto(jdbcProperties, log, connection, sqlQueryObject, idTabellacontesto,true);
		}catch(NotFoundException notFound){
			return;
		}			
		this._delete(jdbcProperties, log, connection, sqlQueryObject, id);
		
	}
	
	//@Override
	public NonNegativeNumber deleteAll(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject) throws NotImplementedException,ServiceException,Exception {
		
		return this.deleteAll(jdbcProperties, log, connection, sqlQueryObject, new JDBCExpression(this.getTabellacontestoFieldConverter()));

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
