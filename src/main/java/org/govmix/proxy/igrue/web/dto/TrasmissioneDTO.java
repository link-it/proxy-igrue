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
package org.govmix.proxy.igrue.web.dto;

import java.util.Date;

public class TrasmissioneDTO{

	private String stato, esitoultimoinviodescrizione;
	private Integer esitoultimoinvio;
	private int file;
	private boolean notificato;
	private String ticket;
	private Date dataultimoinvio;
	
	private String idAmministrazione;
	public String getIdAmministrazione() {
		return idAmministrazione;
	}

	public void setIdAmministrazione(String idAmministrazione) {
		this.idAmministrazione = idAmministrazione;
	}

	private Integer idSistema;
	
	public TrasmissioneDTO() {
	}

	public TrasmissioneDTO(int file, Date dataultimoinvio, Integer esitoultimoinvio, String esitoultimoinviodescrizione,  boolean notificato,String stato, String ticket, String idAmministrazione, Integer idSistema) {
		this.setStato(stato);
		this.setDataultimoinvio(dataultimoinvio);
		this.setEsitoultimoinvio(esitoultimoinvio);
		this.setEsitoultimoinviodescrizione(esitoultimoinviodescrizione);
		this.setFile(file);
		this.setNotificato(notificato);
		this.setTicket(ticket);
		this.setIdAmministrazione(idAmministrazione);
		this.setIdSistema(idSistema);
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getStato() {
		return stato;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getTicket() {
		return ticket;
	}

	public void setEsitoultimoinviodescrizione(
			String esitoultimoinviodescrizione) {
		this.esitoultimoinviodescrizione = esitoultimoinviodescrizione;
	}

	public String getEsitoultimoinviodescrizione() {
		return esitoultimoinviodescrizione;
	}

	public void setFile(int file) {
		this.file = file;
	}

	public int getFile() {
		return file;
	}

	public void setEsitoultimoinvio(Integer esitoultimoinvio) {
		this.esitoultimoinvio = esitoultimoinvio;
	}

	public Integer getEsitoultimoinvio() {
		return esitoultimoinvio;
	}

	public void setNotificato(boolean notificato) {
		this.notificato = notificato;
	}

	public boolean isNotificato() {
		return notificato;
	}

	public void setDataultimoinvio(Date dataultimoinvio) {
		this.dataultimoinvio = dataultimoinvio;
	}

	public Date getDataultimoinvio() {
		return dataultimoinvio;
	}

	public Integer getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(Integer idSistema) {
		this.idSistema = idSistema;
	}
}
