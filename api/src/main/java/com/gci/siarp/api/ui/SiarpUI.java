package com.gci.siarp.api.ui;

import com.gci.siarp.api.ui.layout.SiarpPrivateLayout;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;

/**
 * UI principal de la aplicación. Esta UI es dual, permite cambiar entre dos
 * estados: layout público y layout privado.
 *
 * @author Alejandro Duarte
 */
public interface SiarpUI {

	/**
	 * Lisener de eventos de cambio de layout.
	 *
	 * @author Alejandro Duarte
	 */
	static interface UIChangeListener {

		/**
		 * Este método es llamado justo después de cambiar a la UI pública.
		 */
		void afterSwitchToPublic();

		/**
		 * Este método es llamado justo después de cambiar a la UI privada.
		 */
		void afterSwitchToPrivate();

	}

	/**
	 * Cambia a layout público.
	 */
	void showPublicLayout();

	/**
	 * Cambia a layout privado.
	 */
	void showPrivateLayout();

	/**
	 * @return Layout para usuarios autenticados.
	 */
	SiarpPrivateLayout getSiarpPrivateLayout();

	/**
	 * @return "Page" actual (página actual en el navegador).
	 */
	Page getPage();

	/**
	 * @return Navigator actual.
	 */
	Navigator getNavigator();

	/**
	 * Establece en Navigator actual.
	 * 
	 * @param navigator
	 */
	void setNavigator(Navigator navigator);

}
