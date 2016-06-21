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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.managers.UserManager;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.govmix.proxy.igrue.web.ejb.Utente;
import org.govmix.proxy.igrue.web.ejb.dao.IUtenteService;
import org.openspcoop2.generic_project.exception.ExpressionException;
import org.openspcoop2.generic_project.exception.ExpressionNotImplementedException;
import org.openspcoop2.generic_project.exception.MultipleResultException;
import org.openspcoop2.generic_project.exception.NotFoundException;
import org.openspcoop2.generic_project.exception.NotImplementedException;
import org.openspcoop2.generic_project.exception.ServiceException;
import org.openspcoop2.generic_project.expression.IExpression;

public class UserService implements IUserService {
	
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(UserService.class);
	
	private UserManager userManager = null;
	
	private IUtenteService utenteDAO = null;
	
	public UserService(){
		this.userManager = new UserManager();
	}

	public List<Utente> findAll(int start, int limit) throws ServiceException {
		return new ArrayList<Utente>();
	}

	public int totalCount() throws ServiceException {
		return 0;
	}

	public void store(Utente obj) throws ServiceException {
		IdUtente idUtente = new IdUtente();
		
		idUtente.setIdAmministrazione(obj.getIdAmministrazione());
		idUtente.setIdSistema(obj.getIdSistema());
		
//		 if(this.utenteDAO.exists(idUtente))
//			 this.utenteDAO.update(idUtente, obj);
//		 else 
//			 this.utenteDAO.create(obj);
		
		
	}

	public void deleteById(String key) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	public void delete(Utente obj) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	public Utente findById(String key) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Utente> findAll() throws ServiceException {
		return new ArrayList<Utente>();
	}

	public Utente find(Utente user) throws Exception {
		try{
		IExpression expr = this.utenteDAO.newExpression();
		
		expr.equals(Utente.model().ID_AMMINISTRAZIONE, user.getIdAmministrazione()).and().equals(Utente.model().ID_SISTEMA, user.getIdSistema());
		
		return this.utenteDAO.find(expr);
		} catch(ServiceException e ){
			throw e;
		} catch (NotFoundException e) {
			throw e;
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

	public boolean createUtente(Utente utente, String adminPwd)
			throws Exception {
		return this.userManager.createUtente(utente, adminPwd);
	}

}
