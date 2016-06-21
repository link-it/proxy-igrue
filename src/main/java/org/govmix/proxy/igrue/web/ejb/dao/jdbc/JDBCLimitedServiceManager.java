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

import java.sql.Connection;

import javax.sql.DataSource;

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

/**     
 * Manager that allows you to obtain the services of research and management of objects
 *
 * @author Giovanni Bussu (bussu@link.it)
 * @author $Author$
 * @version $Rev$, $Date$
 */

public class JDBCLimitedServiceManager extends JDBCServiceManager {

	private JDBCServiceManager unlimitedJdbcServiceManager;

	public JDBCLimitedServiceManager(JDBCServiceManager jdbcServiceManager) throws ServiceException {
		this.datasource = jdbcServiceManager.get_Datasource();
		this.connection = jdbcServiceManager.get_Connection();
		this.log = jdbcServiceManager.get_Logger();
		this.jdbcProperties = jdbcServiceManager.get_JdbcProperties();
		this.unlimitedJdbcServiceManager = jdbcServiceManager;
	}
	
	
	//@Override
	public Connection getConnection() throws ServiceException {
		throw new ServiceException("Connection managed from framework");
	}
	//@Override
	public void closeConnection(Connection connection) throws ServiceException {
		throw new ServiceException("Connection managed from framework");
	}
	//@Override
	protected Connection get_Connection() throws ServiceException {
		throw new ServiceException("Connection managed from framework");
	}
	//@Override
	protected DataSource get_Datasource() throws ServiceException {
		throw new ServiceException("Connection managed from framework");
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
		return new JDBCUtenteServiceSearch(this.unlimitedJdbcServiceManager);
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
		return new JDBCUtenteService(this.unlimitedJdbcServiceManager);
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
		return new JDBCEsitoServiceSearch(this.unlimitedJdbcServiceManager);
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
		return new JDBCEsitoService(this.unlimitedJdbcServiceManager);
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
		return new JDBCEventoServiceSearch(this.unlimitedJdbcServiceManager);
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
		return new JDBCEventoService(this.unlimitedJdbcServiceManager);
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
		return new JDBCMailTemplateServiceSearch(this.unlimitedJdbcServiceManager);
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
		return new JDBCMailTemplateService(this.unlimitedJdbcServiceManager);
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
		return new JDBCProprietaEventoServiceSearch(this.unlimitedJdbcServiceManager);
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
		return new JDBCProprietaEventoService(this.unlimitedJdbcServiceManager);
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
		return new JDBCTabellacontestoServiceSearch(this.unlimitedJdbcServiceManager);
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
		return new JDBCTabellacontestoService(this.unlimitedJdbcServiceManager);
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
		return new JDBCTicketServiceSearch(this.unlimitedJdbcServiceManager);
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
		return new JDBCTicketService(this.unlimitedJdbcServiceManager);
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
		return new JDBCTipieventoServiceSearch(this.unlimitedJdbcServiceManager);
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
		return new JDBCTipieventoService(this.unlimitedJdbcServiceManager);
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
		return new JDBCTrasmissioneServiceSearch(this.unlimitedJdbcServiceManager);
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
		return new JDBCTrasmissioneService(this.unlimitedJdbcServiceManager);
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
		return new JDBCMailTraceServiceSearch(this.unlimitedJdbcServiceManager);
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
		return new JDBCMailTraceService(this.unlimitedJdbcServiceManager);
	}
	
	
	
}