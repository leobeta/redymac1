package com.gci.siarp.generales.domain.sap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Data;

@Data
public class ArchivoAnulado {

	public enum CamposAnulado{ID_SIARP, DOC_SAP, MOT_ANULACION, DESC_ANULACION}
	
	private String nombre;
	private List<HashMap<CamposAnulado, String>> registros ;
	
	
	public ArchivoAnulado() {
		super();
		this.registros = new ArrayList<HashMap<CamposAnulado,String>>();
		
	}


	@Override
	public String toString() {
		return "ArchivoAnulado [nombre="+nombre+", ("+registros.size()+") registros=" + registros + "]";
	}
	
	

}
