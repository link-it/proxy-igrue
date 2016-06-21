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

import org.govmix.proxy.igrue.web.ejb.Tipievento;

import org.openspcoop2.generic_project.beans.AbstractModel;
import org.openspcoop2.generic_project.beans.IField;
import org.openspcoop2.generic_project.beans.Field;
import org.openspcoop2.generic_project.beans.ComplexField;


/**     
 * Model Tipievento 
 *
 * @author Giovanni Bussu (bussu@link.it)
 * @author $Author$
 * @version $Rev$, $Date$
 */
public class TipieventoModel extends AbstractModel<Tipievento> {

	public TipieventoModel(){
	
		super();
	
		this.CODE = new Field("code",java.lang.Integer.class,"tipievento",Tipievento.class);
		this.DESCRIPTION = new Field("description",java.lang.String.class,"tipievento",Tipievento.class);
	
	}
	
	public TipieventoModel(IField father){
	
		super(father);
	
		this.CODE = new ComplexField(father,"code",java.lang.Integer.class,"tipievento",Tipievento.class);
		this.DESCRIPTION = new ComplexField(father,"description",java.lang.String.class,"tipievento",Tipievento.class);
	
	}
	
	

	public IField CODE = null;
	 
	public IField DESCRIPTION = null;
	 

	//@Override
	public Class<Tipievento> getModeledClass(){
		return Tipievento.class;
	}

}