package com.gci.siarp.generales.service;

import java.util.Locale;

import com.vaadin.data.util.converter.StringToIntegerConverter;

public class GCIStringToIntegerConverter extends StringToIntegerConverter {	
	
	
	private static final long serialVersionUID = 1L;

	@Override
	public String convertToPresentation(Integer value, Class<? extends String> targetType, Locale locale) throws com.vaadin.data.util.converter.Converter.ConversionException {
		  if (value == null) {
	            return null;
	        }
		return value.toString();
	}


}
