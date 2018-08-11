package com.gci.siarp.auth.domain;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OpcionAplicacion {
	@NotNull
   Integer codigoAplicacion;
	
   @NotNull
   Integer codigoOpcion;
    
   String descripcionOpcion;
    
   String link;
    
   String padre1;
   String padre2;
   String padre3;
   String padre4;
   String padre5;
   String padre6;
   String padre7;
   String padre8;
   String padre9;
}
