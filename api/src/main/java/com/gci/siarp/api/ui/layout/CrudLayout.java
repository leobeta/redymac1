package com.gci.siarp.api.ui.layout;

import com.vaadin.ui.Component;

/**
 * Layout para interfaces CRUD. Consiste en un área principal, un área para
 * filtros una barra de herramientas.
 * 
 * @author Alejandro
 *
 */
public interface CrudLayout extends Component {
	
	/**
	 * Establece el contenido del área principal del layout.
	 * 
	 * @param component
	 *            Componente a mostrar.
	 */
	void setMainComponent(Component component);

	/**
	 * Agrega un componente en el área de filtros.
	 * 
	 * @param component
	 *            Componente a agregar.
	 */
	void addFilterComponent(Component component);

	/**
	 * Agrega un componente en la barra de herramientas.
	 * 
	 * @param component
	 *            Componente a agregar.
	 */
	void addToolbarComponent(Component component);

}
