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
package org.govmix.proxy.igrue.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.cxf.helpers.FileUtils;
import org.govmix.proxy.igrue.Configuration;
import org.govmix.proxy.igrue.web.ejb.IdUtente;
import org.govmix.proxy.igrue.web.ejb.Utente;
import org.govmix.proxy.igrue.web.ejb.dao.IServiceManager;
import org.govmix.proxy.igrue.web.ejb.dao.IUtenteService;
import org.govmix.proxy.igrue.web.ejb.dao.jdbc.JDBCServiceManager;
import org.govmix.proxy.igrue.web.ejb.utils.UtentiUtilities;
import org.govmix.proxy.igrue.web.service.ServiceManagerFactory;

public class UserManager {
	
	public boolean createUtente(Utente utente, String adminPwd) throws Exception {
		
		if(!adminPwd.equals(Configuration.ADMINPWD)) throw new Exception("Password di amministrazione non valida");
		
		PreparedStatement ps = null;
		Connection con = null;
		try{
			con = DBManager.getInstance().getConnection();
			IServiceManager serviceManager = new JDBCServiceManager(con,ServiceManagerFactory.getJDBCProperties(false));
			IUtenteService utenteService = serviceManager.getUtenteService();
			IdUtente idUtente = new IdUtente();
			idUtente.setIdAmministrazione(utente.getIdAmministrazione());
			idUtente.setIdSistema(utente.getIdSistema());
			if(utenteService.exists(idUtente)) {
				return false;
			}
			FileUtils.mkDir(new File(UtentiUtilities.getContestoDir(utente)));
			FileUtils.mkDir(new File(UtentiUtilities.getInboxDir(utente)));
			FileUtils.mkDir(new File(UtentiUtilities.getOutboxDir(utente)));

			String sql = toString("newUser.sql");
			
			sql = sql.replace("#IDAMMINISTRAZIONE#", utente.getIdAmministrazione());
			sql = sql.replace("#IDSISTEMA#", utente.getIdSistema().toString());
			sql = sql.replace("#PASSWORD#", utente.getPassword());
			sql = sql.replace("#MEFPASSWORD#", utente.getMefPassword());
			
			System.out.println(sql);
			
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			con.commit();
		} catch (Exception e){
			LogManager.getLoggerASConsole().error(e,e);
			con.rollback();
			throw e;
		}
		finally {
			if(ps != null) try { ps.close(); } catch (Exception e) {}
			if(con != null) try { con.close(); } catch (Exception e) {}
		}
		
		return true;
	}

	
	private String toString(String file) throws IOException{
		InputStream in = getClass().getClassLoader().getResourceAsStream("properties/"+file);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append('\n');
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
}
