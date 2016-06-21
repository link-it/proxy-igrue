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
package org.govmix.proxy.igrue.web.mbean;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.web.ejb.IdTabellacontesto;
import org.govmix.proxy.igrue.web.ejb.Tabellacontesto;
import org.govmix.proxy.igrue.web.service.IDettaglioTabella;
import org.govmix.proxy.igrue.web.service.ITabellacontesto;

public class TabellaContestoBean extends BaseBean<Tabellacontesto, IdTabellacontesto>{

	private static Logger log = Logger.getLogger(TabellaContestoBean.class);
	
	private List<String> columns;
	private List<IDettaglioTabella> values;
	private Integer totalRows;
	
		
	public List<String> getColumnsHeaders(){
		
		if(this.selectedElement==null || this.selectedElement.getNometabella()==null)
			return new ArrayList<String>();
		
		if(this.columns!=null)
			return this.columns;
		
		ITabellacontesto s = (ITabellacontesto)this.service;
		
		this.columns = s.getTitoli(this.selectedElement.getNometabella());
		
		return this.columns!=null ? this.columns : new ArrayList<String>();
		
	}
	
	public List<IDettaglioTabella> getColumnsValues(){
		
		if(this.selectedElement==null || this.selectedElement.getNometabella()==null)
			return new ArrayList<IDettaglioTabella>();
		
		if(this.values!=null)
			return this.values;
		
		ITabellacontesto s = (ITabellacontesto)this.service;
		
		this.values = s.getDettaglio(this.selectedElement.getNometabella());
		
		return this.values;
	}
	
	public Integer getSizeDetailsValues(){
		
		if(this.selectedElement==null || this.selectedElement.getNometabella()==null)
			return 0;
		
		this.totalRows = this.values!=null ? this.values.size() : 0;
		
		return this.totalRows;
	}
	
	public String download() {
		log.info("downloading file tabella contesto nome="+this.selectedElement.getNometabella());
		try{
			
			// Streams we will use to read, write the file bytes to our response
			ByteArrayInputStream bis = null;
			

			// First we load the file in our InputStream
			//se c'e' eccezione o il file viene non viene trovato questo e' il momento giusto per interrompere il download
			bis = new ByteArrayInputStream(((ITabellacontesto)this.service).getTabellaContesto(this.selectedElement.getNometabella()));
			
			
			// We must get first our context
			FacesContext context = FacesContext.getCurrentInstance();

			// Then we have to get the Response where to write our file
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

			// Now we create some variables we will use for writting the file to the
			// response
			// String filePath = null;
			int read = 0;
			byte[] bytes = new byte[1024];

			// Be sure to retrieve the absolute path to the file with the required
			// method
			// filePath = pathToTheFile;

			// Now set the content type for our response, be sure to use the best
			// suitable content type depending on your file
			// the content type presented here is ok for, lets say, text files and
			// others (like CSVs, PDFs)
			response.setContentType("application/zip");

			// This is another important attribute for the header of the response
			// Here fileName, is a String with the name that you will suggest as a
			// name to save as
			// I use the same name as it is stored in the file system of the server.
			String fileName = this.selectedElement.getNometabella()+".zip";
			
			response.setHeader("Content-Disposition", "attachment;filename="+fileName);

			OutputStream os = null;
			os = response.getOutputStream();
			
			// While there are still bytes in the file, read them and write them to
			// our OutputStream
			while ((read = bis.read(bytes)) != -1) {
				os.write(bytes, 0, read);
			}

			// Clean resources
			os.flush();
			os.close();

			FacesContext.getCurrentInstance().responseComplete();

			// End of the method
		}catch (FileNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Il file richiesto ("+this.selectedElement.getNometabella()+") non e' disponibile."));
			return null;
		}catch (Exception e) {
			log.error(e,e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Si e' verificato un errore durante il download tabella contesto:"+this.selectedElement.getNometabella()));
			return null;
		}
		return "success";
	}
	
	public void elementSelected(ActionEvent ae){
		this.values = null;
		this.columns = null;
		this.totalRows = null;
	}
}
