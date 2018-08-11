package com.gci.siarp.macropartes.domain;

import lombok.Data;

@Data
public class Pedido {
	private Long item;
	private String referencia;
	private String nombre;
	private String ciudad;
	private Long cantidad;
	private String orden;
	private String sigla;
	private Long dir;
	private Long consumo;
	private String estado;
	private String nit;
	private String razonsocial;
	private String telefono;
	private String ciudadnit;
	private String direccion;
	private String email;
}
