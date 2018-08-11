package com.gci.siarp.generales.domain.sap;

import lombok.Data;

@Data
public class SAPIdentificacion {

	private String tipo;
	private String idFichero;

	public SAPIdentificacion(String tipo, String idFichero) {
		super();
		this.tipo = tipo;
		this.idFichero = idFichero;
	}

	public SAPIdentificacion() {
		super();
	}

	@Override
	public String toString() {
		return (tipo == null ? "" : tipo) + ";" + (idFichero == null ? "" : idFichero);
	}

}
