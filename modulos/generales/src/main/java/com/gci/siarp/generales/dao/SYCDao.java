package com.gci.siarp.generales.dao;

import java.util.Collection;

import com.gci.siarp.api.annotation.SiarpSYCDatabase;
import com.gci.siarp.generales.domain.RecaudoSYC;
import com.gci.siarp.generales.domain.Siniestro;

@SiarpSYCDatabase
public interface SYCDao {
	
	Collection<RecaudoSYC> traerIBCsSyc(Siniestro aSIni);

}
