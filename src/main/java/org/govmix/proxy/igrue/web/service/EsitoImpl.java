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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.govmix.proxy.igrue.web.ejb.Esito;
import org.govmix.proxy.igrue.web.ejb.IdEsito;
import org.govmix.proxy.igrue.web.ejb.IdTrasmissione;
import org.govmix.proxy.igrue.web.ejb.Utente;
import org.govmix.proxy.igrue.web.ejb.dao.IEsitoServiceSearch;
import org.govmix.proxy.igrue.web.ejb.utils.UtentiUtilities;
import org.openspcoop2.generic_project.beans.NonNegativeNumber;
import org.openspcoop2.generic_project.exception.ExpressionException;
import org.openspcoop2.generic_project.exception.ExpressionNotImplementedException;
import org.openspcoop2.generic_project.exception.MultipleResultException;
import org.openspcoop2.generic_project.exception.NotFoundException;
import org.openspcoop2.generic_project.exception.NotImplementedException;
import org.openspcoop2.generic_project.exception.ServiceException;
import org.openspcoop2.generic_project.expression.IExpression;
import org.openspcoop2.generic_project.expression.IPaginatedExpression;

public class EsitoImpl implements IEsito {

	private IEsitoServiceSearch esitoSearch;

	public EsitoImpl() throws ServiceException {
		try {
			this.esitoSearch = ServiceManagerFactory.getServiceManager().getEsitoServiceSearch();
		} catch(SQLException e) {
			throw new ServiceException(e);
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		}
	}

	public List<Esito> findAll(int start, int limit) throws ServiceException {
		try {

			IPaginatedExpression exp = esitoSearch.newPaginatedExpression();

			Utente sistema = UtentiUtilities.getUtente();
			exp.and();
			exp.equals(Esito.model().ID_AMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(Esito.model().ID_SISTEMA, sistema.getIdSistema());
			
			exp.offset(start);
			exp.limit(limit);
			exp.addOrder(Esito.model().DATAULTIMOCONTROLLO);

			return esitoSearch.findAll(exp);

		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionNotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionException e) {
			throw new ServiceException(e);
		}

	}


	public int totalCount() throws ServiceException {
		try {
			Utente sistema = UtentiUtilities.getUtente();
			IExpression exp = esitoSearch.newExpression();
			exp.and();
			exp.equals(Esito.model().ID_AMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(Esito.model().ID_SISTEMA, sistema.getIdSistema());
			

			NonNegativeNumber cnt =  esitoSearch.count(exp);
			return (cnt != null) ? (int) cnt.longValue() : 0;
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionNotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionException e) {
			throw new ServiceException(e);
		}
	}

	public void delete(Esito obj) {
	}

	public void deleteById(IdEsito key) {
	}

	public List<Esito> findAll() throws ServiceException {
		try {
			IPaginatedExpression exp = esitoSearch.newPaginatedExpression();
			Utente sistema = UtentiUtilities.getUtente();
			exp.and();
			exp.equals(Esito.model().ID_AMMINISTRAZIONE, sistema.getIdAmministrazione());
			exp.equals(Esito.model().ID_SISTEMA, sistema.getIdSistema());
			
			
			return esitoSearch.findAll(exp);
		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionNotImplementedException e) {
			throw new ServiceException(e);
		} catch (ExpressionException e) {
			throw new ServiceException(e);
		}
	}
	public Esito findById(Integer key) throws ServiceException {
		IdEsito id = new IdEsito();

		IdTrasmissione idTrasmissione = new IdTrasmissione();
		idTrasmissione.setFile(key);
		idTrasmissione.setUtente(UtentiUtilities.getIdUtente());
		id.setTrasmissione(idTrasmissione);
		
		return this.findById(id);
	}
	public Esito findById(IdEsito key) throws ServiceException {
		try {

			return esitoSearch.get(key);

		} catch (NotImplementedException e) {
			throw new ServiceException(e);
		} catch (NotFoundException e) {
			throw new ServiceException(e);
		} catch (MultipleResultException e) {
			throw new ServiceException(e);
		}
	}

	public void store(Esito obj) {
	}

	public String getDescrizioneEsitoElaborazioni(Integer key) throws ServiceException {
		Esito e = findById(key);
		if(e.getEsitoelaborazioneperanagraficadiriferimento() == 2147483647) return "Esito ancora non ricevuto.";
		return "["  + e.getEsitoelaborazioneperanagraficadiriferimento() + "] " + e.getEsitoelaborazioneperanagraficadiriferimentodescrizione();
	}

	public String getDescrizioneLogErrori(Integer key) throws ServiceException {
		Esito e = findById(key);
		if(e.getLogdeglierrori() == 2147483647) return "Esito ancora non ricevuto.";
		return "["  + e.getLogdeglierrori() + "] " + e.getLogdeglierroridescrizione();
	}

	public String getDescrizioneStatisticheElaborazioni(Integer key) throws ServiceException {
		Esito e = findById(key);
		if(e.getStatisticheelaborazioni() == 2147483647) return "Esito ancora non ricevuto.";
		return "["  + e.getStatisticheelaborazioni() + "] " + e.getStatisticheelaborazionidescrizione();
	}

	public String getDescrizioneStatisticheScarti(Integer key) throws ServiceException {
		Esito e = findById(key);
		if(e.getStatistichescarti() == 2147483647) return "Esito ancora non ricevuto.";
		return "["  + e.getStatistichescarti() + "] " + e.getStatistichescartidescrizione();
	}

	public List<IDettaglio> getDettaglioEsitoElaborazioni(Integer key) {
		return Utils.getDettagli(UtentiUtilities.getInboxDir() + key + "/RISULTATI/esitoElaborazionePerAnagraficaDiRiferimento.zip");
	}

	public List<IDettaglio> getDettaglioLogErrori(Integer key) {
		return Utils.getDettagli(UtentiUtilities.getInboxDir() + key + "/RISULTATI/logDegliErrori.zip");
	}

	public List<IDettaglio> getDettaglioStatisticheElaborazioni(Integer key) {
		return Utils.getDettagli(UtentiUtilities.getInboxDir() + key + "/RISULTATI/statisticheElaborazioni.zip");
	}

	public List<IDettaglio> getDettaglioStatisticheScarti(Integer key) {
		return Utils.getDettagli(UtentiUtilities.getInboxDir() + key + "/RISULTATI/statisticheScarti.zip");
	}

	public boolean isEsitoElaborazioniPresente(Integer key) {
		return new File(UtentiUtilities.getInboxDir() + key + "/RISULTATI/esitoElaborazionePerAnagraficaDiRiferimento.zip").exists();
	}

	public boolean isLogErroriPresente(Integer key) {
		return new File(UtentiUtilities.getInboxDir() + key + "/RISULTATI/logDegliErrori.zip").exists();
	}

	public boolean isStatisticheElaborazioniPresente(Integer key) {
		return new File(UtentiUtilities.getInboxDir() + key + "/RISULTATI/statisticheElaborazioni.zip").exists();
	}

	public boolean isStatisticheScartiPresente(Integer key) {
		return new File(UtentiUtilities.getInboxDir() + key + "/RISULTATI/statisticheScarti.zip").exists();
	}
	public byte[] getDettaglioEsitoElaborazioniZip(Integer key) throws IOException {
		File zip = new File(UtentiUtilities.getInboxDir() + key + "/RISULTATI/esitoElaborazionePerAnagraficaDiRiferimento.zip");
		byte[] querys = new byte[(int)zip.length()];
		FileInputStream strm = new FileInputStream(zip);
		strm.read(querys);
		strm.close();
		return querys;
	}
	public byte[] getDettaglioLogErroriZip(Integer key) throws IOException {
		File zip = new File(UtentiUtilities.getInboxDir() + key + "/RISULTATI/logDegliErrori.zip");
		byte[] querys = new byte[(int)zip.length()];
		FileInputStream strm = new FileInputStream(zip);
		strm.read(querys);
		strm.close();
		return querys;
	}
	public byte[] getDettaglioStatisticheElaborazioniZip(Integer key)  throws IOException{
		File zip = new File(UtentiUtilities.getInboxDir() + key + "/RISULTATI/statisticheElaborazioni.zip");
		byte[] querys = new byte[(int)zip.length()];
		FileInputStream strm = new FileInputStream(zip);
		strm.read(querys);
		strm.close();
		return querys;
	}
	public byte[] getDettaglioStatisticheScartiZip(Integer key)  throws IOException{
		File zip = new File(UtentiUtilities.getInboxDir() + key + "/RISULTATI/statisticheScarti.zip");
		byte[] querys = new byte[(int)zip.length()];
		FileInputStream strm = new FileInputStream(zip);
		strm.read(querys);
		strm.close();
		return querys;
	}
}