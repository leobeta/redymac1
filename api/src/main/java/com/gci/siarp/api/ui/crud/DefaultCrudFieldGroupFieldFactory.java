package com.gci.siarp.api.ui.crud;

import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Field;

/**
 * Factory para creación de Fields en formularios de CRUDs.
 * 
 * @author Alejandro
 *
 */
@SuppressWarnings("serial")
public class DefaultCrudFieldGroupFieldFactory extends DefaultFieldGroupFieldFactory {

	@SuppressWarnings("rawtypes")
	@Override
	public <T extends Field> T createField(Class<?> type, Class<T> fieldType) {
		T field = super.createField(type, fieldType);

		if (AbstractTextField.class.isAssignableFrom(field.getClass())) {
			AbstractTextField textField = (AbstractTextField) field;
			textField.setNullRepresentation("");
		}

		return field;
	}

}
