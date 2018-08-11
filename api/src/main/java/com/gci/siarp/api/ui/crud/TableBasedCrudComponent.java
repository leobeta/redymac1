package com.gci.siarp.api.ui.crud;

import java.util.Collection;
import java.util.Set;

import com.gci.siarp.api.ui.SiarpTheme;
import com.gci.siarp.api.ui.layout.CrudLayout;
import com.vaadin.data.Container;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * CrudComponent que muestra una tabla con los registros agregados y opciones
 * CRUD.
 * 
 * @author Alejandro
 *
 */
@SuppressWarnings("serial")
public class TableBasedCrudComponent<T> extends AbstractCrudComponent<T> {

	public static interface TableCrudListener<T> extends CrudListener<T> {

		Collection<T> refreshTable();

	}

	private Button refreshTableButton;
	private Button addButton;
	private Button editButton;
	private Button deleteButton;

	private Table table = new Table();

	public TableBasedCrudComponent(Class<T> domainType, TableCrudListener<T> crudListener) {
		this(domainType, crudListener, new VerticalCrudLayout());
	}

	public TableBasedCrudComponent(Class<T> domainType, TableCrudListener<T> crudListener, CrudLayout mainLayout) {
		super(domainType, crudListener, mainLayout);
		
		refreshTableButton = new Button("", this::refreshTableButtonClicked);
		refreshTableButton.setDescription("Actualizar");
		refreshTableButton.setIcon(FontAwesome.REFRESH);

		addButton = new Button("", this::addButtonClicked);
		addButton.setDescription("Nuevo");
		addButton.setIcon(FontAwesome.PLUS_CIRCLE);
		addButton.addStyleName(SiarpTheme.BUTTON_FRIENDLY);

		editButton = new Button("", this::editButtonClicked);
		editButton.setDescription("Editar");
		editButton.setIcon(FontAwesome.PENCIL);
		editButton.addStyleName(SiarpTheme.BUTTON_PRIMARY);

		deleteButton = new Button("", this::deleteButtonClicked);
		deleteButton.setDescription("Eliminar");
		deleteButton.setIcon(FontAwesome.TIMES);
		deleteButton.addStyleName(SiarpTheme.BUTTON_DANGER);

		table.setSizeFull();
		table.setStyleName(SiarpTheme.TABLE_COMPACT);
		table.setContainerDataSource(container);
		table.setSelectable(true);
		table.setMultiSelect(true);
		mainLayout.setMainComponent(table);
		doRefreshTable();
	}

	@Override
	public void showAllOptions() {
		showRefreshTableOption();
		super.showAllOptions();
	}

	public void showRefreshTableOption() {
		mainLayout.addToolbarComponent(refreshTableButton);
	}
	
	@Override
	public void showAddOption() {
		mainLayout.addToolbarComponent(addButton);
	}

	@Override
	public void showEditOption() {
		mainLayout.addToolbarComponent(editButton);
	}

	@Override
	public void showDeleteOption() {
		mainLayout.addToolbarComponent(deleteButton);
	}

	public void removeAll() {
		container.removeAllItems();
		table.setValue(null);
	}

	public void addAll(Collection<T> collection) {
		if (collection != null) {
			container.addAll(collection);
			table.setValue(null);
		}
	}

	public void addFilter(Object propertyId, Field<?> field) {
		field.addValueChangeListener(e -> {
			container.removeContainerFilters(propertyId);
			container.addContainerFilter(propertyId, "" + e.getProperty().getValue(), true, false);
		});

		mainLayout.addFilterComponent(field);
	}

	public Container getContainer() {
		return container;
	}

	public Table getTable() {
		return table;
	}

	public TableCrudListener<T> getCrudListener() {
		return (TableCrudListener<T>) crudListener;
	}

	private void refreshTableButtonClicked(ClickEvent event) {
		doRefreshTable();
		Notification.show(container.size() + " Registro(s).");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void doRefreshTable() {
		removeAll();
		Collection all = ((TableCrudListener) crudListener).refreshTable();
		addAll(all);
	}

	private void addButtonClicked(ClickEvent event) {
		try {
			T domainObject = domainType.newInstance();
			showFormWindow("Nuevo", domainObject, newFormVisiblePropertyIds, null, newFormFieldCaptions, false, "Guardar", FontAwesome.SAVE, SiarpTheme.BUTTON_PRIMARY, e -> {
				crudListener.add(domainObject);
				Notification.show("Registro guardado.");
			});

		} catch (InstantiationException e2) {
			e2.printStackTrace();
		} catch (IllegalAccessException e3) {
			e3.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public boolean multipleRowsSelected() {
		Set<T> value = (Set<T>) table.getValue();
		return value != null && value.size() > 1;
	}

	@SuppressWarnings("unchecked")
	public T getFirstSelectedValue() {
		Set<T> value = (Set<T>) table.getValue();
		T selected = null;

		if (value != null && !value.isEmpty()) {
			selected = value.iterator().next();
		}

		return selected;
	}

	private void editButtonClicked(ClickEvent event) {
		if (multipleRowsSelected()) {
			Notification.show("Seleccione sólo un registro");
			return;
		}

		T domainObject = getFirstSelectedValue();

		if (domainObject != null) {
			showFormWindow("Editar", domainObject, editFormVisiblePropertyIds, editFormDisabledPropertyIds, editFormFieldCaptions, false, "Guardar", FontAwesome.SAVE, SiarpTheme.BUTTON_PRIMARY, e -> {
				crudListener.update(domainObject);
				Notification.show("Registro guardado.");
			});
		} else {
			Notification.show("Seleccione el registro a editar.");
		}
	}

	private void deleteButtonClicked(ClickEvent event) {
		if (multipleRowsSelected()) {
			Notification.show("Seleccione sólo un registro");
			return;
		}

		T domainObject = getFirstSelectedValue();

		if (domainObject != null) {
			showFormWindow("Eliminar", domainObject, deleteFormVisiblePropertyIds, null, deleteFormFieldCaptions, true, "Eliminar", FontAwesome.TIMES, SiarpTheme.BUTTON_DANGER, e -> {
				crudListener.delete(domainObject);
				Notification.show("Registro eliminado.");
			});
		} else {
			Notification.show("Seleccione el registro a eliminar.");
		}

	}
	
	public Button getRefreshButton(){
		return refreshTableButton;
	}

	private void showFormWindow(String windowTitle, T domainObject, Object[] visiblePropertyIds, Object disabledPropertyIds[], String[] fieldCaptions, boolean readOnly, String buttonCaption, Resource buttonIcon, String buttonStyle, ClickListener saveButtonClickListener) {
		Window window = new Window(windowTitle);
		window.setModal(true);
		UI.getCurrent().addWindow(window);

		VerticalLayout windowLayout = new VerticalLayout();
		windowLayout.setSizeUndefined();
		windowLayout.setMargin(true);
		window.setContent(windowLayout);

		Component crudForm = crudFormBuilder.buildNewForm(domainObject, visiblePropertyIds, disabledPropertyIds, fieldCaptions, readOnly, buttonCaption, buttonIcon, buttonStyle, e -> {
			saveButtonClickListener.buttonClick(e);
			doRefreshTable();
			window.close();
		});

		crudForm.setReadOnly(readOnly);
		windowLayout.addComponent(crudForm);
	}

}
