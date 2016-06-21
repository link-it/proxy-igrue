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

import org.openspcoop2.generic_project.exception.NotImplementedException;
import org.openspcoop2.generic_project.exception.ServiceException;
import org.openspcoop2.generic_project.dao.jdbc.JDBCServiceManagerProperties;
import org.openspcoop2.generic_project.utils.ServiceManagerProperties;

import org.govmix.proxy.igrue.web.ejb.dao.IUtenteServiceSearch;
import org.govmix.proxy.igrue.web.ejb.dao.IUtenteService;
import org.govmix.proxy.igrue.web.ejb.dao.IEsitoServiceSearch;
import org.govmix.proxy.igrue.web.ejb.dao.IEsitoService;
import org.govmix.proxy.igrue.web.ejb.dao.IEventoServiceSearch;
import org.govmix.proxy.igrue.web.ejb.dao.IEventoService;
import org.govmix.proxy.igrue.web.ejb.dao.IMailTemplateServiceSearch;
import org.govmix.proxy.igrue.web.ejb.dao.IMailTemplateService;
import org.govmix.proxy.igrue.web.ejb.dao.IProprietaEventoServiceSearch;
import org.govmix.proxy.igrue.web.ejb.dao.IProprietaEventoService;
import org.govmix.proxy.igrue.web.ejb.dao.ITabellacontestoServiceSearch;
import org.govmix.proxy.igrue.web.ejb.dao.ITabellacontestoService;
import org.govmix.proxy.igrue.web.ejb.dao.ITicketServiceSearch;
import org.govmix.proxy.igrue.web.ejb.dao.ITicketService;
import org.govmix.proxy.igrue.web.ejb.dao.ITipieventoServiceSearch;
import org.govmix.proxy.igrue.web.ejb.dao.ITipieventoService;
import org.govmix.proxy.igrue.web.ejb.dao.ITrasmissioneServiceSearch;
import org.govmix.proxy.igrue.web.ejb.dao.ITrasmissioneService;
import org.govmix.proxy.igrue.web.ejb.dao.IMailTraceServiceSearch;
import org.govmix.proxy.igrue.web.ejb.dao.IMailTraceService;

import org.govmix.proxy.igrue.web.ejb.dao.IServiceManager;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**     
 * Manager that allows you to obtain the services of research and management of objects
 *
 * @author Giovanni Bussu (bussu@link.it)
 * @author $Author$
 * @version $Rev$, $Date$
 */

public class JDBCServiceManager extends org.openspcoop2.generic_project.dao.jdbc.JDBCServiceManager implements IServiceManager {

	protected Connection get_Connection() throws ServiceException {
		return this.connection;
	}
	protected DataSource get_Datasource() throws ServiceException {
		return this.datasource;
	}
	protected JDBCServiceManagerProperties get_JdbcProperties(){
		return this.jdbcProperties;
	}
	protected Logger get_Logger(){
		return this.log;
	}

	protected JDBCServiceManager(){}

	public JDBCServiceManager(String jndiName, Properties contextJNDI,
			ServiceManagerProperties smProperties) throws ServiceException {
		super(jndiName, contextJNDI, smProperties);
	}
	public JDBCServiceManager(String jndiName, Properties contextJNDI,
			JDBCServiceManagerProperties jdbcProperties) throws ServiceException {
		super(jndiName, contextJNDI, jdbcProperties);
	}
	public JDBCServiceManager(String jndiName, Properties contextJNDI,
			ServiceManagerProperties smProperties, Logger alog) throws ServiceException {
		super(jndiName, contextJNDI, smProperties, alog);
	}
	public JDBCServiceManager(String jndiName, Properties contextJNDI,
			JDBCServiceManagerProperties jdbcProperties, Logger alog) throws ServiceException {
		super(jndiName, contextJNDI, jdbcProperties, alog);
	}
	
	
	public JDBCServiceManager(DataSource ds, ServiceManagerProperties smProperties)
			throws ServiceException {
		super(ds, smProperties);
	}
	public JDBCServiceManager(DataSource ds, JDBCServiceManagerProperties jdbcProperties)
			throws ServiceException {
		super(ds, jdbcProperties);
	}
	public JDBCServiceManager(DataSource ds, ServiceManagerProperties smProperties, Logger alog)
			throws ServiceException {
		super(ds, smProperties, alog);
	}
	public JDBCServiceManager(DataSource ds, JDBCServiceManagerProperties jdbcProperties, Logger alog)
			throws ServiceException {
		super(ds, jdbcProperties, alog);
	}
	
	
	public JDBCServiceManager(String connectionUrl, String driverJDBC,
			String username, String password, ServiceManagerProperties smProperties)
			throws ServiceException {
		super(connectionUrl, driverJDBC, username, password, smProperties);
	}
	public JDBCServiceManager(String connectionUrl, String driverJDBC,
			String username, String password, JDBCServiceManagerProperties jdbcProperties)
			throws ServiceException {
		super(connectionUrl, driverJDBC, username, password, jdbcProperties);
	}
	public JDBCServiceManager(String connectionUrl, String driverJDBC,
			String username, String password, ServiceManagerProperties smProperties, Logger alog)
			throws ServiceException {
		super(connectionUrl, driverJDBC, username, password, smProperties, alog);
	}
	public JDBCServiceManager(String connectionUrl, String driverJDBC,
			String username, String password, JDBCServiceManagerProperties jdbcProperties, Logger alog)
			throws ServiceException {
		super(connectionUrl, driverJDBC, username, password, jdbcProperties, alog);
	}
	
	
	public JDBCServiceManager(Connection connection, ServiceManagerProperties smProperties)
			throws ServiceException {
		super(connection, smProperties);
	}
	public JDBCServiceManager(Connection connection, JDBCServiceManagerProperties jdbcProperties)
			throws ServiceException {
		super(connection, jdbcProperties);
	}
	public JDBCServiceManager(Connection connection, ServiceManagerProperties smProperties, Logger alog) throws ServiceException {
		super(connection, smProperties, alog);
	}
	public JDBCServiceManager(Connection connection, JDBCServiceManagerProperties jdbcProperties, Logger alog) throws ServiceException {
		super(connection, jdbcProperties, alog);
	}


	


	/*
	 =================================================================================
	 Services relating to the object: utente
	 =================================================================================
	*/
	
	/**
	 * Return a service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Utente}
	 *
	 * @return Service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Utente}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public IUtenteServiceSearch getUtenteServiceSearch() throws ServiceException,NotImplementedException{
		return new JDBCUtenteServiceSearch(this);
	}
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Utente}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Utente}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public IUtenteService getUtenteService() throws ServiceException,NotImplementedException{
		return new JDBCUtenteService(this);
	}
	
	
	
	/*
	 =================================================================================
	 Services relating to the object: esito
	 =================================================================================
	*/
	
	/**
	 * Return a service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Esito}
	 *
	 * @return Service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Esito}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public IEsitoServiceSearch getEsitoServiceSearch() throws ServiceException,NotImplementedException{
		return new JDBCEsitoServiceSearch(this);
	}
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Esito}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Esito}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public IEsitoService getEsitoService() throws ServiceException,NotImplementedException{
		return new JDBCEsitoService(this);
	}
	
	
	
	/*
	 =================================================================================
	 Services relating to the object: evento
	 =================================================================================
	*/
	
	/**
	 * Return a service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Evento}
	 *
	 * @return Service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Evento}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public IEventoServiceSearch getEventoServiceSearch() throws ServiceException,NotImplementedException{
		return new JDBCEventoServiceSearch(this);
	}
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Evento}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Evento}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public IEventoService getEventoService() throws ServiceException,NotImplementedException{
		return new JDBCEventoService(this);
	}
	
	
	
	/*
	 =================================================================================
	 Services relating to the object: mail-template
	 =================================================================================
	*/
	
	/**
	 * Return a service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.MailTemplate}
	 *
	 * @return Service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.MailTemplate}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public IMailTemplateServiceSearch getMailTemplateServiceSearch() throws ServiceException,NotImplementedException{
		return new JDBCMailTemplateServiceSearch(this);
	}
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.MailTemplate}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.MailTemplate}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public IMailTemplateService getMailTemplateService() throws ServiceException,NotImplementedException{
		return new JDBCMailTemplateService(this);
	}
	
	
	
	/*
	 =================================================================================
	 Services relating to the object: proprieta-evento
	 =================================================================================
	*/
	
	/**
	 * Return a service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.ProprietaEvento}
	 *
	 * @return Service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.ProprietaEvento}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public IProprietaEventoServiceSearch getProprietaEventoServiceSearch() throws ServiceException,NotImplementedException{
		return new JDBCProprietaEventoServiceSearch(this);
	}
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.ProprietaEvento}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.ProprietaEvento}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public IProprietaEventoService getProprietaEventoService() throws ServiceException,NotImplementedException{
		return new JDBCProprietaEventoService(this);
	}
	
	
	
	/*
	 =================================================================================
	 Services relating to the object: tabellacontesto
	 =================================================================================
	*/
	
	/**
	 * Return a service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Tabellacontesto}
	 *
	 * @return Service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Tabellacontesto}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public ITabellacontestoServiceSearch getTabellacontestoServiceSearch() throws ServiceException,NotImplementedException{
		return new JDBCTabellacontestoServiceSearch(this);
	}
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Tabellacontesto}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Tabellacontesto}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public ITabellacontestoService getTabellacontestoService() throws ServiceException,NotImplementedException{
		return new JDBCTabellacontestoService(this);
	}
	
	
	
	/*
	 =================================================================================
	 Services relating to the object: ticket
	 =================================================================================
	*/
	
	/**
	 * Return a service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Ticket}
	 *
	 * @return Service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Ticket}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public ITicketServiceSearch getTicketServiceSearch() throws ServiceException,NotImplementedException{
		return new JDBCTicketServiceSearch(this);
	}
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Ticket}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Ticket}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public ITicketService getTicketService() throws ServiceException,NotImplementedException{
		return new JDBCTicketService(this);
	}
	
	
	
	/*
	 =================================================================================
	 Services relating to the object: tipievento
	 =================================================================================
	*/
	
	/**
	 * Return a service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Tipievento}
	 *
	 * @return Service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Tipievento}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public ITipieventoServiceSearch getTipieventoServiceSearch() throws ServiceException,NotImplementedException{
		return new JDBCTipieventoServiceSearch(this);
	}
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Tipievento}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Tipievento}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public ITipieventoService getTipieventoService() throws ServiceException,NotImplementedException{
		return new JDBCTipieventoService(this);
	}
	
	
	
	/*
	 =================================================================================
	 Services relating to the object: trasmissione
	 =================================================================================
	*/
	
	/**
	 * Return a service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Trasmissione}
	 *
	 * @return Service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Trasmissione}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public ITrasmissioneServiceSearch getTrasmissioneServiceSearch() throws ServiceException,NotImplementedException{
		return new JDBCTrasmissioneServiceSearch(this);
	}
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Trasmissione}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Trasmissione}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public ITrasmissioneService getTrasmissioneService() throws ServiceException,NotImplementedException{
		return new JDBCTrasmissioneService(this);
	}
	
	
	
	/*
	 =================================================================================
	 Services relating to the object: mail-trace
	 =================================================================================
	*/
	
	/**
	 * Return a service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.MailTrace}
	 *
	 * @return Service used to research on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.MailTrace}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public IMailTraceServiceSearch getMailTraceServiceSearch() throws ServiceException,NotImplementedException{
		return new JDBCMailTraceServiceSearch(this);
	}
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.MailTrace}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.MailTrace}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	//@Override
	public IMailTraceService getMailTraceService() throws ServiceException,NotImplementedException{
		return new JDBCMailTraceService(this);
	}
	
	
	
	

}
