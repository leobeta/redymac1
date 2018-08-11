package com.gci.siarp.generales.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RecaudoSYC {


	private String tdoc_apo;
	private String ndoc_apo;
	private String tdoc_afi;
	private String ndoc_afi;
	private String nomb1_afi;
	private String nomb2_afi;
	private String apel1_afi;
	private String apel2_afi;
	private String fech_pago;
	private BigDecimal cotiz_obl;
	private BigDecimal tari_arp;
	private String nomb_apo;
	private String ciclo;
	private String origen;
	private String nov_ret;
	private Long ibc;
	private Long dias;
	
	
}
