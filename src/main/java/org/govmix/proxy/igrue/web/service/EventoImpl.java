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
import java.util.ArrayList;
import java.util.List;

import org.govmix.proxy.igrue.web.dto.EventoDTO;
import org.govmix.proxy.igrue.web.ejb.Evento;
import org.govmix.proxy.igrue.web.ejb.IdTrasmissione;
import org.govmix.proxy.igrue.web.ejb.Ticket;
import org.govmix.proxy.igrue.web.ejb.TicketId;
import org.govmix.proxy.igrue.web.ejb.Tipievento;
import org.govmix.proxy.igrue.web.ejb.Utente;
import org.govmix.proxy.igrue.web.ejb.dao.IEventoServiceSearch;
import org.govmix.proxy.igrue.web.ejb.dao.ITicketServiceSearch;
import org.govmix.proxy.igrue.web.ejb.dao.ITipieventoServiceSearch;
import org.govmix.proxy.igrue.web.ejb.utils.UtentiUtilities;
import org.govmix.proxy.igrue.web.mbean.EventiSearchBean;
import org.openspcoop2.generic_project.beans.NonNegativeNumber;
import org.openspcoop2.generic_project.exception.ExpressionException;
import org.openspcoop2.generic_project.exception.ExpressionNotImplementedException;
import org.openspcoop2.generic_project.exception.MultipleResultException;
import org.openspcoop2.generic_project.exception.NotFoundException;
import org.openspcoop2.generic_project.exception.NotImplementedException;
import org.openspcoop2.generic_project.exception.ServiceException;
import org.openspcoop2.generic_project.expression.IExpression;
import org.openspcoop2.generic_project.expression.IPaginatedExpression;
import org.openspcoop2.generic_project.expression.SortOrder;

public class EventoImpl implements IEvento {

	private EventiSearchBean search;
	private IEventoServiceSearch eventoSearch;
	private ITicketServiceSearch ticketSearch;
	private ITipieventoServiceSearch tipiEventoSearch;

	public EventoImpl() throws ServiceException {
		try {
			this.eventoSearch = ServiceManagerFactory.getServiceManager().getEventoServiceSearch();
			this.tipiEventoSearch = ServiceManagerFactory.getServiceManager().getTipieventoServiceSearch();
			this.ticketSearch = ServiceManagerFactory.getServiceManager().getTicketServiceSearch();
		} catch(SQLException e) {
			throw new ServiceException(e);
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		}
	}

	public void setSearch(EventiSearchBean search) {
		this.search = search;
	}

	public List<EventoDTO> findAll(int start, int limit) throws ServiceException {

		try {
			Utente sistema = UtentiUtilities.getUtente();
			IPaginatedExpression exp = eventoSearch.newPaginatedExpression();
			exp.and();
			exp.equals(Evento.model().OWNER_IDAMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(Evento.model().OWNER_IDSISTEMA, sistema.getIdSistema());
			if(search.isDescrizioneSet()) {
				exp.like(Tipievento.model().DESCRIPTION, search.getDescrizione());
			}
			exp.limit(limit);
			exp.offset(start);
			exp.addOrder(Evento.model().EVENT_ID);
			exp.sortOrder(SortOrder.DESC);
			List<Evento> lst = eventoSearch.findAll(exp);
			List<EventoDTO> eventi = new ArrayList<EventoDTO>();
			for(Evento evento : lst) {
				
				Tipievento tipo = new Tipievento();
				tipo.setCode(evento.getEventtypeCode());
				tipo.setDescription(evento.getEventtypeDescription());
				EventoDTO dto = new EventoDTO(evento,tipo);
				eventi.add(dto);
			}
			return eventi;
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionNotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionException e) {
			throw new ServiceException(e);
		}

	}

	private List<EventoDTO> _findAll(IPaginatedExpression exp) throws ServiceException {
		try {
			List<Evento> lst = eventoSearch.findAll(exp);

			List<EventoDTO> eventi = new ArrayList<EventoDTO>();

			for(Evento evento : lst) {
				IExpression expTipiEvento = tipiEventoSearch.newExpression();
				expTipiEvento.equals(Tipievento.model().CODE, evento.getEventtypeCode());
				if(this.search.isDescrizioneSet()) {

					expTipiEvento.and().like(Tipievento.model().DESCRIPTION, search.getDescrizione());
				}
				Tipievento tipo = tipiEventoSearch.find(expTipiEvento);
				EventoDTO dto = new EventoDTO(evento,tipo);
				eventi.add(dto);
			}

			return eventi;
		} catch (NotFoundException e) {
			throw new ServiceException(e);
		} catch (MultipleResultException e) {
			throw new ServiceException(e);
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionNotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionException e) {
			throw new ServiceException(e);
		}

	}

	public int totalCount() throws ServiceException {

		try {
			Utente sistema = UtentiUtilities.getUtente();

			IExpression exp = eventoSearch.newExpression();
			exp.and();
			exp.equals(Evento.model().OWNER_IDAMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(Evento.model().OWNER_IDSISTEMA, sistema.getIdSistema());


			if(search.isDescrizioneSet()) {
				exp.like(Tipievento.model().DESCRIPTION, search.getDescrizione());
			}

			NonNegativeNumber res = eventoSearch.count(exp);
			return res != null ? (int) res.longValue() : 0;
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionNotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionException e) {
			throw new ServiceException(e);
		}

		//		Long res = (Long) this.serviceManager.createQuery("SELECT count(te) FROM Evento e, Tipievento te WHERE e.eventtypeCode=te.code AND te.description LIKE :descr").setParameter("descr",rest).getSingleResult();
		//		return res!=null ? res.intValue() : 0;
	}

	public void deleteById(Integer key) {
	}

	public List<EventoDTO> findAll() throws ServiceException {
		try {

			Utente sistema = UtentiUtilities.getUtente();

			IPaginatedExpression exp = eventoSearch.newPaginatedExpression();

			exp.and();
			exp.equals(Evento.model().OWNER_IDAMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(Evento.model().OWNER_IDSISTEMA, sistema.getIdSistema());

			return this._findAll(exp);
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionNotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionException e) {
			throw new ServiceException(e);
		}
	}

	public EventoDTO findById(Integer key) throws ServiceException{

		try {
			Utente sistema = UtentiUtilities.getUtente();

			IExpression exp = eventoSearch.newExpression();

			exp.and();
			exp.equals(Evento.model().OWNER_IDAMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(Evento.model().OWNER_IDSISTEMA, sistema.getIdSistema());
			exp.equals(Evento.model().EVENT_ID, key);
			Evento evento = eventoSearch.find(exp);
			
			IExpression expTipiEvento = tipiEventoSearch.newExpression();
			expTipiEvento.equals(Tipievento.model().CODE, evento.getEventtypeCode());
			Tipievento tipo = tipiEventoSearch.find(expTipiEvento);
			
			return new org.govmix.proxy.igrue.web.dto.EventoDTO(evento,tipo);
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionNotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionException e) {
			throw new ServiceException(e);
		} catch (NotFoundException e) {
			throw new ServiceException(e);
		} catch (MultipleResultException e) {
			throw new ServiceException(e);
		}
	}

	public List<EventoDTO> findByFile(int file) throws ServiceException {
		try {
			IPaginatedExpression exp = eventoSearch.newPaginatedExpression();
			Utente sistema = UtentiUtilities.getUtente();
			TicketId tid = new TicketId();
			IdTrasmissione trasmissione = new IdTrasmissione();
			trasmissione.setFile(file);
			trasmissione.setUtente(UtentiUtilities.getIdUtente(sistema));
			tid.setTrasmissione(trasmissione);
			Ticket t = ticketSearch.get(tid);
			
			
			exp.and();
			exp.equals(Evento.model().OWNER_IDAMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(Evento.model().OWNER_IDSISTEMA, sistema.getIdSistema());
			exp.equals(Evento.model().PARAMETERID, t.getIdticket());
			exp.addOrder(Evento.model().STARTDATE);
			exp.sortOrder(SortOrder.ASC);

			List<Evento> eventoLst = eventoSearch.findAll(exp);

			List<EventoDTO> lst = new ArrayList<EventoDTO>();

			for(Evento evento : eventoLst) {

				IExpression expTipiEvento = tipiEventoSearch.newExpression();
				if(this.search.isDescrizioneSet()) {
					expTipiEvento.like(Tipievento.model().DESCRIPTION, search.getDescrizione());
				}

				expTipiEvento.equals(Tipievento.model().CODE, evento.getEventtypeCode());
				Tipievento tipo = tipiEventoSearch.find(expTipiEvento);

				lst.add(new EventoDTO(evento, tipo));
			}

			return lst;
		} catch (NotFoundException e) {
			throw new ServiceException(e);
		} catch (MultipleResultException e) {
			throw new ServiceException(e);
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionNotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionException e) {
			throw new ServiceException(e);
		}
	}

	public List<EventoDTO> findByTicket(int ticket) throws ServiceException {
		try {
			
			Utente sistema = UtentiUtilities.getUtente();

			IPaginatedExpression exp = eventoSearch.newPaginatedExpression();

			exp.and();
			exp.equals(Evento.model().OWNER_IDAMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(Evento.model().OWNER_IDSISTEMA, sistema.getIdSistema());

			exp.equals(Evento.model().PARAMETERID, ticket);
			return this._findAll(exp);
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		}catch (ExpressionNotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionException e) {
			throw new ServiceException(e);
		}
	}

	public void delete(EventoDTO obj) {
	}

	public void store(EventoDTO obj) {
	}

	public List<String> getDescrizioni() throws ServiceException {
		List<Tipievento> lst;
		try {
			lst = tipiEventoSearch.findAll(tipiEventoSearch.newPaginatedExpression());

			List<String> descrizioni = new ArrayList<String>();

			descrizioni.add("<qualsiasi>");

			for(Tipievento tipo : lst) {
				descrizioni.add(tipo.getDescription());
			}
			return descrizioni;

		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		}
	}
}
