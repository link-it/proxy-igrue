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

import java.io.Serializable;
import java.util.List;

import org.govmix.proxy.igrue.web.service.IDettaglio;

public class EsitoDTO implements Serializable{

	private static final long serialVersionUID = -6646555893244897386L;
	private Integer id;
	private String nome;
	private String descrizioneEsito;
	private Boolean esitoPresente;
	private List<IDettaglio> dettagliEsito;
	private String parteVariabileLbl;
	
	public Integer getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getDescrizioneEsito() {
		return descrizioneEsito;
	}
	public Boolean getEsitoPresente() {
		return esitoPresente;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDescrizioneEsito(String descrizioneEsito) {
		this.descrizioneEsito = descrizioneEsito;
	}
	public void setEsitoPresente(Boolean esitoPresente) {
		this.esitoPresente = esitoPresente;
	}
	public List<IDettaglio> getDettagliEsito() {
		return dettagliEsito;
	}
	public void setDettagliEsito(List<IDettaglio> dettagliEsito) {
		this.dettagliEsito = dettagliEsito;
	}
	public String getParteVariabileLbl() {
		return parteVariabileLbl;
	}
	public void setParteVariabileLbl(String parteVariabileLbl) {		
		this.parteVariabileLbl = parteVariabileLbl;
	}
	
	
}
