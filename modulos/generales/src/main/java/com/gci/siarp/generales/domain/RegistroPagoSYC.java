package com.gci.siarp.generales.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RegistroPagoSYC{
	
	/*
	 * Registro que retorna el SP de SYC
	 */
	
	private String tdocApo;
	private String ndocApo;
	private String tdocAfi;
	private String ndocAfi;
	private String nomb1Afi;
	private String nomb2Afi;
	private String apel1Afi;
	private String apel2Afi;
	private String fechPago;
	private BigDecimal cotizObl;
	private BigDecimal tariArp;
	private String nombApo;
	private String ciclo;
	private String origen;
	private String novRet;
	private BigDecimal ibc;
	private BigDecimal dias;

}