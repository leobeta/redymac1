package com.gci.siarp.generales.domain;

import java.util.Date;
import lombok.Data;

@Data
public class BeneficiarioResMatem {
	private String 	ID_TIPO_DOC_BEN;
	private String 	ID_BENEFICIARIO;
	private Integer parentesco;
	private Date 	FECHA_NACIMIENTO;
	private String 	sexo;
	private String 	tipo_persona;
	private Double 	ei;
	private Double 	mortalidad;
	private Double 	edad;
	private Double 	edad2010;
	private Double 	mortalidad2010RV08;
	private Double 	mortalidad2010RV89;
}
