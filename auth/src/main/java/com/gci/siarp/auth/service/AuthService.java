package com.gci.siarp.auth.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import com.gci.siarp.api.annotation.SiarpService;
import com.gci.siarp.auth.dao.AuthenticationDao;
import com.gci.siarp.auth.dao.AuthorizationDao;
import com.gci.siarp.auth.dao.OpcionAplicacionDao;
import com.gci.siarp.auth.domain.OpcionAplicacion;
import com.gci.siarp.auth.domain.OpcionesMenuUsuario;
import com.gci.siarp.auth.ui.view.ErrorView;

@SiarpService
//@PropertySource("file:${SIARP_CONF}/siarp.properties")
@PropertySource("file:/Users/leo/siarp_conf/siarp.properties")
public class AuthService {

	private static Collection<String> publicUrls = null;

	@Value("${siarp.publicUrls}")
	private String publicUrlsProperty;

	@Autowired
	private AuthenticationDao authenticationDao;

	@Autowired
	private AuthorizationDao authorizationDao;

	@Autowired
	private OpcionAplicacionDao opcionAplicacionDao;

	public boolean isAuthenticUser(String username, String password) {
		Boolean authentic = authenticationDao.isAuthentic(username, password);
		return authentic != null && authentic;

	}

	public boolean userCanAccess(String username, String url) {
		lazilyLoadPublicUrls();
		return publicUrls.contains(url) || true ;//authorizationDao.userCanAccess(username, url);
		
	}

	private void lazilyLoadPublicUrls() {
		if (publicUrls == null) {
			publicUrls = new HashSet<String>(Arrays.asList("", ErrorView.viewName));

			String[] urls = publicUrlsProperty.split(",");

			for (int i = 0; i < urls.length; i++) {
				publicUrls.add(urls[i].trim());
			}
		}
	}

	public ArrayList<OpcionesMenuUsuario> opcionesMenu(String userId) {
		ArrayList<OpcionAplicacion> todas = new ArrayList<>();

		todas = opcionAplicacionDao.opcionesMenuUsuario(userId);
		
		ArrayList<OpcionesMenuUsuario> opciones = new ArrayList<>();

		for (int i = 0; i < todas.size(); i++) {

			OpcionesMenuUsuario opcion = new OpcionesMenuUsuario();
			ArrayList<String> link = new ArrayList<>();

			if (todas.get(i).getPadre9() != null) {
				link.add(todas.get(i).getPadre9());
			}
			if (todas.get(i).getPadre8() != null) {
				link.add(todas.get(i).getPadre8());
			}
			if (todas.get(i).getPadre7() != null) {
				link.add(todas.get(i).getPadre7());
			}
			if (todas.get(i).getPadre6() != null) {
				link.add(todas.get(i).getPadre6());
			}
			if (todas.get(i).getPadre5() != null) {
				link.add(todas.get(i).getPadre5());
			}
			if (todas.get(i).getPadre4() != null) {
				link.add(todas.get(i).getPadre4());
			}
			if (todas.get(i).getPadre3() != null) {
				link.add(todas.get(i).getPadre3());
			}
			if (todas.get(i).getPadre2() != null) {
				link.add(todas.get(i).getPadre2());
			}
			if (todas.get(i).getPadre1() != null) {
				link.add(todas.get(i).getPadre1());
			}
			link.add(todas.get(i).getDescripcionOpcion());
			opcion.setTree(link);
			opcion.setAction(todas.get(i).getLink());
			opciones.add(opcion);
		}
		return opciones;
	}

}
