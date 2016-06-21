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
import java.util.List;

import org.govmix.proxy.igrue.web.ejb.MailTrace;
import org.govmix.proxy.igrue.web.ejb.MailTraceId;
import org.govmix.proxy.igrue.web.ejb.Utente;
import org.govmix.proxy.igrue.web.ejb.dao.IMailTraceServiceSearch;
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

public class MailTraceImpl implements IMailTrace {

	private IMailTraceServiceSearch mailTrace;

	public MailTraceImpl() throws ServiceException {
		try {
			this.mailTrace = ServiceManagerFactory.getServiceManager().getMailTraceServiceSearch();
		} catch(SQLException e) {
			throw new ServiceException(e);
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		}
	}

	public List<MailTrace> findAll(int start, int limit) throws ServiceException {
		try {

			IPaginatedExpression exp = mailTrace.newPaginatedExpression();
		
			Utente sistema = UtentiUtilities.getUtente();
			exp.and();
			exp.equals(MailTrace.model().ID_AMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(MailTrace.model().ID_SISTEMA, sistema.getIdSistema());

			exp.offset(start);
			exp.limit(limit);
			exp.addOrder(MailTrace.model().TIME);
			exp.sortOrder(SortOrder.DESC);
			return mailTrace.findAll(exp);

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
			IExpression exp = mailTrace.newExpression();

			Utente sistema = UtentiUtilities.getUtente();
			exp.and();
			exp.equals(MailTrace.model().ID_AMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(MailTrace.model().ID_SISTEMA, sistema.getIdSistema());

			NonNegativeNumber res = mailTrace.count(exp);
			return res != null ? (int) res.longValue() : 0;
			} catch (NotImplementedException e) {
				throw new ServiceException(e);
			} catch (ExpressionNotImplementedException e) {
				throw new ServiceException(e);
			} catch (ExpressionException e) {
				throw new ServiceException(e);
			}
	}
	
	public List<MailTrace> findAll() throws ServiceException {
		try {

			IPaginatedExpression exp = mailTrace.newPaginatedExpression();
			
			Utente sistema = UtentiUtilities.getUtente();
			exp.and();
			exp.equals(MailTrace.model().ID_AMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(MailTrace.model().ID_SISTEMA, sistema.getIdSistema());

			return mailTrace.findAll(exp);

		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionNotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionException e) {
			throw new ServiceException(e);
		}
	}
	public void delete(MailTrace obj) {
		// TODO Auto-generated method stub
		
	}
	public void deleteById(MailTraceId key) {
		// TODO Auto-generated method stub
		
	}
	public MailTrace findById(MailTraceId key) throws ServiceException {
		try {

			return mailTrace.get(key);

		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (NotFoundException e) {
			throw new ServiceException(e);
		} catch (MultipleResultException e) {
			throw new ServiceException(e);
		}
	}
	public void store(MailTrace obj) {
		// TODO Auto-generated method stub
		
	}
	
}
