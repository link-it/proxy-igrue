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

import org.govmix.proxy.igrue.web.ejb.dao.jdbc.JDBCServiceManager;
import org.govmix.proxy.igrue.web.ejb.utils.ProjectInfo;
import org.openspcoop2.generic_project.dao.jdbc.JDBCServiceManagerProperties;
import org.openspcoop2.generic_project.exception.ServiceException;

public class ServiceUtils {

	public static JDBCServiceManager getServiceManager() throws ServiceException {
		// DB
		String url = "jdbc:postgresql://127.0.0.1:5432/igrue";
		String driver = "org.postgresql.Driver";
		String userName = "igrue";
		String password = "igrue";
		String databaseType = "postgresql";
		
		// init Service Manager
		JDBCServiceManagerProperties jdbcProperties = new JDBCServiceManagerProperties();
		jdbcProperties.setDatabaseType(databaseType);
		jdbcProperties.setShowSql(true);
		org.openspcoop2.generic_project.dao.jdbc.JDBCServiceManager.configureDefaultLog4jProperties(ProjectInfo.getInstance());
		JDBCServiceManager serviceManager = new JDBCServiceManager(url,driver,userName,password,jdbcProperties);
		return serviceManager;
	}
}
