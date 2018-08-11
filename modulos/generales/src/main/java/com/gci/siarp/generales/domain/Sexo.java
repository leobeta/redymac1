package com.gci.siarp.generales.domain;

import lombok.Data;

@Data
public class Sexo {
	private String codigo;
	private String descripSexo;
	public Sexo(String asCodigo,String asDescripSexo){
		codigo=asCodigo;
		descripSexo=asDescripSexo;
	}
}
