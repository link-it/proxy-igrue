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
package org.govmix.proxy.igrue.web.ejb.dao;

import org.openspcoop2.generic_project.exception.ServiceException;
import org.openspcoop2.generic_project.exception.NotImplementedException;


/**	
 * Manager with which 'can get the service for the management of the objects defined in the package org.govmix.proxy.igrue.web.ejb 
 *
 * @author Giovanni Bussu (bussu@link.it)
 * @author $Author$
 * @version $Rev$, $Date$
 */

public interface IServiceManager {

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
	public IUtenteServiceSearch getUtenteServiceSearch() throws ServiceException,NotImplementedException;
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Utente}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Utente}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	public IUtenteService getUtenteService() throws ServiceException,NotImplementedException;
	
	
	
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
	public IEsitoServiceSearch getEsitoServiceSearch() throws ServiceException,NotImplementedException;
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Esito}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Esito}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	public IEsitoService getEsitoService() throws ServiceException,NotImplementedException;
	
	
	
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
	public IEventoServiceSearch getEventoServiceSearch() throws ServiceException,NotImplementedException;
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Evento}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Evento}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	public IEventoService getEventoService() throws ServiceException,NotImplementedException;
	
	
	
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
	public IMailTemplateServiceSearch getMailTemplateServiceSearch() throws ServiceException,NotImplementedException;
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.MailTemplate}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.MailTemplate}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	public IMailTemplateService getMailTemplateService() throws ServiceException,NotImplementedException;
	
	
	
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
	public IProprietaEventoServiceSearch getProprietaEventoServiceSearch() throws ServiceException,NotImplementedException;
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.ProprietaEvento}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.ProprietaEvento}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	public IProprietaEventoService getProprietaEventoService() throws ServiceException,NotImplementedException;
	
	
	
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
	public ITabellacontestoServiceSearch getTabellacontestoServiceSearch() throws ServiceException,NotImplementedException;
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Tabellacontesto}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Tabellacontesto}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	public ITabellacontestoService getTabellacontestoService() throws ServiceException,NotImplementedException;
	
	
	
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
	public ITicketServiceSearch getTicketServiceSearch() throws ServiceException,NotImplementedException;
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Ticket}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Ticket}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	public ITicketService getTicketService() throws ServiceException,NotImplementedException;
	
	
	
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
	public ITipieventoServiceSearch getTipieventoServiceSearch() throws ServiceException,NotImplementedException;
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Tipievento}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Tipievento}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	public ITipieventoService getTipieventoService() throws ServiceException,NotImplementedException;
	
	
	
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
	public ITrasmissioneServiceSearch getTrasmissioneServiceSearch() throws ServiceException,NotImplementedException;
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Trasmissione}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.Trasmissione}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	public ITrasmissioneService getTrasmissioneService() throws ServiceException,NotImplementedException;
	
	
	
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
	public IMailTraceServiceSearch getMailTraceServiceSearch() throws ServiceException,NotImplementedException;
	
	/**
	 * Return a service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.MailTrace}
	 *
	 * @return Service used to research and manage on the backend on objects of type {@link org.govmix.proxy.igrue.web.ejb.MailTrace}	
	 * @throws ServiceException Exception thrown when an error occurs during processing of the request
	 * @throws NotImplementedException Exception thrown when the method is not implemented
	 */
	public IMailTraceService getMailTraceService() throws ServiceException,NotImplementedException;
	
	
	
	
}
