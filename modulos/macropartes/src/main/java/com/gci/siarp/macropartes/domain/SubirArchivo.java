package com.gci.siarp.macropartes.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.csvreader.CsvReader;
import com.gci.siarp.api.ui.SiarpSession;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

public class SubirArchivo implements Receiver, SucceededListener {

	private static final long serialVersionUID = 1L;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	public File file;
	private String ruta;
	
	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		//leerArchivo();
	}

	@Override
	public synchronized OutputStream receiveUpload(String filename, String mimeType) {
		FileOutputStream fos = null; // Stream to write to
		try {
			// Open the file for writing.
			file = new File("D:/siarp_conf/masivos/"+ruta);
			fos = new FileOutputStream(file);
		} catch (final java.io.FileNotFoundException e) {
			new Notification("Could not open file<br/>", e.getMessage(),
					Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
			return null;
		} 
		return fos; // Return the output stream to write to
	}

	/**public void leerArchivo()  {
		CsvReader masivo;
		try {
			masivo = new CsvReader(ruta);
			masivo.readHeaders();
			masivo.setDelimiter(';');
			listPedidos = new ArrayList<Pedido>();
			while(masivo.readRecord()){
				Pedido ped = new Pedido();
				ped.setSigla(masivo.get("SIGLA"));
				ped.setCiudad(masivo.get("CIUDAD"));
				ped.setOrden(masivo.get("ORDEN DE TRABAJO"));
				ped.setReferencia(masivo.get("REFERENCIA"));
				ped.setNombre(masivo.get("DESCRIPCION"));
				ped.setCantidad(Long.parseLong(masivo.get("CANTIDAD")));
				ped.setItem(Long.parseLong(masivo.get("ITEM")));
				
				listPedidos.add(ped);
//				System.out.println("SIGLA "+masivo.get(0)+" CIUDAD "+masivo.get(1)+" ORDEN "+masivo.get(2)+" REFERENCIA "+masivo.get(3)+" DESCRIPCION "+masivo.get(4)+" CANTIDAD "+masivo.get(5)+" ITEM "+masivo.get(6));
			}
			System.err.println("leerArchivo -> "+listPedidos);

		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}