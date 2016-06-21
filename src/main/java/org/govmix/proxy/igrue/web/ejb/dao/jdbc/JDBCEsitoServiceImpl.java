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
import org.govmix.proxy.igrue.web.ejb.Esito;
import org.govmix.proxy.igrue.web.ejb.IdEsito;
import org.govmix.proxy.igrue.web.ejb.IdTrasmissione;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
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


public class JDBCEsitoServiceImpl extends JDBCEsitoServiceSearchImpl
	implements IJDBCServiceCRUDWithId<Esito, IdEsito, JDBCServiceManager> {

	//@Override
	public void create(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Esito esito) throws NotImplementedException,ServiceException,Exception {

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		


		// Object esito
		sqlQueryObjectInsert.addInsertTable(this.getEsitoFieldConverter().toTable(Esito.model()));
		sqlQueryObjectInsert.addInsertField(this.getEsitoFieldConverter().toColumn(Esito.model().FILE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEsitoFieldConverter().toColumn(Esito.model().ID_AMMINISTRAZIONE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEsitoFieldConverter().toColumn(Esito.model().ID_SISTEMA,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEsitoFieldConverter().toColumn(Esito.model().STATISTICHEELABORAZIONI,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEsitoFieldConverter().toColumn(Esito.model().STATISTICHEELABORAZIONIDESCRIZIONE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEsitoFieldConverter().toColumn(Esito.model().STATISTICHESCARTI,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEsitoFieldConverter().toColumn(Esito.model().STATISTICHESCARTIDESCRIZIONE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEsitoFieldConverter().toColumn(Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTO,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEsitoFieldConverter().toColumn(Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTODESCRIZIONE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEsitoFieldConverter().toColumn(Esito.model().LOGDEGLIERRORI,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEsitoFieldConverter().toColumn(Esito.model().LOGDEGLIERRORIDESCRIZIONE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEsitoFieldConverter().toColumn(Esito.model().LOGDEGLIERRORIRICEVUTO,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getEsitoFieldConverter().toColumn(Esito.model().DATAULTIMOCONTROLLO,false),"?");

		// Insert esito
		String insertSql = sqlQueryObjectInsert.createSQLInsert();
		jdbcUtilities.execute(insertSql, jdbcProperties.isShowSql(), 
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(esito.getFile(),Esito.model().FILE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(esito.getIdAmministrazione(),Esito.model().ID_AMMINISTRAZIONE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(esito.getIdSistema(),Esito.model().ID_SISTEMA.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(esito.getStatisticheelaborazioni(),Esito.model().STATISTICHEELABORAZIONI.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(esito.getStatisticheelaborazionidescrizione(),Esito.model().STATISTICHEELABORAZIONIDESCRIZIONE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(esito.getStatistichescarti(),Esito.model().STATISTICHESCARTI.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(esito.getStatistichescartidescrizione(),Esito.model().STATISTICHESCARTIDESCRIZIONE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(esito.getEsitoelaborazioneperanagraficadiriferimento(),Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTO.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(esito.getEsitoelaborazioneperanagraficadiriferimentodescrizione(),Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTODESCRIZIONE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(esito.getLogdeglierrori(),Esito.model().LOGDEGLIERRORI.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(esito.getLogdeglierroridescrizione(),Esito.model().LOGDEGLIERRORIDESCRIZIONE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(esito.getLogdeglierroriricevuto(),Esito.model().LOGDEGLIERRORIRICEVUTO.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(esito.getDataultimocontrollo(),Esito.model().DATAULTIMOCONTROLLO.getFieldType())
		);

		
	}

	//@Override
	public void update(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, IdEsito oldId, Esito esito) throws NotFoundException, NotImplementedException, ServiceException, Exception {
		

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObjectInsert.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectGet = sqlQueryObjectDelete.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectUpdate = sqlQueryObjectGet.newSQLQueryObject();
				
		// Object esito
		sqlQueryObjectUpdate.setANDLogicOperator(true);
		sqlQueryObjectUpdate.addUpdateTable(this.getEsitoFieldConverter().toTable(Esito.model()));
		boolean isUpdate_esito = true;
		java.util.List<JDBCObject> lstObjects_esito = new java.util.ArrayList<JDBCObject>();
		sqlQueryObjectUpdate.addUpdateField(this.getEsitoFieldConverter().toColumn(Esito.model().FILE,false), "?");
		lstObjects_esito.add(new JDBCObject(esito.getFile(), Esito.model().FILE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEsitoFieldConverter().toColumn(Esito.model().ID_AMMINISTRAZIONE,false), "?");
		lstObjects_esito.add(new JDBCObject(esito.getIdAmministrazione(), Esito.model().ID_AMMINISTRAZIONE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEsitoFieldConverter().toColumn(Esito.model().ID_SISTEMA,false), "?");
		lstObjects_esito.add(new JDBCObject(esito.getIdSistema(), Esito.model().ID_SISTEMA.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEsitoFieldConverter().toColumn(Esito.model().STATISTICHEELABORAZIONI,false), "?");
		lstObjects_esito.add(new JDBCObject(esito.getStatisticheelaborazioni(), Esito.model().STATISTICHEELABORAZIONI.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEsitoFieldConverter().toColumn(Esito.model().STATISTICHEELABORAZIONIDESCRIZIONE,false), "?");
		lstObjects_esito.add(new JDBCObject(esito.getStatisticheelaborazionidescrizione(), Esito.model().STATISTICHEELABORAZIONIDESCRIZIONE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEsitoFieldConverter().toColumn(Esito.model().STATISTICHESCARTI,false), "?");
		lstObjects_esito.add(new JDBCObject(esito.getStatistichescarti(), Esito.model().STATISTICHESCARTI.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEsitoFieldConverter().toColumn(Esito.model().STATISTICHESCARTIDESCRIZIONE,false), "?");
		lstObjects_esito.add(new JDBCObject(esito.getStatistichescartidescrizione(), Esito.model().STATISTICHESCARTIDESCRIZIONE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEsitoFieldConverter().toColumn(Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTO,false), "?");
		lstObjects_esito.add(new JDBCObject(esito.getEsitoelaborazioneperanagraficadiriferimento(), Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTO.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEsitoFieldConverter().toColumn(Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTODESCRIZIONE,false), "?");
		lstObjects_esito.add(new JDBCObject(esito.getEsitoelaborazioneperanagraficadiriferimentodescrizione(), Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTODESCRIZIONE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEsitoFieldConverter().toColumn(Esito.model().LOGDEGLIERRORI,false), "?");
		lstObjects_esito.add(new JDBCObject(esito.getLogdeglierrori(), Esito.model().LOGDEGLIERRORI.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEsitoFieldConverter().toColumn(Esito.model().LOGDEGLIERRORIDESCRIZIONE,false), "?");
		lstObjects_esito.add(new JDBCObject(esito.getLogdeglierroridescrizione(), Esito.model().LOGDEGLIERRORIDESCRIZIONE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEsitoFieldConverter().toColumn(Esito.model().LOGDEGLIERRORIRICEVUTO,false), "?");
		lstObjects_esito.add(new JDBCObject(esito.getLogdeglierroriricevuto(), Esito.model().LOGDEGLIERRORIRICEVUTO.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getEsitoFieldConverter().toColumn(Esito.model().DATAULTIMOCONTROLLO,false), "?");
		lstObjects_esito.add(new JDBCObject(esito.getDataultimocontrollo(), Esito.model().DATAULTIMOCONTROLLO.getFieldType()));

		sqlQueryObjectUpdate.addWhereCondition(this.getEsitoFieldConverter().toColumn(Esito.model().FILE,false)+"=?");
		lstObjects_esito.add(new JDBCObject(oldId.getTrasmissione().getFile(), Esito.model().FILE.getFieldType()));
		sqlQueryObjectUpdate.addWhereCondition(this.getEsitoFieldConverter().toColumn(Esito.model().ID_AMMINISTRAZIONE,false)+"=?");
		lstObjects_esito.add(new JDBCObject(oldId.getTrasmissione().getUtente().getIdAmministrazione(), Esito.model().ID_AMMINISTRAZIONE.getFieldType()));
		sqlQueryObjectUpdate.addWhereCondition(this.getEsitoFieldConverter().toColumn(Esito.model().ID_SISTEMA,false)+"=?");
		lstObjects_esito.add(new JDBCObject(oldId.getTrasmissione().getUtente().getIdSistema(), Esito.model().ID_SISTEMA.getFieldType()));

		if(isUpdate_esito) {
			// Update esito
			jdbcUtilities.executeUpdate(sqlQueryObjectUpdate.createSQLUpdate(), jdbcProperties.isShowSql(), 
				lstObjects_esito.toArray(new JDBCObject[]{}));
		}


	}	
	
	//@Override
	public void updateOrCreate(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, IdEsito oldId, Esito esito) throws NotImplementedException,ServiceException,Exception {
	
		if(this.exists(jdbcProperties, log, connection, sqlQueryObject, oldId)) {
			this.update(jdbcProperties, log, connection, sqlQueryObject, oldId, esito);
		} else {
			this.create(jdbcProperties, log, connection, sqlQueryObject, esito);
		}
		
	}
	
	//@Override
	public void delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Esito esito) throws NotImplementedException,ServiceException,Exception {
		
		IdEsito idObject = new IdEsito();

		IdTrasmissione idTrasmissione = new IdTrasmissione();

		idTrasmissione.setFile(esito.getFile());
		IdUtente utente = new IdUtente();
		utente.setIdAmministrazione(esito.getIdAmministrazione());
		utente.setIdSistema(esito.getIdSistema());
		idTrasmissione.setUtente(utente);
		
		idObject.setTrasmissione(idTrasmissione);
		

		this._delete(jdbcProperties, log, connection, sqlQueryObject, idTrasmissione);

	}

	private void _delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Object id) throws NotImplementedException,ServiceException,Exception {
		
		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObject.newSQLQueryObject();
		
		IdEsito idEsito = this.getIdFromObject(id);

		// Object esito
		sqlQueryObjectDelete.setANDLogicOperator(true);
		sqlQueryObjectDelete.addDeleteTable(this.getEsitoFieldConverter().toTable(Esito.model()));
		
		sqlQueryObjectDelete.addWhereCondition(this.getEsitoFieldConverter().toColumn(Esito.model().FILE,true)+"=?");
		sqlQueryObjectDelete.addWhereCondition(this.getEsitoFieldConverter().toColumn(Esito.model().ID_AMMINISTRAZIONE,true)+"=?");
		sqlQueryObjectDelete.addWhereCondition(this.getEsitoFieldConverter().toColumn(Esito.model().ID_SISTEMA,true)+"=?");

		// Delete esito
		jdbcUtilities.execute(sqlQueryObjectDelete.createSQLDelete(), jdbcProperties.isShowSql(), 
				new JDBCObject(idEsito.getTrasmissione().getFile(),idEsito.getTrasmissione().getFile().getClass()),
				new JDBCObject(idEsito.getTrasmissione().getUtente().getIdAmministrazione(),idEsito.getTrasmissione().getUtente().getIdAmministrazione().getClass()),
				new JDBCObject(idEsito.getTrasmissione().getUtente().getIdSistema(),idEsito.getTrasmissione().getUtente().getIdSistema().getClass()));

	}

	//@Override
	public void deleteById(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, IdEsito idEsito) throws NotImplementedException,ServiceException,Exception {

		Object id = null;
		try{
			id = this.findIdEsito(jdbcProperties, log, connection, sqlQueryObject, idEsito,true);
		}catch(NotFoundException notFound){
			return;
		}			
		this._delete(jdbcProperties, log, connection, sqlQueryObject, id);
		
	}
	
	//@Override
	public NonNegativeNumber deleteAll(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject) throws NotImplementedException,ServiceException,Exception {
		
		return this.deleteAll(jdbcProperties, log, connection, sqlQueryObject, new JDBCExpression(this.getEsitoFieldConverter()));

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
