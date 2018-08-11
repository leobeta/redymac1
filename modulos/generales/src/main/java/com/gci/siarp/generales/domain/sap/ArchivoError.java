package com.gci.siarp.generales.domain.sap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Data;


@Data
public class ArchivoError {
	
public enum CamposError{ID_SIARP, COD_ERROR, DESC_ERROR}
	
	private String nombre;
	private List<HashMap<CamposError, String>> registros ;
	
	
	public ArchivoError() {
		super();
		this.registros = new ArrayList<HashMap<CamposError,String>>();
	}


	@Override
	public String toString() {
		return "ArchivoError [nombre= "+nombre+", ("+registros.size()+") registros=" + registros + "]";
	}
	
	
}
