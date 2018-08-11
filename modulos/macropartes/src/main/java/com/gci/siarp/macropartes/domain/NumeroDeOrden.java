package com.gci.siarp.macropartes.domain;

public class NumeroDeOrden {
private long consecutivo;
private long consecutivoMasUno;
private String prefijo;
	public NumeroDeOrden() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(long consecutivo) {
		this.consecutivo = consecutivo;
	}
	public String getPrefijo() {
		return prefijo;
	}
	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}
	public long getConsecutivoMasUno() {
		return consecutivoMasUno;
	}
	public void setConsecutivoMasUno(long consecutivoMasUno) {
		this.consecutivoMasUno = consecutivoMasUno;
	}

}
