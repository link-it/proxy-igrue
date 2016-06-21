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

public class DettaglioHdrFtr implements IDettaglio {
	private String parteVariabile,tipoRecord;
	private int numeroProgressivo;
	public DettaglioHdrFtr(String tipoRecord, int numeroProgressivo, String parteVariabile){
		this.parteVariabile = parteVariabile;
		this.numeroProgressivo = numeroProgressivo;
		this.tipoRecord = tipoRecord;
	}
	public String getCodiceIdentificativo() {
		return "";
	}
	public int getNumeroProgressivo() {
		return numeroProgressivo;
	}
	public List<String> getParteVariabile() {
		List<String> pv = new ArrayList<String>();
		pv.add(parteVariabile);
		return pv;
	}
	public String getTipoRecord() {
		return tipoRecord;
	}
}
