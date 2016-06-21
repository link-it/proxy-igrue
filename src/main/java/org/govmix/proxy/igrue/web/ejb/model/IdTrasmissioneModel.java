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

import org.govmix.proxy.igrue.web.ejb.IdTrasmissione;

import org.openspcoop2.generic_project.beans.AbstractModel;
import org.openspcoop2.generic_project.beans.IField;
import org.openspcoop2.generic_project.beans.Field;
import org.openspcoop2.generic_project.beans.ComplexField;


/**     
 * Model IdTrasmissione 
 *
 * @author Giovanni Bussu (bussu@link.it)
 * @author $Author$
 * @version $Rev$, $Date$
 */
public class IdTrasmissioneModel extends AbstractModel<IdTrasmissione> {

	public IdTrasmissioneModel(){
	
		super();
	
		this.FILE = new Field("file",java.lang.Integer.class,"id-trasmissione",IdTrasmissione.class);
		this.UTENTE = new org.govmix.proxy.igrue.web.ejb.model.IdUtenteModel(new Field("utente",org.govmix.proxy.igrue.web.ejb.IdUtente.class,"id-trasmissione",IdTrasmissione.class));
	
	}
	
	public IdTrasmissioneModel(IField father){
	
		super(father);
	
		this.FILE = new ComplexField(father,"file",java.lang.Integer.class,"id-trasmissione",IdTrasmissione.class);
		this.UTENTE = new org.govmix.proxy.igrue.web.ejb.model.IdUtenteModel(new ComplexField(father,"utente",org.govmix.proxy.igrue.web.ejb.IdUtente.class,"id-trasmissione",IdTrasmissione.class));
	
	}
	
	

	public IField FILE = null;
	 
	public org.govmix.proxy.igrue.web.ejb.model.IdUtenteModel UTENTE = null;
	 

	//@Override
	public Class<IdTrasmissione> getModeledClass(){
		return IdTrasmissione.class;
	}

}