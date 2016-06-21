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
package org.govmix.proxy.igrue.test;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import org.govmix.proxy.igrue.ws.Credenziali;
import org.govmix.proxy.igrue.ws.GetEsiti;
import org.govmix.proxy.igrue.ws.GetEsitiResponse;
import org.govmix.proxy.igrue.ws.GetEventiResponse;
import org.govmix.proxy.igrue.ws.GetEventiResponse.Evento;
import org.govmix.proxy.igrue.ws.GetTabellaContesto;
import org.govmix.proxy.igrue.ws.GetTabellaContestoResponse;
import org.govmix.proxy.igrue.ws.Igrue;
import org.govmix.proxy.igrue.ws.IgrueService;
import org.govmix.proxy.igrue.ws.SendDatiAttuazione;
import org.govmix.proxy.igrue.ws.SendDatiAttuazioneResponse;



public final class Client {

    private static Igrue port;
    static boolean isInitialize = false;
    private static Holder<byte[]> statisticheelaborazioni = new Holder<byte[]>();
    private static Holder<byte[]> statistichescarti = new Holder<byte[]>();
    private static Holder<byte[]> esitoelaborazioneperanagraficadiriferimento = new Holder<byte[]>();
    private static Holder<byte[]> logdeglierrori = new Holder<byte[]>();
    private Client() {
    }

    private static String idAmministrazione, password;
    private static int idSistema;
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String args[]) {
    	
    	Scanner scanner = new Scanner( System.in );
    	
    	System.out.print( "--- Id Amministrazione: ");
		idAmministrazione = scanner.nextLine();
		System.out.print( "--- Id Sistema: ");
		try {
			idSistema = Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			System.out.println( "Id Sistema non valido: " + e);
			System.out.println();
			return;
		}
		System.out.print( "--- Password accesso IGRUE: ");
		password = scanner.nextLine();
		
		
        IgrueService ss = new IgrueService(Client.class.getClassLoader().getResource("Igrue.wsdl"));
        port = ss.getIgrue();
        ((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/proxyigrue/ws");
    	
    	while(true) {
    		
	    	System.out.println( "----------------------------------------------------- ");
			System.out.println( "------------- WSClient ProxyIGRUE v.2  -------------- ");
			System.out.println( "----------------------------------------------------- ");
			System.out.println( "---  1: Spedizione dati                           --- ");
			System.out.println( "---  2: Raccolta eventi                           --- ");
			System.out.println( "---  3: Raccolta esiti                            --- ");
			System.out.println( "---  4: Raccolta tabelle di contesto              --- ");
			System.out.println( "---  5: Richiesta Codice di procedura attivazione --- ");
			System.out.println( "---  9: Cambiare le credenziali di accesso        --- ");
			System.out.println( "----------------------------------------------------- ");
			System.out.println( "---  0: Modificare URL di invocazione             --- ");
			System.out.println( "---  " +  ((BindingProvider)port).getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY)); 
			System.out.println( "----------------------------------------------------- ");
			System.out.println();
			
			
			System.out.print( "--- Inserire un comando: ");
			
			int cmd = 0;
			try {
				cmd = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				System.out.println( "Comando non valido: " + e);
				System.out.println();
				continue;
			}
			

	        
	        switch (cmd) {
			case 0:
				System.out.print( "--- Inserire nuova URL: ");
				
				try {
					((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, scanner.nextLine());
				} catch (Exception e) {
					System.out.println( "URL non valida: " + e);
					System.out.println();
					continue;
				}
				break;

			case 1:
				System.out.print( "--- Path del file: ");
				try {
					System.out.println("File zip consegnato e registrato con nome " + trasmissione(scanner.nextLine()));
				} catch (Exception e) {
					System.out.println( "Spedizione fallita: " + e);
					System.out.println();
					continue;
				}
				break;
				
			case 2:
				System.out.print( "--- Data e ora di inizio di raccolta (es: 2013-01-01T12:00:00): ");
				String time = scanner.nextLine();
				System.out.print( "--- Folder di output: ");
				String out = scanner.nextLine();
				List<GetEventiResponse.Evento> eventi;
				try {
					eventi = eventi(time);
		        	System.out.println("Richiesti eventi successivi al " + time);
		            System.out.println("Ricevuta una lista di " + eventi.size() + " eventi");
		            File outDir = new File(out);
		            if(!outDir.isDirectory()) outDir.mkdir();
		            JAXBContext jaxbContext = JAXBContext.newInstance(GetEventiResponse.Evento.class);
		    		Marshaller marshaller = jaxbContext.createMarshaller();
		            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT , new Boolean(true));
		            FileOutputStream fos;
		            for(int i = 0; i<eventi.size();i++){
		            	File eventoXML = new File(out+"/"+eventi.get(i).getId() + ".xml");
		            	System.out.println(eventoXML.getAbsolutePath());
		            	if(!eventoXML.exists()) eventoXML.createNewFile();
						fos = new FileOutputStream(eventoXML);
						
						marshaller.marshal( new JAXBElement( new QName("http://ws.igrue.proxy.govmix.org","evento"), GetEventiResponse.Evento.class,  eventi.get(i)) , fos );
						fos.close();
		            }
				} catch (Exception e) {
					System.out.println( "Richiesta fallita: " + e);
					System.out.println();
					continue;
				}
				break;
				
			case 3:
				System.out.print( "--- Numero di trasmissione per il quale richiedere gli esiti: ");
				String trasmissione = scanner.nextLine();
				System.out.print( "--- Folder di output: ");
				out = scanner.nextLine();
				try {
					GetEsitiResponse esito = esito(trasmissione);
					System.out.println("Richiesti gli esiti per il file " + trasmissione);
					System.out.println("Elaborazione per Anagrafica di Riferimento (" + esito.getEsitoelaborazioneperanagraficadiriferimento() + "): " + esito.getEsitoelaborazioneperanagraficadiriferimentodescrizione());
				    System.out.println("Log degli Errori (" + esito.getLogdeglierrori() + "): " + esito.getLogdeglierroridescrizione());
				    System.out.println("Statistiche di Elaborazione (" + esito.getStatisticheelaborazioni() + "): " + esito.getStatisticheelaborazionidescrizione());
				    System.out.println("Statistiche Scarti (" + esito.getStatistichescarti() + "): " + esito.getStatistichescartidescrizione());
				    File outDir = new File(out);
		            if(!outDir.isDirectory()) outDir.mkdir();
		            File statisticheel = new File(outDir.getAbsolutePath()+"/" + "statisticheelaborazioni.zip");
		            File statistichesc = new File(outDir.getAbsolutePath()+"/" + "statistichescarti.zip");
	 	            File esitoelaboraz = new File(outDir.getAbsolutePath()+"/" + "esitoelaborazioneperanagraficadiriferimento.zip");
		            File logdeglierror = new File(outDir.getAbsolutePath()+"/" + "logdeglierrori.zip");
		            FileOutputStream fos = new FileOutputStream(statisticheel);
		            fos.write(statisticheelaborazioni.value);
		            fos.close();
		            fos = new FileOutputStream(statistichesc);
		            fos.write(statistichescarti.value);
		            fos.close();
		            fos = new FileOutputStream(esitoelaboraz);
		            fos.write(esitoelaborazioneperanagraficadiriferimento.value);
		            fos.close();
		            fos = new FileOutputStream(logdeglierror);
		            fos.write(logdeglierrori.value);
		            fos.close();
				} catch (Exception e) {
					System.out.println( "Richiesta fallita: " + e);
					System.out.println();
					continue;
				}
				break;
				
			case 4:
				System.out.print( "--- Tabella da richiedere: ");
				String tab = scanner.nextLine();
				System.out.print( "--- Folder di output: ");
				out = scanner.nextLine();
				try {
					byte[] tabyte = tabellaContesto(tab);
					System.out.println("Richiesta tabella " + tab);
		            File outDir = new File(out);
		            if(!outDir.isDirectory()) outDir.mkdir();
		            File tabella = new File(outDir.getAbsolutePath()+ "/" + tab + ".zip");
		            FileOutputStream fos = new FileOutputStream(tabella);
		            fos.write(tabyte);
		            fos.close();
				} catch (Exception e) {
					System.out.println( "Richiesta fallita: " + e);
					System.out.println();
					continue;
				}
				break;
				
			case 5:
				try{
				Holder<String> idAmministrazioneH = new Holder<String>();
				Holder<String> codiceProceduraAttivazioneH = new Holder<String>();
				Holder<Integer> idSistemaH = new Holder<Integer>();
				Holder<XMLGregorianCalendar> dataAssegnazione = new Holder<XMLGregorianCalendar>();
				codiceAttivazione(dataAssegnazione, idAmministrazioneH, idSistemaH, codiceProceduraAttivazioneH);
				System.out.println("Ricevuto nuovo Codice Procedura Attuazione:");
				System.out.println("Id Amministrazione: " + idAmministrazioneH.value);
				System.out.println("Id Sistema: " + idSistemaH.value);
				System.out.println("Id Procedura Attivazione:" + codiceProceduraAttivazioneH.value);
				} catch (Exception e) {
					System.out.println( "Richiesta fallita: " + e);
					System.out.println();
					continue;
				}
				break;
				
			case 9:
				System.out.print( "--- Id Amministrazione: ");
				idAmministrazione = scanner.nextLine();
				System.out.print( "--- Id Sistema: ");
				try {
					idSistema = Integer.parseInt(scanner.nextLine());
				} catch (Exception e) {
					System.out.println( "Id Sistema non valido: " + e);
					System.out.println();
					continue;
				}
				System.out.print( "--- Password accesso IGRUE: ");
				password = scanner.nextLine();
				break;
			default:
				System.out.println( "Comando non valido");
				System.out.println();
				break;
			}
	        
    	}
    }

    public static int trasmissione(String filename) throws IOException{
    	System.out.println("Invoking sendDatiAttuazione...");
        SendDatiAttuazione request = new SendDatiAttuazione();
        Credenziali c = new Credenziali();
        c.setIdAmministrazione(idAmministrazione);
        c.setIdSistema(idSistema);
        c.setPassword(password);
        request.setCredenziali(c);
        File file = new File(filename);
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fis.read(bytes);
        fis.close();
        SendDatiAttuazioneResponse response = port.sendDatiAttuazione(request, bytes);
        return response.getIdAssegnato();
    }
    
    
    public static GetEsitiResponse esito(String file) throws IOException{
    	System.out.println("Invoking esito...");
        GetEsiti request = new GetEsiti();
        Credenziali c = new Credenziali();
        c.setIdAmministrazione(idAmministrazione);
        c.setIdSistema(idSistema);
        c.setPassword(password);
        request.setCredenziali(c);
        Holder<GetEsitiResponse> response = new Holder<GetEsitiResponse>();
     
        request.setIdAssegnato(Integer.parseInt(file));
        
        port.getEsiti(request, response, statisticheelaborazioni, statistichescarti, esitoelaborazioneperanagraficadiriferimento, logdeglierrori);
       
        if(statisticheelaborazioni.value != null) System.out.println("Ricevuto esito statisticheelaborazioni size " + statisticheelaborazioni.value.length);
        if(statistichescarti.value != null) System.out.println("Ricevuto esito statistichescarti size " + statistichescarti.value.length);
        if(esitoelaborazioneperanagraficadiriferimento.value != null) System.out.println("Ricevuto esito esitoelaborazioneperanagraficadiriferimento size " + esitoelaborazioneperanagraficadiriferimento.value.length);
        if(logdeglierrori.value != null) System.out.println("Ricevuto esito logdeglierrori size " + logdeglierrori.value.length);
        return response.value;
    }
    
    public static List<Evento> eventi(String startdate) throws Exception{
    	System.out.println("Invoking eventi...");
    	Credenziali c = new Credenziali();
        c.setIdAmministrazione(idAmministrazione);
        c.setIdSistema(idSistema);
        c.setPassword(password);
        return port.getEventi(c, DatatypeFactory.newInstance().newXMLGregorianCalendar(startdate));
    }
    
    public static byte[] tabellaContesto(String nometabella) throws IOException{
    	System.out.println("Invoking tabellacontesto...");
        GetTabellaContesto request = new GetTabellaContesto();
        request.setNometabella(nometabella);
        Credenziali c = new Credenziali();
        c.setIdAmministrazione(idAmministrazione);
        c.setIdSistema(idSistema);
        c.setPassword(password);
        request.setCredenziali(c);
        Holder<GetTabellaContestoResponse> response = new Holder<GetTabellaContestoResponse>();
        Holder<byte[]> content = new Holder<byte[]>();
        port.getTabellaContesto(request, response, content);
        return content.value;
    }
    
    public static void codiceAttivazione(Holder<XMLGregorianCalendar> dataAssegnazione, Holder<String> idAmministrazioneH, Holder<Integer> idSistemaH, Holder<String> idCodiceProceduraAmministrazione) throws IOException{
    	System.out.println("Invoking CodiceProceduraAttivazione...");
        Credenziali c = new Credenziali();
        c.setIdAmministrazione(idAmministrazione);
        c.setIdSistema(idSistema);
        c.setPassword(password);
        port.getCodiceProceduraAttivazione(c, dataAssegnazione, idAmministrazioneH, idCodiceProceduraAmministrazione, idSistemaH);
    }
    
    
}
