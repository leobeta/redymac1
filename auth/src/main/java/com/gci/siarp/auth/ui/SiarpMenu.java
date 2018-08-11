package com.gci.siarp.auth.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.gci.siarp.api.annotation.SiarpUIComponent;
import com.gci.siarp.api.ui.SiarpSession;
import com.gci.siarp.api.ui.SiarpUI;
import com.gci.siarp.api.ui.layout.SiarpPrivateLayout;
import com.vaadin.ui.UI;
import com.gci.siarp.auth.domain.OpcionesMenuUsuario;
import com.gci.siarp.auth.service.AuthService;

/**
 * 
 * @author alejandro
 *
 */
@SiarpUIComponent
@Scope("session")
@SuppressWarnings("serial")
public class SiarpMenu implements Serializable {

	private Map<String, String> urls = new HashMap<>();
	
	@Autowired
    private AuthService authService;

	public void buildForCurrentUser() {
		ArrayList<OpcionesMenuUsuario> opcMenu;
        
        /*if (SiarpSession.getUsername()!=null){
        	
            opcMenu=authService.opcionesMenu(SiarpSession.getUsername());
            urls.clear();

            for (OpcionesMenuUsuario opcMenu1 : opcMenu) {
                addOption(opcMenu1.getTree(),opcMenu1.getAction());
            }
        }*/
		urls.clear();

		addOption(Arrays.asList("Usuarios", "Usuarios"), "admin/usuarios");
		addOption(Arrays.asList("Usuarios", "Perfiles"), "admin/perfiles");
		addOption(Arrays.asList("Usuarios", "Permisos"), "admin/permisos");
		addOption(Arrays.asList("Usuarios", "Permiso Perfil"), "admin/PermisoPerfil");
		addOption(Arrays.asList("Usuarios", "Perfiles Usuarios"), "admin/Perfilusuario");
		addOption(Arrays.asList("Usuarios", "Cambiar Contraseña"), "admin/cambiarContrasena");
		addOption(Arrays.asList("Parametrización", "Consecutivos"), "admin/consecutivo");
		addOption(Arrays.asList("Pedidos", "Listado Pedidos"), "pedidos/listadoPedidos");
		addOption(Arrays.asList("Servicios", "ARS"), "servicios/ars");
		addOption(Arrays.asList("Servicios", "Consulta de Referencias"), "servicios/consultaReferencias");
		addOption(Arrays.asList("Reportes", "Reporte de Servicios"), "reportes/rservicios");
//		addOption(Arrays.asList("Pruebas", "Prueba Tabla"), "macropartes/prueba");
//                addOption(Arrays.asList("Producto", "Consultar Solicitudes"), "nomina-pensionados/consultar-solicitudes");
//                addOption(Arrays.asList("Producto", "Cierres / Nóminas"), "nomina-pensionados/consultar-nomina");
//		addOption(Arrays.asList("Producto", "Liquidar Fallo Judicial PS"), "nomina-pensionados/liquidar-fallo-judicial-ps");
//                addOption(Arrays.asList("Producto", "Liquidar Fallo Judicial PI"), "nomina-pensionados/liquidar-fallo-judicial-pi");
//		addOption(Arrays.asList("Producto", "Submenu", "Opción 1"), "producto/view1");
//		addOption(Arrays.asList("Producto", "Submenu", "Opción 2"), "producto/view1");
//
//		int n = (int) ((Math.random() * 10000) % 26) + 5;
//
//		for (int i = 1; i <= n; i++) {
//			addOption(Arrays.asList("Otro menú", "Otra opción " + i), "otro" + i);
//		}
	}

	public void addOption(List<String> submenus, String url) {
		SiarpUI siarpUI = (SiarpUI) UI.getCurrent();
		SiarpPrivateLayout siarpMainLayout = siarpUI.getSiarpPrivateLayout();

		String optionId = siarpMainLayout.addMenuOption(submenus);
		urls.put(optionId, url);
	}

	public String getUrl(String optionID) {
		return urls.get(optionID);
	}

}
