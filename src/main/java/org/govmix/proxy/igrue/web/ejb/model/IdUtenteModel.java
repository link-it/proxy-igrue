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

import org.govmix.proxy.igrue.web.ejb.IdUtente;

import org.openspcoop2.generic_project.beans.AbstractModel;
import org.openspcoop2.generic_project.beans.IField;
import org.openspcoop2.generic_project.beans.Field;
import org.openspcoop2.generic_project.beans.ComplexField;


/**     
 * Model IdUtente 
 *
 * @author Giovanni Bussu (bussu@link.it)
 * @author $Author$
 * @version $Rev$, $Date$
 */
public class IdUtenteModel extends AbstractModel<IdUtente> {

	public IdUtenteModel(){
	
		super();
	
		this.ID_AMMINISTRAZIONE = new Field("id-amministrazione",java.lang.String.class,"id-utente",IdUtente.class);
		this.ID_SISTEMA = new Field("id-sistema",java.lang.Integer.class,"id-utente",IdUtente.class);
	
	}
	
	public IdUtenteModel(IField father){
	
		super(father);
	
		this.ID_AMMINISTRAZIONE = new ComplexField(father,"id-amministrazione",java.lang.String.class,"id-utente",IdUtente.class);
		this.ID_SISTEMA = new ComplexField(father,"id-sistema",java.lang.Integer.class,"id-utente",IdUtente.class);
	
	}
	
	

	public IField ID_AMMINISTRAZIONE = null;
	 
	public IField ID_SISTEMA = null;
	 

	//@Override
	public Class<IdUtente> getModeledClass(){
		return IdUtente.class;
	}

}