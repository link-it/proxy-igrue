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
package org.govmix.proxy.igrue.web.ejb.dao.jdbc.fetch;

import org.openspcoop2.generic_project.beans.IModel;
import org.openspcoop2.generic_project.dao.jdbc.utils.AbstractJDBCFetch;
import org.openspcoop2.generic_project.dao.jdbc.utils.JDBCParameterUtilities;
import org.openspcoop2.generic_project.exception.ServiceException;

import java.sql.ResultSet;

import org.openspcoop2.utils.TipiDatabase;
import org.openspcoop2.utils.jdbc.IKeyGeneratorObject;

import org.govmix.proxy.igrue.web.ejb.Esito;


public class EsitoFetch extends AbstractJDBCFetch {

	//@Override
	public Object fetch(TipiDatabase tipoDatabase, IModel<?> model , ResultSet rs) throws ServiceException {
		
		try{
			JDBCParameterUtilities jdbcParameterUtilities =  
					new JDBCParameterUtilities(tipoDatabase);

			if(model.equals(Esito.model())){
				Esito object = new Esito();
				setParameter(object, "setIdAmministrazione", Esito.model().ID_AMMINISTRAZIONE.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "id_amministrazione", Esito.model().ID_AMMINISTRAZIONE.getFieldType()));
				setParameter(object, "setIdSistema", Esito.model().ID_SISTEMA.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "id_sistema", Esito.model().ID_SISTEMA.getFieldType()));
				setParameter(object, "setFile", Esito.model().FILE.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "file", Esito.model().FILE.getFieldType()));
				setParameter(object, "setStatisticheelaborazioni", Esito.model().STATISTICHEELABORAZIONI.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "statisticheelaborazioni", Esito.model().STATISTICHEELABORAZIONI.getFieldType()));
				setParameter(object, "setStatisticheelaborazionidescrizione", Esito.model().STATISTICHEELABORAZIONIDESCRIZIONE.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "statisticheelaborazionidescrizione", Esito.model().STATISTICHEELABORAZIONIDESCRIZIONE.getFieldType()));
				setParameter(object, "setStatistichescarti", Esito.model().STATISTICHESCARTI.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "statistichescarti", Esito.model().STATISTICHESCARTI.getFieldType()));
				setParameter(object, "setStatistichescartidescrizione", Esito.model().STATISTICHESCARTIDESCRIZIONE.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "statistichescartidescrizione", Esito.model().STATISTICHESCARTIDESCRIZIONE.getFieldType()));
				setParameter(object, "setEsitoelaborazioneperanagraficadiriferimento", Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTO.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "esitoelaborazioneperanagraficadiriferimento", Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTO.getFieldType()));
				setParameter(object, "setEsitoelaborazioneperanagraficadiriferimentodescrizione", Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTODESCRIZIONE.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "esitoelaborazioneperanagraficadiriferimentodescrizione", Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTODESCRIZIONE.getFieldType()));
				setParameter(object, "setLogdeglierrori", Esito.model().LOGDEGLIERRORI.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "logdeglierrori", Esito.model().LOGDEGLIERRORI.getFieldType()));
				setParameter(object, "setLogdeglierroridescrizione", Esito.model().LOGDEGLIERRORIDESCRIZIONE.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "logdeglierroridescrizione", Esito.model().LOGDEGLIERRORIDESCRIZIONE.getFieldType()));
				setParameter(object, "setLogdeglierroriricevuto", Esito.model().LOGDEGLIERRORIRICEVUTO.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "logdeglierroriricevuto", Esito.model().LOGDEGLIERRORIRICEVUTO.getFieldType()));
				setParameter(object, "setDataultimocontrollo", Esito.model().DATAULTIMOCONTROLLO.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "dataultimocontrollo", Esito.model().DATAULTIMOCONTROLLO.getFieldType()));
				return object;
			}
			
			else{
				throw new ServiceException("Model ["+model.toString()+"] not supported by fetch: "+this.getClass().getName());
			}	
					
		}catch(Exception e){
			throw new ServiceException("Model ["+model.toString()+"] occurs error in fetch: "+e.getMessage(),e);
		}
		
	}
	
	
	//@Override
	public IKeyGeneratorObject getKeyGeneratorObject( IModel<?> model )  throws ServiceException {
		
		try{

			if(model.equals(Esito.model())){
				return new org.openspcoop2.utils.jdbc.CustomKeyGeneratorObject("esiti","id","seq_esiti","esiti_init_seq");
			}
			
			else{
				throw new ServiceException("Model ["+model.toString()+"] not supported by getKeyGeneratorObject: "+this.getClass().getName());
			}

		}catch(Exception e){
			throw new ServiceException("Model ["+model.toString()+"] occurs error in getKeyGeneratorObject: "+e.getMessage(),e);
		}
		
	}

}
