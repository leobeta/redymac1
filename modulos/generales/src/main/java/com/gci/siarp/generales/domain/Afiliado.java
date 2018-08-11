package com.gci.siarp.generales.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class Afiliado {
	
	private String 	idTipoDoc;
	private String 	idPersona;
	private String 	nombre1;
	private String 	nombre2;
	private String 	apellido1;
	private String 	apellido2;
	private String 	sexo;
	private Date 	fechaNacimiento;
	private BigDecimal edad;
	private String nombreCompleto;
	private String direccion;
	private String telefono;
	private String email;
		

}
