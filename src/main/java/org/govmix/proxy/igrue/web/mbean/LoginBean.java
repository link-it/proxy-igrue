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
package org.govmix.proxy.igrue.web.mbean;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.govmix.proxy.igrue.web.ejb.Utente;
import org.govmix.proxy.igrue.web.service.ILoginDAO;
import org.openspcoop2.generic_project.exception.NotFoundException;


public class LoginBean {

	//	private String username;
	private String pwd;
	private String idAmministrazione;
	private String idSistema;
	private Boolean isLoggedIn=false;
	private Utente loggedUtente = null;


	private ILoginDAO loginDao;

	public void setLoginDao(ILoginDAO loginDao) {
		this.loginDao = loginDao;
	}

	public Boolean getIsLoggedIn() {
		return isLoggedIn;
	}

	public String getIsLoggedInAsString() {
		return isLoggedIn.toString();
	}

	public void setIsLoggedIn(Boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String logout(){

		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getExternalContext().getSessionMap().put("loginBean", null);
		HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
		session.invalidate();
		return "login";
	}

	public String login(){

		if(null == this.idAmministrazione || null==this.pwd || null==this.idSistema){		
			return "login";
		}

		try{
			Integer.parseInt(this.idSistema);
		}catch(NumberFormatException e){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Errore: Id Sistema non valido, sono ammessi solo valori numerici.",null));
			return "login";
		}

		try{
			if(this.loginDao.login(this.idAmministrazione, this.idSistema, this.pwd)){
				//			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				//			HttpSession session = (HttpSession) ec.getSession(true);
				//			session.setAttribute("logged", true);
				this.isLoggedIn = true;
				this.setLoggedUtente(this.loginDao.getUtente(this.idAmministrazione, this.idSistema));

				//

				return "loginSuccess";
			}else{
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Errore: Id Amministazione e/o Id Sistema o password non validi.",null));
			}
		} 
		catch(NotFoundException e){
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Errore: Id Amministazione e/o Id Sistema non validi.",null));
		}

		return "login";
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	public String getIdAmministrazione() {
		return idAmministrazione;
	}

	public void setIdAmministrazione(String idAmministrazione) {
		this.idAmministrazione = idAmministrazione;
	}

	public Utente getLoggedUtente() {
		return loggedUtente;
	}

	public void setLoggedUtente(Utente loggedUtente) {
		this.loggedUtente = loggedUtente;
	}

	public String getUsername(){
		return this.idAmministrazione + "_" + this.idSistema; 
	}




}
