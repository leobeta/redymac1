package com.gci.siarp.generales.service;

import java.text.NumberFormat;
import java.util.Locale;

import com.vaadin.data.util.converter.StringToDoubleConverter;

/**
 * Clase para cambiar el Locale por defecto, con esta configuracion asegura que tome la 
 * configuracion de deciamles con ".", ademas se quita la opcion de agrupar miles.
 *  
 * @author gci 
 * 
 */

public class GCIStringToDoubleConverter extends StringToDoubleConverter {	
	
	
	private static final long serialVersionUID = 1L;

	@Override
	protected NumberFormat getFormat(Locale locale) {
		NumberFormat format = NumberFormat.getInstance(Locale.US);
		format.setGroupingUsed(false);
		return format;
	}

	
}



