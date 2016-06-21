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

import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.govmix.proxy.igrue.web.ejb.MailTemplate;
import org.govmix.proxy.igrue.web.ejb.MailTemplateId;
import org.govmix.proxy.igrue.web.ejb.Utente;
import org.govmix.proxy.igrue.web.ejb.dao.IMailTemplateService;
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
import org.springframework.transaction.annotation.Transactional;

public class MailTemplateImpl implements IMailTemplate {

	private IMailTemplateService mailTemplate;
	
	public MailTemplateImpl() throws ServiceException {
		try {
			this.mailTemplate = ServiceManagerFactory.getServiceManager().getMailTemplateService();
		} catch(SQLException e) {
			throw new ServiceException(e);
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional(readOnly=true)
	public List<MailTemplate> findAll(int start, int limit) throws ServiceException {
		try {

			IPaginatedExpression exp = mailTemplate.newPaginatedExpression();
			Utente sistema = UtentiUtilities.getUtente();
			exp.and();
			exp.equals(MailTemplate.model().ID_AMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(MailTemplate.model().ID_SISTEMA, sistema.getIdSistema());
			exp.offset(start);
			exp.limit(limit);
			exp.addOrder(MailTemplate.model().TEMPLATE_CODE);
			exp.sortOrder(SortOrder.ASC);
			
			return mailTemplate.findAll(exp);

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
			IExpression exp = mailTemplate.newExpression();
			Utente sistema = UtentiUtilities.getUtente();
			exp.and();
			exp.equals(MailTemplate.model().ID_AMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(MailTemplate.model().ID_SISTEMA, sistema.getIdSistema());

			NonNegativeNumber res = mailTemplate.count(exp);
			return res != null ? (int) res.longValue() : 0;
			} catch (NotImplementedException e) {
				throw new ServiceException(e);
			} catch (ExpressionNotImplementedException e) {
				throw new ServiceException(e);
			} catch (ExpressionException e) {
				throw new ServiceException(e);
			}
	}

	public void delete(MailTemplate obj) {
	}

	public void deleteById(MailTemplateId key) {
	}

	public List<MailTemplate> findAll() throws ServiceException {
		try {

			IPaginatedExpression exp = mailTemplate.newPaginatedExpression();
			Utente sistema = UtentiUtilities.getUtente();
			exp.and();
			exp.equals(MailTemplate.model().ID_AMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(MailTemplate.model().ID_SISTEMA, sistema.getIdSistema());

			return mailTemplate.findAll(exp);

		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionNotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionException e) {
			throw new ServiceException(e);
		}
	}

	public MailTemplate findById(MailTemplateId key) throws ServiceException {
		try {

			return mailTemplate.get(key);

		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (NotFoundException e) {
			throw new ServiceException(e);
		} catch (MultipleResultException e) {
			throw new ServiceException(e);
		}

	}

	public void store(MailTemplate obj) throws ServiceException {
		try {

			MailTemplateId id = new MailTemplateId();
			id.setTemplateCode(obj.getTemplateCode());
			IdUtente utente = new IdUtente();
			utente.setIdAmministrazione(obj.getIdAmministrazione());
			utente.setIdSistema(obj.getIdSistema());
			id.setUtente(utente);
			mailTemplate.updateOrCreate(id, obj);

		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		}

	}
}
