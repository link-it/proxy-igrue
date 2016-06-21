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

import org.apache.cxf.interceptor.AttachmentOutInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxws.interceptors.SwAOutInterceptor;
import org.apache.cxf.jaxws.interceptors.WrapperClassOutInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class SwaRequestFixInterceptor extends AbstractPhaseInterceptor<Message> {

	public SwaRequestFixInterceptor() {
		super(Phase.PRE_LOGICAL);
		addAfter(SwAOutInterceptor.class.getName());
		addBefore(WrapperClassOutInterceptor.class.getName());
	}
	
	public void handleMessage(Message message) throws Fault {
		if(message.getExchange().getBindingOperationInfo().getOperationInfo().getInputName().equals("inviaFile")) {
			message.put(AttachmentOutInterceptor.WRITE_ATTACHMENTS, true);
		} else {
			message.put(AttachmentOutInterceptor.WRITE_ATTACHMENTS, false);
			message.put(Message.ATTACHMENTS, null);
		}
	}
}