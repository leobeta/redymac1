package com.gci.siarp.macropartes.domain;

import lombok.Data;

@Data
public class Proveedor {
	private String identificacion;
	private String razonSocial;
	private String telefono;
	private String direccion;
	private String ciudad;
	private String email;
}
