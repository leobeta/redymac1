package com.gci.siarp.generales.dao;

import java.util.Collection;

import com.gci.siarp.api.annotation.SiarpSABASSDatabase;
import com.gci.siarp.generales.domain.RecaudoSABASS;
import com.gci.siarp.generales.domain.Siniestro;

@SiarpSABASSDatabase
public interface SABASSDao {
	
	Collection<RecaudoSABASS> traerIBCsSABASS(Siniestro aSini);

}
