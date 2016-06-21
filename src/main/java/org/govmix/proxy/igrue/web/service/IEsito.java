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

import java.io.IOException;
import java.util.List;

import org.govmix.proxy.igrue.web.ejb.Esito;
import org.govmix.proxy.igrue.web.ejb.IdEsito;
import org.openspcoop2.generic_project.exception.ServiceException;

public interface IEsito extends IService<Esito, IdEsito>{
	public boolean isStatisticheElaborazioniPresente(Integer key);
	public boolean isStatisticheScartiPresente(Integer key);
	public boolean isEsitoElaborazioniPresente(Integer key);
	public boolean isLogErroriPresente(Integer key);
	
	public byte[] getDettaglioStatisticheElaborazioniZip(Integer key) throws IOException;
	public byte[] getDettaglioStatisticheScartiZip(Integer key) throws IOException;
	public byte[] getDettaglioEsitoElaborazioniZip(Integer key) throws IOException;
	public byte[] getDettaglioLogErroriZip(Integer key) throws IOException;
	
	public List<IDettaglio> getDettaglioStatisticheElaborazioni(Integer key);
	public List<IDettaglio> getDettaglioStatisticheScarti(Integer key);
	public List<IDettaglio> getDettaglioEsitoElaborazioni(Integer key);
	public List<IDettaglio> getDettaglioLogErrori(Integer key);
	
	public String getDescrizioneStatisticheElaborazioni(Integer key) throws ServiceException;
	public String getDescrizioneStatisticheScarti(Integer key) throws ServiceException;
	public String getDescrizioneEsitoElaborazioni(Integer key) throws ServiceException;
	public String getDescrizioneLogErrori(Integer key) throws ServiceException;
}
