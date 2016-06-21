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

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.web.core.Utils;
import org.govmix.proxy.igrue.web.ejb.Utente;
import org.openspcoop2.generic_project.exception.NotFoundException;


public class FileLoginDAO implements ILoginDAO {
	
	private static Logger log = Logger.getLogger(FileLoginDAO.class);
	
	public boolean login(String username, String pwd) {
		
		if(username==null || pwd == null){
			log.error("username e password null");
			return false;
		}
		
		try{			
			String u = Utils.getMessageFromJSFBundle("app_prop", "LOGIN_USERNAME");
			String p = Utils.getMessageFromJSFBundle("app_prop", "LOGIN_PWD");
			if(u.equals(username) && p.equals(pwd)) return true;
			else {
				log.error("Username o password non valide.");
				return false;
			}
		}catch (Exception e) {
			log.error("Errore durante login",e);
			return false;
		}
	}
	
	//@Override
	
	public boolean login(String idAmministrazione, String idSistema, String pwd) throws NotFoundException {
		//donothing
		return false;
	}

	//@Override
	public Utente getUtente(String idAmministrazione, String idSistema) {
		// TODO Auto-generated method stub
		return null;
	}
}
