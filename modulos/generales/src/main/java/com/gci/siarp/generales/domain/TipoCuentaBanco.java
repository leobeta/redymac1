package com.gci.siarp.generales.domain;

import lombok.Data;

@Data
public class TipoCuentaBanco {
	private String codigo;
	private String descripTipoCuenta;
	public TipoCuentaBanco(String asCodigo,String asDescrip){
		codigo=asCodigo;
		descripTipoCuenta=asDescrip;
	}
}
