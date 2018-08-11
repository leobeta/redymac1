package com.gci.siarp.generales.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.gci.siarp.generales.dao.IBCDao;
import com.gci.siarp.api.annotation.SiarpService;
import com.gci.siarp.generales.domain.IBC;


/**
 * 
 * @author cmoreno
 *
 */
@SiarpService
public class IBCService {
	@Autowired
	private IBCDao IBCDao;
	
	public void crearIBC(IBC aIBC,String asOrigen){
		if (asOrigen.equals("IP"))
			IBCDao.crearIBCIPP(aIBC);
		else
			IBCDao.crearIBC(aIBC);
	}
	
	public void actualizarIBC(IBC aIBC,String asOrigen){
		if (asOrigen.equals("IP"))
			IBCDao.editIBCIPP(aIBC);
		else
			IBCDao.editIBC(aIBC);
	}
	
	public Collection<IBC> findAllIBCSolic(Integer idSolicitud,String asOrigen) {
		if (asOrigen.equals("PE"))
			return IBCDao.getIBCsSolic(idSolicitud);
		else
			return IBCDao.getIBCsSolicIPP(idSolicitud);
	}
	
}
