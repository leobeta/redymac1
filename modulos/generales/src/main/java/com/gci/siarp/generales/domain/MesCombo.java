package com.gci.siarp.generales.domain;

import lombok.Data;

@Data
public class MesCombo {
	private Integer nroMes;
	private String mes;
	
	public MesCombo(Integer aiNro, String asMes){
		nroMes=aiNro;
		mes=asMes;
	}
}
