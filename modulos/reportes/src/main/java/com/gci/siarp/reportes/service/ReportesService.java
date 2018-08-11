package com.gci.siarp.reportes.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.gci.siarp.api.annotation.SiarpService;
import com.gci.siarp.reportes.dao.ReportesDao;
import com.gci.siarp.reportes.domain.Reporte;

/**
 * 
 * @author cmoreno
 *
 */
@SiarpService
public class ReportesService implements com.gci.siarp.api.service.ReportesService {

	@Autowired
	ReportesDao reportesDao;

	public Collection<Reporte> findAllReportsUsuario(String idUsuario, Integer codRep) {
		return reportesDao.findAllReportsUsuario(idUsuario, codRep);
	}
	
	//estos metodos no sbreescriben al de API porque solo se utilizan en el módulo reportes
	//en cambio solicitarReporte si ya que se utiliza en otros módulos
	public Collection<Reporte> findDistinctReportUsuario(String idUsuario) {
		return reportesDao.findDistinctReportUsuario(idUsuario);
	}


	@Override
	public EstadoSolcitudReporte solicitarReporte(Integer aiCodReporte, String asUsuario, String asSql, String asTipoRep, String asUsarSqlyDw, String asArg1, String asArg2, String asArg3, String asArg4, String asArg5, String asArg6, String asArg7, String asArg8, String asArg9, String asArg10) {
		EstadoSolcitudReporte retorno;
		retorno = new EstadoSolcitudReporte();
		try {
			Integer liNsolic = reportesDao.maxReport(asUsuario);
			if (liNsolic == null)
				liNsolic = 0;
			liNsolic++;
			reportesDao.insertarReporte(asUsuario, liNsolic, aiCodReporte, asArg1, asArg2, asArg3, asArg4, asArg5, asArg6, asArg7, asArg8, asArg9, asArg10, asTipoRep, asUsarSqlyDw);
			if (asSql != null) {
				reportesDao.actualizaSQLreport(liNsolic, asUsuario, asSql);
			}
			retorno.setError(false);
		} catch (Exception e) {
			//StringWriter sw = new StringWriter();
			//PrintWriter pw = new PrintWriter(sw);
			//e.printStackTrace(pw);
			//retorno.setDescripcion(sw.toString());
			retorno.setDescripcion(e.getMessage());
			retorno.setError(true);
		}

		return retorno;
	}
	
	@Override
	public String traerSQL(Integer aiIdReporte){
		String lsDW=reportesDao.traerSQL(aiIdReporte);
		int index = lsDW.indexOf("SELECT");
		String substring = lsDW.substring(index);
		String Select = substring.substring(0, substring.indexOf("\""));
		return Select;
	}
	
}
