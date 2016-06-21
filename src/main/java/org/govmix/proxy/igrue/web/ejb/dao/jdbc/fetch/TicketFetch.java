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

import org.govmix.proxy.igrue.web.ejb.Ticket;


public class TicketFetch extends AbstractJDBCFetch {

	//@Override
	public Object fetch(TipiDatabase tipoDatabase, IModel<?> model , ResultSet rs) throws ServiceException {
		
		try{
			JDBCParameterUtilities jdbcParameterUtilities =  
					new JDBCParameterUtilities(tipoDatabase);

			if(model.equals(Ticket.model())){
				Ticket object = new Ticket();
				setParameter(object, "setFile", Ticket.model().FILE.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "file", Ticket.model().FILE.getFieldType()));
				setParameter(object, "setIdAmministrazione", Ticket.model().ID_AMMINISTRAZIONE.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "id_amministrazione", Ticket.model().ID_AMMINISTRAZIONE.getFieldType()));
				setParameter(object, "setIdSistema", Ticket.model().ID_SISTEMA.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "id_sistema", Ticket.model().ID_SISTEMA.getFieldType()));
				setParameter(object, "setDataassegnazione", Ticket.model().DATAASSEGNAZIONE.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "dataassegnazione", Ticket.model().DATAASSEGNAZIONE.getFieldType()));
				setParameter(object, "setDatafinetrasmissione", Ticket.model().DATAFINETRASMISSIONE.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "datafinetrasmissione", Ticket.model().DATAFINETRASMISSIONE.getFieldType()));
				setParameter(object, "setFilericevuto", Ticket.model().FILERICEVUTO.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "filericevuto", Ticket.model().FILERICEVUTO.getFieldType()));
				setParameter(object, "setIdticket", Ticket.model().IDTICKET.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "idticket", Ticket.model().IDTICKET.getFieldType()));
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

			if(model.equals(Ticket.model())){
				return new org.openspcoop2.utils.jdbc.CustomKeyGeneratorObject("tickets","id","seq_tickets","tickets_init_seq");
			}
			
			else{
				throw new ServiceException("Model ["+model.toString()+"] not supported by getKeyGeneratorObject: "+this.getClass().getName());
			}

		}catch(Exception e){
			throw new ServiceException("Model ["+model.toString()+"] occurs error in getKeyGeneratorObject: "+e.getMessage(),e);
		}
		
	}

}
