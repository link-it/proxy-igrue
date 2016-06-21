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
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.govmix.proxy.igrue.web.dto.EsitoDTO;
import org.govmix.proxy.igrue.web.dto.EventoDTO;
import org.govmix.proxy.igrue.web.dto.TrasmissioneDTO;
import org.govmix.proxy.igrue.web.ejb.IdTrasmissione;
import org.govmix.proxy.igrue.web.ejb.utils.UtentiUtilities;
import org.govmix.proxy.igrue.web.service.IDettaglioDatiContesto;
import org.govmix.proxy.igrue.web.service.IEsito;
import org.govmix.proxy.igrue.web.service.IEvento;
import org.govmix.proxy.igrue.web.service.ITrasmissione;
import org.govmix.proxy.igrue.web.service.Utils;
import org.openspcoop2.generic_project.exception.ServiceException;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

public class TrasmissioniBean extends BaseBean<TrasmissioneDTO, IdTrasmissione> {

	private static final String LOG_DEGLI_ERRORI = "Log degli Errori";
	private static final String ELABORAZIONE_PER_ANAGRAFICA_DI_RIFERIMENTO = "Elaborazione Per Anagrafica di Riferimento";
	private static final String STATISTICHE_SCARTI = "Statistiche Scarti";
	private static final String STATISTICHE_ELABORAZIONI = "Statistiche Elaborazioni";

	private static Logger log = Logger.getLogger(TrasmissioniBean.class);
	
	private IEvento eventiService;
	private IEsito esitiService;
	private List<EventoDTO> eventi;
	private List<EsitoDTO> esiti;
	private EsitoDTO selectedEsito;
	
	private boolean showEventiPanel;
	private boolean showEsitiPanel;
	private boolean showDatiCtxPanel = true;	
	private String stato;
	
	private List<String> datiCtxColumnNames;
	private List<IDettaglioDatiContesto> datiCtxValues;
	private Integer sizeDatiCtx;
	
	private Integer esitoUpload;
	private String transferError;
	
	public void setEsitiService(IEsito esitiService) {
		this.esitiService = esitiService;
	}
	
	public void setEventiService(IEvento eventiService) {
		this.eventiService = eventiService;
	}
	
	public List<EsitoDTO> getEsiti() throws ServiceException{
		
		if(this.selectedElement==null)
			return new ArrayList<EsitoDTO>();
		
		if(this.esiti!=null)
			return this.esiti;
		
		this.esiti = new ArrayList<EsitoDTO>();

		int key = this.selectedElement.getFile();
		//statistiche elaborazioni
		EsitoDTO selaborazioni = new EsitoDTO();
		selaborazioni.setNome(STATISTICHE_ELABORAZIONI);
		selaborazioni.setEsitoPresente(this.esitiService.isEsitoElaborazioniPresente(key));
		selaborazioni.setDescrizioneEsito(this.esitiService.getDescrizioneEsitoElaborazioni(key));
		selaborazioni.setDettagliEsito(this.esitiService.getDettaglioStatisticheElaborazioni(key));
		selaborazioni.setParteVariabileLbl("Parte Variabile");
		selaborazioni.setId(key);
		
		this.esiti.add(selaborazioni);
		
		//sttistiche scarti
		EsitoDTO scarti = new EsitoDTO();
		scarti.setNome(STATISTICHE_SCARTI);
		scarti.setEsitoPresente(this.esitiService.isStatisticheScartiPresente(key));
		scarti.setDescrizioneEsito(this.esitiService.getDescrizioneStatisticheScarti(key));
		scarti.setDettagliEsito(this.esitiService.getDettaglioStatisticheScarti(key));
		scarti.setParteVariabileLbl("Errore");
		scarti.setId(key);
		
		this.esiti.add(scarti);
		
		//elaborazioni per anagrafica
		EsitoDTO anagrafica = new EsitoDTO();
		anagrafica.setNome(ELABORAZIONE_PER_ANAGRAFICA_DI_RIFERIMENTO);
		anagrafica.setEsitoPresente(this.esitiService.isEsitoElaborazioniPresente(key));
		anagrafica.setDescrizioneEsito(this.esitiService.getDescrizioneEsitoElaborazioni(key));
		anagrafica.setDettagliEsito(this.esitiService.getDettaglioEsitoElaborazioni(key));
		anagrafica.setParteVariabileLbl("Esito");
		
		anagrafica.setId(key);
		
		this.esiti.add(anagrafica);
		//log errori
		EsitoDTO errori = new EsitoDTO();
		errori.setNome(LOG_DEGLI_ERRORI);
		errori.setEsitoPresente(this.esitiService.isLogErroriPresente(key));
		errori.setDescrizioneEsito(this.esitiService.getDescrizioneLogErrori(key));
		errori.setDettagliEsito(this.esitiService.getDettaglioLogErrori(key));
		errori.setParteVariabileLbl("Errore");
		errori.setId(key);
		this.esiti.add(errori);
		
		return this.esiti;
	}
	
	public List<String> getDatiCtxColumnNames(){
		
		if(this.selectedElement==null || this.selectedElement.getFile()<=0)
			return new ArrayList<String>();
		
		if(this.datiCtxColumnNames!=null)
			return this.datiCtxColumnNames;
		
		this.datiCtxColumnNames = ((ITrasmissione)this.service).getTitoli();
		
		return this.datiCtxColumnNames;
	
	}
	
	public Integer getSizeDatiCtx(){
		if(this.datiCtxValues==null)
			return 0;
		
		if(this.sizeDatiCtx!=null)
			return this.sizeDatiCtx;
		
		this.sizeDatiCtx = this.datiCtxValues.size();
		
		return this.sizeDatiCtx;
	}
	
	public String getStato() throws ServiceException{
		
		if(this.selectedElement==null || this.selectedElement.getFile()<1)
			return null;
		
		if(this.stato!=null)
			return this.stato;
		
		this.stato = ((ITrasmissione)this.service).getStato(this.selectedElement.getFile());
		
		return this.stato;
		
	}
	
	public String download() {
		log.info("downloading file id="+this.selectedElement.getFile());
		try{
						
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
			String fileName = this.selectedElement.getFile()+".zip";
			
			response.setHeader("Content-Disposition", "attachment;filename="+fileName);

			// Streams we will use to read, write the file bytes to our response
			ByteArrayInputStream bis = null;
			OutputStream os = null;

			// First we load the file in our InputStream
			bis = new ByteArrayInputStream(((ITrasmissione)this.service).getDatiContesto(this.selectedElement.getFile()));
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
		}catch (Exception e) {
			log.error(e,e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Si e' verificato un errore durante il download del file:"+this.selectedElement.getFile()));
		}
		return "success";
	}
	
	public boolean isDownloadFile() {
		int file = this.selectedElement.getFile();
		File f = new File(UtentiUtilities.getInboxDir() + file + "/" + file + ".zip");
		return f.exists();
	}
	
	public String downloadEsito() {
		
		log.info("downloading esito id="+this.selectedEsito.getId());
		try{
						
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
			String fileName = this.selectedEsito.getNome()+".zip";
			
			fileName = StringUtils.replace(fileName, " ", "_");
			
			response.setHeader("Content-Disposition", "attachment;filename="+fileName);

			// Streams we will use to read, write the file bytes to our response
			ByteArrayInputStream bis = null;
			OutputStream os = null;

			// First we load the file in our InputStream
			if(STATISTICHE_ELABORAZIONI.equals(this.selectedEsito.getNome()))
				bis = new ByteArrayInputStream(this.esitiService.getDettaglioStatisticheElaborazioniZip(this.selectedEsito.getId()));
				
			if(STATISTICHE_SCARTI.equals(this.selectedEsito.getNome()))
				bis = new ByteArrayInputStream(this.esitiService.getDettaglioStatisticheScartiZip(this.selectedEsito.getId()));
			
			if(ELABORAZIONE_PER_ANAGRAFICA_DI_RIFERIMENTO.equals(this.selectedEsito.getNome()))
				bis = new ByteArrayInputStream(this.esitiService.getDettaglioEsitoElaborazioniZip(this.selectedEsito.getId()));
			
			if(LOG_DEGLI_ERRORI.equals(this.selectedEsito.getNome()))
				bis = new ByteArrayInputStream(this.esitiService.getDettaglioLogErroriZip(this.selectedEsito.getId()));
			
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
		}catch (Exception e) {
			log.error(e,e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Si e' verificato un errore durante il download dell'esito:"+this.selectedElement.getFile()));
		}
		return "success";
	}
	
	public List<IDettaglioDatiContesto> getDatiCtxValues() {
		if(this.selectedElement==null || this.selectedElement.getFile()<=0)
			return null;//new ArrayList<IDettaglioDatiContesto>();
		
		if(this.datiCtxValues!=null)
			return this.datiCtxValues;
		
		this.datiCtxValues = Utils.getDettagliDatiContesto(this.selectedElement.getFile());
		
		return this.datiCtxValues;
	}
	
	public void showEsitiListener(ActionEvent ae){
		this.showDatiCtxPanel=false;
		this.showEventiPanel=false;
		this.showEsitiPanel=true;
	}
	
	public void showEventiListener(ActionEvent ae){
		this.showDatiCtxPanel=false;
		this.showEventiPanel=true;
		this.showEsitiPanel=false;
	}
	
	public void showDatiCtxListener(ActionEvent ae){
		this.showDatiCtxPanel=true;
		this.showEventiPanel=false;
		this.showEsitiPanel=false;
	}
	public List<EventoDTO> getEventi() throws ServiceException{
		
		if(this.selectedElement==null)
			return null;//new ArrayList<EventoDTO>();
		
		if(this.eventi==null)
			this.eventi = this.eventiService.findByFile(this.selectedElement.getFile());
		
		return this.eventi;
	}

	public String dettagliEsito(){
		
		return null;
	}

	public Integer getSizeDettagliEsiti(){
		if(this.selectedEsito!=null && this.selectedEsito.getDettagliEsito()!=null)
			return this.selectedEsito.getDettagliEsito().size();
		
		return 0;
	}
	
	public EsitoDTO getSelectedEsito() {
		return selectedEsito;
	}

	public void setSelectedEsito(EsitoDTO selectedEsito) {
		this.selectedEsito = selectedEsito;
	}
	
	
	public void listener(UploadEvent event) throws Exception {
		UploadItem item = event.getUploadItem();
		
		try{
			byte[] data = null;
			
			//http://docs.jboss.org/richfaces/3.3.2.GA/en/apidoc_framework/org/richfaces/model/UploadItem.html
			if(item.isTempFile()){
				data = org.govmix.proxy.igrue.web.core.Utils.readBytesFromFile(item.getFile());
			}else{
				data = item.getData();
			}
						
			this.esitoUpload = ((ITrasmissione)this.service).nuovaTrasmissione(data);
		}catch (Exception e) {
			log.error(e,e);
			this.transferError = e.getMessage();			
			throw e;
		}
	}

	public String getTransferError(){
			return this.transferError;	
	}
	
	public String uploadCompleted(){
		if(this.esitoUpload!=null){
			String m = "Dati di Attuazione inviati con successo. La nuova trasmissione e' stata registrata con il codice "+this.esitoUpload;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(m));
			return "listaTrasmissioni";
		}
		return null;
	}
	
	public void nuovaTrasmissione(ActionEvent ae){
		this.esitoUpload = null;
	}
	
	public Integer getEsitoUpload() {
		return esitoUpload;
	}

	public boolean isShowEventiPanel() {
		return showEventiPanel;
	}

	public boolean isShowEsitiPanel() {
		return showEsitiPanel;
	}

	public boolean isShowDatiCtxPanel() {
		return showDatiCtxPanel;
	}

	public void setShowEventiPanel(boolean showEventiPanel) {
		this.showEventiPanel = showEventiPanel;
	}

	public void setShowEsitiPanel(boolean showEsitiPanel) {
		this.showEsitiPanel = showEsitiPanel;
	}

	public void setShowDatiCtxPanel(boolean showDatiCtxPanel) {
		this.showDatiCtxPanel = showDatiCtxPanel;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
}
