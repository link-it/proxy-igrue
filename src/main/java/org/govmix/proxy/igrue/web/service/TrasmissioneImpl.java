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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.govmix.proxy.igrue.managers.MailManager;
import org.govmix.proxy.igrue.moduli.Spedizione;
import org.govmix.proxy.igrue.moduli.utils.FileUtils;
import org.govmix.proxy.igrue.web.dto.TrasmissioneDTO;
import org.govmix.proxy.igrue.web.ejb.IdTrasmissione;
import org.govmix.proxy.igrue.web.ejb.Trasmissione;
import org.govmix.proxy.igrue.web.ejb.Utente;
import org.govmix.proxy.igrue.web.ejb.dao.ITicketServiceSearch;
import org.govmix.proxy.igrue.web.ejb.dao.ITrasmissioneServiceSearch;
import org.govmix.proxy.igrue.web.ejb.utils.UtentiUtilities;
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

public class TrasmissioneImpl implements ITrasmissione {

	private ITrasmissioneServiceSearch trasmissioneSearch;
	private ITicketServiceSearch ticketSearch;

	public TrasmissioneImpl() throws ServiceException {
		try {
			this.trasmissioneSearch = ServiceManagerFactory.getServiceManager().getTrasmissioneServiceSearch();
			this.ticketSearch = ServiceManagerFactory.getServiceManager().getTicketServiceSearch();
		} catch(SQLException e) {
			throw new ServiceException(e);
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		}
	}
	

	public List<IDettaglioDatiContesto> getDettagli(int ticket) throws ServiceException {
		// vado a prendere lo zip;
		
		try {
			Utente sistema = UtentiUtilities.getUtente();
			IExpression exp = ticketSearch.newExpression();
			exp.and();
			exp.equals(org.govmix.proxy.igrue.web.ejb.Ticket.model().IDTICKET, ticket);
			exp.equals(org.govmix.proxy.igrue.web.ejb.Ticket.model().ID_AMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(org.govmix.proxy.igrue.web.ejb.Ticket.model().ID_SISTEMA, sistema.getIdSistema());
			
			org.govmix.proxy.igrue.web.ejb.Ticket t = ticketSearch.find(exp);
			
			return Utils.getDettagliDatiContesto(t.getFile());

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

	public int totalCount() throws ServiceException {
		try {
			IExpression exp = trasmissioneSearch.newExpression();
			
			Utente sistema = UtentiUtilities.getUtente();
			exp.and();
			exp.equals(Trasmissione.model().ID_AMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(Trasmissione.model().ID_SISTEMA, sistema.getIdSistema());
			
			NonNegativeNumber cnt = trasmissioneSearch.count(exp);
			return cnt!=null ? (int) cnt.longValue() : 0;

		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionNotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionException e) {
			throw new ServiceException(e);
		}

	}
	
	public int nuovaTrasmissione(byte[] data) throws Exception {
		File zipFile = null;
		try{
			String file = "WEB" + new Random().nextLong();
			zipFile = new File(UtentiUtilities.getOutboxDir() + file + ".zip");
			FileOutputStream fos = null;
			zipFile.createNewFile();
			fos = new FileOutputStream(zipFile);
			fos.write(data);
			fos.close();
			org.govmix.proxy.igrue.managers.Trasmissione trasmissione;
			try {
				Spedizione spedizione = new Spedizione(file, UtentiUtilities.getIdUtente());
				trasmissione = new org.govmix.proxy.igrue.managers.Trasmissione(spedizione, Integer.MAX_VALUE);
			} catch (Exception e) {
				MailManager.postMail(UtentiUtilities.getIdUtente(), "INV_FIL_06", e);
				throw new Exception ("Sistema non disponibile. Errore nel database.", e);
			}
		    	
	    	//Creo il contesto
	    	try{FileUtils.makeContext(trasmissione, UtentiUtilities.getIdUtente());}
	    	catch(Exception e){
	    		MailManager.postMail(UtentiUtilities.getIdUtente(), "INV_FIL_03", e);
	    		throw new Exception ("Errore nella crezione del contesto.", e);
			}
		    	
			
			return trasmissione.getFileId();
		} catch (Exception e) {
			if (zipFile != null) {
				zipFile.delete();
			}
			throw e;
		}
	}
	
	public List<String> getTitoli(){
		List<String> titoli = new ArrayList<String>();
		titoli.add("Codice Id.");
		titoli.add("?");
		titoli.add("?");
		titoli.add("?");
		titoli.add("?");
		titoli.add("?");
		titoli.add("?");
		titoli.add("?");
		titoli.add("?");
		titoli.add("?");
		return titoli;
	}
	public void delete(TrasmissioneDTO obj) {
		// Non implementare.
	}
	public void deleteById(IdTrasmissione key) {
		// Non implementare.
	}
	public void store(TrasmissioneDTO obj) {
		// Non implementare.
	}
	public byte[] getDatiContesto(int file) throws IOException{
		File zip = new File(UtentiUtilities.getInboxDir() + file + "/" + file + ".zip");
		byte[] querys = new byte[(int)zip.length()];
		FileInputStream strm = new FileInputStream(zip);
		strm.read(querys);
		strm.close();
		return querys;
	}
	
	public String getStato(int file) throws ServiceException{
		
		IdTrasmissione key = new IdTrasmissione();
		key.setFile(file);
		key.setUtente(UtentiUtilities.getIdUtente());
		TrasmissioneDTO t = findById(key);
		return t.getStato();
	}
	
	public String getStato(String stato, Date data, Integer esito){
		if(	stato != null && stato.trim().compareTo("") != 0) {
			return stato;
		} 
		if (data == null){
			return "File in attesa di spedizione";
		}
		if (esito != 0){
			return "File spedito con errore, in attesa di nuova trasmissione.";
		}
		return "File spedito, in attesa di eventi.";
	}

	//@Override
	public List<TrasmissioneDTO> findAll(int start, int limit)
			throws ServiceException {
		try {
			IPaginatedExpression exp = this.trasmissioneSearch.newPaginatedExpression();
			Utente sistema = UtentiUtilities.getUtente();
			exp.and();
			exp.equals(Trasmissione.model().ID_AMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(Trasmissione.model().ID_SISTEMA, sistema.getIdSistema());

			exp.offset(start);
			exp.limit(limit);
			exp.addOrder(Trasmissione.model().DATAULTIMOINVIO);
			exp.sortOrder(SortOrder.DESC);
			return _findAll(exp);
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionNotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionException e) {
			throw new ServiceException(e);
		}
	}


	private List<TrasmissioneDTO> _findAll(IPaginatedExpression exp) throws ServiceException {
		try {
			List<TrasmissioneDTO> lst = new ArrayList<TrasmissioneDTO>();
			List<Trasmissione> lstTrasmissioni = this.trasmissioneSearch.findAll(exp);
			for(Trasmissione t: lstTrasmissioni) {
				TrasmissioneDTO trasmissioneDto = new TrasmissioneDTO();
				trasmissioneDto.setFile(t.getFile());
				trasmissioneDto.setDataultimoinvio(t.getDataultimoinvio());
				trasmissioneDto.setEsitoultimoinvio(t.getEsitoultimoinvio());
				trasmissioneDto.setEsitoultimoinviodescrizione(t.getEsitoultimoinviodescrizione());
				trasmissioneDto.setStato(t.getStato());
				trasmissioneDto.setTicket(t.getTicket());
				trasmissioneDto.setNotificato(t.getNotificato());
				trasmissioneDto.setIdAmministrazione(t.getIdAmministrazione());
				trasmissioneDto.setIdSistema(t.getIdSistema());
				
				lst.add(trasmissioneDto);
			}
			return lst;
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		}
	}

	//@Override
	public TrasmissioneDTO findById(IdTrasmissione key) throws ServiceException {
		try {
			
			Trasmissione t = this.trasmissioneSearch.get(key);
			TrasmissioneDTO trasmissioneDto = new TrasmissioneDTO();
			trasmissioneDto.setFile(t.getFile());
			trasmissioneDto.setDataultimoinvio(t.getDataultimoinvio());
			trasmissioneDto.setEsitoultimoinvio(t.getEsitoultimoinvio());
			trasmissioneDto.setEsitoultimoinviodescrizione(t.getEsitoultimoinviodescrizione());
			trasmissioneDto.setStato(t.getStato());
			trasmissioneDto.setTicket(t.getTicket());
			trasmissioneDto.setNotificato(t.getNotificato());
			trasmissioneDto.setIdAmministrazione(t.getIdAmministrazione());
			trasmissioneDto.setIdSistema(t.getIdSistema());
			
			return trasmissioneDto;
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (NotFoundException e) {
			throw new ServiceException(e);
		} catch (MultipleResultException e) {
			throw new ServiceException(e);
		}
	}

	//@Override
	public List<TrasmissioneDTO> findAll() throws ServiceException {
		
		try {
			IPaginatedExpression exp = this.trasmissioneSearch.newPaginatedExpression();
			Utente sistema = UtentiUtilities.getUtente();
			exp.and();
			exp.equals(Trasmissione.model().ID_AMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(Trasmissione.model().ID_SISTEMA, sistema.getIdSistema());
			exp.addOrder(Trasmissione.model().FILE);
			exp.sortOrder(SortOrder.DESC);
			return _findAll(exp);
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionNotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionException e) {
			throw new ServiceException(e);
		}
	}
}
