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

/**     
 * Factory
 *
 * @author Giovanni Bussu (bussu@link.it)
 * @author $Author$
 * @version $Rev$, $Date$
 */
public class ModelFactory {

	public static UtenteModel UTENTE = new UtenteModel();
	
	public static EsitoModel ESITO = new EsitoModel();
	
	public static EventoModel EVENTO = new EventoModel();
	
	public static MailTemplateModel MAIL_TEMPLATE = new MailTemplateModel();
	
	public static ProprietaEventoModel PROPRIETA_EVENTO = new ProprietaEventoModel();
	
	public static TabellacontestoModel TABELLACONTESTO = new TabellacontestoModel();
	
	public static TicketModel TICKET = new TicketModel();
	
	public static TipieventoModel TIPIEVENTO = new TipieventoModel();
	
	public static TrasmissioneModel TRASMISSIONE = new TrasmissioneModel();
	
	public static MailTraceModel MAIL_TRACE = new MailTraceModel();
	

}