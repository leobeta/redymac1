package com.gci.siarp.generales.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import org.apache.ibatis.annotations.Param;
import com.gci.siarp.api.annotation.SiarpDatabase;
import com.gci.siarp.generales.domain.BeneficiarioResMatem;
import com.gci.siarp.generales.domain.IyK;
import com.gci.siarp.generales.domain.MovimientoReserva;
import com.gci.siarp.generales.domain.PensionNewNota;
import com.gci.siarp.generales.domain.ResultadoPension;
import com.gci.siarp.generales.domain.SaldosReserva;
import com.gci.siarp.generales.domain.SaldosRubro;
import com.gci.siarp.generales.domain.Siniestro;
import com.gci.siarp.generales.domain.porcentSaludPension;

@SiarpDatabase
public interface ReservasDao {
	
	void bloqueaTabla(@Param("alIdSiniestro") Long alIdSiniestro);
	
	Integer consultarReserva(@Param("alIdSiniestro") Long alIdSiniestro);
	
	Siniestro consultarSiniestro(@Param("alIdSiniestro") Long alIdSiniestro);
	
	Double valorAsistencialAT(@Param("aiParteCuerpo")Integer aiParteCuerpo,@Param("aiNaturalezaLesion")Integer aiNaturalezaLesion);
	
	Double valorAsistencialEP(@Param("asDiagnostico")String asDiagnostico);
	
	Double valorPCLAT(@Param("aiParteCuerpo")Integer aiParteCuerpo,@Param("aiNaturalezaLesion")Integer aiNaturalezaLesion);
	
	Double valorPCLlEP(@Param("asDiagnostico")String asDiagnostico);
	
	Integer diasITAT(@Param("aiParteCuerpo")Integer aiParteCuerpo,@Param("aiNaturalezaLesion")Integer aiNaturalezaLesion);
	
	Integer diasITEP(@Param("asDiagnostico")String asDiagnostico);
	
	porcentSaludPension traerPorcentSaludPension(@Param("adFecha")Date asFecha);
	
	Double ibcConst(@Param("alIdSiniestro") Long alIdSiniestro);
	
	Integer tieneMovLiberaMatemat(@Param("alIdSiniestro") Long alIdSiniestro);
	
	Integer existeTipoMovOtr(@Param("tipoMovOtr") String tipoMovOtr);
	
	Double getHonorarios(@Param("idSiniestro") Long idSiniestro);
	
	SaldosRubro getSaldosOtrJur(@Param("idSiniestro") Long idSiniestro,@Param("tipoReserva")String tipoReserva, @Param("tipoMovOtr") String tipoMovOtr,@Param("asModuloOrigina") String asModuloOrigina);
	
	Double consultarMortalidad(@Param("alEdad")Long alEdad,@Param("asTipo")String asTipo);
	
	Double consultarMortalidadVersion(@Param("asVersion")String asVersion,@Param("asTipoPer")String asTipoPer,@Param("aiEdad")Integer aiEdad);
	
	ResultadoPension valorPension(@Param("tDoc") String tDoc,@Param("docu") String docu,@Param("tipoPension") String tipoPension,@Param("edad1") Double edad1, 
			@Param("edad2")Double edad2,@Param("tipoPersona1") String tipoPersona1,@Param("tipoPersona2") String tipoPersona2,
			@Param("a47") String a47,@Param("p") Double p,@Param("par14") String par14,@Param("mesCorte") Long mesCorte,@Param("k") Double k, 
			@Param("smin") Double smin,@Param("i") Double i,@Param("feCau") Date feCau,@Param("mortalIni") Double mortalIni, 
			@Param("A") Double A,@Param("mortalIni2v") Double mortalIni2v);
	
	IyK buscarIyK(@Param("adFecha") Date adFecha);
	
	SaldosReserva saldosReserva(@Param("alIdSiniestro")Long alIdSiniestro);
	
	SaldosReserva saldosReservaReaseguro(@Param("alIdSiniestro")Long alIdSiniestro);
	
	Double reservaJuridico(@Param("alIdSiniestro") Long alIdSiniestro, @Param("tipoReserva")String tipoReserva,@Param("asTipoMovOtr") String asTipoMovOtr);
	
	Double reservaNoJuridico(@Param("alIdSiniestro") Long alIdSiniestro, @Param("tipoReserva")String tipoReserva,@Param("asTipoMovOtr") String asTipoMovOtr);
		
	Long maxMovRes(@Param("alIdSiniestro") Long alIdSiniestro);
	
	void insertMaestroReserva(SaldosReserva aSaldos);
	
	void insertMovReserva(MovimientoReserva movReserva);
	
	void modificaMaestro(@Param("saldos")SaldosReserva saldos,@Param("mov")MovimientoReserva mov);
	
	void modificaMaestroRecon(@Param("saldos")SaldosReserva saldos,@Param("mov")MovimientoReserva mov);
			
	void actuRetroPiMaestro(@Param("alIdSiniestro")Long alIdSiniestro,@Param("adbValor")Double adbValor,@Param("asUsuario")String asUsuario,@Param("asMaquina")String asMaquina);
	
	void actuRetroPiDetalle(@Param("alIdSiniestro")Long alIdSiniestro,@Param("alNroMov")Long alNroMov,@Param("adbValor")Double adbValor);
	
	void actuRetroPsMaestro(@Param("alIdSiniestro")Long alIdSiniestro,@Param("adbValor")Double adbValor,@Param("asUsuario")String asUsuario,@Param("asMaquina")String asMaquina);
	
	void actuRetroPsDetalle(@Param("alIdSiniestro")Long alIdSiniestro,@Param("alNroMov")Long alNroMov,@Param("adbValor")Double adbValor);
	
	void liberaRetroPI(@Param("alIdSiniestro")Long alIdSiniestro,@Param("adPCL")BigDecimal adPCL,@Param("asUsuario")String asUsuario,@Param("asMaquina")String asMaquina);
	
	void insertReservasCM(@Param("idSiniestro")Long idSiniestro, @Param("nroMov")Long nroMov, @Param("idCuenta")Long idCuenta, @Param("idFactura")Long idFactura, @Param("usuario")String usuario, @Param("maquina")String maquina);
	
	ArrayList<BeneficiarioResMatem> traerBeneficiarios(@Param("asTDoc")String asTDoc,@Param("asDocu")String asDocu,@Param("adFechaCorte")Date adFechaCorte);
	
	PensionNewNota pension2b(	
			@Param("asTDoc")String asTDoc,			@Param("asDocu")String asDocu,			@Param("asTipoPension")String asTipoPension,@Param("adbEdad1")Double adbEdad1,		
			@Param("adbEdad2")Double adbEdad2,		@Param("adbEdad3")Double adbEdad3,		@Param("asTipoPer1")String asTipoPer1,		@Param("asTipoPer2")String asTipoPer2,
			@Param("asTipoPer3")String asTipoPer3,	@Param("asA47")String asA47,			@Param("adbP")Double adbP,					@Param("asPar14")String asPar14,
			@Param("aiMesCorte")Integer aiMesCorte,	@Param("adbK")Double adbK,				@Param("adbSmin")Double adbSmin,			@Param("adbI")Double adbI,
			@Param("adFeCau")Date adFeCau,			@Param("adbA")Double adbA,				@Param("adbMortIni1")Double adbMortIni1,	@Param("adbMortIni2")Double adbMortIni2,
			@Param("adbMortIni3")Double adbMortIni3,@Param("adbLimit1")Double adbLimit1,	@Param("adbLimit2")Double adbLimit2,		@Param("adbLimit3")Double adbLimit3,
			@Param("asVersion")String asVersion,	@Param("adbIncSalud")Double adbIncSalud,@Param("adbIncPension")Double adbIncPension);

	String nombreBeneficiario(@Param("asTdocAfil")String asTdocAfil,@Param("asDocAfil")String asDocAfil,@Param("asTdocBenef")String asTdocBenef,@Param("asDocBenef")String asDocBenef);
	
}
