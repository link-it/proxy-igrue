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
package org.govmix.proxy.igrue.web.service;

import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.govmix.proxy.igrue.Configuration;
import org.govmix.proxy.igrue.web.ejb.dao.IServiceManager;
import org.govmix.proxy.igrue.web.ejb.dao.jdbc.JDBCServiceManager;
import org.openspcoop2.generic_project.dao.jdbc.JDBCServiceManagerProperties;
import org.openspcoop2.generic_project.exception.ServiceException;

public class ServiceManagerFactory {

	private static IServiceManager serviceManager;
	
	public static IServiceManager getServiceManager() throws ServiceException, SQLException {
		if(serviceManager == null) {
			init();
		}
		return serviceManager;
	}
	
	private static synchronized void init() throws ServiceException, SQLException {
		
		

		DataSource ds = null;
		try {
			InitialContext initC = new InitialContext();
			ds = (DataSource) initC.lookup(Configuration.DBJNDI);
			initC.close();
		} catch (NamingException e) {
			throw new ServiceException("Inizializzazione Datasource non riuscita", e);
		}
		
		ServiceManagerFactory.serviceManager = new JDBCServiceManager(ds, getJDBCProperties(true));
		
	}
	
	public static JDBCServiceManagerProperties getJDBCProperties(boolean automaticTransactionManagement){
		JDBCServiceManagerProperties jdbcProperties = new JDBCServiceManagerProperties();
		jdbcProperties.setDatabaseType(Configuration.DB_TYPE);
		jdbcProperties.setShowSql(Configuration.IS_SHOW_SQL);
		jdbcProperties.setAutomaticTransactionManagement(automaticTransactionManagement);
		return jdbcProperties;
	}
}
