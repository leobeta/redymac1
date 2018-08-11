package com.gci.siarp.generales.domain;

import lombok.Data;

@Data
public class OficinaGiro {
	private Integer idOficina;
	private Integer codigoOficina;
	private String nombreOficina;
	private String direccionOficina;
	private String descripcionConcatenada;
	private Integer idDepartamento;
	private Integer idMunicipio;
	private Integer idBanco;
}
