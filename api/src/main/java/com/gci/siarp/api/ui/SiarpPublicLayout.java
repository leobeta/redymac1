package com.gci.siarp.api.ui;

import com.vaadin.ui.Component;

/**
 * Layout principal para usuarios NO autenticados conformado por un formulario
 * de autenticación.
 * 
 * @author Alejandro
 */
public interface SiarpPublicLayout extends Component {

	/**
	 * Listener de eventos de intento de login.
	 * 
	 * @author Alejandro
	 */
	static interface SiarpLoginListener {

		/**
		 * Este método es llamado cada vez que algún usuario intenta
		 * autenticarse.
		 * 
		 * @param username
		 *            "Login" del usuario que intenta autenticarse.
		 * @param password
		 *            Contraseña del usuario que intenta autenticarse.
		 * 
		 * @return true si el usuario es auténtico (username y password
		 *         correctos), false en otro caso.
		 */
		boolean login(String username, String password);

	}

}
