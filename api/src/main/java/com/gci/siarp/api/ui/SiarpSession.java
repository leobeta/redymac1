package com.gci.siarp.api.ui;

import com.vaadin.server.VaadinSession;

/**
 * Esta clase contiene métodos estáticos que permiten la manipulación
 * centralizada de los elementos en la sesión HTTP.
 *
 * @author Alejandro Duarte
 */
public class SiarpSession {

	private static final String USERNAME = "username";
	private static final String MACHINE = "machine";

	/**
	 * @return Nombre del usuario autenticado o null si no existe.
	 */
	public static String getUsername() {
		return (String) VaadinSession.getCurrent().getAttribute(USERNAME);
	}
	
	public static String getMachine() {
		return (String) VaadinSession.getCurrent().getAttribute(MACHINE);
	}

	public static void setUsername(String username) {
		VaadinSession.getCurrent().setAttribute(USERNAME, username);
	}
	
	public static void setMachine(String machine) {
		VaadinSession.getCurrent().setAttribute(MACHINE, machine);
	}

}
