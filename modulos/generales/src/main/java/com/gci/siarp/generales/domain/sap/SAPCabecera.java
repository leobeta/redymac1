package com.gci.siarp.generales.domain.sap;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class SAPCabecera {

	private String tipoLinea;
	private String tipoRegistro;
	private Date fechaDocumento;
	private String sociedad;
	private Date fechaContab;
	private String codMoneda;
	private String idSiarp;
	private String textoOP;
 
	public SAPCabecera() {
		super();
	}

	public SAPCabecera(String tipoLinea, String tipoRegistro, Date fechaDocumento, String sociedad, Date fechaContab, String codMoneda, String idSiarp, String textoOP) {
		
		
		
		
		super();
		this.tipoLinea = tipoLinea;
		this.tipoRegistro = tipoRegistro;
		this.fechaDocumento = fechaDocumento;
		this.sociedad = sociedad;
		this.fechaContab = fechaContab;
		this.codMoneda = codMoneda;
		this.idSiarp = idSiarp;
		this.textoOP = textoOP;
	}

	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		return (tipoLinea == null ? "" : tipoLinea)
				+ ";"
				+ (tipoRegistro == null ? "" : tipoRegistro)
				+ ";"
				+ (fechaDocumento == null ? "" : df.format(fechaDocumento) + ";" + (sociedad == null ? "" : sociedad) + ";" + (fechaContab == null ? "" : df.format(fechaContab)) + ";"
						+ (codMoneda == null ? "" : codMoneda) + ";" + (idSiarp == null ? "" : idSiarp) + ";" + (textoOP == null ? "" : textoOP));
	}

}
