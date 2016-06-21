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

import org.govmix.proxy.igrue.web.ejb.Trasmissione;

import org.openspcoop2.generic_project.beans.AbstractModel;
import org.openspcoop2.generic_project.beans.IField;
import org.openspcoop2.generic_project.beans.Field;
import org.openspcoop2.generic_project.beans.ComplexField;


/**     
 * Model Trasmissione 
 *
 * @author Giovanni Bussu (bussu@link.it)
 * @author $Author$
 * @version $Rev$, $Date$
 */
public class TrasmissioneModel extends AbstractModel<Trasmissione> {

	public TrasmissioneModel(){
	
		super();
	
		this.UTENTE = new org.govmix.proxy.igrue.web.ejb.model.IdUtenteModel(new Field("utente",org.govmix.proxy.igrue.web.ejb.IdUtente.class,"trasmissione",Trasmissione.class));
		this.FILE = new Field("file",java.lang.Integer.class,"trasmissione",Trasmissione.class);
		this.ID_AMMINISTRAZIONE = new Field("id_amministrazione",java.lang.String.class,"trasmissione",Trasmissione.class);
		this.ID_SISTEMA = new Field("id_sistema",java.lang.Integer.class,"trasmissione",Trasmissione.class);
		this.DATAULTIMOINVIO = new Field("dataultimoinvio",java.util.Date.class,"trasmissione",Trasmissione.class);
		this.ESITOULTIMOINVIO = new Field("esitoultimoinvio",java.lang.Integer.class,"trasmissione",Trasmissione.class);
		this.ESITOULTIMOINVIODESCRIZIONE = new Field("esitoultimoinviodescrizione",java.lang.String.class,"trasmissione",Trasmissione.class);
		this.NOTIFICATO = new Field("notificato",boolean.class,"trasmissione",Trasmissione.class);
	
	}
	
	public TrasmissioneModel(IField father){
	
		super(father);
	
		this.UTENTE = new org.govmix.proxy.igrue.web.ejb.model.IdUtenteModel(new ComplexField(father,"utente",org.govmix.proxy.igrue.web.ejb.IdUtente.class,"trasmissione",Trasmissione.class));
		this.FILE = new ComplexField(father,"file",java.lang.Integer.class,"trasmissione",Trasmissione.class);
		this.ID_AMMINISTRAZIONE = new ComplexField(father,"id_amministrazione",java.lang.String.class,"trasmissione",Trasmissione.class);
		this.ID_SISTEMA = new ComplexField(father,"id_sistema",java.lang.Integer.class,"trasmissione",Trasmissione.class);
		this.DATAULTIMOINVIO = new ComplexField(father,"dataultimoinvio",java.util.Date.class,"trasmissione",Trasmissione.class);
		this.ESITOULTIMOINVIO = new ComplexField(father,"esitoultimoinvio",java.lang.Integer.class,"trasmissione",Trasmissione.class);
		this.ESITOULTIMOINVIODESCRIZIONE = new ComplexField(father,"esitoultimoinviodescrizione",java.lang.String.class,"trasmissione",Trasmissione.class);
		this.NOTIFICATO = new ComplexField(father,"notificato",boolean.class,"trasmissione",Trasmissione.class);
	
	}
	
	

	public org.govmix.proxy.igrue.web.ejb.model.IdUtenteModel UTENTE = null;
	 
	public IField FILE = null;
	 
	public IField ID_AMMINISTRAZIONE = null;
	 
	public IField ID_SISTEMA = null;
	 
	public IField DATAULTIMOINVIO = null;
	 
	public IField ESITOULTIMOINVIO = null;
	 
	public IField ESITOULTIMOINVIODESCRIZIONE = null;
	 
	public IField NOTIFICATO = null;
	 

	//@Override
	public Class<Trasmissione> getModeledClass(){
		return Trasmissione.class;
	}

}