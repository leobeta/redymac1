package com.gci.siarp.generales.domain.sap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.Data;

@Data
public class ArchivoPagoDetalle {
	
	public enum CamposPagoDetalle{NUM_CUENTA, TIPO_RET, IND_RET, IND_IVA, VALOR_MOV}
	
	private String nombre;
	private List<HashMap<CamposPagoDetalle, String>> registros ;
	
	public ArchivoPagoDetalle() {
		super();
		this.registros = new ArrayList<HashMap<CamposPagoDetalle,String>>();
	}

	@Override
	public String toString() {
		return "ArchivoPagoDetalle [nombre=" + nombre + ", registros=" + registros + "]";
	}


}
