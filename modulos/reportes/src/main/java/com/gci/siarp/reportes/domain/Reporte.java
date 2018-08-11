package com.gci.siarp.reportes.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Reporte {
	
	/*
	 * Reporte de un usuario en la bandeja de reportes
	 */
	private String codUsuario;
	private Integer codReporte;
	private Integer nroSolicitud;
	private String  descripReporte;
	private String descripcion_modulo; 
	private Date fechaSolicitud;
	private Integer tiempoGeneracion;
	private String ruta;
	private Integer nroArgumentos;
	private String datoArg1; 
	private String datoArg2;
	private String datoArg3;
	private String datoArg4;
	private String datoArg5;
	private String datoArg6;
	private String datoArg7;
	private String datoArg8;
	private String datoArg9;
	private String datoArg10;
	private String descripArg1;
	private String descripArg2; 
	private String descripArg3;
	private String descripArg4;
	private String descripArg5;
	private String descripArg6;
	private String descripArg7;
	private String descripArg8;
	private String descripArg9;
	private String descripArg10;
	private String descripError;
	
	private String rutaDmz;
	private String descripErrorDmz;

}