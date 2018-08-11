package com.gci.siarp.generales.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class Siniestro {
	private Long 		idSiniestro;
	private Date 		fechaAT;
	private String 		tDocEmpre;
	private String 		docEmpre;
	private String 		tDocAfil;
	private String 		docAfil;
	private Date		fechaMuerte;
	private String 		tipo;
	private String 		fechaIniSYC;
	private String 		fechaFinSYC;
	private Date		fechaIniSABASS;
	private Date		fechaFinSABASS;
	private Date 		fechaEstructuracion;
	private String 		origen;
	private BigDecimal 	PCL;
	private String		razonSocial;
	private Integer		nroCalifPCLOrigen;
	private String		emisorDictamen;
	private String		municipioSiniestro;
	private Integer		ayudatep;
	private Date		fechaDictamen;
	private Long		nroDictamen;
	
	private String 		nombre1;
	private String 		nombre2;
	private String 		apellido1;
	private String 		apellido2;
	private String		estado;
	
	private Integer		idProceso;						//utilizado en pensiones cuando se crea una solicitud de fallo judicial
	
	private Integer		mortal;							//utilizados en reservas constitucion
	private Date		fechaAviso;						//utilizados en reservas constitucion
	private Integer		parteCuerpo;					//utilizados en reservas constitucion
	private Integer		naturalezaLesion;				//utilizados en reservas constitucion
	private String		diagnostico;					//utilizados en reservas constitucion
	
}
