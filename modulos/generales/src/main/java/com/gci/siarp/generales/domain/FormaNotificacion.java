package com.gci.siarp.generales.domain;

import lombok.Data;

@Data
public class FormaNotificacion {
	private String codigo;
	private String descripcion;
	
	public FormaNotificacion(String codigo, String descripcion){
		this.codigo=codigo;
		this.descripcion=descripcion;
	}
}
