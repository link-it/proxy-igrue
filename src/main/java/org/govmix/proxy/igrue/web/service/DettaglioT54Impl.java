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

public class DettaglioT54Impl implements IDettaglioTabella {
	List<String> valori;
	public DettaglioT54Impl(String tabella, String numero, String partevariabile){
		valori = new ArrayList<String>();
		valori.add(numero);
		String[] pvs = partevariabile.split("\\|",8);
		valori.add(pvs[0]);
		valori.add(pvs[1]);
		valori.add(pvs[2]);
		valori.add(pvs[3]);
		valori.add("[" + pvs[4] + "] " + pvs[5]);
		valori.add(pvs[6]);
		valori.add(pvs[7]);
	}
	public List<String> getValori() {
		return valori;
	}
}
