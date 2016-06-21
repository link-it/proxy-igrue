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

import org.openspcoop2.generic_project.dao.jdbc.IJDBCServiceCRUDWithId;
import org.govmix.proxy.igrue.web.ejb.ProprietaEventiId;

import org.openspcoop2.generic_project.beans.NonNegativeNumber;
import org.openspcoop2.generic_project.dao.jdbc.JDBCProperties;
import org.openspcoop2.generic_project.exception.NotFoundException;
import org.openspcoop2.generic_project.exception.NotImplementedException;
import org.openspcoop2.generic_project.exception.ServiceException;
import org.openspcoop2.generic_project.expression.IExpression;
import org.openspcoop2.generic_project.dao.jdbc.JDBCExpression;

import org.govmix.proxy.igrue.web.ejb.dao.jdbc.JDBCServiceManager;
import org.govmix.proxy.igrue.web.ejb.ProprietaEvento;
import org.govmix.proxy.igrue.web.ejb.dao.IProprietaEventoService;
import org.govmix.proxy.igrue.web.ejb.utils.ProjectInfo;

import java.sql.Connection;

import org.openspcoop2.utils.sql.ISQLQueryObject;
import org.openspcoop2.utils.sql.SQLObjectFactory;

/**     
 * Service can be used to search for and manage the backend objects of type {@link org.govmix.proxy.igrue.web.ejb.ProprietaEvento} 
 *
 * @author Giovanni Bussu (bussu@link.it)
 * @author $Author$
 * @version $Rev$, $Date$
 */

public class JDBCProprietaEventoService extends JDBCProprietaEventoServiceSearch  implements IProprietaEventoService {


	private IJDBCServiceCRUDWithId<ProprietaEvento, ProprietaEventiId, JDBCServiceManager> serviceCRUD = null;
	public JDBCProprietaEventoService(JDBCServiceManager jdbcServiceManager) throws ServiceException {
		super(jdbcServiceManager);
		this.log.debug(JDBCProprietaEventoService.class.getName()+ " initialized");
		this.serviceCRUD = JDBCProperties.getInstance(ProjectInfo.getInstance()).getServiceCRUD("proprietaEvento");
		this.serviceCRUD.setServiceManager(new JDBCLimitedServiceManager(this.jdbcServiceManager));
	}

	
	
	//@Override
	public void create(ProprietaEvento proprietaEvento) throws ServiceException, NotImplementedException {
		
		Connection connection = null;
		boolean oldValueAutoCommit = false;
		boolean rollback = false;
		try{
			
			// check parameters
			if(proprietaEvento==null){
				throw new Exception("Parameter (type:"+ProprietaEvento.class.getName()+") 'proprietaEvento' is null");
			}

			// ISQLQueryObject
			ISQLQueryObject sqlQueryObject = SQLObjectFactory.createSQLQueryObject(this.jdbcProperties.getDatabase());
			sqlQueryObject.setANDLogicOperator(true);
			// Connection sql
			connection = this.jdbcServiceManager.getConnection();
	
			// transaction
			if(this.jdbcProperties.isAutomaticTransactionManagement()){
				oldValueAutoCommit = connection.getAutoCommit();
				connection.setAutoCommit(false);
			}
		
			this.serviceCRUD.create(this.jdbcProperties,this.log,connection,sqlQueryObject,proprietaEvento);			

		}catch(ServiceException e){
			rollback = true;
			this.log.error(e,e); throw e;
		}catch(NotImplementedException e){
			rollback = true;
			this.log.error(e,e); throw e;
		}catch(Exception e){
			rollback = true;
			this.log.error(e,e); throw new ServiceException("Create not completed: "+e.getMessage(),e);
		}finally{
			if(this.jdbcProperties.isAutomaticTransactionManagement()){
				if(rollback){
					try{
						if(connection!=null)
							connection.rollback();
					}catch(Exception eIgnore){}
				}else{
					try{
						if(connection!=null)
							connection.commit();
					}catch(Exception eIgnore){}
				}
				try{
					if(connection!=null)
						connection.setAutoCommit(oldValueAutoCommit);
				}catch(Exception eIgnore){}
			}
			if(connection!=null){
				this.jdbcServiceManager.closeConnection(connection);
			}
		}

	}
	
	//@Override
	public void update(ProprietaEventiId oldId, ProprietaEvento proprietaEvento) throws ServiceException, NotFoundException, NotImplementedException {
	
		Connection connection = null;
		boolean oldValueAutoCommit = false;
		boolean rollback = false;
		try{
			
			// check parameters
			if(proprietaEvento==null){
				throw new Exception("Parameter (type:"+ProprietaEvento.class.getName()+") 'proprietaEvento' is null");
			}
			if(oldId==null){
				throw new Exception("Parameter (type:"+ProprietaEventiId.class.getName()+") 'oldId' is null");
			}

			// ISQLQueryObject
			ISQLQueryObject sqlQueryObject = SQLObjectFactory.createSQLQueryObject(this.jdbcProperties.getDatabase());
			sqlQueryObject.setANDLogicOperator(true);
			// Connection sql
			connection = this.jdbcServiceManager.getConnection();
		
			// transaction
			if(this.jdbcProperties.isAutomaticTransactionManagement()){
				oldValueAutoCommit = connection.getAutoCommit();
				connection.setAutoCommit(false);
			}

			this.serviceCRUD.update(this.jdbcProperties,this.log,connection,sqlQueryObject,oldId,proprietaEvento);
			
		}catch(ServiceException e){
			rollback = true;
			this.log.error(e,e); throw e;
		}catch(NotFoundException e){
			rollback = true;
			this.log.debug(e,e); throw e;
		}catch(NotImplementedException e){
			rollback = true;
			this.log.error(e,e); throw e;
		}catch(Exception e){
			rollback = true;
			this.log.error(e,e); throw new ServiceException("Update not completed: "+e.getMessage(),e);
		}finally{
			if(this.jdbcProperties.isAutomaticTransactionManagement()){
				if(rollback){
					try{
						if(connection!=null)
							connection.rollback();
					}catch(Exception eIgnore){}
				}else{
					try{
						if(connection!=null)
							connection.commit();
					}catch(Exception eIgnore){}
				}
				try{
					if(connection!=null)
						connection.setAutoCommit(oldValueAutoCommit);
				}catch(Exception eIgnore){}
			}
			if(connection!=null){
				this.jdbcServiceManager.closeConnection(connection);
			}
		}

	}

	//@Override
	public void updateOrCreate(ProprietaEventiId oldId, ProprietaEvento proprietaEvento) throws ServiceException, NotImplementedException {
	
		Connection connection = null;
		boolean oldValueAutoCommit = false;
		boolean rollback = false;
		try{
			
			// check parameters
			if(proprietaEvento==null){
				throw new Exception("Parameter (type:"+ProprietaEvento.class.getName()+") 'proprietaEvento' is null");
			}
			if(oldId==null){
				throw new Exception("Parameter (type:"+ProprietaEventiId.class.getName()+") 'oldId' is null");
			}

			// ISQLQueryObject
			ISQLQueryObject sqlQueryObject = SQLObjectFactory.createSQLQueryObject(this.jdbcProperties.getDatabase());
			sqlQueryObject.setANDLogicOperator(true);
			// Connection sql
			connection = this.jdbcServiceManager.getConnection();
		
			// transaction
			if(this.jdbcProperties.isAutomaticTransactionManagement()){
				oldValueAutoCommit = connection.getAutoCommit();
				connection.setAutoCommit(false);
			}

			this.serviceCRUD.updateOrCreate(this.jdbcProperties,this.log,connection,sqlQueryObject,oldId,proprietaEvento);
			
		}catch(ServiceException e){
			rollback = true;
			this.log.error(e,e); throw e;
		}catch(NotImplementedException e){
			rollback = true;
			this.log.error(e,e); throw e;
		}catch(Exception e){
			rollback = true;
			this.log.error(e,e); throw new ServiceException("UpdateOrCreate not completed: "+e.getMessage(),e);
		}finally{
			if(this.jdbcProperties.isAutomaticTransactionManagement()){
				if(rollback){
					try{
						if(connection!=null)
							connection.rollback();
					}catch(Exception eIgnore){}
				}else{
					try{
						if(connection!=null)
							connection.commit();
					}catch(Exception eIgnore){}
				}
				try{
					if(connection!=null)
						connection.setAutoCommit(oldValueAutoCommit);
				}catch(Exception eIgnore){}
			}
			if(connection!=null){
				this.jdbcServiceManager.closeConnection(connection);
			}
		}

	}
	
	//@Override
	public void delete(ProprietaEvento proprietaEvento) throws ServiceException,NotImplementedException {
		
		Connection connection = null;
		boolean oldValueAutoCommit = false;
		boolean rollback = false;
		try{
			
			// check parameters
			if(proprietaEvento==null){
				throw new Exception("Parameter (type:"+ProprietaEvento.class.getName()+") 'proprietaEvento' is null");
			}

			// ISQLQueryObject
			ISQLQueryObject sqlQueryObject = SQLObjectFactory.createSQLQueryObject(this.jdbcProperties.getDatabase());
			sqlQueryObject.setANDLogicOperator(true);
			// Connection sql
			connection = this.jdbcServiceManager.getConnection();

			// transaction
			if(this.jdbcProperties.isAutomaticTransactionManagement()){
				oldValueAutoCommit = connection.getAutoCommit();
				connection.setAutoCommit(false);
			}

			this.serviceCRUD.delete(this.jdbcProperties,this.log,connection,sqlQueryObject,proprietaEvento);	

		}catch(ServiceException e){
			rollback = true;
			this.log.error(e,e); throw e;
		}catch(NotImplementedException e){
			rollback = true;
			this.log.error(e,e); throw e;
		}catch(Exception e){
			rollback = true;
			this.log.error(e,e); throw new ServiceException("Delete not completed: "+e.getMessage(),e);
		}finally{
			if(this.jdbcProperties.isAutomaticTransactionManagement()){
				if(rollback){
					try{
						if(connection!=null)
							connection.rollback();
					}catch(Exception eIgnore){}
				}else{
					try{
						if(connection!=null)
							connection.commit();
					}catch(Exception eIgnore){}
				}
				try{
					if(connection!=null)
						connection.setAutoCommit(oldValueAutoCommit);
				}catch(Exception eIgnore){}
			}
			if(connection!=null){
				this.jdbcServiceManager.closeConnection(connection);
			}
		}
	
	}

	//@Override
	public void deleteById(ProprietaEventiId id) throws ServiceException, NotImplementedException {

		Connection connection = null;
		boolean oldValueAutoCommit = false;
		boolean rollback = false;
		try{
			
			// check parameters
			if(id==null){
				throw new Exception("Parameter (type:"+ProprietaEventiId.class.getName()+") 'id' is null");
			}

			// ISQLQueryObject
			ISQLQueryObject sqlQueryObject = SQLObjectFactory.createSQLQueryObject(this.jdbcProperties.getDatabase());
			sqlQueryObject.setANDLogicOperator(true);
			// Connection sql
			connection = this.jdbcServiceManager.getConnection();

			// transaction
			if(this.jdbcProperties.isAutomaticTransactionManagement()){
				oldValueAutoCommit = connection.getAutoCommit();
				connection.setAutoCommit(false);
			}

			this.serviceCRUD.deleteById(this.jdbcProperties,this.log,connection,sqlQueryObject,id);			

		}catch(ServiceException e){
			rollback = true;
			this.log.error(e,e); throw e;
		}catch(NotImplementedException e){
			rollback = true;
			this.log.error(e,e); throw e;
		}catch(Exception e){
			rollback = true;
			this.log.error(e,e); throw new ServiceException("DeleteById not completed: "+e.getMessage(),e);
		}finally{
			if(this.jdbcProperties.isAutomaticTransactionManagement()){
				if(rollback){
					try{
						if(connection!=null)
							connection.rollback();
					}catch(Exception eIgnore){}
				}else{
					try{
						if(connection!=null)
							connection.commit();
					}catch(Exception eIgnore){}
				}
				try{
					if(connection!=null)
						connection.setAutoCommit(oldValueAutoCommit);
				}catch(Exception eIgnore){}
			}
			if(connection!=null){
				this.jdbcServiceManager.closeConnection(connection);
			}
		}

	}

	//@Override
	public NonNegativeNumber deleteAll() throws ServiceException, NotImplementedException {

		Connection connection = null;
		boolean oldValueAutoCommit = false;
		boolean rollback = false;
		try{

			// ISQLQueryObject
			ISQLQueryObject sqlQueryObject = SQLObjectFactory.createSQLQueryObject(this.jdbcProperties.getDatabase());
			sqlQueryObject.setANDLogicOperator(true);
			// Connection sql
			connection = this.jdbcServiceManager.getConnection();
		
			// transaction
			if(this.jdbcProperties.isAutomaticTransactionManagement()){
				oldValueAutoCommit = connection.getAutoCommit();
				connection.setAutoCommit(false);
			}

			return this.serviceCRUD.deleteAll(this.jdbcProperties,this.log,connection,sqlQueryObject);	

		}catch(ServiceException e){
			rollback = true;
			this.log.error(e,e); throw e;
		}catch(NotImplementedException e){
			rollback = true;
			this.log.error(e,e); throw e;
		}catch(Exception e){
			rollback = true;
			this.log.error(e,e); throw new ServiceException("DeleteAll not completed: "+e.getMessage(),e);
		}finally{
			if(this.jdbcProperties.isAutomaticTransactionManagement()){
				if(rollback){
					try{
						if(connection!=null)
							connection.rollback();
					}catch(Exception eIgnore){}
				}else{
					try{
						if(connection!=null)
							connection.commit();
					}catch(Exception eIgnore){}
				}
				try{
					if(connection!=null)
						connection.setAutoCommit(oldValueAutoCommit);
				}catch(Exception eIgnore){}
			}
			if(connection!=null){
				this.jdbcServiceManager.closeConnection(connection);
			}
		}
	
	}

	//@Override
	public NonNegativeNumber deleteAll(IExpression expression) throws ServiceException, NotImplementedException {
		
		Connection connection = null;
		boolean oldValueAutoCommit = false;
		boolean rollback = false;
		try{
			
			// check parameters
			if(expression==null){
				throw new Exception("Parameter (type:"+IExpression.class.getName()+") 'expression' is null");
			}
			if( ! (expression instanceof JDBCExpression) ){
				throw new Exception("Parameter (type:"+expression.getClass().getName()+") 'expression' has wrong type, expect "+JDBCExpression.class.getName());
			}
			JDBCExpression jdbcExpression = (JDBCExpression) expression;
			this.log.debug("sql = "+jdbcExpression.toSql());
		
			// ISQLQueryObject
			ISQLQueryObject sqlQueryObject = SQLObjectFactory.createSQLQueryObject(this.jdbcProperties.getDatabase());
			sqlQueryObject.setANDLogicOperator(true);
			// Connection sql
			connection = this.jdbcServiceManager.getConnection();

			// transaction
			if(this.jdbcProperties.isAutomaticTransactionManagement()){
				oldValueAutoCommit = connection.getAutoCommit();
				connection.setAutoCommit(false);
			}

			return this.serviceCRUD.deleteAll(this.jdbcProperties,this.log,connection,sqlQueryObject,jdbcExpression);
	
		}catch(ServiceException e){
			rollback = true;
			this.log.error(e,e); throw e;
		}catch(NotImplementedException e){
			rollback = true;
			this.log.error(e,e); throw e;
		}catch(Exception e){
			rollback = true;
			this.log.error(e,e); throw new ServiceException("DeleteAll(expression) not completed: "+e.getMessage(),e);
		}finally{
			if(this.jdbcProperties.isAutomaticTransactionManagement()){
				if(rollback){
					try{
						if(connection!=null)
							connection.rollback();
					}catch(Exception eIgnore){}
				}else{
					try{
						if(connection!=null)
							connection.commit();
					}catch(Exception eIgnore){}
				}
				try{
					if(connection!=null)
						connection.setAutoCommit(oldValueAutoCommit);
				}catch(Exception eIgnore){}
			}
			if(connection!=null){
				this.jdbcServiceManager.closeConnection(connection);
			}
		}
	
	}
	
	
}
