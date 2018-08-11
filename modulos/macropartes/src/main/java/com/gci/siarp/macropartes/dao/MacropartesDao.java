package com.gci.siarp.macropartes.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gci.siarp.api.annotation.SiarpDatabase;
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

@SiarpDatabase
public interface MacropartesDao {
	
	/**---------INI LEOBETA---------*/
	
	public Permisos getPermisosUsuario(@Param("username") String username);
	
	public int getMaxNumeroPedido(@Param("detalle") String detalle);

	public List<Pedido> getPedidosPorID(@Param("consecutivo") Long consecutivo);

	public Collection<Proveedor> getAllProveedores();

	public Proveedor getEmpresasById(@Param("id") String id);

	public Collection<Ciudad> getAllCiudades();

	public Collection<Referencia> getAllReferencias();
	
	public Referencia getReferenciaById(@Param("id") String id);
	
	public String getItemByReferencia(@Param("referencia") String referencia);

	public String getNombreById(@Param("referencia") String referencia);

	public String getReferenciasById(@Param("item") String item);

	public String getNombreCiudad(@Param("departamento") String departamento, @Param("municipio") String municipio);
	
	public int crearProgramacion(@Param("sigla") String sigla, @Param("orden") String orden, @Param("ciudad") String ciudad, @Param("referencia") String referencia,
			@Param("descripcion") String descripcion, @Param("cantidad") Long cantidad, @Param("item") Long item, @Param("consecutivo") String consecutivo,
			@Param("tercero") String tercero, @Param("observacion") String observacion, @Param("username") String username);
	
	public void actualizarConsecutivo(@Param("consecutivo") long consecutivo, @Param("detalle") String detalle);
	
	public List<PedidoReporte> getPedidosPorIDReporte(Long consecutivo);
	
	public void crearProgramacionConsolidada(@Param("consecutivo") long consecutivo);

	public void eliminarPedido(@Param("consecutivo") long consecutivo);
	
	public void eliminarPedidoConsolidado(@Param("consecutivo") long consecutivo);
	
	public void autorizarProgramacion(@Param("consecutivo") long consecutivo);
	
	public void autorizarProgramacionConsolidada(@Param("consecutivo") long consecutivo);
	
	/**---------FIN LEOBETA---------*/

	public Collection<Ciudades> buscaCiudades();
	
	public Collection<Serviteca> buscaServiteca(@Param("ciudad") String ciudad);
	
	public Collection<Marca> buscaMarca();
	
	public Collection<Modelo> buscaModelo();
	
	public Collection<Programacion> buscaSiglaEnProgrfamacion(@Param("sigla") String sigla);
	
	public Collection<Referencias> buscaCodigoNombreP(@Param("referencia") String referencia);
	
	public Collection<Stock> buscaStok1(@Param("codigo") String codigo,@Param("bodega") String bodega);
	
	public int actualizaStok(@Param("codigo") String codigo,@Param("bodega") String bodega,
										   @Param("stock") float stock, @Param("stockAnterior") float stockAnterior);
	
	public int actualizaStokGeneral(@Param("codigo") String codigo,
			   @Param("stockActual") float stockActual,@Param("stockAnterior") float stockAnterior);
	
	public int actualizaStokGeneralAnterior(@Param("codigo") String codigo,
			   @Param("stock") float stock);
	
	public Collection<Ars> buscaStokGeneralActualAnterior(@Param("codigo") String codigo);
	
	public Collection<NumeroDeOrden> generaNumeroDeOrden(@Param("usuario") String usuario);
	
	public int generaActualizaNumeroDeOrden(@Param("usuario") String usuario);
	
	public int buscaNumeroOrden(@Param("numOrden") String numOrden);
	
	public int guardaArs(@Param("ars") Ars ars);
	
	public Collection<Referencias> buscaReferencia(@Param("referencia") String referencia);
	
	public Collection<Referencias> buscaTodaReferencia(@Param("referencia") String referencia);
	
	public Collection<Ars> buscandoArs(@Param("criterio") String criterio);

	public String buscaNombreServiteca(@Param("codigo") String codigo);
	
	public long buscaConsectivoTablaMovimiento();
	
	public int insertarMovimientoArs(@Param("ars") Ars ars,@Param("stockActual") float stockActual,
									  @Param("stockAnterior") float stockAnterior,@Param("consecutivo") long consecutivo);

	public List<ARSReporte> getARSPorIDReporte(@Param("consecutivo") String consecutivo);

	public String getLogoUsuario(@Param("username") String username);

	public Collection<String> getAllSiglas();

	public Collection<String> getAllRecibe();

	public Collection<String> getAllUnidad();

	
}
