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

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.web.ejb.IdTrasmissione;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.govmix.proxy.igrue.web.ejb.Trasmissione;
import org.openspcoop2.generic_project.beans.NonNegativeNumber;
import org.openspcoop2.generic_project.dao.jdbc.IJDBCServiceCRUDWithId;
import org.openspcoop2.generic_project.dao.jdbc.JDBCExpression;
import org.openspcoop2.generic_project.dao.jdbc.JDBCPaginatedExpression;
import org.openspcoop2.generic_project.dao.jdbc.JDBCServiceManagerProperties;
import org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject;
import org.openspcoop2.generic_project.exception.NotFoundException;
import org.openspcoop2.generic_project.exception.NotImplementedException;
import org.openspcoop2.generic_project.exception.ServiceException;
import org.openspcoop2.utils.sql.ISQLQueryObject;


public class JDBCTrasmissioneServiceImpl extends JDBCTrasmissioneServiceSearchImpl
	implements IJDBCServiceCRUDWithId<Trasmissione, IdTrasmissione, JDBCServiceManager> {

	//@Override
	public void create(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Trasmissione trasmissione) throws NotImplementedException,ServiceException,Exception {

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		


		// Object trasmissione
		sqlQueryObjectInsert.addInsertTable(this.getTrasmissioneFieldConverter().toTable(Trasmissione.model()));
		sqlQueryObjectInsert.addInsertField(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().FILE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().ID_AMMINISTRAZIONE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().ID_SISTEMA,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().DATAULTIMOINVIO,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().ESITOULTIMOINVIO,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().ESITOULTIMOINVIODESCRIZIONE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().NOTIFICATO,false),"?");

		// Insert trasmissione
		String insertSql = sqlQueryObjectInsert.createSQLInsert();
		jdbcUtilities.execute(insertSql, jdbcProperties.isShowSql(), 
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(trasmissione.getFile(),Trasmissione.model().FILE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(trasmissione.getIdAmministrazione(),Trasmissione.model().ID_AMMINISTRAZIONE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(trasmissione.getIdSistema(),Trasmissione.model().ID_SISTEMA.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(trasmissione.getDataultimoinvio(),Trasmissione.model().DATAULTIMOINVIO.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(trasmissione.getEsitoultimoinvio(),Trasmissione.model().ESITOULTIMOINVIO.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(trasmissione.getEsitoultimoinviodescrizione(),Trasmissione.model().ESITOULTIMOINVIODESCRIZIONE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(trasmissione.getNotificato(),Trasmissione.model().NOTIFICATO.getFieldType())
		);

		
	}

	//@Override
	public void update(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, IdTrasmissione oldId, Trasmissione trasmissione) throws NotFoundException, NotImplementedException, ServiceException, Exception {
		

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObjectInsert.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectGet = sqlQueryObjectDelete.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectUpdate = sqlQueryObjectGet.newSQLQueryObject();
				
		// Object trasmissione
		sqlQueryObjectUpdate.setANDLogicOperator(true);
		sqlQueryObjectUpdate.addUpdateTable(this.getTrasmissioneFieldConverter().toTable(Trasmissione.model()));
		boolean isUpdate_trasmissione = true;
		java.util.List<JDBCObject> lstObjects_trasmissione = new java.util.ArrayList<JDBCObject>();
		sqlQueryObjectUpdate.addUpdateField(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().FILE,false), "?");
		lstObjects_trasmissione.add(new JDBCObject(trasmissione.getFile(), Trasmissione.model().FILE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().ID_AMMINISTRAZIONE,false), "?");
		lstObjects_trasmissione.add(new JDBCObject(trasmissione.getIdAmministrazione(), Trasmissione.model().ID_AMMINISTRAZIONE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().ID_SISTEMA,false), "?");
		lstObjects_trasmissione.add(new JDBCObject(trasmissione.getIdSistema(), Trasmissione.model().ID_SISTEMA.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().DATAULTIMOINVIO,false), "?");
		lstObjects_trasmissione.add(new JDBCObject(trasmissione.getDataultimoinvio(), Trasmissione.model().DATAULTIMOINVIO.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().ESITOULTIMOINVIO,false), "?");
		lstObjects_trasmissione.add(new JDBCObject(trasmissione.getEsitoultimoinvio(), Trasmissione.model().ESITOULTIMOINVIO.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().ESITOULTIMOINVIODESCRIZIONE,false), "?");
		lstObjects_trasmissione.add(new JDBCObject(trasmissione.getEsitoultimoinviodescrizione(), Trasmissione.model().ESITOULTIMOINVIODESCRIZIONE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().NOTIFICATO,false), "?");
		lstObjects_trasmissione.add(new JDBCObject(trasmissione.getNotificato(), Trasmissione.model().NOTIFICATO.getFieldType()));

		sqlQueryObjectUpdate.addWhereCondition(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().FILE,false)+"=?");
		lstObjects_trasmissione.add(new JDBCObject(oldId.getFile(), Trasmissione.model().FILE.getFieldType()));
		sqlQueryObjectUpdate.addWhereCondition(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().ID_AMMINISTRAZIONE,false)+"=?");
		lstObjects_trasmissione.add(new JDBCObject(oldId.getUtente().getIdAmministrazione(), Trasmissione.model().ID_AMMINISTRAZIONE.getFieldType()));
		sqlQueryObjectUpdate.addWhereCondition(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().ID_SISTEMA,false)+"=?");
		lstObjects_trasmissione.add(new JDBCObject(oldId.getUtente().getIdSistema(), Trasmissione.model().ID_SISTEMA.getFieldType()));
		
		if(isUpdate_trasmissione) {
			// Update trasmissione
			jdbcUtilities.executeUpdate(sqlQueryObjectUpdate.createSQLUpdate(), jdbcProperties.isShowSql(), 
				lstObjects_trasmissione.toArray(new JDBCObject[]{}));
		}

	}	
	
	//@Override
	public void updateOrCreate(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, IdTrasmissione oldId, Trasmissione trasmissione) throws NotImplementedException,ServiceException,Exception {
	
		if(this.exists(jdbcProperties, log, connection, sqlQueryObject, oldId)) {
			this.update(jdbcProperties, log, connection, sqlQueryObject, oldId, trasmissione);
		} else {
			this.create(jdbcProperties, log, connection, sqlQueryObject, trasmissione);
		}
		
	}
	
	//@Override
	public void delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Trasmissione trasmissione) throws NotImplementedException,ServiceException,Exception {
		
		IdTrasmissione idObject = new IdTrasmissione();

		idObject.setFile(trasmissione.getFile());
		IdUtente utente = new IdUtente();
		utente.setIdAmministrazione(trasmissione.getIdAmministrazione());
		utente.setIdSistema(trasmissione.getIdSistema());
		idObject.setUtente(utente);
		
		this._delete(jdbcProperties, log, connection, sqlQueryObject, idObject);
        
	}

	private void _delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Object id) throws NotImplementedException,ServiceException,Exception {
		
		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObject.newSQLQueryObject();
		
		IdTrasmissione idTrasmissione = this.getIdFromObject(id); 

		// Object trasmissione
		sqlQueryObjectDelete.setANDLogicOperator(true);
		sqlQueryObjectDelete.addDeleteTable(this.getTrasmissioneFieldConverter().toTable(Trasmissione.model()));
		sqlQueryObjectDelete.addWhereCondition(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().FILE,true)+"=?");
		sqlQueryObjectDelete.addWhereCondition(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().ID_AMMINISTRAZIONE,true)+"=?");
		sqlQueryObjectDelete.addWhereCondition(this.getTrasmissioneFieldConverter().toColumn(Trasmissione.model().ID_SISTEMA,true)+"=?");


		// Delete trasmissione
		jdbcUtilities.execute(sqlQueryObjectDelete.createSQLDelete(), jdbcProperties.isShowSql(), 
				new JDBCObject(idTrasmissione.getFile(),idTrasmissione.getFile().getClass()),
				new JDBCObject(idTrasmissione.getUtente().getIdAmministrazione(),idTrasmissione.getUtente().getIdAmministrazione().getClass()),
				new JDBCObject(idTrasmissione.getUtente().getIdSistema(),idTrasmissione.getUtente().getIdSistema().getClass()));

	}

	//@Override
	public void deleteById(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, IdTrasmissione idTrasmissione) throws NotImplementedException,ServiceException,Exception {

		Object id = null;
		try{
			id = this.findIdTrasmissione(jdbcProperties, log, connection, sqlQueryObject, idTrasmissione,true);
		}catch(NotFoundException notFound){
			return;
		}			
		this._delete(jdbcProperties, log, connection, sqlQueryObject, id);
		
	}
	
	//@Override
	public NonNegativeNumber deleteAll(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject) throws NotImplementedException,ServiceException,Exception {
		
		return this.deleteAll(jdbcProperties, log, connection, sqlQueryObject, new JDBCExpression(this.getTrasmissioneFieldConverter()));

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
