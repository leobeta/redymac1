package com.gci.siarp.generales.domain;

import java.util.ArrayList;
import java.util.Date;

import lombok.Data;

@Data
public class ParametrosPensiones {
	private Double					valor;
	private String					mensaje;
	private ArrayList<Double>		edad;
	private ArrayList<Double>		edad10;
	private ArrayList<String>		tipopersona;
	private ArrayList<String>		tipopersona10;
	private ArrayList<Double>		mortalidad;
	private ArrayList<Double>		mortalidad10_rv08;
	private ArrayList<Double>		mortalidad10_rv89;
	private ArrayList<Double>		limit;
	private ArrayList<Double>		limit10;
	
	private ArrayList<String>		indicador;
	private ArrayList<String>		parentesco;
	private ArrayList<Date>			fnace;
	private ArrayList<String>		nombre;
	private Double					mesada;
	private Integer					mesadas;

}
