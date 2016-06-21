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
package org.govmix.proxy.igrue.web.listener;



import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.web.mbean.LoginBean;

public class HttpSessionCheckListener implements HttpSessionListener {

	private static Logger log = Logger.getLogger(HttpSessionCheckListener.class);
	
	public void sessionCreated(HttpSessionEvent e) {
		log.debug("session "+e.getSession().getId()+" created.");
		
	}

	public void sessionDestroyed(HttpSessionEvent e) {
		log.debug("session "+e.getSession().getId()+" destroyed.");
		try{
			cleanUp(e.getSession());
		}catch (Exception ex) {
			log.error("errore durante le operazioni di clean-up",ex);
		}
	}
	
	private void cleanUp(HttpSession session) throws Exception{
		//recuper utente dalla sessione se esiste
		LoginBean lb = (LoginBean)session.getAttribute("loginBean");
		if(lb!=null){
			log.debug("remove user "+lb.getUsername()+" from session");
			session.setAttribute("loginBean", null);
		}else{
			log.debug("no login info found in session, nothing to do.");
		}
	}
	
}
