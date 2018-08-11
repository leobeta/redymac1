package com.gci.siarp.generales.domain;

import java.util.Date;
import lombok.Data;
import lombok.NonNull;


@Data
public class MovimientoReserva {
	@NonNull
	private Long idSiniestro;
	private Long nroMov;
	private	String idTipoMov;
	private Date fechaMovimiento;
	private String indTipoProceso;
	private Double auxFunerario;
	private Double it;
	private Double ipp;
	private Double pi;
	private Double ps;
	private Double asistencial;
	private Double aportesSalud;
	private Double aportesPension;
	private Double retroPi;
	private Double retroPs;
	private Double honorarios;
	private Double otros;
	private Double valorSolicitud;
	private Double nroInterfaz;
	private Date fechaReg;
	private String tipoOpera;
	private Integer idParteCuerpo;
	private Integer idTipoLesion;
	private String idDx;
	private Integer diasIt;
	private Double PCL;
	private Integer indMortal;
	private Double ibc;
	private String usuarioAud;
	private String maquinaAud;
	private String estadoAud;
	private String operacionAud;
	private Date fechaModificacionAud;
	private String tipoMovOtr;
	private String moduloOrigina;
	
	public MovimientoReserva(){
		super();
	}

	
	public MovimientoReserva(Long idSiniestro) {
		super();
		this.idSiniestro = idSiniestro;
		this.auxFunerario = 0d;
		this.it = 0d;
		this.ipp = 0d;
		this.pi = 0d;
		this.ps = 0d;
		this.asistencial = 0d;
		this.aportesSalud = 0d;
		this.aportesPension = 0d;
		this.retroPi = 0d;
		this.retroPs = 0d;
		this.honorarios = 0d;
		this.otros = 0d;
		this.diasIt=0;
		this.estadoAud="A";
		this.operacionAud="I";
	}
	
	
}
