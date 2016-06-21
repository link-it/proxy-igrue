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

import org.govmix.proxy.igrue.web.ejb.Utente;

import org.openspcoop2.generic_project.beans.AbstractModel;
import org.openspcoop2.generic_project.beans.IField;
import org.openspcoop2.generic_project.beans.Field;
import org.openspcoop2.generic_project.beans.ComplexField;


/**     
 * Model Utente 
 *
 * @author Giovanni Bussu (bussu@link.it)
 * @author $Author$
 * @version $Rev$, $Date$
 */
public class UtenteModel extends AbstractModel<Utente> {

	public UtenteModel(){
	
		super();
	
		this.ID_AMMINISTRAZIONE = new Field("id_amministrazione",java.lang.String.class,"utente",Utente.class);
		this.ID_SISTEMA = new Field("id_sistema",java.lang.Integer.class,"utente",Utente.class);
		this.PASSWORD = new Field("password",java.lang.String.class,"utente",Utente.class);
		this.SIL = new Field("sil",java.lang.String.class,"utente",Utente.class);
		this.MEF_PASSWORD = new Field("mef_password",java.lang.String.class,"utente",Utente.class);
	
	}
	
	public UtenteModel(IField father){
	
		super(father);
	
		this.ID_AMMINISTRAZIONE = new ComplexField(father,"id_amministrazione",java.lang.String.class,"utente",Utente.class);
		this.ID_SISTEMA = new ComplexField(father,"id_sistema",java.lang.Integer.class,"utente",Utente.class);
		this.PASSWORD = new ComplexField(father,"password",java.lang.String.class,"utente",Utente.class);
		this.SIL = new ComplexField(father,"sil",java.lang.String.class,"utente",Utente.class);
		this.MEF_PASSWORD = new ComplexField(father,"mef_password",java.lang.String.class,"utente",Utente.class);
	
	}
	
	

	public IField ID_AMMINISTRAZIONE = null;
	 
	public IField ID_SISTEMA = null;
	 
	public IField PASSWORD = null;
	 
	public IField SIL = null;
	 
	public IField MEF_PASSWORD = null;
	 

	//@Override
	public Class<Utente> getModeledClass(){
		return Utente.class;
	}

}