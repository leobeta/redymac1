package com.gci.siarp.macropartes.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gci.siarp.api.annotation.SiarpDatabase;
import com.gci.siarp.api.annotation.SiarpService;
import com.gci.siarp.macropartes.dao.MacropartesDao;
import com.gci.siarp.macropartes.domain.Proveedor;
import com.gci.siarp.macropartes.domain.Referencia;
import com.gci.siarp.macropartes.domain.Referencias;
import com.gci.siarp.macropartes.domain.Serviteca;
import com.gci.siarp.macropartes.domain.Stock;
import com.gci.siarp.macropartes.domain.ARSReporte;
import com.gci.siarp.macropartes.domain.Ars;
import com.gci.siarp.macropartes.domain.Ciudad;
import com.gci.siarp.macropartes.domain.Ciudades;
import com.gci.siarp.macropartes.domain.Marca;
import com.gci.siarp.macropartes.domain.Modelo;
import com.gci.siarp.macropartes.domain.NumeroDeOrden;
import com.gci.siarp.macropartes.domain.Pedido;
import com.gci.siarp.macropartes.domain.PedidoReporte;
import com.gci.siarp.macropartes.domain.Permisos;
import com.gci.siarp.macropartes.domain.Programacion;

@SiarpService
public class MacropartesService {
	@Autowired
	MacropartesDao macropartesDao;
	
	/**--------INI LEOBETA--------*/

	public Permisos getPermisosUsuario(String username) {
		return macropartesDao.getPermisosUsuario(username);
	}
	
	public int getMaxNumeroPedido(String detalle){
		return macropartesDao.getMaxNumeroPedido(detalle);
	}

	public List<Pedido> getPedidosPorID(Long consecutivo) {
		return macropartesDao.getPedidosPorID(consecutivo);
	}

	public Collection<Proveedor> getAllProveedores() {
		return macropartesDao.getAllProveedores();
	}

	public Proveedor getEmpresasById(String id) {
		return macropartesDao.getEmpresasById(id);
	}

	public Collection<Ciudad> getAllCiudades() {
		return macropartesDao.getAllCiudades();
	}

	public Collection<Referencia> getAllReferencias() {
		return macropartesDao.getAllReferencias();
	}

	public String getReferenciaById(String item) {
		return macropartesDao.getReferenciasById(item);
	}
	
	public String getItemByReferencia(String referencia) {
		return macropartesDao.getItemByReferencia(referencia);
	}

	public String getNombreById(String referencia) {
		return macropartesDao.getNombreById(referencia);
	}

	public String getNombreCiudad(String idCiudad) {
		return macropartesDao.getNombreCiudad(idCiudad.substring(0, 2), idCiudad.substring(2));
	}
	@Transactional(rollbackFor=Exception.class,value=SiarpDatabase.transactionManagerBeanName)
	public void crearProgramacion(String sigla, String orden, String ciudad, String referencia, String descripcion,
			Long cantidad, Long item, String consecutivo, String tercero, String observacion, String username) {
		macropartesDao.crearProgramacion(sigla, orden, ciudad, referencia, descripcion, cantidad, item, consecutivo, tercero, observacion, username);
	}
	
	@Transactional(rollbackFor=Exception.class,value=SiarpDatabase.transactionManagerBeanName)
	public void crearProgramacionConsolidada(long consecutivo) {
		macropartesDao.crearProgramacionConsolidada(consecutivo);
	}
	
	@Transactional(rollbackFor=Exception.class,value=SiarpDatabase.transactionManagerBeanName)
	public void actualizarConsecutivoProgramacion(long consecutivo, String detalle) {
		macropartesDao.actualizarConsecutivo(consecutivo, detalle);
	}
	
	public List<PedidoReporte> getPedidosPorIDReporte(Long consecutivo) {
		return macropartesDao.getPedidosPorIDReporte(consecutivo);
	}

	@Transactional(rollbackFor=Exception.class,value=SiarpDatabase.transactionManagerBeanName)
	public void eliminarPedido(long consecutivo) {
		macropartesDao.eliminarPedido(consecutivo);
	}

	@Transactional(rollbackFor=Exception.class,value=SiarpDatabase.transactionManagerBeanName)
	public void eliminarPedidoConsolidado(long consecutivo) {
		macropartesDao.eliminarPedidoConsolidado(consecutivo);
	}

	@Transactional(rollbackFor=Exception.class,value=SiarpDatabase.transactionManagerBeanName)
	public void autorizarPedido(String consecutivo) {
		macropartesDao.autorizarProgramacion(Long.parseLong(consecutivo));
	}
	
	@Transactional(rollbackFor=Exception.class,value=SiarpDatabase.transactionManagerBeanName)
	public void autorizarPedidoConsolidado(String consecutivo){
		macropartesDao.autorizarProgramacionConsolidada(Long.parseLong(consecutivo));
	}

	@Transactional(rollbackFor=Exception.class,value=SiarpDatabase.transactionManagerBeanName)
	public void modificarProgramacion(String consecutivo) {
		macropartesDao.eliminarPedido(Long.parseLong(consecutivo));
	}

	@Transactional(rollbackFor=Exception.class,value=SiarpDatabase.transactionManagerBeanName)
	public void modificarProgramacionConsolidada(String consecutivo) {
		macropartesDao.eliminarPedidoConsolidado(Long.parseLong(consecutivo));
	}
	

	/**--------FIN LEOBETA--------*/

	public Collection<Ciudades> buscarCiudades(){
		return macropartesDao.buscaCiudades();
	}
	
	public Collection<Serviteca> buscarServiteca(String ciudad){
		return macropartesDao.buscaServiteca(ciudad);
	}
	
	public Collection<Marca> buscarMarca(){
		return macropartesDao.buscaMarca();
	}
	
	public Collection<Modelo> buscarModelo(){
		return macropartesDao.buscaModelo();
	}
	
	public Collection<Programacion> buscarSiglaEnProgramacion(String sigla){
		return macropartesDao.buscaSiglaEnProgrfamacion(sigla);
	}
	
	public Collection<Referencias> buscarCodigoNombreP(String referencia){
		return macropartesDao.buscaCodigoNombreP(referencia);
	}
	
	public Collection<Stock> buscarStock1(String codigo,String bodega){
		return macropartesDao.buscaStok1(codigo, bodega);
	}
	
	public Collection<NumeroDeOrden> generarNumeroDeOrden(String usuario){
		return macropartesDao.generaNumeroDeOrden(usuario);
	}
	
	//@Transactional(rollbackFor=Exception.class,value=SiarpDatabase.transactionManagerBeanName)
	public int generarYActualizaNumeroDeOrden(String usuario){
		return macropartesDao.generaActualizaNumeroDeOrden(usuario);
		
	}
	
	public int buscarNumeroOrden(String numOrden){
		return macropartesDao.buscaNumeroOrden(numOrden);
	}
	
	public int actualizaStock(String codigo,String bodega,float stock,float stockAnterior){
		return macropartesDao.actualizaStok(codigo, bodega,stock,stockAnterior);
	}
	
//	public int actualizaStockGeneralActual(String codigo,float stock){
//		return macropartesDao.actualizaStokGeneral(codigo,stock);
//	}
	
	public int actualizaStockGeneralAnterios(String codigo,float stockActual,float stockAnterior){
		return macropartesDao.actualizaStokGeneral(codigo,stockActual,stockAnterior);
	}
	
	public Collection<Ars> buscaStockGeneralActualAnterior(String codigo){
		return macropartesDao.buscaStokGeneralActualAnterior(codigo);
	}
	
	public long buscaConsecutivoTablaMovimiento(){
	    return macropartesDao.buscaConsectivoTablaMovimiento();	
	}
	
	public void actualizarConsecutivo(long consecutivo,String detalle){
		macropartesDao.actualizarConsecutivo(consecutivo, detalle);
	}
	
	@Transactional(rollbackFor=Exception.class,value=SiarpDatabase.transactionManagerBeanName)
	public int guardarArs(Ars ars,float stock,float stock2,int sw,int swProgramado){
		Collection<Ars> listaStock;float stocActual = 0,stockAnterior,stocActualAux,stockAnteriorAux;
		float stockMovimientoActual,stockMovimientoAnterior;
		long consecutivoInventario;
		consecutivoInventario = buscaConsecutivoTablaMovimiento()+1;
		actualizarConsecutivo(consecutivoInventario,"REMISION");
		ars.setCrem(String.valueOf(consecutivoInventario));
		macropartesDao.guardaArs(ars);
		if(sw==1){
			 System.out.println("1"+ars.getServiteca2());
			 if(!ars.getCodigo2().isEmpty() && ars.getServiteca2().isEmpty()){
				 System.out.println("2");
				 //if(swProgramado==1){
//				 	consecutivoInventario = buscaConsecutivoTablaMovimiento()+1;
//				 	actualizarConsecutivo(consecutivoInventario,"REMISION");
					 stockMovimientoActual = stock;
					 stockMovimientoAnterior =  stock+ars.getCantidad();
					 macropartesDao.insertarMovimientoArs(ars,stockMovimientoActual,stockMovimientoAnterior,consecutivoInventario);
					 actualizaStock(ars.getCodigo(),ars.getCserviteca(),stock,stockMovimientoAnterior);
					 
					 listaStock = buscaStockGeneralActualAnterior(ars.getCodigo());
					 for(Ars x:listaStock){
						 stocActual = x.getStockGeneralActual();
						 stockAnterior = x.getStockGeneralAnterio();
					 }
					 stockAnteriorAux = stocActual;
					 stocActualAux = stocActual - ars.getCantidad();
					 actualizaStockGeneralAnterios(ars.getCodigo(),stocActualAux,stockAnteriorAux);
					 
					 System.out.println("stock2-> "+stock2);
					 stockMovimientoActual = stock2;
					 stockMovimientoAnterior =  stock2+ars.getCantidad2();
//					 consecutivoInventario = buscaConsecutivoTablaMovimiento()+1;
//					 actualizarConsecutivo(consecutivoInventario,"REMISION");
					 ars.setCodigo(ars.getCodigo2());
					 ars.setNreferencia(ars.getNreferencia2());
					 ars.setCantidad(ars.getCantidad2());
					 /*ars.setDiseno(ars.getDiseno2());
					 ars.setDot(ars.getDot2());
					 ars.setIdllanta(ars.getIdLlanta2());*/
					 macropartesDao.insertarMovimientoArs(ars,stockMovimientoActual,stockMovimientoAnterior,consecutivoInventario);
					 actualizaStock(ars.getCodigo2(),ars.getCserviteca(),stock2,stockMovimientoAnterior);
					 					 
					 listaStock = buscaStockGeneralActualAnterior(ars.getCodigo2());
					 for(Ars x:listaStock){
						 stocActual = x.getStockGeneralActual();
						 stockAnterior = x.getStockGeneralAnterio();
					 }
					 stockAnteriorAux = stocActual;
					 stocActualAux = stocActual - ars.getCantidad2();
					 System.out.println("cambio aqui codigo  a codigo2");
					 actualizaStockGeneralAnterios(ars.getCodigo2(),stocActualAux,stockAnteriorAux);
				 //}
				
			 }else{
				 System.out.println("3");
				 if(ars.getCodigo2().isEmpty() && ars.getServiteca2().isEmpty()){
//					 consecutivoInventario = buscaConsecutivoTablaMovimiento()+1;
//					 actualizarConsecutivo(consecutivoInventario,"remision");
					 stockMovimientoActual = stock;
					 stockMovimientoAnterior =  stock+ars.getCantidad();
					 macropartesDao.insertarMovimientoArs(ars,stockMovimientoActual,stockMovimientoAnterior,consecutivoInventario);
					 actualizaStock(ars.getCodigo(),ars.getCserviteca(),stock,stockMovimientoAnterior);
					 
					 listaStock = buscaStockGeneralActualAnterior(ars.getCodigo());
					 for(Ars x:listaStock){
						 stocActual = x.getStockGeneralActual();
						 stockAnterior = x.getStockGeneralAnterio();
					 }
					 stockAnteriorAux = stocActual;
					 stocActualAux = stocActual - ars.getCantidad();
					 actualizaStockGeneralAnterios(ars.getCodigo(),stocActualAux,stockAnteriorAux);
				 }
			 }
		 }else{
			 if(sw==2){
				 System.out.println("4");
				 //if(swProgramado==1){
//					 consecutivoInventario = buscaConsecutivoTablaMovimiento()+1;
//					 actualizarConsecutivo(consecutivoInventario, "REMISION");
					 stockMovimientoActual = stock;
					 stockMovimientoAnterior =  stock+ars.getCantidad();
					 macropartesDao.insertarMovimientoArs(ars,stockMovimientoActual,stockMovimientoAnterior,consecutivoInventario);
					 actualizaStock(ars.getCodigo(),ars.getCserviteca(),stock,stockMovimientoAnterior);
					 
					 listaStock = buscaStockGeneralActualAnterior(ars.getCodigo());
					 for(Ars x:listaStock){
						 stocActual = x.getStockGeneralActual();
						 stockAnterior = x.getStockGeneralAnterio();
						 
					 }
					 stockAnteriorAux = stocActual;
					 stocActualAux = stocActual - ars.getCantidad();
					 actualizaStockGeneralAnterios(ars.getCodigo(),stocActualAux,stockAnteriorAux);
					 
					 stockMovimientoActual = stock2;
					 stockMovimientoAnterior =  stock2+ars.getCantidad2();
//					 consecutivoInventario = buscaConsecutivoTablaMovimiento()+1;
//					 actualizarConsecutivo(consecutivoInventario,"REMISION");
					 ars.setCodigo(ars.getCodigo2());
					 ars.setNreferencia(ars.getNreferencia2());
					 ars.setCantidad(ars.getCantidad2());
					 /*ars.setDiseno(ars.getDiseno2());
					 ars.setDot(ars.getDot2());
					 ars.setIdllanta(ars.getIdLlanta2());*/
					 macropartesDao.insertarMovimientoArs(ars,stockMovimientoActual,stockMovimientoAnterior,consecutivoInventario);
					 actualizaStock(ars.getCodigo2(),ars.getCserviteca2(),stock2,stockMovimientoAnterior);
					  System.out.println("5");				 					 
					 listaStock = buscaStockGeneralActualAnterior(ars.getCodigo2());
					 for(Ars x:listaStock){
						 stocActual = x.getStockGeneralActual();
						 stockAnterior = x.getStockGeneralAnterio();
					 }
					 stockAnteriorAux = stocActual;
					 stocActualAux = stocActual - ars.getCantidad2();
					 actualizaStockGeneralAnterios(ars.getCodigo2(),stocActualAux,stockAnteriorAux);
				
			 }
		 }
		generarYActualizaNumeroDeOrden(ars.getUsuario());
		return 1;
		//return macropartesDao.guardaArs(ars);
	}
	
	public Collection<Referencias> buscarReferencia(String referencia){
		return macropartesDao.buscaReferencia(referencia);
	}
	
	public Collection<Referencias> buscarTodasLasReferencia(String referencia){
		return macropartesDao.buscaTodaReferencia(referencia);
	}
	
	public Collection<Ars> buscarArs(String criterio){
		return macropartesDao.buscandoArs(criterio);
	}
	
	public String buscarNombreServiteca(String codigo){
		return macropartesDao.buscaNombreServiteca(codigo);
	}

	public List<ARSReporte> getARSPorIDReporte(String consecutivo) {
		return macropartesDao.getARSPorIDReporte(consecutivo);
	}

	public String getLogoUsuario(String username) {
		return macropartesDao.getLogoUsuario(username);
	}

	public Collection<String> getAllSiglas() {
		return macropartesDao.getAllSiglas();
	}

	public Collection<String> getAllRecibe() {
		return macropartesDao.getAllRecibe();
	}

	public Collection<String> getAllUnidad() {
		return macropartesDao.getAllUnidad();
	}

	
}
