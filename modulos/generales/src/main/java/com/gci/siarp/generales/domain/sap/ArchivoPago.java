package com.gci.siarp.generales.domain.sap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Data;



@Data
public class ArchivoPago {
	public enum CamposPago{ID_SIARP, FECHA_PAGO, VALOR, ORDEN_PAGO, SOLICITUD_PAGO, ID_ORIGEN_PAGO}
	
	
	private String nombre;
	private List<HashMap<CamposPago, String>> registros ;
	
	public ArchivoPago() {
		super();
		this.registros = new ArrayList<HashMap<CamposPago,String>>();
	}

	@Override
	public String toString() {
		return "ArchivoPago [nombre=" + nombre + ", registros=" + registros + "]";
	}
	

}
