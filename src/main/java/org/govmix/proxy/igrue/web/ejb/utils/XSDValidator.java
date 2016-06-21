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
package org.govmix.proxy.igrue.web.ejb.utils;

import org.apache.log4j.Logger;
import org.openspcoop2.utils.xml.ValidatoreXSD;
import org.openspcoop2.generic_project.exception.ServiceException;

import org.govmix.proxy.igrue.web.ejb.Proxy;

/** 
 * XSD Validator    
 *
 * @author Giovanni Bussu (bussu@link.it)
 * @author $Author$
 * @version $Rev$, $Date$
 */

public class XSDValidator {

	private static org.openspcoop2.generic_project.utils.XSDValidator validator = null;
	
	private static synchronized void initValidator(Logger log) throws ServiceException{
		if(validator==null){
			validator = new org.openspcoop2.generic_project.utils.XSDValidator(log,Proxy.class, "/ProxyIgrue.xsd");
		}
	}
	
	public static ValidatoreXSD getXSDValidator(Logger log) throws ServiceException{
		if(validator==null){
			initValidator(log);
		}
		return validator.getXsdValidator();
	}
	
}
