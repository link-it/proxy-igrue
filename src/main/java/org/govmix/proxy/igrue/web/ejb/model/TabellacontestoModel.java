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

import org.govmix.proxy.igrue.web.ejb.Tabellacontesto;

import org.openspcoop2.generic_project.beans.AbstractModel;
import org.openspcoop2.generic_project.beans.IField;
import org.openspcoop2.generic_project.beans.Field;
import org.openspcoop2.generic_project.beans.ComplexField;


/**     
 * Model Tabellacontesto 
 *
 * @author Giovanni Bussu (bussu@link.it)
 * @author $Author$
 * @version $Rev$, $Date$
 */
public class TabellacontestoModel extends AbstractModel<Tabellacontesto> {

	public TabellacontestoModel(){
	
		super();
	
		this.UTENTE = new org.govmix.proxy.igrue.web.ejb.model.IdUtenteModel(new Field("utente",org.govmix.proxy.igrue.web.ejb.IdUtente.class,"tabellacontesto",Tabellacontesto.class));
		this.ID_AMMINISTRAZIONE = new Field("id_amministrazione",java.lang.String.class,"tabellacontesto",Tabellacontesto.class);
		this.ID_SISTEMA = new Field("id_sistema",java.lang.Integer.class,"tabellacontesto",Tabellacontesto.class);
		this.CHECKSUM = new Field("checksum",long.class,"tabellacontesto",Tabellacontesto.class);
		this.DATAAGGIORNAMENTO = new Field("dataaggiornamento",java.util.Date.class,"tabellacontesto",Tabellacontesto.class);
		this.NOMETABELLA = new Field("nometabella",java.lang.String.class,"tabellacontesto",Tabellacontesto.class);
	
	}
	
	public TabellacontestoModel(IField father){
	
		super(father);
	
		this.UTENTE = new org.govmix.proxy.igrue.web.ejb.model.IdUtenteModel(new ComplexField(father,"utente",org.govmix.proxy.igrue.web.ejb.IdUtente.class,"tabellacontesto",Tabellacontesto.class));
		this.ID_AMMINISTRAZIONE = new ComplexField(father,"id_amministrazione",java.lang.String.class,"tabellacontesto",Tabellacontesto.class);
		this.ID_SISTEMA = new ComplexField(father,"id_sistema",java.lang.Integer.class,"tabellacontesto",Tabellacontesto.class);
		this.CHECKSUM = new ComplexField(father,"checksum",long.class,"tabellacontesto",Tabellacontesto.class);
		this.DATAAGGIORNAMENTO = new ComplexField(father,"dataaggiornamento",java.util.Date.class,"tabellacontesto",Tabellacontesto.class);
		this.NOMETABELLA = new ComplexField(father,"nometabella",java.lang.String.class,"tabellacontesto",Tabellacontesto.class);
	
	}
	
	

	public org.govmix.proxy.igrue.web.ejb.model.IdUtenteModel UTENTE = null;
	 
	public IField ID_AMMINISTRAZIONE = null;
	 
	public IField ID_SISTEMA = null;
	 
	public IField CHECKSUM = null;
	 
	public IField DATAAGGIORNAMENTO = null;
	 
	public IField NOMETABELLA = null;
	 

	//@Override
	public Class<Tabellacontesto> getModeledClass(){
		return Tabellacontesto.class;
	}

}