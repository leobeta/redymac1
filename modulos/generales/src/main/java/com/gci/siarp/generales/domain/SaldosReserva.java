package com.gci.siarp.generales.domain;

import java.util.Date;
import lombok.Data;

@Data
public class SaldosReserva {
	private Long   idSiniestro;
	private Double constAs;
	private Double reconAs;
	private Double constAF;
	private Double reconAF;
	private Double constIT;
	private Double reconIT;
	private Double constApS;
	private Double reconApS;
	private Double constApP;
	private Double reconApP;
	private Double constPI;
	private Double reconPI;
	private Double constPS;
	private Double reconPS;
	private Double constRI;
	private Double reconRI;
	private Double constIPP;
	private Double reconIPP;
	private Double constRS;
	private Double reconRS;
	private Double constHon;
	private Double reconHon;
	private Double constOtr;
	private Double reconOtr;
	private String usuario;
	private String maquina;
	private Date   fechaSiniestro;
	
	private Double mesada;
	private Double IBL;
	private Double IBLActualizado;
	private Integer nroMesadas;
	
	public SaldosReserva(){
		super();
	}
	
	public SaldosReserva(Long idSiniestro) {
		super();
		this.idSiniestro = idSiniestro;
		constAs=0.0;
		reconAs=0.0;
		constAF=0.0;
		reconAF=0.0;
		constIT=0.0;
		reconIT=0.0;
		constApS=0.0;
		reconApS=0.0;
		constApP=0.0;
		reconApP=0.0;
		constPI=0.0;
		reconPI=0.0;
		constPS=0.0;
		reconPS=0.0;
		constRI=0.0;
		reconRI=0.0;
		constIPP=0.0;
		reconIPP=0.0;
		constRS=0.0;
		reconRS=0.0;
		constHon=0.0;
		reconHon=0.0;
		constOtr=0.0;
		reconOtr=0.0;
		nroMesadas=0;
	}

}
