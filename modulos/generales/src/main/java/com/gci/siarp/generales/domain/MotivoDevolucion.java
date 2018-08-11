package com.gci.siarp.generales.domain;

import lombok.Data;

@Data
public class MotivoDevolucion {
	private String codigo;
	private String descripcion;
	
	public MotivoDevolucion(String codigo, String descripcion){
		this.codigo=codigo;
		this.descripcion=descripcion;
	}
}
