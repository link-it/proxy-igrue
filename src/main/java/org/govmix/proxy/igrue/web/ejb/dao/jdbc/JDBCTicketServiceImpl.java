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
package org.govmix.proxy.igrue.web.ejb.dao.jdbc;

import java.sql.Connection;

import org.openspcoop2.utils.sql.ISQLQueryObject;
import org.apache.log4j.Logger;
import org.openspcoop2.generic_project.dao.jdbc.IJDBCServiceCRUDWithId;
import org.govmix.proxy.igrue.web.ejb.IdTrasmissione;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.govmix.proxy.igrue.web.ejb.TicketId;
import org.openspcoop2.generic_project.beans.NonNegativeNumber;
import org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject;
import org.openspcoop2.generic_project.exception.NotFoundException;
import org.openspcoop2.generic_project.exception.NotImplementedException;
import org.openspcoop2.generic_project.exception.ServiceException;
import org.openspcoop2.generic_project.dao.jdbc.JDBCExpression;
import org.openspcoop2.generic_project.dao.jdbc.JDBCPaginatedExpression;
import org.openspcoop2.generic_project.dao.jdbc.JDBCServiceManagerProperties;
import org.govmix.proxy.igrue.web.ejb.Ticket;
import org.govmix.proxy.igrue.web.ejb.dao.jdbc.JDBCServiceManager;


public class JDBCTicketServiceImpl extends JDBCTicketServiceSearchImpl
	implements IJDBCServiceCRUDWithId<Ticket, TicketId, JDBCServiceManager> {

	//@Override
	public void create(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Ticket ticket) throws NotImplementedException,ServiceException,Exception {

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		


		// Object ticket
		sqlQueryObjectInsert.addInsertTable(this.getTicketFieldConverter().toTable(Ticket.model()));
		sqlQueryObjectInsert.addInsertField(this.getTicketFieldConverter().toColumn(Ticket.model().ID_AMMINISTRAZIONE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getTicketFieldConverter().toColumn(Ticket.model().ID_SISTEMA,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getTicketFieldConverter().toColumn(Ticket.model().FILE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getTicketFieldConverter().toColumn(Ticket.model().DATAASSEGNAZIONE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getTicketFieldConverter().toColumn(Ticket.model().DATAFINETRASMISSIONE,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getTicketFieldConverter().toColumn(Ticket.model().FILERICEVUTO,false),"?");
		sqlQueryObjectInsert.addInsertField(this.getTicketFieldConverter().toColumn(Ticket.model().IDTICKET,false),"?");

		// Insert ticket
		String insertSql = sqlQueryObjectInsert.createSQLInsert();
		jdbcUtilities.execute(insertSql, jdbcProperties.isShowSql(), 
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(ticket.getIdAmministrazione(),Ticket.model().ID_AMMINISTRAZIONE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(ticket.getIdSistema(),Ticket.model().ID_SISTEMA.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(ticket.getFile(),Ticket.model().FILE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(ticket.getDataassegnazione(),Ticket.model().DATAASSEGNAZIONE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(ticket.getDatafinetrasmissione(),Ticket.model().DATAFINETRASMISSIONE.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(ticket.getFilericevuto(),Ticket.model().FILERICEVUTO.getFieldType()),
			new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCObject(ticket.getIdticket(),Ticket.model().IDTICKET.getFieldType())
		);

		
	}

	//@Override
	public void update(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, TicketId oldId, Ticket ticket) throws NotFoundException, NotImplementedException, ServiceException, Exception {
		

		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectInsert = sqlQueryObject.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObjectInsert.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectGet = sqlQueryObjectDelete.newSQLQueryObject();
		ISQLQueryObject sqlQueryObjectUpdate = sqlQueryObjectGet.newSQLQueryObject();
				
		// Object ticket
		sqlQueryObjectUpdate.setANDLogicOperator(true);
		sqlQueryObjectUpdate.addUpdateTable(this.getTicketFieldConverter().toTable(Ticket.model()));
		boolean isUpdate_ticket = true;
		java.util.List<JDBCObject> lstObjects_ticket = new java.util.ArrayList<JDBCObject>();
		sqlQueryObjectUpdate.addUpdateField(this.getTicketFieldConverter().toColumn(Ticket.model().ID_AMMINISTRAZIONE,false), "?");
		lstObjects_ticket.add(new JDBCObject(ticket.getIdAmministrazione(), Ticket.model().ID_AMMINISTRAZIONE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getTicketFieldConverter().toColumn(Ticket.model().ID_SISTEMA,false), "?");
		lstObjects_ticket.add(new JDBCObject(ticket.getIdSistema(), Ticket.model().ID_SISTEMA.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getTicketFieldConverter().toColumn(Ticket.model().FILE,false), "?");
		lstObjects_ticket.add(new JDBCObject(ticket.getFile(), Ticket.model().FILE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getTicketFieldConverter().toColumn(Ticket.model().DATAASSEGNAZIONE,false), "?");
		lstObjects_ticket.add(new JDBCObject(ticket.getDataassegnazione(), Ticket.model().DATAASSEGNAZIONE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getTicketFieldConverter().toColumn(Ticket.model().DATAFINETRASMISSIONE,false), "?");
		lstObjects_ticket.add(new JDBCObject(ticket.getDatafinetrasmissione(), Ticket.model().DATAFINETRASMISSIONE.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getTicketFieldConverter().toColumn(Ticket.model().FILERICEVUTO,false), "?");
		lstObjects_ticket.add(new JDBCObject(ticket.getFilericevuto(), Ticket.model().FILERICEVUTO.getFieldType()));
		sqlQueryObjectUpdate.addUpdateField(this.getTicketFieldConverter().toColumn(Ticket.model().IDTICKET,false), "?");
		lstObjects_ticket.add(new JDBCObject(ticket.getIdticket(), Ticket.model().IDTICKET.getFieldType()));
		
		sqlQueryObjectUpdate.addWhereCondition(this.getTicketFieldConverter().toColumn(Ticket.model().FILE,true)+"=?");
		lstObjects_ticket.add(new JDBCObject(oldId.getTrasmissione().getFile(), Ticket.model().FILE.getFieldType()));
		sqlQueryObjectUpdate.addWhereCondition(this.getTicketFieldConverter().toColumn(Ticket.model().ID_AMMINISTRAZIONE,true)+"=?");
		lstObjects_ticket.add(new JDBCObject(oldId.getTrasmissione().getUtente().getIdAmministrazione(), Ticket.model().ID_AMMINISTRAZIONE.getFieldType()));
		sqlQueryObjectUpdate.addWhereCondition(this.getTicketFieldConverter().toColumn(Ticket.model().ID_SISTEMA,true)+"=?");
		lstObjects_ticket.add(new JDBCObject(oldId.getTrasmissione().getUtente().getIdSistema(), Ticket.model().ID_SISTEMA.getFieldType()));

		if(isUpdate_ticket) {
			// Update ticket
			jdbcUtilities.executeUpdate(sqlQueryObjectUpdate.createSQLUpdate(), jdbcProperties.isShowSql(), 
				lstObjects_ticket.toArray(new JDBCObject[]{}));
		}

	}	
	
	//@Override
	public void updateOrCreate(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, TicketId oldId, Ticket ticket) throws NotImplementedException,ServiceException,Exception {
	
		if(this.exists(jdbcProperties, log, connection, sqlQueryObject, oldId)) {
			this.update(jdbcProperties, log, connection, sqlQueryObject, oldId, ticket);
		} else {
			this.create(jdbcProperties, log, connection, sqlQueryObject, ticket);
		}
		
	}
	
	//@Override
	public void delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Ticket ticket) throws NotImplementedException,ServiceException,Exception {
		
		TicketId idObject = new TicketId();
		IdTrasmissione idTrasmissione = new IdTrasmissione();
		idTrasmissione.setFile(ticket.getFile());
		IdUtente utente = new IdUtente();
		utente.setIdAmministrazione(ticket.getIdAmministrazione());
		utente.setIdSistema(ticket.getIdSistema());
		idTrasmissione.setUtente(utente);
		
		idObject.setTrasmissione(idTrasmissione);

		this._delete(jdbcProperties, log, connection, sqlQueryObject, idObject);

	}

	private void _delete(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, Object id) throws NotImplementedException,ServiceException,Exception {
		
		org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities jdbcUtilities = 
				new org.openspcoop2.generic_project.dao.jdbc.utils.JDBCPreparedStatementUtilities(sqlQueryObject.getTipoDatabaseOpenSPCoop2(), log, connection);
		
		ISQLQueryObject sqlQueryObjectDelete = sqlQueryObject.newSQLQueryObject();
		
		TicketId idTicket = this.getIdFromObject(id);

		// Object ticket
		sqlQueryObjectDelete.setANDLogicOperator(true);
		sqlQueryObjectDelete.addDeleteTable(this.getTicketFieldConverter().toTable(Ticket.model()));
		
		sqlQueryObjectDelete.addWhereCondition(this.getTicketFieldConverter().toColumn(Ticket.model().FILE,true)+"=?");
		sqlQueryObjectDelete.addWhereCondition(this.getTicketFieldConverter().toColumn(Ticket.model().ID_AMMINISTRAZIONE,true)+"=?");
		sqlQueryObjectDelete.addWhereCondition(this.getTicketFieldConverter().toColumn(Ticket.model().ID_SISTEMA,true)+"=?");


		// Delete ticket
		jdbcUtilities.execute(sqlQueryObjectDelete.createSQLDelete(), jdbcProperties.isShowSql(), 
				new JDBCObject(idTicket.getTrasmissione().getFile(),idTicket.getTrasmissione().getFile().getClass()),
				new JDBCObject(idTicket.getTrasmissione().getUtente().getIdAmministrazione(),idTicket.getTrasmissione().getUtente().getIdAmministrazione().getClass()),
				new JDBCObject(idTicket.getTrasmissione().getUtente().getIdSistema(),idTicket.getTrasmissione().getUtente().getIdSistema().getClass()));

	}

	//@Override
	public void deleteById(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, TicketId idTicket) throws NotImplementedException,ServiceException,Exception {

		Object id = null;
		try{
			id = this.findIdTicket(jdbcProperties, log, connection, sqlQueryObject, idTicket,true);
		}catch(NotFoundException notFound){
			return;
		}			
		this._delete(jdbcProperties, log, connection, sqlQueryObject, id);
		
	}
	
	//@Override
	public NonNegativeNumber deleteAll(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject) throws NotImplementedException,ServiceException,Exception {
		
		return this.deleteAll(jdbcProperties, log, connection, sqlQueryObject, new JDBCExpression(this.getTicketFieldConverter()));

	}

	//@Override
	public NonNegativeNumber deleteAll(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, JDBCExpression expression) throws NotImplementedException, ServiceException,Exception {

		java.util.List<Object> lst = this._findAllObjectIds(jdbcProperties, log, connection, sqlQueryObject, new JDBCPaginatedExpression(expression));
		
		for(Object id : lst) {
			this._delete(jdbcProperties, log, connection, sqlQueryObject, id);
		}
		
		return new NonNegativeNumber(lst.size());
	
	}



	// -- DB
	
	//@Override
	public void deleteById(JDBCServiceManagerProperties jdbcProperties, Logger log, Connection connection, ISQLQueryObject sqlQueryObject, long tableId) throws ServiceException, NotImplementedException, Exception {
		throw new NotImplementedException("Table without long id column PK");
	}
}
