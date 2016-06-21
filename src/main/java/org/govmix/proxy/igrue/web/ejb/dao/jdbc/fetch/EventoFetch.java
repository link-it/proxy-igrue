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
import org.govmix.proxy.igrue.web.ejb.Evento;
import org.govmix.proxy.igrue.web.ejb.Tipievento;


public class EventoFetch extends AbstractJDBCFetch {

	//@Override
	public Object fetch(TipiDatabase tipoDatabase, IModel<?> model , ResultSet rs) throws ServiceException {
		
		try{
			JDBCParameterUtilities jdbcParameterUtilities =  
					new JDBCParameterUtilities(tipoDatabase);

			if(model.equals(Evento.model())){
				Evento object = new Evento();
				setParameter(object, "setEnddate", Evento.model().ENDDATE.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "enddate", Evento.model().ENDDATE.getFieldType()));
				setParameter(object, "setEventtypeCode", Evento.model().EVENTTYPE_CODE.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "eventtype_code", Evento.model().EVENTTYPE_CODE.getFieldType()));
				setParameter(object, "setEventtypeDescription", Tipievento.model().DESCRIPTION.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "eventtype_description", Tipievento.model().DESCRIPTION.getFieldType()));
				setParameter(object, "setEventId", Evento.model().EVENT_ID.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "id", Evento.model().EVENT_ID.getFieldType()));
				setParameter(object, "setOwnerDescription", Evento.model().OWNER_DESCRIPTION.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "owner_description", Evento.model().OWNER_DESCRIPTION.getFieldType()));
				setParameter(object, "setOwnerIdamministrazione", Evento.model().OWNER_IDAMMINISTRAZIONE.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "owner_idamministrazione", Evento.model().OWNER_IDAMMINISTRAZIONE.getFieldType()));
				setParameter(object, "setOwnerIdsistema", Evento.model().OWNER_IDSISTEMA.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "owner_idsistema", Evento.model().OWNER_IDSISTEMA.getFieldType()));
				setParameter(object, "setParameterid", Evento.model().PARAMETERID.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "parameterid", Evento.model().PARAMETERID.getFieldType()));
				setParameter(object, "setReason", Evento.model().REASON.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "reason", Evento.model().REASON.getFieldType()));
				setParameter(object, "setStartdate", Evento.model().STARTDATE.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "startdate", Evento.model().STARTDATE.getFieldType()));
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

			if(model.equals(Evento.model())){
				return new org.openspcoop2.utils.jdbc.CustomKeyGeneratorObject("eventi","id","seq_eventi","eventi_init_seq");
			}
			
			else{
				throw new ServiceException("Model ["+model.toString()+"] not supported by getKeyGeneratorObject: "+this.getClass().getName());
			}

		}catch(Exception e){
			throw new ServiceException("Model ["+model.toString()+"] occurs error in getKeyGeneratorObject: "+e.getMessage(),e);
		}
		
	}

}
