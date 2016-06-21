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
package org.govmix.proxy.igrue.web.ejb.dao.jdbc.converter;

import org.openspcoop2.generic_project.beans.IField;
import org.openspcoop2.generic_project.beans.IModel;
import org.openspcoop2.generic_project.exception.ExpressionException;
import org.openspcoop2.generic_project.expression.impl.sql.AbstractSQLFieldConverter;

import org.govmix.proxy.igrue.web.ejb.Esito;


public class EsitoFieldConverter extends AbstractSQLFieldConverter {

	//@Override
	public String toColumn(IField field,boolean returnAlias,boolean appendTablePrefix) throws ExpressionException {
		
		// In the case of columns with alias, using parameter returnAlias​​, 
		// it is possible to drive the choice whether to return only the alias or 
		// the full definition of the column containing the alias
		
		if(field.equals(Esito.model().TRASMISSIONE.FILE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".file";
			}else{
				return "file";
			}
		}
		if(field.equals(Esito.model().TRASMISSIONE.UTENTE.ID_AMMINISTRAZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_amministrazione";
			}else{
				return "id_amministrazione";
			}
		}
		if(field.equals(Esito.model().TRASMISSIONE.UTENTE.ID_SISTEMA)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_sistema";
			}else{
				return "id_sistema";
			}
		}
		if(field.equals(Esito.model().ID_AMMINISTRAZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_amministrazione";
			}else{
				return "id_amministrazione";
			}
		}
		if(field.equals(Esito.model().ID_SISTEMA)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".id_sistema";
			}else{
				return "id_sistema";
			}
		}
		if(field.equals(Esito.model().FILE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".file";
			}else{
				return "file";
			}
		}
		if(field.equals(Esito.model().STATISTICHEELABORAZIONI)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".statisticheelaborazioni";
			}else{
				return "statisticheelaborazioni";
			}
		}
		if(field.equals(Esito.model().STATISTICHEELABORAZIONIDESCRIZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".statisticheelaborazionidescrizione";
			}else{
				return "statisticheelaborazionidescrizione";
			}
		}
		if(field.equals(Esito.model().STATISTICHESCARTI)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".statistichescarti";
			}else{
				return "statistichescarti";
			}
		}
		if(field.equals(Esito.model().STATISTICHESCARTIDESCRIZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".statistichescartidescrizione";
			}else{
				return "statistichescartidescrizione";
			}
		}
		if(field.equals(Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTO)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".esitoelaborazioneperanagraficadiriferimento";
			}else{
				return "esitoelaborazioneperanagraficadiriferimento";
			}
		}
		if(field.equals(Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTODESCRIZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".esitoelaborazioneperanagraficadiriferimentodescrizione";
			}else{
				return "esitoelaborazioneperanagraficadiriferimentodescrizione";
			}
		}
		if(field.equals(Esito.model().LOGDEGLIERRORI)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".logdeglierrori";
			}else{
				return "logdeglierrori";
			}
		}
		if(field.equals(Esito.model().LOGDEGLIERRORIDESCRIZIONE)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".logdeglierroridescrizione";
			}else{
				return "logdeglierroridescrizione";
			}
		}
		if(field.equals(Esito.model().LOGDEGLIERRORIRICEVUTO)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".logdeglierroriricevuto";
			}else{
				return "logdeglierroriricevuto";
			}
		}
		if(field.equals(Esito.model().DATAULTIMOCONTROLLO)){
			if(appendTablePrefix){
				return this.toAliasTable(field)+".dataultimocontrollo";
			}else{
				return "dataultimocontrollo";
			}
		}


		else{
			throw new ExpressionException("Field ["+field.toString()+"] not supported by converter.toColumn: "+this.getClass().getName());
		}
		
	}
	
	//@Override
	public String toTable(IField field,boolean returnAlias) throws ExpressionException {
		
		// In the case of table with alias, using parameter returnAlias​​, 
		// it is possible to drive the choice whether to return only the alias or 
		// the full definition of the table containing the alias
		
		if(field.equals(Esito.model().TRASMISSIONE.FILE)){
			return this.toTable(Esito.model().TRASMISSIONE, returnAlias);
		}
		if(field.equals(Esito.model().TRASMISSIONE.UTENTE.ID_AMMINISTRAZIONE)){
			return this.toTable(Esito.model().TRASMISSIONE.UTENTE, returnAlias);
		}
		if(field.equals(Esito.model().TRASMISSIONE.UTENTE.ID_SISTEMA)){
			return this.toTable(Esito.model().TRASMISSIONE.UTENTE, returnAlias);
		}
		if(field.equals(Esito.model().ID_AMMINISTRAZIONE)){
			return this.toTable(Esito.model(), returnAlias);
		}
		if(field.equals(Esito.model().ID_SISTEMA)){
			return this.toTable(Esito.model(), returnAlias);
		}
		if(field.equals(Esito.model().FILE)){
			return this.toTable(Esito.model(), returnAlias);
		}
		if(field.equals(Esito.model().STATISTICHEELABORAZIONI)){
			return this.toTable(Esito.model(), returnAlias);
		}
		if(field.equals(Esito.model().STATISTICHEELABORAZIONIDESCRIZIONE)){
			return this.toTable(Esito.model(), returnAlias);
		}
		if(field.equals(Esito.model().STATISTICHESCARTI)){
			return this.toTable(Esito.model(), returnAlias);
		}
		if(field.equals(Esito.model().STATISTICHESCARTIDESCRIZIONE)){
			return this.toTable(Esito.model(), returnAlias);
		}
		if(field.equals(Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTO)){
			return this.toTable(Esito.model(), returnAlias);
		}
		if(field.equals(Esito.model().ESITOELABORAZIONEPERANAGRAFICADIRIFERIMENTODESCRIZIONE)){
			return this.toTable(Esito.model(), returnAlias);
		}
		if(field.equals(Esito.model().LOGDEGLIERRORI)){
			return this.toTable(Esito.model(), returnAlias);
		}
		if(field.equals(Esito.model().LOGDEGLIERRORIDESCRIZIONE)){
			return this.toTable(Esito.model(), returnAlias);
		}
		if(field.equals(Esito.model().LOGDEGLIERRORIRICEVUTO)){
			return this.toTable(Esito.model(), returnAlias);
		}
		if(field.equals(Esito.model().DATAULTIMOCONTROLLO)){
			return this.toTable(Esito.model(), returnAlias);
		}


		else{
			throw new ExpressionException("Field ["+field.toString()+"] not supported by converter.toTable: "+this.getClass().getName());
		}
		
	}

	//@Override
	public String toTable(IModel<?> model,boolean returnAlias) throws ExpressionException {
		
		// In the case of table with alias, using parameter returnAlias​​, 
		// it is possible to drive the choice whether to return only the alias or 
		// the full definition of the table containing the alias
		
		if(model.equals(Esito.model())){
			return "esiti";
		}
		if(model.equals(Esito.model().TRASMISSIONE)){
			return "trasmissioni";
		}
		if(model.equals(Esito.model().TRASMISSIONE.UTENTE)){
			return "utenti";
		}


		else{
			throw new ExpressionException("Model ["+model.toString()+"] not supported by converter.toTable: "+this.getClass().getName());
		}
		
	}

}
