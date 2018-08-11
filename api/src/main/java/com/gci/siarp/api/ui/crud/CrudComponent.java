package com.gci.siarp.api.ui.crud;

import java.io.Serializable;

import com.gci.siarp.api.ui.layout.CrudLayout;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

/**
 * Componente para presentar interfaces CRUD.
 * 
 * @author Alejandro
 *
 */
public interface CrudComponent<T> extends Component {

	/**
	 * Permite escuchar eventos CRUD.
	 * 
	 * @author Alejandro
	 *
	 */
	public static interface CrudListener<T> extends Serializable {

		/**
		 * Este método es llamado cuando se agrega un nuevo registro.
		 * 
		 * @param domainObjectToAdd
		 *            registro a agregar.
		 */
		void add(T domainObjectToAdd);

		/**
		 * Este método es llamado cuando se modifica un registro.
		 * 
		 * @param domainObjectToUpdate
		 *            registro a actualizar.
		 */
		void update(T domainObjectToUpdate);

		/**
		 * Este método es llamado cuando se elimina un registro.
		 * 
		 * @param domainObjectToDelete
		 *            registro a eliminar.
		 */
		void delete(T domainObjectToDelete);

	}

	/**
	 * Builder de formularios para presentación y edición de registros en un
	 * CrudComponent.
	 */
	public static interface CrudFormBuilder<T> extends Serializable {

		Component buildNewForm(T domainObject, Object visiblePropertyIds[], Object disabledPropertyIds[], String[] fieldCaptions, boolean readOnly, String buttonCaption, Resource buttonIcon, String buttonStyle, ClickListener saveButtonClickListener);

	}

	/**
	 * Muestra todas las opciones disponibles en el CRUD (nuevo, modificar,
	 * eliminar, etc.).
	 */
	void showAllOptions();

	/**
	 * Muestra la opción para agregar nuevos registros.
	 */
	void showAddOption();

	/**
	 * Muestra la opción para editar registros.
	 */
	void showEditOption();

	/**
	 * Muestra la opción para eliminar registros.
	 */
	void showDeleteOption();

	/**
	 * Configura los fields visibles en el formulario para agregar nuevos
	 * registros.
	 * 
	 * @param newFormVisiblePropertyIds
	 *            Property IDs de los fields a mostrar.
	 */
	void setNewFormVisiblePropertyIds(Object... newFormVisiblePropertyIds);

	/**
	 * Configura los fields visibles en el formulario para editar un registro.
	 * 
	 * @param newFormVisiblePropertyIds
	 *            Property IDs de los fields a mostrar.
	 */
	void setEditFormVisiblePropertyIds(Object... editFormVisiblePropertyIds);

	/**
	 * Configura los fields visibles en el formulario para eliminar un registro.
	 * 
	 * @param newFormVisiblePropertyIds
	 *            Property IDs de los fields a mostrar.
	 */
	void setDeleteFormVisiblePropertyIds(Object... deleteFormVisiblePropertyIds);

	/**
	 * Establece los fields a deshabilitar en el formulario para editar un
	 * registro.
	 * 
	 * @param editFormFieldCaptions
	 */
	void setEditFormDisabledPropertyIds(Object... editFormDisabledPropertyIds);
	
	/**
	 * Establece los títulos de los fields en el formulario para agregar nuevos
	 * registros.
	 * 
	 * @param newFormFieldCaptions
	 */
	void setNewFormFieldCaptions(String... newFormFieldCaptions);

	/**
	 * Establece los títulos de los fields en el formulario para editar un
	 * registro.
	 * 
	 * @param editFormFieldCaptions
	 */
	void setEditFormFieldCaptions(String... editFormFieldCaptions);

	/**
	 * Establece los títulos de los fields en el formulario para eliminar un
	 * registro.
	 * 
	 * @param deleteFormFieldCaptions
	 */
	void setDeleteFormFieldCaptions(String... deleteFormFieldCaptions);

	/**
	 * @return CrudLayout usado.
	 */
	CrudLayout getMainLayout();

	/**
	 * Establece el CrudForm a usar para las opciones del CRUD (nuevo,
	 * modificar, eliminar, etc.).
	 * 
	 * @param crudForm
	 */
	void setCrudFormBuilder(CrudFormBuilder<T> crudForm);

}
