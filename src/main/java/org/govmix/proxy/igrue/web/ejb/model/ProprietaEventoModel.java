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
package org.govmix.proxy.igrue.web.ejb.model;

import org.govmix.proxy.igrue.web.ejb.ProprietaEvento;

import org.openspcoop2.generic_project.beans.AbstractModel;
import org.openspcoop2.generic_project.beans.IField;
import org.openspcoop2.generic_project.beans.Field;
import org.openspcoop2.generic_project.beans.ComplexField;


/**     
 * Model ProprietaEvento 
 *
 * @author Giovanni Bussu (bussu@link.it)
 * @author $Author$
 * @version $Rev$, $Date$
 */
public class ProprietaEventoModel extends AbstractModel<ProprietaEvento> {

	public ProprietaEventoModel(){
	
		super();
	
		this.PROPERTY_ID = new Field("property_id",java.lang.String.class,"proprieta-evento",ProprietaEvento.class);
		this.PROPERTY_KEY = new Field("property_key",java.lang.String.class,"proprieta-evento",ProprietaEvento.class);
		this.PROPERTY_VALUE = new Field("property_value",java.lang.String.class,"proprieta-evento",ProprietaEvento.class);
	
	}
	
	public ProprietaEventoModel(IField father){
	
		super(father);
	
		this.PROPERTY_ID = new ComplexField(father,"property_id",java.lang.String.class,"proprieta-evento",ProprietaEvento.class);
		this.PROPERTY_KEY = new ComplexField(father,"property_key",java.lang.String.class,"proprieta-evento",ProprietaEvento.class);
		this.PROPERTY_VALUE = new ComplexField(father,"property_value",java.lang.String.class,"proprieta-evento",ProprietaEvento.class);
	
	}
	
	

	public IField PROPERTY_ID = null;
	 
	public IField PROPERTY_KEY = null;
	 
	public IField PROPERTY_VALUE = null;
	 

	//@Override
	public Class<ProprietaEvento> getModeledClass(){
		return ProprietaEvento.class;
	}

}