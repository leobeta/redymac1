package com.gci.siarp.generales.dao;

import java.util.Collection;

import org.apache.ibatis.annotations.Param;

import com.gci.siarp.api.annotation.SiarpDatabase;
import com.gci.siarp.generales.domain.IBC;

@SiarpDatabase
public interface IBCDao {

	void crearIBC(IBC aIBC);
	
	void editIBC (IBC aIBC);
	
	void crearIBCIPP(IBC aIBC);
	
	void editIBCIPP(IBC aIBC);
	
	Collection<IBC> getIBCsSolic(@Param("idSolicitud")Integer idSolicitud);
	
	Collection<IBC> getIBCsSolicIPP(@Param("idSolicitud")Integer idSolicitud);
}
