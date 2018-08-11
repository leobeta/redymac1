package com.gci.siarp.webapp.domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Upload.Receiver;

import lombok.extern.log4j.Log4j;

@Log4j
public class BloqueoReceiver implements Receiver {
	private static final long serialVersionUID = 1L;
	private String nombreArchivo;
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	private String url;
	private File file;
	
	public BloqueoReceiver(String url){
		this.url = url;
	}

	@Override
	public OutputStream receiveUpload(String filename, String mimeType) {
		FileOutputStream fos = null;
		if(!filename.equals("")){
			try{
				if(!mimeType.equals("application/xls")){
					Notification.show("Atencón", "El archivo debe ser formato Excel XLS", Type.ERROR_MESSAGE);
					return new OutputStream() {
						@Override
						public void write(int b) throws IOException {
						}
					};
				}
				if(this.nombreArchivo!=null && !nombreArchivo.trim().equals("")){
					file = new File(url + nombreArchivo);
					return fos = new FileOutputStream(file);
				}
			}catch(final java.io.FileNotFoundException e){
				log.error("Error en el archivo");
				log.error(e.getCause());
			}
			return fos;
		} else {
			return new OutputStream() {
				@Override
				public void write(int b) throws IOException {
				}
			};
		}
	}



	
}
