package com.gci.siarp.api.ui.layout;

import java.util.List;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;

/**
 * Layout principal para usuarios autenticados conformado por un menú principal
 * y un área de visualización (ver getCurrentViewContainer()).
 *
 * @author Alejandro Duarte
 */
public interface SiarpPrivateLayout extends Component {

	/**
	 * Lisener de eventos del menú.
	 * 
	 * @author Alejandro
	 */
	static interface SiarpMenuListener {

		/**
		 * Este método es llamado cuando se hace clic en alguna de las opciones
		 * del menú, incluyendo submenús.
		 * 
		 * @param optionId
		 *            Objeto que identifica de forma única a la opción dentro
		 *            del menú.
		 */
		void optionClicked(String optionId);

	}

	/**
	 * Agrega una nueva opción al menú principal. Sobreescribe existentes.
	 *
	 * @param submenus
	 *            Lista representando la ruta de la opción a agregar. El último
	 *            elemento de la lista representa la opción concreta, el resto
	 *            de elementos representan los submenus.
	 *
	 * @return Objeto usado para representar de forma única la opción a agregar
	 *         (optionId en SiarpMenuListener.optionClicked(String)).
	 */
	String addMenuOption(List<String> submenus);

	/**
	 * @return ComponentContainer en el que se pueden presentar componentes para
	 *         usuarios autenticados. Representa el área principal de trabajo.
	 */
	ComponentContainer getCurrentViewContainer();

	/**
	 * Establece el título a mostrar en el botón de opciones del usuario.
	 * 
	 * @param username
	 *            Nuevo valor.
	 */
	void setUsernameButtonCaption(String username);

}
