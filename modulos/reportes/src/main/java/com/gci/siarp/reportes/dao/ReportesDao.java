package com.gci.siarp.reportes.dao;

import java.util.Collection;

import org.apache.ibatis.annotations.Param;

import com.gci.siarp.api.annotation.SiarpDatabase;
import com.gci.siarp.reportes.domain.Reporte;

@SiarpDatabase
public interface ReportesDao {
	
	Collection<Reporte> findAllReportsUsuario(@Param("idUsuario")String idUsuario,@Param("codRep") Integer codRep);
	
	Collection<Reporte> findDistinctReportUsuario(@Param("idUsuario")String idUsuario);

	Integer maxReport(@Param("asIdUsuario")String asIdUsuario);
	
	void insertarReporte (@Param("asUsuario")String asUsuario,@Param("aiNsolic")Integer liNsolic,@Param("aiCodReporte")Integer aiCodReporte,
			@Param("asArg1")String asArg1,@Param("asArg2")String asArg2,@Param("asArg3")String asArg3,@Param("asArg4")String asArg4,@Param("asArg5")String asArg5,@Param("asArg6")String asArg6,@Param("asArg7")String asArg7,@Param("asArg8")String asArg8,@Param("asArg9")String asArg9,@Param("asArg10")String asArg10,
			@Param("asTipoRep")String asTipoRep,@Param("asUsarSqlyDw")String asUsarSqlyDw);
	
	void actualizaSQLreport(@Param("aiNsolic")Integer aiNsolic,@Param("asUsuario")String asUsuario,@Param("asSQL")String asSQL);
	
	String traerSQL(@Param("aiIdReporte")Integer aiIdReporte);
	
}
