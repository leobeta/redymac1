package com.gci.siarp.generales.domain;

import java.util.Date;

import lombok.Data;

@Data
public class RecaudoSABASS {
	
	private String periodo;
	private Long ibc;
	private Date agFechaPago;
	private Date periodo2;
	private Integer diasCotizados;

	
}
