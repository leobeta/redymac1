package com.gci.siarp.generales.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.gci.siarp.api.annotation.SiarpService;
import com.gci.siarp.generales.dao.GeneralesDao;
import com.gci.siarp.generales.dao.SABASSDao;
import com.gci.siarp.generales.dao.SYCDao;
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
import com.gci.siarp.generales.domain.IbcIbl;
import com.gci.siarp.generales.domain.MotivoDevolucion;
import com.gci.siarp.generales.domain.Municipio;
import com.gci.siarp.generales.domain.OficinaGiro;
import com.gci.siarp.generales.domain.ParametroGen;
import com.gci.siarp.generales.domain.RecaudoSABASS;
import com.gci.siarp.generales.domain.RecaudoSYC;
import com.gci.siarp.generales.domain.Seccional;
import com.gci.siarp.generales.domain.Siniestro;
import com.gci.siarp.generales.domain.TipoDocumento;

@SiarpService
public class GeneralesService {

	@Autowired
	GeneralesDao generalesDao;
	@Autowired
	SABASSDao iSABBASDao;
	@Autowired
	SYCDao iSycDao;
	
	UtilidadesFechas iUtilFechas=new UtilidadesFechas();

	public Collection<Seccional> findAllSeccional() {
		return generalesDao.traerSeccionales();
	}

	public Collection<TipoDocumento> findAllTiposDocumento() {
		return generalesDao.traerTiposDocu();
	}

	public Afiliado findAfiliado(String asTDoc, String asDocu) {
		return generalesDao.traerDatosAfiliado(asTDoc, asDocu);
	}

	public Collection<Departamento> findDepartamentos() {
		return generalesDao.traerDepartamentos();
	}

	public Collection<Municipio> findMunicipios(Integer aiIdDepto) {
		return generalesDao.traerMunicipios(aiIdDepto);
	}

	public Collection<Banco> findBancos() {
		return generalesDao.traerBancos();
	}

	public Banco findBancoById(Integer idBanco) {

		return generalesDao.consultarBancoById(idBanco);
	}

	public Siniestro findSiniestro(Long alIdSini) {
		return generalesDao.traerDatosSiniestro(alIdSini);
	}

	public Collection<Siniestro> findSiniestros(String asTdoc, String asDocu, String asTipo) {
		if (asTipo.equals("PI") || asTipo.equals("SP"))
			return generalesDao.traerSiniestrosPension(asTdoc, asDocu);
		else if (asTipo.equals("MU"))
			return generalesDao.traerSiniestrosMuerte(asTdoc, asDocu);
		else if (asTipo.equals("TO"))
			return generalesDao.traerSiniestrosMuerte(asTdoc, asDocu);
		else if (asTipo.equals("JI"))//juridicos invalidez
			return generalesDao.traerSiniestrosPJInvalidez(asTdoc, asDocu);
		else if (asTipo.equals("JS"))//juridicos sobrevivientes
			return generalesDao.traerSiniestrosPJSobrevivientes(asTdoc, asDocu);
		else if (asTipo.equals("")) //buscar todos los siniestros que tenga el afiliado
			return generalesDao.traerSiniestros(asTdoc, asDocu);
		return generalesDao.traerSiniestrosMuerte(asTdoc, asDocu);
	}

	public Empresa findEmpresa(String asTDoc, String asDoc) {
		return generalesDao.traerDatosEmpresa(asTDoc, asDoc);
	}

	public Collection<DiagGral> findDiags(String asIdDiag, String asDescripDiag) {
		return generalesDao.buscarDiags(asIdDiag, asDescripDiag);
	}

	public Double SMLV(Date adtFecha) {
		return generalesDao.SMLV(adtFecha);
	}

	public IbcIbl traerIbcIbl(Siniestro aSini) {
		LocalDate ldtFechaSini = iUtilFechas.convertirDateLD(aSini.getFechaAT());
		LocalDate ldtFecha1 = LocalDate.now(), ldtFecha2 = LocalDate.now();
		Collection<RecaudoSABASS> lRecaudosSABASS;
		Collection<RecaudoSYC> lRecaudosSYC;
		Long llSalarioRelLab = new Long(0), llSumatoria = new Long(0), llCuantos = new Long(0), llMaxIBC = new Long(0);
		IbcIbl lIbcIbl = new IbcIbl();
		Double smlv = new Double(0);

		if (aSini.getTipo().equals("EP")) {
			if (ldtFechaSini.getMonthValue()==2 && ldtFechaSini.getDayOfMonth()==29)
				ldtFecha1=LocalDate.of(ldtFechaSini.getYear() -1, ldtFechaSini.getMonthValue(), 28);
			else
				ldtFecha1=LocalDate.of(ldtFechaSini.getYear() -1, ldtFechaSini.getMonthValue(), ldtFechaSini.getDayOfMonth());
		} else {
			if (ldtFechaSini.getMonthValue() <= 6) {
				ldtFecha1=LocalDate.of(ldtFechaSini.getYear() - 1, ldtFechaSini.getMonthValue() + 6, 1);
				
			} else {
				ldtFecha1=LocalDate.of(ldtFechaSini.getYear(), ldtFechaSini.getMonthValue() - 6, 1);
			}
		}
		ldtFecha2=ldtFechaSini;

		aSini.setFechaIniSABASS(iUtilFechas.convertirLD(ldtFecha1));
		aSini.setFechaIniSABASS(iUtilFechas.convertirLD(ldtFecha2));

		String lsTemp = "0" + ldtFecha1.getMonth();
		lsTemp = lsTemp.substring(lsTemp.length() - 2, lsTemp.length());
		aSini.setFechaIniSYC((ldtFecha1.getYear()) + "" + lsTemp + "01");

		lsTemp = "0" + ldtFecha2.getMonth();
		lsTemp = lsTemp.substring(lsTemp.length() - 2, lsTemp.length());
		aSini.setFechaFinSYC((ldtFecha2.getYear()) + "" + lsTemp + "01");

		lRecaudosSABASS = iSABBASDao.traerIBCsSABASS(aSini);
		lRecaudosSYC = iSycDao.traerIBCsSyc(aSini);

		llSalarioRelLab = generalesDao.traerSalarioRelacionLab(aSini.getIdSiniestro());
		if (llSalarioRelLab == null)
			llSalarioRelLab = new Long(0);

		for (RecaudoSABASS recSabass : lRecaudosSABASS) {
			llCuantos++;
			llSumatoria += recSabass.getIbc();
			if (llMaxIBC < recSabass.getIbc())
				llMaxIBC = recSabass.getIbc();
		}
		for (RecaudoSYC recSyc : lRecaudosSYC) {
			llCuantos++;
			llSumatoria += recSyc.getIbc();
			if (llMaxIBC < recSyc.getIbc())
				llMaxIBC = recSyc.getIbc();
		}
		if (llCuantos == 0) {
			lIbcIbl.setIbc((double) llSalarioRelLab);
			lIbcIbl.setIbl((double) llSalarioRelLab);
		} else {
			lIbcIbl.setIbc((double) llMaxIBC);
			lIbcIbl.setIbl((double) (llSumatoria / llCuantos));
		}
		smlv = generalesDao.SMLV(aSini.getFechaAT());
		if (smlv == null)
			smlv = new Double(0);
		smlv = (double) Math.round(smlv * 100.0) / 100.0;

		LocalDate ldtFechaC = LocalDate.of(1994, 12, 31);

		if ((aSini.getFechaAT()).compareTo(iUtilFechas.convertirLD(ldtFechaC)) < 0) {// en date
																		// menor
			lIbcIbl.setIbc(smlv);
			lIbcIbl.setIbl(smlv);
		} else {
			if (lIbcIbl.getIbl() < smlv)
				lIbcIbl.setIbl(smlv);
			if (lIbcIbl.getIbc() < smlv)
				lIbcIbl.setIbl(smlv);
		}

		return lIbcIbl;
	}

	public Double iblActualizado(Double adbIBL, Date adFechaOcurrencia, Date adFechaACalcular, String asTipo) {
		int j;
		Double ldbSmin, ldbSminFat;
		boolean lbSmin = false;
		LocalDate ldtFechaAt, ldtFechaCorte;

		ldtFechaAt = iUtilFechas.convertirDateLD(adFechaOcurrencia);
		ldtFechaCorte = iUtilFechas.convertirDateLD(adFechaACalcular);

		ldbSmin = SMLV(adFechaACalcular);
		ldbSminFat = SMLV(adFechaOcurrencia);

		if (adbIBL.longValue() == ldbSminFat.longValue())
			lbSmin = true;
		if (adbIBL < ldbSminFat && !asTipo.equals("P"))
			lbSmin = true;

		if (ldtFechaAt.getYear() == ldtFechaCorte.getYear())
			if (asTipo.equals("P")) // /// el IBL en IPP no se ajusta
				return adbIBL;
			else if (adbIBL.longValue() < ldbSmin.longValue())
				return ldbSmin;
			else
				return adbIBL;

		for (j = ldtFechaAt.getYear(); j <= (ldtFechaCorte.getYear()) - 1; j++) {
			
			if (!lbSmin)
				adbIBL = adbIBL * (1.0 + ipc(iUtilFechas.convertirLD(LocalDate.of(j, 1, 1))));
		}

		if (lbSmin)
			adbIBL = ldbSmin;
		adbIBL = (double) adbIBL.longValue();
		ldbSmin = (double) ldbSmin.longValue();

		if (asTipo.equals("P")) // /// el IBL en IPP no se ajusta
			return adbIBL;
		else if (adbIBL.longValue() < ldbSmin.longValue())
			return ldbSmin;
		else
			return adbIBL;

	}

	public Double mesadaActual(Double adbMesada, Date adFechaAt, Date adFechaCorte, Boolean abEsSMin) {
		LocalDate ldtFechaAt = iUtilFechas.convertirDateLD(adFechaAt);
		LocalDate ldtFechaCorte = iUtilFechas.convertirDateLD(adFechaCorte);
		Double ldbSmin;
		ldbSmin = SMLV(adFechaCorte);

		if (ldtFechaAt.getYear() == ldtFechaCorte.getYear()) {
			if (abEsSMin) // /// el IBL en IPP no se ajusta
				return ldbSmin;
			else if (adbMesada < ldbSmin)
				return ldbSmin;
			else
				return adbMesada;
		}

		if (abEsSMin)
			return ldbSmin;


		for (int j = ldtFechaAt.getYear(); j <= (ldtFechaCorte.getYear()) - 1; j++) {
			adbMesada = adbMesada * (1 + ipc(iUtilFechas.convertirLD(LocalDate.of(j,1,2))));
		}
		ldbSmin = (double) ldbSmin.longValue();
		adbMesada = (double) adbMesada.longValue();
		if (adbMesada.longValue() < ldbSmin.longValue())
			return ldbSmin;
		else
			return adbMesada;
	}

	public Double ipc(Date adFecha) {
		return generalesDao.ipc(adFecha);
	}

	public LocalDate ultimoDiaMes(Date adFecha) {
		LocalDate ldtFecha = iUtilFechas.convertirDateLD(adFecha), t = LocalDate.now();
		if (ldtFecha.getMonthValue() == 12) {
			t=LocalDate.of(ldtFecha.getYear()+1, 1, 1);

		} else {
			t=LocalDate.of(ldtFecha.getYear(), ldtFecha.getMonthValue() + 1, 1);
		}
		ldtFecha=t.minusDays(1);
		return ldtFecha;
	}

	public Double calcularEdad(Date adFechaNace, Date adFechaCorte) {
		LocalDate ldtFechaNace = iUtilFechas.convertirDateLD(adFechaNace), ldtFechaCorte = iUtilFechas.convertirDateLD(adFechaCorte);
		ldtFechaCorte = ultimoDiaMes(adFechaCorte);
		Double edadReal;
		edadReal = (ldtFechaCorte.getYear()) - ( ldtFechaNace.getYear())
				+ (ldtFechaCorte.getMonthValue() - ldtFechaNace.getMonthValue() + (ldtFechaCorte.getDayOfMonth() - ldtFechaNace.getDayOfMonth()) / (double) ldtFechaCorte.getDayOfMonth()) / 12.0;
		return edadReal;
	}

	public EstadoPJ buscarEstadoPJ(Long alIdSiniestro) {
		EstadoPJ estado = new EstadoPJ();
		Long llProceso;
		llProceso = generalesDao.buscarProcesoPJSiniestro(alIdSiniestro);

		if (llProceso != null) { // puede ser de proceso juridico

			estado = generalesDao.pretensionesPJ(llProceso);
			estado.setAlProceso(llProceso);
			
			if (estado.getAsIpp()==null) estado.setAsIpp("0");
			if (estado.getAsAf()==null) estado.setAsAf("0");
			if (estado.getAsIt()==null) estado.setAsIt("0");
			if (estado.getAsPa()==null) estado.setAsPa("0");
			if (estado.getAsPi()==null) estado.setAsPi("0");
			if (estado.getAsPs()==null) estado.setAsPs("0");
			
			if (estado.getAsEstado().toLowerCase().equals("z")) { // C002_14 ,
																	// ahora hay
																	// que
																	// validar
																	// si quedo
																	// en contra
																	// para no
																	// liberar
																	// la
																	// reserva

				Long llCuantos = generalesDao.instanciasEnContra(llProceso);

				if (llCuantos > 0) {

					if (estado.getAsAf().equals("1"))
						if (generalesDao.cuantosMovPJ(llProceso, "AF") > 0)
							estado.setAlProceso(new Long(0));

					if (estado.getAsIt().equals("1"))
						if (generalesDao.cuantosMovPJ(llProceso, "IT") > 0)
							estado.setAlProceso(new Long(0));

					if (estado.getAsIpp().equals("1"))
						if (generalesDao.cuantosMovPJ(llProceso, "IPP") > 0)
							estado.setAlProceso(new Long(0));

					if (estado.getAsPa().equals("1"))
						if (generalesDao.cuantosMovPJ(llProceso, "AS") > 0)
							estado.setAlProceso(new Long(0));

					if (estado.getAsPi().equals("1"))
						if (generalesDao.cuantosMovPJ(llProceso, "PI") > 0)
							estado.setAlProceso(new Long(0));

					if (estado.getAsPs().equals("1"))
						if (generalesDao.cuantosMovPJ(llProceso, "PS") > 0)
							estado.setAlProceso(new Long(0));

					estado.setAbnEnContra(true);
				}
			}
		} else
			estado.setAlProceso(new Long(0));

		return estado;
	}

	public Collection<AFP> traerAFPs() {
		return generalesDao.traerAFPs();
	}

	public Collection<EPS> traerEPSs() {
		return generalesDao.traerEPSs();
	}
	
	public Collection<IPS> traerIPSs() {
		return generalesDao.traerIPSs();
	}

	public BigDecimal edad(Date adFechaInicio, Date adFechaFin, Integer aiDecimales) {
		return generalesDao.edad(adFechaInicio, adFechaFin, aiDecimales);
	}

	public CoberturaSiniestro coberturaSiniestro(Integer aiIdSiniestro) {
		return generalesDao.coberturaSiniestro(aiIdSiniestro);
	}
	
	public ParametroGen recuperarParametroGen(Integer aiCodigo){
		return generalesDao.recuperarParametroGen(aiCodigo);
	}
	
	public Integer recuperarConsecutivoGCI(Integer aplicacion){
		return generalesDao.recuperarConsecutivoGCI(aplicacion);		
	}
	
	public void actualizarConsecutivoGCI(Integer aplicacion){
		generalesDao.actualizarConsecutivoGCI(aplicacion);
	}
	
	public Collection<DiagGral> traerDiagnosticos(){
		return generalesDao.traerDiagnosticos();
	}

	
	/** Retorna una fecha mas el numero de dias habiles */
	public Date funcionDiaHabil(Date fecha, Integer diasHabiles){
		return generalesDao.funcionDiaHabil(fecha, diasHabiles);
	}
	

	public Collection<Departamento> traerDeptosGiro(){
		return generalesDao.traerDeptosGiro();
	}
	
	public Collection<Banco> traerBancosGiro(Integer aiIdDepto,Integer aiIdMunic){
		return generalesDao.traerBancosGiro(aiIdDepto,aiIdMunic);
	}
	
	public Collection<OficinaGiro> traerOficinasGiro(Integer aiIdDepto,Integer aiIdMunic,Integer aiIdBanco){
		return generalesDao.traerOficinasGiro(aiIdDepto,aiIdMunic,aiIdBanco);
	}
	
	public OficinaGiro traerDatosGiro(Integer aiIdOficinaGiro){
		return generalesDao.traerDatosGiro(aiIdOficinaGiro);
	}	
	
	public Collection<Municipio> traerMunicsGiro(Integer aiIdDepto){
		return generalesDao.traerMunicsGiro(aiIdDepto);

	}
	
	public Collection<ARP> traerARPs(){
		return generalesDao.traerARPs();
	}
	
	public Collection<MotivoDevolucion> buscarMotivoDevolucion(){
		return generalesDao.buscaMotivoDevolucion();
	}
	
	public Collection<FormaNotificacion> buscarFormaNotificacion(){
		return generalesDao.buscarFormaNotificacion();
	}
}
