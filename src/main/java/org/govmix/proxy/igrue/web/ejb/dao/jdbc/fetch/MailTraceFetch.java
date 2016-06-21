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

import org.govmix.proxy.igrue.web.ejb.MailTrace;


public class MailTraceFetch extends AbstractJDBCFetch {

	//@Override
	public Object fetch(TipiDatabase tipoDatabase, IModel<?> model , ResultSet rs) throws ServiceException {
		
		try{
			JDBCParameterUtilities jdbcParameterUtilities =  
					new JDBCParameterUtilities(tipoDatabase);

			if(model.equals(MailTrace.model())){
				MailTrace object = new MailTrace();
				setParameter(object, "setTemplateCode", MailTrace.model().TEMPLATE_CODE.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "template_code", MailTrace.model().TEMPLATE_CODE.getFieldType()));
				setParameter(object, "setIdAmministrazione", MailTrace.model().ID_AMMINISTRAZIONE.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "id_amministrazione", MailTrace.model().ID_AMMINISTRAZIONE.getFieldType()));
				setParameter(object, "setIdSistema", MailTrace.model().ID_SISTEMA.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "id_sistema", MailTrace.model().ID_SISTEMA.getFieldType()));
				setParameter(object, "setSubject", MailTrace.model().SUBJECT.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "subject", MailTrace.model().SUBJECT.getFieldType()));
				setParameter(object, "setContent", MailTrace.model().CONTENT.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "content", MailTrace.model().CONTENT.getFieldType()));
				setParameter(object, "setReceivers", MailTrace.model().RECEIVERS.getFieldType(),
					jdbcParameterUtilities.readParameter(rs, "receivers", MailTrace.model().RECEIVERS.getFieldType()));
//				setParameter(object, "setTime", MailTrace.model().TIME.getFieldType(),
//					jdbcParameterUtilities.readParameter(rs, "time", MailTrace.model().TIME.getFieldType()));
				object.setTime((java.sql.Timestamp)jdbcParameterUtilities.readParameter(rs, "time", MailTrace.model().TIME.getFieldType()));
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

			if(model.equals(MailTrace.model())){
				return new org.openspcoop2.utils.jdbc.CustomKeyGeneratorObject("mail_trace","id","seq_mail_trace","mail_trace_init_seq");
			}
			
			else{
				throw new ServiceException("Model ["+model.toString()+"] not supported by getKeyGeneratorObject: "+this.getClass().getName());
			}

		}catch(Exception e){
			throw new ServiceException("Model ["+model.toString()+"] occurs error in getKeyGeneratorObject: "+e.getMessage(),e);
		}
		
	}

}
