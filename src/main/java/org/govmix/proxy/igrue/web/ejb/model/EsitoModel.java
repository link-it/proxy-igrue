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

import org.govmix.proxy.igrue.web.ejb.Esito;

import org.openspcoop2.generic_project.beans.AbstractModel;
import org.openspcoop2.generic_project.beans.IField;
import org.openspcoop2.generic_project.beans.Field;
import org.openspcoop2.generic_project.beans.ComplexField;


/**     
 * Model Esito 
 *
 * @author Giovanni Bussu (bussu@link.it)
 * @author $Author$
 * @version $Rev$, $Date$
 */
public class EsitoModel extends AbstractModel<Esito> {

	public EsitoModel(){
	
		super();
	
		this.TRASMISSIONE = new org.govmix.proxy.igrue.web.ejb.model.IdTrasmissioneModel(new Field("trasmissione",org.govmix.proxy.igrue.web.ejb.IdTrasmissione.class,"esito",Esito.class));
		this.ID_AMMINISTRAZIONE = new Field("id_amministrazione",java.lang.String.class,"esito",Esito.class);
		this.ID_SISTEMA = new Field("id_sistema",java.lang.Integer.class,"esito",Esito.class);
		this.FILE = new Field("file",java.lang.Integer.class,"esito",Esito.class);
		this.STATISTICHEELABORAZIONI = new Field("statisticheelaborazioni",java.lang.Integer.class,"esito",Esito.class);
		this.STATISTICHEELABORAZIONIDESCRIZIONE = new Field("statisticheelaborazionidescrizione",java.lang.String.class,"esito",Esito.class);
		this.STATISTICHESCARTI = new Field("statistichescarti",java.lang.Integer.class,"esito",Esito.class);
		this.STATISTICHESCARTIDESCRIZIONE = new Field("statistichescartidescrizione",java.lang.String.class,"esito",Esito.class);
		this.ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTO = new Field("esitoelaborazioneperanagraficadiriferimento",java.lang.Integer.class,"esito",Esito.class);
		this.ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTODESCRIZIONE = new Field("esitoelaborazioneperanagraficadiriferimentodescrizione",java.lang.String.class,"esito",Esito.class);
		this.LOGDEGLIERRORI = new Field("logdeglierrori",java.lang.Integer.class,"esito",Esito.class);
		this.LOGDEGLIERRORIDESCRIZIONE = new Field("logdeglierroridescrizione",java.lang.String.class,"esito",Esito.class);
		this.LOGDEGLIERRORIRICEVUTO = new Field("logdeglierroriricevuto",boolean.class,"esito",Esito.class);
		this.DATAULTIMOCONTROLLO = new Field("dataultimocontrollo",java.util.Date.class,"esito",Esito.class);
	
	}
	
	public EsitoModel(IField father){
	
		super(father);
	
		this.TRASMISSIONE = new org.govmix.proxy.igrue.web.ejb.model.IdTrasmissioneModel(new ComplexField(father,"trasmissione",org.govmix.proxy.igrue.web.ejb.IdTrasmissione.class,"esito",Esito.class));
		this.ID_AMMINISTRAZIONE = new ComplexField(father,"id_amministrazione",java.lang.String.class,"esito",Esito.class);
		this.ID_SISTEMA = new ComplexField(father,"id_sistema",java.lang.Integer.class,"esito",Esito.class);
		this.FILE = new ComplexField(father,"file",java.lang.Integer.class,"esito",Esito.class);
		this.STATISTICHEELABORAZIONI = new ComplexField(father,"statisticheelaborazioni",java.lang.Integer.class,"esito",Esito.class);
		this.STATISTICHEELABORAZIONIDESCRIZIONE = new ComplexField(father,"statisticheelaborazionidescrizione",java.lang.String.class,"esito",Esito.class);
		this.STATISTICHESCARTI = new ComplexField(father,"statistichescarti",java.lang.Integer.class,"esito",Esito.class);
		this.STATISTICHESCARTIDESCRIZIONE = new ComplexField(father,"statistichescartidescrizione",java.lang.String.class,"esito",Esito.class);
		this.ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTO = new ComplexField(father,"esitoelaborazioneperanagraficadiriferimento",java.lang.Integer.class,"esito",Esito.class);
		this.ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTODESCRIZIONE = new ComplexField(father,"esitoelaborazioneperanagraficadiriferimentodescrizione",java.lang.String.class,"esito",Esito.class);
		this.LOGDEGLIERRORI = new ComplexField(father,"logdeglierrori",java.lang.Integer.class,"esito",Esito.class);
		this.LOGDEGLIERRORIDESCRIZIONE = new ComplexField(father,"logdeglierroridescrizione",java.lang.String.class,"esito",Esito.class);
		this.LOGDEGLIERRORIRICEVUTO = new ComplexField(father,"logdeglierroriricevuto",boolean.class,"esito",Esito.class);
		this.DATAULTIMOCONTROLLO = new ComplexField(father,"dataultimocontrollo",java.util.Date.class,"esito",Esito.class);
	
	}
	
	

	public org.govmix.proxy.igrue.web.ejb.model.IdTrasmissioneModel TRASMISSIONE = null;
	 
	public IField ID_AMMINISTRAZIONE = null;
	 
	public IField ID_SISTEMA = null;
	 
	public IField FILE = null;
	 
	public IField STATISTICHEELABORAZIONI = null;
	 
	public IField STATISTICHEELABORAZIONIDESCRIZIONE = null;
	 
	public IField STATISTICHESCARTI = null;
	 
	public IField STATISTICHESCARTIDESCRIZIONE = null;
	 
	public IField ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTO = null;
	 
	public IField ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTODESCRIZIONE = null;
	 
	public IField LOGDEGLIERRORI = null;
	 
	public IField LOGDEGLIERRORIDESCRIZIONE = null;
	 
	public IField LOGDEGLIERRORIRICEVUTO = null;
	 
	public IField DATAULTIMOCONTROLLO = null;
	 

	//@Override
	public Class<Esito> getModeledClass(){
		return Esito.class;
	}

}