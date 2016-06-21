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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.govmix.proxy.igrue.web.ejb.IdTabellacontesto;
import org.govmix.proxy.igrue.web.ejb.Tabellacontesto;
import org.govmix.proxy.igrue.web.ejb.Utente;
import org.govmix.proxy.igrue.web.ejb.dao.ITabellacontestoServiceSearch;
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


public class TabellacontestoImpl implements ITabellacontesto {

	private ITabellacontestoServiceSearch tabellacontestoSearch;


	public TabellacontestoImpl() throws ServiceException {
		try {
			this.tabellacontestoSearch = ServiceManagerFactory.getServiceManager().getTabellacontestoServiceSearch();
		} catch(SQLException e) {
			throw new ServiceException(e);
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		}
	}

	public List<Tabellacontesto> findAll(int start, int limit) throws ServiceException {
		try {
			IPaginatedExpression exp = this.tabellacontestoSearch.newPaginatedExpression();

			Utente sistema = UtentiUtilities.getUtente();
			exp.and();
			exp.equals(Tabellacontesto.model().ID_AMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(Tabellacontesto.model().ID_SISTEMA, sistema.getIdSistema());

			exp.greaterThan(Tabellacontesto.model().CHECKSUM, 0l);
			exp.addOrder(Tabellacontesto.model().NOMETABELLA);
			exp.sortOrder(SortOrder.ASC);
			exp.offset(start);
			exp.limit(limit);
			
			List<Tabellacontesto> tabelle = this.tabellacontestoSearch.findAll(exp);
			for(Tabellacontesto t : tabelle){
				t.setTitolo(getTitolo(t.getNometabella()));

			}
			return tabelle;
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
			IExpression exp = this.tabellacontestoSearch.newExpression();
			Utente sistema = UtentiUtilities.getUtente();
			exp.and();
			exp.equals(Tabellacontesto.model().ID_AMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(Tabellacontesto.model().ID_SISTEMA, sistema.getIdSistema());

			exp.greaterThan(Tabellacontesto.model().CHECKSUM, 0l);
			
			NonNegativeNumber cnt = this.tabellacontestoSearch.count(exp);
			return cnt!=null ? (int)cnt.longValue() : 0;
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionNotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionException e) {
			throw new ServiceException(e);
		}
	}

	public void delete(Tabellacontesto obj) {
	}

	public void deleteById(IdTabellacontesto key) {

	}

	public List<Tabellacontesto> findAll() throws ServiceException {
		try {
			IPaginatedExpression exp = this.tabellacontestoSearch.newPaginatedExpression();

			Utente sistema = UtentiUtilities.getUtente();
			exp.and();
			exp.equals(Tabellacontesto.model().ID_AMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(Tabellacontesto.model().ID_SISTEMA, sistema.getIdSistema());

			exp.greaterThan(Tabellacontesto.model().CHECKSUM, 0l);
			exp.addOrder(Tabellacontesto.model().NOMETABELLA);
			exp.sortOrder(SortOrder.ASC);
			
			List<Tabellacontesto> tabelle = this.tabellacontestoSearch.findAll(exp);
			for(Tabellacontesto t : tabelle){
				t.setTitolo(getTitolo(t.getNometabella()));
			}
			return tabelle;
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionNotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionException e) {
			throw new ServiceException(e);
		}
	}

	public Tabellacontesto findById(IdTabellacontesto key) throws ServiceException {
		try {
			return this.tabellacontestoSearch.get(key);
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (NotFoundException e) {
			throw new ServiceException(e);
		} catch (MultipleResultException e) {
			throw new ServiceException(e);
		}

	}

	public void store(Tabellacontesto obj) {
	}

	public List<IDettaglioTabella> getDettaglio(String key){
		return Utils.getDettagliTabelle(UtentiUtilities.getContestoDir() + key + ".zip");
	}
	
	public List<String> getTitoli(String key){
		return Utils.getTitoli(key);
	}
	
	public byte[] getTabellaContesto(String tab) throws IOException {
		File zip = new File(UtentiUtilities.getContestoDir() + tab + ".zip");
		byte[] querys = new byte[(int)zip.length()];
		FileInputStream strm = new FileInputStream(zip);
		strm.read(querys);
		strm.close();
		return querys;
	}

	//@Override
	public String getTitolo(String key) {
		return Utils.getTitolo(key);
	}
}