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
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.web.ejb.Utente;
import org.govmix.proxy.igrue.web.service.IService;
import org.govmix.proxy.igrue.web.service.IUserService;
import org.govmix.proxy.igrue.web.service.UserService;

public class UtentiBean extends BaseBean<Utente, Long>{

	private static Logger log =  Logger.getLogger(UtentiBean.class);

	private Utente utente = null;

	private IUserService userService = null;
	
	private String pwdAdmin;

	public UtentiBean(){
			this.utente = new Utente();
			
			this.userService = new UserService();
	}
	
	@Override
	public void setService(IService<Utente, Long> service) {
		super.setService(service);
	}

	public String salva() throws Exception {

		UtentiBean.log.debug("Salvataggio utente...");

		if (this.utente.getId() != null && this.utente.getId() < 1) {
			// nuovo utente

//			// controllo se username disponibile
//			Utente exiting = null;
//			try {
//				exiting = (Utente) this.userService.find(this.utente);
//				// getEntityManager().createQuery("from User u where u.username=:nome").setParameter("nome",
//				// this.user.getLogin()).getSingleResult();
//			} catch (Exception e) {
//				exiting = null;
//			}
//			if (exiting != null) {
//				// errore
//				FacesContext.getCurrentInstance().addMessage(
//						null,
//						new FacesMessage(
//								"Esiste un utenza con questo Id Amministrazione e/o Id Sistema."));
//				return null;
//			}

			// [TODO] abilitare la decifratura in fase di lettura
			// cript pwd 
			//			Password passwordManager = new Password();
			//			this.utente.setPassword(passwordManager.cryptPw(this.utente
			//					.getPassword()));

			try{
			// salvo il nuovo utente
			if(!this.userService.createUtente(this.utente, this.pwdAdmin)){
				// errore
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Esiste un utenza con questo Id Amministrazione e/o Id Sistema.",null));
				return null;
			}
			}catch(Exception e){
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Si e' verificato un errore interno.",null));
				return null;
			}

			// carico il valore dell'id dell'utente creato, necessario per
			// inserire le altre informazioni ad esso collegato
			//this.utente = this.userService.find(this.utente);	
		}

		return "creazioneUtenzaOk";
	}

	public Utente getUtente() {
		if(this.utente == null)
			this.utente = new Utente();
		
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public String getPwdAdmin() {
		return pwdAdmin;
	}

	public void setPwdAdmin(String pwdAdmin) {
		this.pwdAdmin = pwdAdmin;
	}
	
	public void nuovoUtente(ActionEvent ae){
		this.setUtente(null);
	}


}
