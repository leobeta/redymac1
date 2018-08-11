package com.gci.siarp.generales.domain.sap;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class SAPDetalle {

	private String tipoLinea;
	private String tipo;
	private String codAbona;
	private String txtApellido1;
	private String txtApellido2;
	private String txtNombre;
	private String ciudad;
	private String cuentaAsociada;
	private String cuentaCont;
	private String grTesoreria;
	private String claseImpuesto;
	private String nroDoc;
	private String codTipoDoc;
	private String digitoVer;
	private String codBco;
	private String codTipoCta;
	private String nroCta;
	private String viaPagoTercero;
	private String debeHaber;
	private Integer posicion;
	private String retAplic;
	private Double impValor;
	private String condPago;
	private Date fechaBase;
	private String viaPagoFactura;
	private String snCausacion;
	private Double impIva;
	private String desc_pago;
	private String codSucCta;
	private String idSiarp;
	private String CECO;
	private String codigoCiudadGiro;
	private String codigoOficinaGiro;
	private String codConcepto;
	private String numExp;
	private Integer codJuzgado;
	private Integer numCAhorro;
	private Double valDeposito;
	private String tipoDocDemand;
	private String idDemand;
	private String tipoDocDemando;
	private String idDemando;
	private String apellDemandante;
	private String nomDemandante;
	private String apellDemando;
	private String nomDemando;

	public SAPDetalle() {
		super();
	}

	public SAPDetalle(String tipoLinea, String tipo, String codAbona, String txtApellido1, String txtApellido2, String txtNombre, String ciudad, String cuentaAsociada, String cuentaCont,
			String grTesoreria, String claseImpuesto, String nroDoc, String codTipoDoc, String digitoVer, String codBco, String codTipoCta, String nroCta, String viaPagoTercero, String debeHaber,
			Integer posicion, String retAplic, Double impValor, String condPago, Date fechaBase, String viaPagoFactura, String snCausacion, Double impIva, String desc_pago, String codSucCta,
			String idSiarp, String cECO, String codigoCiudadGiro, String codigoOficinaGiro, String codConcepto, String numExp, Integer codJuzgado, Integer numCAhorro, Double valDeposito,
			String tipoDocDemand, String idDemand, String tipoDocDemando, String idDemando, String apellDemandante, String nomDemandante, String apellDemando, String nomDemando) {
		super();
		this.tipoLinea = tipoLinea;
		this.tipo = tipo;
		this.codAbona = codAbona;
		this.txtApellido1 = txtApellido1;
		this.txtApellido2 = txtApellido2;
		this.txtNombre = txtNombre;
		this.ciudad = ciudad;
		this.cuentaAsociada = cuentaAsociada;
		this.cuentaCont = cuentaCont;
		this.grTesoreria = grTesoreria;
		this.claseImpuesto = claseImpuesto;
		this.nroDoc = nroDoc;
		this.codTipoDoc = codTipoDoc;
		this.digitoVer = digitoVer;
		this.codBco = codBco;
		this.codTipoCta = codTipoCta;
		this.nroCta = nroCta;
		this.viaPagoTercero = viaPagoTercero;
		this.debeHaber = debeHaber;
		this.posicion = posicion;
		this.retAplic = retAplic;
		this.impValor = impValor;
		this.condPago = condPago;
		this.fechaBase = fechaBase;
		this.viaPagoFactura = viaPagoFactura;
		this.snCausacion = snCausacion;
		this.impIva = impIva;
		this.desc_pago = desc_pago;
		this.codSucCta = codSucCta;
		this.idSiarp = idSiarp;
		this.CECO = cECO;
		this.codigoCiudadGiro = codigoCiudadGiro;
		this.codigoOficinaGiro = codigoOficinaGiro;
		this.codConcepto = codConcepto;
		this.numExp = numExp;
		this.codJuzgado = codJuzgado;
		this.numCAhorro = numCAhorro;
		this.valDeposito = valDeposito;
		this.tipoDocDemand = tipoDocDemand;
		this.idDemand = idDemand;
		this.tipoDocDemando = tipoDocDemando;
		this.idDemando = idDemando;
		this.apellDemandante = apellDemandante;
		this.nomDemandante = nomDemandante;
		this.apellDemando = apellDemando;
		this.nomDemando = nomDemando;
	}

	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		DecimalFormatSymbols simboloD = new DecimalFormatSymbols();
		simboloD.setDecimalSeparator('.');		
		DecimalFormat formato = new DecimalFormat("#.##", simboloD);
		return (tipoLinea == null ? "" : tipoLinea) + ";" + (tipo == null ? "" : tipo) + ";" + (codAbona == null ? "" : codAbona) + ";" + (txtApellido1 == null ? "" : txtApellido1) + ";"
				+ (txtApellido2 == null ? "" : txtApellido2) + ";" + (txtNombre == null ? "" : txtNombre) + ";" + (ciudad == null ? "" : ciudad) + ";" + (cuentaAsociada == null ? "" : cuentaAsociada)
				+ ";" + (cuentaCont == null ? "" : cuentaCont) + ";" + (grTesoreria == null ? "" : grTesoreria) + ";" + (claseImpuesto == null ? "" : claseImpuesto) + ";"
				+ (nroDoc == null ? "" : nroDoc) + ";" + (codTipoDoc == null ? "" : codTipoDoc) + ";" + (digitoVer == null ? "" : digitoVer) + ";" + (codBco == null ? "" : codBco) + ";"
				+ (codTipoCta == null ? "" : codTipoCta) + ";" + (nroCta == null ? "" : nroCta) + ";" + (viaPagoTercero == null ? "" : viaPagoTercero) + ";" + (debeHaber == null ? "" : debeHaber)
				+ ";" + (posicion == null ? "" : posicion) + ";" + (retAplic == null ? "" : retAplic) + ";" + (impValor == null ? "" : formato.format(impValor)) + ";" + (condPago == null ? "" : condPago) + ";"
				+ (fechaBase == null ? "" : df.format(fechaBase)) + ";" + (viaPagoFactura == null ? "" : viaPagoFactura) + ";" + (snCausacion == null ? "" : snCausacion) + ";"
				+ (impIva == null ? "" : formato.format(impIva)) + ";" + (desc_pago == null ? "" : desc_pago) + ";" + (codSucCta == null ? "" : codSucCta) + ";" + (idSiarp == null ? "" : idSiarp) + ";"
				+ (CECO == null ? "" : CECO) + ";" + (codigoCiudadGiro == null ? "" : codigoCiudadGiro) + ";" + (codigoOficinaGiro == null ? "" : codigoOficinaGiro) + ";"
				+ (codConcepto == null ? "" : codConcepto) + ";" + (numExp == null ? "" : numExp) + ";" + (codJuzgado == null ? "" : codJuzgado) + ";" + (numCAhorro == null ? "" : numCAhorro) + ";"
				+ (valDeposito == null ? "" : formato.format(valDeposito)) + ";" + (tipoDocDemand == null ? "" : tipoDocDemand) + ";" + (idDemand == null ? "" : idDemand) + ";"
				+ (tipoDocDemando == null ? "" : tipoDocDemando) + ";" + (idDemando == null ? "" : idDemando) + ";" + (apellDemandante == null ? "" : apellDemandante) + ";"
				+ (nomDemandante == null ? "" : nomDemandante) + ";" + (apellDemando == null ? "" : apellDemando) + ";" + (nomDemando == null ? "" : nomDemando);
	}

}
