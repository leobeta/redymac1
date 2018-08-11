package com.gci.siarp.generales.service;

import java.text.NumberFormat;
import java.util.Locale;

import com.vaadin.data.util.converter.StringToLongConverter;


public class GCIStringToLongConverter extends StringToLongConverter {

	private static final long serialVersionUID = 1L;

	@Override
	protected NumberFormat getFormat(Locale locale) {
		NumberFormat format = super.getFormat(locale);
		format.setGroupingUsed(false);
		return format;
	}

}
