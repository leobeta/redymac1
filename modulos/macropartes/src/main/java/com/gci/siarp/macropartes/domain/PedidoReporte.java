package com.gci.siarp.macropartes.domain;

import java.util.Date;

import lombok.Data;

@Data
public class PedidoReporte {
	private Long item;
	private String referencia;
	private String nombre;
	private String ciudad;
	private Long cantidad;
	private String orden;
	private String sigla;
	private String nit;
	private String razonSocial;
	private String direccion;
	private String telefono;
	private String email;
	private Date fechaPedido;
}
