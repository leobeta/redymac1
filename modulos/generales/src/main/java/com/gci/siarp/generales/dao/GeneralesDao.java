package com.gci.siarp.generales.dao;


import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.gci.siarp.api.annotation.SiarpDatabase;
import com.gci.siarp.generales.domain.AFP;
import com.gci.siarp.generales.domain.ARP;
import com.gci.siarp.generales.domain.Afiliado;
import com.gci.siarp.generales.domain.Banco;
import com.gci.siarp.generales.domain.CoberturaSiniestro;
import com.gci.siarp.generales.domain.Departamento;
import com.gci.siarp.generales.domain.DiagGral;
import com.gci.siarp.generales.domain.EPS;
import com.gci.siarp.generales.domain.Empresa;
import com.gci.siarp.generales.domain.EstadoPJ;
import com.gci.siarp.generales.domain.FormaNotificacion;
import com.gci.siarp.generales.domain.IPS;
import com.gci.siarp.generales.domain.MotivoDevolucion;
import com.gci.siarp.generales.domain.Municipio;
import com.gci.siarp.generales.domain.OficinaGiro;
import com.gci.siarp.generales.domain.ParametroGen;
import com.gci.siarp.generales.domain.Seccional;
import com.gci.siarp.generales.domain.Siniestro;
import com.gci.siarp.generales.domain.TipoDocumento;
import com.gci.siarp.generales.domain.IbcIbl;

@SiarpDatabase
public interface GeneralesDao {
	Collection<TipoDocumento> traerTiposDocu();
	
	Afiliado traerDatosAfiliado(@Param("asTDoc") String asTDoc ,@Param("asDocu") String asDocu);
	
	Empresa traerDatosEmpresa(@Param("asTDoc") String asTDoc,@Param("asDocu") String asDocu);
	
	Siniestro traerDatosSiniestro(@Param("alSini") Long alSini);
	
	Collection<Siniestro> traerSiniestros(@Param("asTdoc")String asTdoc,@Param("asDocu") String asDocu);
	
	Collection<Siniestro> traerSiniestrosPension(@Param("asTdoc")String asTdoc,@Param("asDocu") String asDocu);
	
	Collection<Siniestro> traerSiniestrosMuerte(@Param("asTdoc")String asTdoc,@Param("asDocu") String asDocu);
	
	Collection<Siniestro> traerSiniestrosPJInvalidez(@Param("asTdoc")String asTdoc,@Param("asDocu") String asDocu);
	
	Collection<Siniestro> traerSiniestrosPJSobrevivientes(@Param("asTdoc")String asTdoc,@Param("asDocu") String asDocu);
	
	Collection<DiagGral> buscarDiags(@Param("asIdDiag") String asIdDiag, @Param("asDescripDiag") String asDescripDiag);
	
	Collection<DiagGral> traerDiagnosticos();
	
	Double SMLV(@Param("adtFecha") Date adtFecha);
	
	IbcIbl traerIbcIbl(@Param("alIdSiniestro") long alIdSiniestro);
	
	Long traerSalarioRelacionLab(@Param("alIdSiniestro") long alIdSiniestro);
	
	Double ipc(@Param("adFecha") Date adFecha);
	
	Long buscarProcesoPJSiniestro(@Param("alIdSiniestro")Long alIdSiniestro);
	
	EstadoPJ pretensionesPJ(@Param("alIdProceso")Long alIdProceso);
	
	Long instanciasEnContra(@Param("alIdProceso")Long alIdProceso);
	
	Long cuantosMovPJ(@Param("alIdSiniestro")Long alIdSiniestro,@Param("asRubro")String asRubro);
	
	Collection<Seccional> traerSeccionales();
	
	Collection<Departamento> traerDepartamentos();
	
	Collection<Banco> traerBancos();
	
	Banco consultarBancoById (@Param("idBanco") Integer idBanco);
	
	Collection<Municipio> traerMunicipios(@Param ("aiIdDepto") Integer aiIdDepto);
	
	Collection<AFP> traerAFPs();
	
	Collection<EPS> traerEPSs();
	
	Collection<IPS> traerIPSs();		
	
	BigDecimal edad(@Param("fechaInicio")Date fechaInicio,@Param("fechaFin")Date fechaFin,@Param("decimales")Integer decimales);
	
	CoberturaSiniestro coberturaSiniestro(@Param("aiIdSiniestro")Integer aiIdSiniestro);
	
	ParametroGen recuperarParametroGen(@Param("aiCodigo")Integer aiCodigo);
	
	void actualizarConsecutivoGCI(@Param("aplicacion")Integer aplicacion);
	
	Integer recuperarConsecutivoGCI(@Param("aplicacion")Integer aplicacion);
	
	
	Date funcionDiaHabil(@Param("fecha") Date fecha, @Param("diasHabiles") Integer diasHabiles);
	
	public Collection<Departamento> traerDeptosGiro();
	
	public Collection<Banco> traerBancosGiro(@Param("aiIdDepto")Integer aiIdDepto,@Param("aiIdMunic")Integer aiIdMunic);
	
	public Collection<OficinaGiro> traerOficinasGiro(@Param("aiIdDepto")Integer aiIdDepto,@Param("aiIdMunic")Integer aiIdMunic,@Param("aiIdBanco")Integer aiIdBanco);
	
	public OficinaGiro traerDatosGiro(@Param("aiIdOficinaGiro")Integer aiIdOficinaGiro);
	
	public Collection<Municipio> traerMunicsGiro(@Param("aiIdDepto")Integer aiIdDepto);
	
	public Collection<ARP> traerARPs();

	public Collection<MotivoDevolucion> buscaMotivoDevolucion();

	public Collection<FormaNotificacion> buscarFormaNotificacion();
	
}