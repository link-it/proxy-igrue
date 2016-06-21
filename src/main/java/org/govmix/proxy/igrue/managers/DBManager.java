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
package org.govmix.proxy.igrue.managers;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.naming.InitialContext;
import javax.sql.DataSource;



/**
 * Contiene la gestione delle connessioni ad un Database. Il nome della risorsa
 * JNDI da cui e' possibile attingere connessioni verso il Database, viene
 * selezionato attraverso le impostazioni lette dal file
 * 'datasource.properties'.
 */

public class DBManager {

	/** Logger utilizzato per debug. */
	private static org.apache.log4j.Logger log = LogManager.getLoggerASConsole();

	/** DBManager */
	private static DBManager manager = null;

	/** DataSource dove attingere connessioni */
	private DataSource dataSource = null;

	private static boolean initialized = false;

	/**
	 * Viene chiamato in causa per istanziare il datasource
	 * 
	 * @param jndiName
	 *            Nome JNDI del Datasource
	 * @param context
	 *            Contesto JNDI da utilizzare
	 */
	public DBManager(String jndiName, java.util.Properties context) throws Exception {
		try {
			
			if (context != null) {
				DBManager.log.info("Proprieta' di contesto:" + context.size());
				Enumeration<?> en = context.keys();
				while (en.hasMoreElements()) {
					String key = (String) en.nextElement();
					DBManager.log.info("\tNome[" + key + "] Valore[" + context.getProperty(key) + "]");
				}
			} else {
				DBManager.log.info("Proprieta' di contesto non fornite");
			}

			DBManager.log.info("Nome dataSource:" + jndiName);

			InitialContext initC = null;
			if (context != null && context.size() > 0)
				initC = new InitialContext(context);
			else
				initC = new InitialContext();
			this.dataSource = (DataSource) initC.lookup(jndiName);
			initC.close();
			
			
		} catch (Exception e) {
			DBManager.log.error("Lookup datasource non riuscita", e);
			throw e;
		}
	}

	/**
	 * Il Metodo si occupa di inizializzare il propertiesReader del QueueManager
	 * 
	 * @param jndiName
	 *            Nome JNDI del Datasource
	 * @param context
	 *            Contesto JNDI da utilizzare
	 */
	public static boolean initialize(String jndiName, java.util.Properties context) {
		try {
			DBManager.manager = new DBManager(jndiName, context);
			DBManager.setInitialized(true);
			return true;
		} catch (Exception e) {
			DBManager.log.debug("Errore di inizializzazione del Database: " + e.getMessage());
			DBManager.setInitialized(false);
			return false;
		}
	}

	/**
	 * Ritorna l'istanza di questo DBManager
	 * 
	 * @return Istanza di DBManager
	 */
	public static DBManager getInstance() {
		return DBManager.manager;
	}

	/**
	 * Viene chiamato in causa per ottenere una connessione al DB
	 * @throws SQLException 
	 */
	public java.sql.Connection getConnection() throws SQLException {
		if (this.dataSource == null) {
			return null;
		}

		Connection connectionDB = null;
		try {
			connectionDB = this.dataSource.getConnection();
			connectionDB.setAutoCommit(false);
		} catch (SQLException e) {
			throw e;
		}

		return connectionDB;
	}

	/**
	 * Viene chiamato in causa per rilasciare una connessione al DB, effettuando
	 * precedentemente un commit
	 * 
	 * @param connectionDB
	 *            Connessione da rilasciare.
	 */
	public void releaseConnection(java.sql.Connection connectionDB) {
		try {
			if (connectionDB != null) {
				connectionDB.close();
			}
		} catch (SQLException e) {
		}
	}

	public static boolean isInitialized() {
		return DBManager.initialized;
	}

	private static void setInitialized(boolean initialized) {
		DBManager.initialized = initialized;
	}
}
