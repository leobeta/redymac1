package com.gci.siarp.generales.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.gci.siarp.api.annotation.SiarpService;

@SiarpService
public class UtilidadesFechas {
	
	public Date convertirLDT(LocalDateTime ld){
		return Date.from(((LocalDateTime) ld).atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public Date convertirLD(LocalDate ld){
		return Date.from(((LocalDate) ld).atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	public LocalDateTime convertirDateLDT(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
	
	public LocalDate convertirDateLD(Date date){
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

}
