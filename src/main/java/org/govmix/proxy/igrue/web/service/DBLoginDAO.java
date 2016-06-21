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

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.web.ejb.Utente;
import org.govmix.proxy.igrue.web.ejb.dao.IUtenteServiceSearch;
import org.openspcoop2.generic_project.exception.ExpressionException;
import org.openspcoop2.generic_project.exception.ExpressionNotImplementedException;
import org.openspcoop2.generic_project.exception.MultipleResultException;
import org.openspcoop2.generic_project.exception.NotFoundException;
import org.openspcoop2.generic_project.exception.NotImplementedException;
import org.openspcoop2.generic_project.exception.ServiceException;

public class DBLoginDAO implements ILoginDAO{

	private static Logger log = Logger.getLogger(DBLoginDAO.class);

	private IUtenteServiceSearch utenteDAO = null;

	public DBLoginDAO(){
		try {
			this.utenteDAO = ServiceManagerFactory.getServiceManager().getUtenteServiceSearch();
		} catch (ServiceException e) {
			DBLoginDAO.log.error(e, e);
		} catch (NotImplementedException e) {
			DBLoginDAO.log.error(e, e);
		} catch (SQLException e) {
			DBLoginDAO.log.error(e, e);
		}
	}

	//@Override
	public boolean login(String username, String pwd) {
		return false;
	}

	//@Override
	public boolean login(String idAmministrazione, String idSistema, String pwd) throws NotFoundException {

		try {
			Integer i = new Integer(idSistema);

			Utente u = this.utenteDAO.find(this.utenteDAO.newExpression()
					.equals(Utente.model().ID_AMMINISTRAZIONE, idAmministrazione).and()
					.equals(Utente.model().ID_SISTEMA, i));

//		//	Password passwordManager = new Password();
//			try{
//				return passwordManager.checkPw(pwd, u.getPassword());
//			} catch(IndexOutOfBoundsException e){
//				DBLoginDAO.log.error(e, e);
//				return false;
//			}
			
			return pwd.equals(u.getPassword());
			
		} catch (ServiceException e) {
			DBLoginDAO.log.error(e, e);
			return false;
		} catch (NotFoundException e1) {
			throw e1;
		} catch (MultipleResultException e) {
			DBLoginDAO.log.error(e, e);
			return false;
		} catch (NotImplementedException e) {
			DBLoginDAO.log.error(e, e);
			return false;
		} catch (ExpressionNotImplementedException e) {
			DBLoginDAO.log.error(e, e);
			return false;
		} catch (ExpressionException e) {
			DBLoginDAO.log.error(e, e);
			return false;
		}
	}

	//@Override
	public Utente getUtente(String idAmministrazione, String idSistema) {
		try {
			Integer i = new Integer(idSistema);

			return this.utenteDAO.find(this.utenteDAO.newExpression()
					.equals(Utente.model().ID_AMMINISTRAZIONE, idAmministrazione).and()
					.equals(Utente.model().ID_SISTEMA, i));

		} catch (ServiceException e) {
			DBLoginDAO.log.error(e, e);
			return null;
		} catch (NotFoundException e1) {
			return null;
		} catch (MultipleResultException e) {
			DBLoginDAO.log.error(e, e);
			return null;
		} catch (NotImplementedException e) {
			DBLoginDAO.log.error(e, e);
			return null;
		} catch (ExpressionNotImplementedException e) {
			DBLoginDAO.log.error(e, e);
			return null;
		} catch (ExpressionException e) {
			DBLoginDAO.log.error(e, e);
			return null;
		}
	}

}
