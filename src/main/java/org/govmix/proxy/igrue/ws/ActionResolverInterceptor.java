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
package org.govmix.proxy.igrue.ws;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.MessageSenderInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class ActionResolverInterceptor extends AbstractPhaseInterceptor<Message> {
	
	public ActionResolverInterceptor() {
		super(Phase.PREPARE_SEND);
		addBefore(MessageSenderInterceptor.class.getName());
	}
	
	public void handleMessage(Message message) throws Fault {
		String result = (String)message.get(Message.ENDPOINT_ADDRESS);
		if(!result.endsWith("/")) result = result + "/";
		String action = message.getExchange().getBindingOperationInfo().getOperationInfo().getName().getLocalPart();
		
		message.put(Message.ENDPOINT_ADDRESS, result + action);
	}
}