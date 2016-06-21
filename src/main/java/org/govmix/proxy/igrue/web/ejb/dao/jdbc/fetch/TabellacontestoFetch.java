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

import org.govmix.proxy.igrue.web.ejb.Tabellacontesto;


public class TabellacontestoFetch extends AbstractJDBCFetch {

	//@Override
	public Object fetch(TipiDatabase tipoDatabase, IModel<?> model , ResultSet rs) throws ServiceException {
		
		try{
			JDBCParameterUtilities jdbcParameterUtilities =  
					new JDBCParameterUtilities(tipoDatabase);

			if(model.equals(Tabellacontesto.model())){
				Tabellacontesto object = new Tabellacontesto();
				setParameter(object, "setIdAmministrazione", Tabellacontesto.model().ID_AMMINISTRAZIONE.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "id_amministrazione", Tabellacontesto.model().ID_AMMINISTRAZIONE.getFieldType()));
				setParameter(object, "setIdSistema", Tabellacontesto.model().ID_SISTEMA.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "id_sistema", Tabellacontesto.model().ID_SISTEMA.getFieldType()));
				setParameter(object, "setChecksum", Tabellacontesto.model().CHECKSUM.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "checksum", Tabellacontesto.model().CHECKSUM.getFieldType()));
				setParameter(object, "setDataaggiornamento", Tabellacontesto.model().DATAAGGIORNAMENTO.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "dataaggiornamento", Tabellacontesto.model().DATAAGGIORNAMENTO.getFieldType()));
				setParameter(object, "setNometabella", Tabellacontesto.model().NOMETABELLA.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "nometabella", Tabellacontesto.model().NOMETABELLA.getFieldType()));
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

			if(model.equals(Tabellacontesto.model())){
				return new org.openspcoop2.utils.jdbc.CustomKeyGeneratorObject("tabellecontesto","id","seq_tabellecontesto","tabellecontesto_init_seq");
			}
			
			else{
				throw new ServiceException("Model ["+model.toString()+"] not supported by getKeyGeneratorObject: "+this.getClass().getName());
			}

		}catch(Exception e){
			throw new ServiceException("Model ["+model.toString()+"] occurs error in getKeyGeneratorObject: "+e.getMessage(),e);
		}
		
	}

}
