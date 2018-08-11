package com.gci.siarp.generales.domain;

import lombok.Data;

@Data
public class Condicion {
	private String codigo;
	private String descripCondicion;
	public Condicion(String asCodigo,String asDescrip){
		codigo=asCodigo;
		descripCondicion=asDescrip;
	}
}
