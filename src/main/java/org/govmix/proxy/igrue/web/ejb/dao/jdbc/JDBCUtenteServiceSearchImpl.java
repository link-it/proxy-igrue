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

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.sql.Connection;

import org.apache.log4j.Logger;
import org.openspcoop2.utils.sql.ISQLQueryObject;
import org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject;
import org.openspcoop2.generic_project.dao.jdbc.IJDBCServiceSearchWithId;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.openspcoop2.generic_project.beans.InUse;
import org.openspcoop2.generic_project.beans.NonNegativeNumber;
import org.openspcoop2.generic_project.beans.IField;
import org.openspcoop2.generic_project.beans.UnionExpression;
import org.openspcoop2.generic_project.beans.Union;
import org.openspcoop2.generic_project.beans.FunctionField;
import org.openspcoop2.generic_project.exception.MultipleResultException;
import org.openspcoop2.generic_project.exception.NotFoundException;
import org.openspcoop2.generic_project.exception.NotImplementedException;
import org.openspcoop2.generic_project.exception.ServiceException;
import org.openspcoop2.generic_project.expression.IExpression;
import org.openspcoop2.generic_project.dao.jdbc.JDBCExpression;
import org.openspcoop2.generic_project.dao.jdbc.JDBCPaginatedExpression;
import org.openspcoop2.generic_project.dao.jdbc.JDBCServiceManagerProperties;
import org.govmix.proxy.igrue.web.ejb.dao.jdbc.converter.UtenteFieldConverter;
import org.govmix.proxy.igrue.web.ejb.dao.jdbc.fetch.UtenteFetch;
import org.govmix.proxy.igrue.web.ejb.dao.jdbc.JDBCServiceManager;
import org.govmix.proxy.igrue.web.ejb.Utente;


public class JDBCUtenteServiceSearchImpl implements IJDBCServiceSearchWithId<Utente, IdUtente, JDBCServiceManager> {

	private UtenteFieldConverter utenteFieldConverter = new UtenteFieldConverter();
	public UtenteFieldConverter getUtenteFieldConverter() {
		return this.utenteFieldConverter;
	}
	
	private UtenteFetch utenteFetch = new UtenteFetch();
	public UtenteFetch getUtenteFetch() {
		return this.utenteFetch;
	}
	
	
	private JDBCServiceManager jdbcServiceManager = null;

	//@Override
	public void setServiceManager(JDBCServiceManager serviceManager) throws ServiceException{
		this.jdbcServiceManager = serviceManager;
	}
	
	//@Override
	public JDBCServiceManager getServiceManager() throws ServiceException{
		return this.jdbcServiceManager;
	}
	

	//@Override
	public Utente get(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, IdUtente id) throws NotFoundException, MultipleResultException, NotImplementedException, ServiceException,Exception {
		Object id_utente = this.findIdUtente(jdbcProperties, log, connection, sqlQueryObject, id, true);
		return this._get(jdbcProperties, log, connection, sqlQueryObject, id_utente);
		
		
	}
	
	//@Override
	public boolean exists(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, IdUtente id) throws MultipleResultException, NotImplementedException, ServiceException,Exception {

		Object id_utente = this.findIdUtente(jdbcProperties, log, connection, sqlQueryObject, id, false);
		return id_utente != null;	
		
	}
	
	//@Override
	public List<IdUtente> findAllIds(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, JDBCPaginatedExpression expression) throws NotImplementedException, ServiceException,Exception {

		List<IdUtente> list = new ArrayList<IdUtente>();

		List<Object> ids = this._findAllObjectIds(jdbcProperties, log, connection, sqlQueryObject, expression);
        
        for(Object id: ids) {
        	IdUtente idUtente = (IdUtente) id;
        	list.add(idUtente);
        }

        return list;
		
	}
	
	//@Override
	public List<Utente> findAll(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, JDBCPaginatedExpression expression) throws NotImplementedException, ServiceException,Exception {

        List<Utente> list = new ArrayList<Utente>();

		List<Object> ids = this._findAllObjectIds(jdbcProperties, log, connection, sqlQueryObject, expression);
        
        for(Object id: ids) {
        	list.add(this._get(jdbcProperties, log, connection, sqlQueryObject, id));
        }

        return list;      
		
	}
	
	//@Override
	public Utente find(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, JDBCExpression expression) 
		throws NotFoundException, MultipleResultException, NotImplementedException, ServiceException,Exception {

        Object id = this._findObjectId(jdbcProperties, log, connection, sqlQueryObject, expression);
        if(id!=null){
        	return this._get(jdbcProperties, log, connection, sqlQueryObject, id);
        }else{
        	throw new NotFoundException("Entry with id["+id+"] not found");
        }
		
	}
	
	//@Override
	public NonNegativeNumber count(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, JDBCExpression expression) throws NotImplementedException, ServiceException,Exception {
		
		List<Object> listaQuery = org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.prepareCount(jdbcProperties, log, connection, sqlQueryObject, expression,
												this.getUtenteFieldConverter(), Utente.model());
		
		sqlQueryObject.addSelectCountField("tot");
		
		_join(expression,sqlQueryObject);
		
		return org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.count(jdbcProperties, log, connection, sqlQueryObject, expression,
																			this.getUtenteFieldConverter(), Utente.model(),listaQuery);
	}

	//@Override
	public InUse inUse(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, IdUtente id) throws NotFoundException, NotImplementedException, ServiceException,Exception {
		
		Object id_utente = this.findIdUtente(jdbcProperties, log, connection, sqlQueryObject, id, true);
        return this._inUse(jdbcProperties, log, connection, sqlQueryObject, id_utente);
		
	}

	//@Override
	public List<Object> select(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, 
													JDBCPaginatedExpression paginatedExpression, IField field) throws ServiceException,NotFoundException,NotImplementedException,Exception {
		List<Map<String,Object>> map = 
			this.select(jdbcProperties, log, connection, sqlQueryObject, paginatedExpression, new IField[]{field});
		return org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.selectSingleObject(map);
	}
	
	//@Override
	public List<Map<String,Object>> select(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, 
													JDBCPaginatedExpression paginatedExpression, IField ... field) throws ServiceException,NotFoundException,NotImplementedException,Exception {
		
		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.setFields(sqlQueryObject,paginatedExpression,field);
		try{
			return _select(jdbcProperties, log, connection, sqlQueryObject, paginatedExpression);
		}finally{
			org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.removeFields(sqlQueryObject,paginatedExpression,field);
		}
	}

	//@Override
	public Object aggregate(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, 
													JDBCExpression expression, FunctionField functionField) throws ServiceException,NotFoundException,NotImplementedException,Exception {
		Map<String,Object> map = 
			this.aggregate(jdbcProperties, log, connection, sqlQueryObject, expression, new FunctionField[]{functionField});
		return org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.selectAggregateObject(map,functionField);
	}
	
	//@Override
	public Map<String,Object> aggregate(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, 
													JDBCExpression expression, FunctionField ... functionField) throws ServiceException,NotFoundException,NotImplementedException,Exception {													
		
		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.setFields(sqlQueryObject,expression,functionField);
		try{
			List<Map<String,Object>> list = _select(jdbcProperties, log, connection, sqlQueryObject, expression);
			return list.get(0);
		}finally{
			org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.removeFields(sqlQueryObject,expression,functionField);
		}
	}

	//@Override
	public List<Map<String,Object>> groupBy(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, 
													JDBCExpression expression, FunctionField ... functionField) throws ServiceException,NotFoundException,NotImplementedException,Exception {
		
		if(expression.getGroupByFields().size()<=0){
			throw new ServiceException("GroupBy conditions not found in expression");
		}
		
		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.setFields(sqlQueryObject,expression,functionField);
		try{
			return _select(jdbcProperties, log, connection, sqlQueryObject, expression);
		}finally{
			org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.removeFields(sqlQueryObject,expression,functionField);
		}
	}
	

	//@Override
	public List<Map<String,Object>> groupBy(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, 
													JDBCPaginatedExpression paginatedExpression, FunctionField ... functionField) throws ServiceException,NotFoundException,NotImplementedException,Exception {
		
		if(paginatedExpression.getGroupByFields().size()<=0){
			throw new ServiceException("GroupBy conditions not found in expression");
		}
		
		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.setFields(sqlQueryObject,paginatedExpression,functionField);
		try{
			return _select(jdbcProperties, log, connection, sqlQueryObject, paginatedExpression);
		}finally{
			org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.removeFields(sqlQueryObject,paginatedExpression,functionField);
		}
	}
	
	protected List<Map<String,Object>> _select(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, 
												IExpression expression) throws ServiceException,NotFoundException,NotImplementedException,Exception {
		
		List<Object> listaQuery = new ArrayList<Object>();
		List<JDBCObject> listaParams = new ArrayList<JDBCObject>();
		List<Object> returnField = org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.prepareSelect(jdbcProperties, log, connection, sqlQueryObject, 
        						expression, this.getUtenteFieldConverter(), Utente.model(), 
        						listaQuery,listaParams);
		
		_join(expression,sqlQueryObject);
        
        List<Map<String,Object>> list = org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.select(jdbcProperties, log, connection, sqlQueryObject, 
        								expression, this.getUtenteFieldConverter(), Utente.model(),
        								listaQuery,listaParams,returnField);
		if(list!=null && list.size()>0){
			return list;
		}
		else{
			throw new NotFoundException("Not Found");
		}
	}
		
	//@Override
	public List<Map<String,Object>> union(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, 
												Union union, UnionExpression ... unionExpression) throws ServiceException,NotFoundException,NotImplementedException,Exception {		
		
		List<ISQLQueryObject> sqlQueryObjectInnerList = new ArrayList<ISQLQueryObject>();
		List<JDBCObject> jdbcObjects = new ArrayList<JDBCObject>();
		List<Class<?>> returnClassTypes = org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.prepareUnion(jdbcProperties, log, connection, sqlQueryObject, 
        						this.getUtenteFieldConverter(), Utente.model(), 
        						sqlQueryObjectInnerList, jdbcObjects, union, unionExpression);
		
		if(unionExpression!=null){
			for (int i = 0; i < unionExpression.length; i++) {
				UnionExpression ue = unionExpression[i];
				IExpression expression = ue.getExpression();
				_join(expression,sqlQueryObjectInnerList.get(i));
			}
		}
        
        List<Map<String,Object>> list = org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.union(jdbcProperties, log, connection, sqlQueryObject, 
        								this.getUtenteFieldConverter(), Utente.model(), 
        								sqlQueryObjectInnerList, jdbcObjects, returnClassTypes, union, unionExpression);
        if(list!=null && list.size()>0){
			return list;
		}
		else{
			throw new NotFoundException("Not Found");
		}								
	}
	
	//@Override
	public NonNegativeNumber unionCount(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, 
												Union union, UnionExpression ... unionExpression) throws ServiceException,NotFoundException,NotImplementedException,Exception {		
		
		List<ISQLQueryObject> sqlQueryObjectInnerList = new ArrayList<ISQLQueryObject>();
		List<JDBCObject> jdbcObjects = new ArrayList<JDBCObject>();
		List<Class<?>> returnClassTypes = org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.prepareUnionCount(jdbcProperties, log, connection, sqlQueryObject, 
        						this.getUtenteFieldConverter(), Utente.model(), 
        						sqlQueryObjectInnerList, jdbcObjects, union, unionExpression);
		
		if(unionExpression!=null){
			for (int i = 0; i < unionExpression.length; i++) {
				UnionExpression ue = unionExpression[i];
				IExpression expression = ue.getExpression();
				_join(expression,sqlQueryObjectInnerList.get(i));
			}
		}
        
        NonNegativeNumber number = org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.unionCount(jdbcProperties, log, connection, sqlQueryObject, 
        								this.getUtenteFieldConverter(), Utente.model(), 
        								sqlQueryObjectInnerList, jdbcObjects, returnClassTypes, union, unionExpression);
        if(number!=null && number.longValue()>=0){
			return number;
		}
		else{
			throw new NotFoundException("Not Found");
		}
	}



	// -- ConstructorExpression	

	//@Override
	public JDBCExpression newExpression(Logger log) throws NotImplementedException, ServiceException {
		try{
			return new JDBCExpression(this.utenteFieldConverter);
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}


	//@Override
	public JDBCPaginatedExpression newPaginatedExpression(Logger log) throws NotImplementedException, ServiceException {
		try{
			return new JDBCPaginatedExpression(this.utenteFieldConverter);
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}
	
	//@Override
	public JDBCExpression toExpression(JDBCPaginatedExpression paginatedExpression, Logger log) throws NotImplementedException, ServiceException {
		try{
			return new JDBCExpression(paginatedExpression);
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}

	//@Override
	public JDBCPaginatedExpression toPaginatedExpression(JDBCExpression expression, Logger log) throws NotImplementedException, ServiceException {
		try{
			return new JDBCPaginatedExpression(expression);
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}
	
	
	
	// -- DB
	
	//@Override
	public Utente get(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, long tableId) throws NotFoundException, MultipleResultException, NotImplementedException, ServiceException, Exception {
		throw new NotImplementedException("Table without long id column PK");
	}
	
	protected Utente _get(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Object objectId) throws NotFoundException, MultipleResultException, NotImplementedException, ServiceException, Exception {
	
		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
					new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
				
		ISQLQueryObject sqlQueryObjectGet = sqlQueryObject.newSQLQueryObject();
				
		Utente utente = new Utente();
		

		IdUtente id = this.getIdFromObject(objectId);
		// Object utente
		ISQLQueryObject sqlQueryObjectGet_utente = sqlQueryObjectGet.newSQLQueryObject();
		sqlQueryObjectGet_utente.setANDLogicOperator(true);
		sqlQueryObjectGet_utente.addFromTable(this.getUtenteFieldConverter().toTable(Utente.model()));
		sqlQueryObjectGet_utente.addSelectField(this.getUtenteFieldConverter().toColumn(Utente.model().ID_AMMINISTRAZIONE,true));
		sqlQueryObjectGet_utente.addSelectField(this.getUtenteFieldConverter().toColumn(Utente.model().ID_SISTEMA,true));
		sqlQueryObjectGet_utente.addSelectField(this.getUtenteFieldConverter().toColumn(Utente.model().PASSWORD,true));
		sqlQueryObjectGet_utente.addSelectField(this.getUtenteFieldConverter().toColumn(Utente.model().SIL,true));
		sqlQueryObjectGet_utente.addSelectField(this.getUtenteFieldConverter().toColumn(Utente.model().MEF_PASSWORD,true));

		sqlQueryObjectGet_utente.addWhereCondition(this.getUtenteFieldConverter().toColumn(Utente.model().ID_AMMINISTRAZIONE,true)+"=?");
		sqlQueryObjectGet_utente.addWhereCondition(this.getUtenteFieldConverter().toColumn(Utente.model().ID_SISTEMA,true)+"=?");
		sqlQueryObjectGet_utente.addWhereCondition("abilitato=?");

		// Get utente
		utente = (Utente) jdbcUtilities.executeQuerySingleResult(sqlQueryObjectGet_utente.createSQLQuery(), jdbcProperties.isShowSql(), Utente.model(), this.getUtenteFetch(),
			new JDBCObject(id.getIdAmministrazione(),id.getIdAmministrazione().getClass()),
			new JDBCObject(id.getIdSistema(),id.getIdSistema().getClass()),
			new JDBCObject(true,Boolean.class));

        return utente;  
	
	} 
	
	protected IdUtente getIdFromObject(Object objectId) throws ServiceException {
		if(!(objectId instanceof IdUtente)) throw new ServiceException("ID dovrebbe essere un IdUtente");
		return (IdUtente)objectId;
	}

	//@Override
	public boolean exists(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, long tableId) throws MultipleResultException, NotImplementedException, ServiceException, Exception {
		throw new NotImplementedException("Table without long id column PK");
	}
	
	protected boolean _exists(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Object objectId) throws MultipleResultException, NotImplementedException, ServiceException, Exception {
	
		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
					new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
				
		boolean existsUtente = false;
		IdUtente id = this.getIdFromObject(objectId);

		sqlQueryObject = sqlQueryObject.newSQLQueryObject();

		sqlQueryObject.setANDLogicOperator(true);
		sqlQueryObject.addFromTable(this.getUtenteFieldConverter().toTable(Utente.model()));
		sqlQueryObject.addSelectField(this.getUtenteFieldConverter().toColumn(Utente.model().ID_AMMINISTRAZIONE,true));
		
		sqlQueryObject.addWhereCondition(this.getUtenteFieldConverter().toColumn(Utente.model().ID_AMMINISTRAZIONE,true)+"=?");
		sqlQueryObject.addWhereCondition(this.getUtenteFieldConverter().toColumn(Utente.model().ID_SISTEMA,true)+"=?");
		sqlQueryObject.addWhereCondition("abilitato=true");

		// Exists utente
		existsUtente = jdbcUtilities.exists(sqlQueryObject.createSQLQuery(), jdbcProperties.isShowSql(),
				new JDBCObject(id.getIdAmministrazione(),id.getIdAmministrazione().getClass()),
				new JDBCObject(id.getIdSistema(),id.getIdSistema().getClass()));
		
        return existsUtente;
	
	}
	
	private void _join(IExpression expression, ISQLQueryObject sqlQueryObject) throws NotImplementedException, ServiceException, Exception{
	
	}
	
	//@Override
	public List<Long> findAllTableIds(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, JDBCPaginatedExpression paginatedExpression) throws ServiceException, NotImplementedException, Exception {
		
		throw new NotImplementedException("Table without long id column PK");
		
	}
	public List<Object> _findAllObjectIds(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, JDBCPaginatedExpression paginatedExpression) throws ServiceException, NotImplementedException, Exception {
		
		sqlQueryObject.setANDLogicOperator(true);
		sqlQueryObject.addSelectField(this.getUtenteFieldConverter().toColumn(Utente.model().ID_AMMINISTRAZIONE,true));
		sqlQueryObject.addSelectField(this.getUtenteFieldConverter().toColumn(Utente.model().ID_SISTEMA,true));
		sqlQueryObject.addWhereCondition("abilitato=true");
		List<Class<?>> objectIdClass = new ArrayList<Class<?>>();
		objectIdClass.add(Utente.model().ID_AMMINISTRAZIONE.getFieldType());
		objectIdClass.add(Utente.model().ID_SISTEMA.getFieldType());
		List<Object> listaQuery = org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.prepareFindAll(jdbcProperties, log, connection, sqlQueryObject, paginatedExpression,
												this.getUtenteFieldConverter(), Utente.model());
		
		_join(paginatedExpression,sqlQueryObject);
		
		List<List<Object>> listObjects = org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.findAll(jdbcProperties, log, connection, sqlQueryObject, paginatedExpression,
																			this.getUtenteFieldConverter(), Utente.model(), objectIdClass, listaQuery);
		List<Object> lstObjects = new ArrayList<Object>(); 
		for(List<Object> object: listObjects) {
			
			IdUtente id = new IdUtente();
			id.setIdAmministrazione((String)object.get(0));
			id.setIdSistema((Integer)object.get(1));
			lstObjects.add(id);
		}
		
        return lstObjects;
		
	}
	
	//@Override
	public long findTableId(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, JDBCExpression expression) throws ServiceException, NotFoundException, MultipleResultException, NotImplementedException, Exception {
	
		throw new NotImplementedException("Table without long id column PK");
		
	}
	
	public Object _findObjectId(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, JDBCExpression expression) throws ServiceException, NotFoundException, MultipleResultException, NotImplementedException, Exception {
		
		sqlQueryObject.setANDLogicOperator(true);
		sqlQueryObject.addSelectField(this.getUtenteFieldConverter().toColumn(Utente.model().ID_AMMINISTRAZIONE,true));
		sqlQueryObject.addSelectField(this.getUtenteFieldConverter().toColumn(Utente.model().ID_SISTEMA,true));
		sqlQueryObject.addWhereCondition("abilitato=true");
		List<Class<?>> objectIdClass = new ArrayList<Class<?>>();
		objectIdClass.add(Utente.model().ID_AMMINISTRAZIONE.getFieldType());
		objectIdClass.add(Utente.model().ID_SISTEMA.getFieldType());

		List<Object> listaQuery = org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.prepareFind(jdbcProperties, log, connection, sqlQueryObject, expression,
												this.getUtenteFieldConverter(), Utente.model());
		
		_join(expression,sqlQueryObject);

		List<Object> res = org.openspcoop2.generic_project.dao.jdbc.utils.JDBCUtilities.find(jdbcProperties, log, connection, sqlQueryObject, expression,
														this.getUtenteFieldConverter(), Utente.model(), objectIdClass, listaQuery);
		if(res!=null && res.size() == 2){
			IdUtente id = new IdUtente();
			id.setIdAmministrazione((String)res.get(0));
			id.setIdSistema((Integer)res.get(1));
			return id;
		}
		else{
			throw new NotFoundException("Not Found");
		}
		
	}

	//@Override
	public InUse inUse(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, long tableId) throws ServiceException, NotFoundException, NotImplementedException, Exception {
		throw new NotImplementedException("Table without long id column PK");
	}

	protected InUse _inUse(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Object objectId) throws ServiceException, NotFoundException, NotImplementedException, Exception {

		InUse inUse = new InUse();
		inUse.setInUse(false);
		
		/* 
         * TODO: implement code that checks whether the object identified by the id parameter is used by other objects
        */

        // Delete this line when you have implemented the method
        int throwNotImplemented = 1;
        if(throwNotImplemented==1){
                throw new NotImplementedException("NotImplemented");
        }
        // Delete this line when you have implemented the method

        return inUse;

	}
	
	protected Object findIdUtente(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, IdUtente id, boolean throwNotFound) throws NotFoundException, ServiceException, NotImplementedException, Exception {

		if(!_exists(jdbcProperties, log, connection, sqlQueryObject, id)) {
			if(throwNotFound) {
				throw new NotFoundException("Not Found");
			} else {
				return null;
			}
		}

		return id;
	}
}
