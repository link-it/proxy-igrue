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

public class DettaglioDatiContestoImpl implements IDettaglioDatiContesto {
	private String tipo, progressivo, codice, parteVariabile;
	List<IDatoContesto> dati;
	public DettaglioDatiContestoImpl(String tipo, String progressivo, String codice, String parteVariabile){
		this.tipo = tipo;
		this.progressivo = progressivo;
		this.codice = codice;
		this.parteVariabile = parteVariabile;
		String[] pvs = this.parteVariabile.split("\\|");
		dati = new ArrayList<IDatoContesto>();
		dati.add(new DatoContestoImpl(this.codice));
		for(int i = 0; i < 9; i++){
			try{
				dati.add(new DatoContestoImpl(pvs[i]));
			} catch( Exception e) { 
				dati.add(new DatoContestoImpl(""));
			}
		}
		
	}

	public String getNumeroProgressivo() {
		return progressivo;
	}

	public List<IDatoContesto> getParteVariabile() {
		return dati;
	}

	public String getTipoRecord() {
		return tipo;
	}
	
}
