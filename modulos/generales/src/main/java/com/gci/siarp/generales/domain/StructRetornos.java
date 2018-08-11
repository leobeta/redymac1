package com.gci.siarp.generales.domain;

import lombok.Data;

@Data
public class StructRetornos {
	private Integer iCodigo;
	private String sMensaje;

	public StructRetornos(Integer aiCodigo, String asMensaje) {
		iCodigo = aiCodigo;
		sMensaje = asMensaje;
	}
}
