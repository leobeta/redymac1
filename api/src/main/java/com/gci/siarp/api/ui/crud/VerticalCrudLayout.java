package com.gci.siarp.api.ui.crud;

import com.gci.siarp.api.ui.SiarpTheme;
import com.gci.siarp.api.ui.layout.CrudLayout;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * CrudLayout que presenta las áreas internas de forma vertical.
 * 
 * @author Alejandro
 *
 */
@SuppressWarnings("serial")
public class VerticalCrudLayout extends CustomComponent implements CrudLayout {

	private VerticalLayout mainLayout = new VerticalLayout();
	private Label captionLabel = new Label();
	private HorizontalLayout headerLayout = new HorizontalLayout();
	private HorizontalLayout toolbarLayout = new HorizontalLayout();
	private HorizontalLayout filterLayout = new HorizontalLayout();
	private VerticalLayout mainComponentLayout = new VerticalLayout();

	public VerticalCrudLayout() {
		mainLayout.setSizeFull();
		mainLayout.setSpacing(true);
		setCompositionRoot(mainLayout);
		setSizeFull();
		
		captionLabel.addStyleName(SiarpTheme.LABEL_COLORED);
		captionLabel.addStyleName(SiarpTheme.LABEL_BOLD);
		captionLabel.addStyleName(SiarpTheme.LABEL_H3);
		captionLabel.addStyleName(SiarpTheme.LABEL_NO_MARGIN);
		captionLabel.setVisible(false);

		headerLayout.setVisible(false);
		headerLayout.setSpacing(true);

		toolbarLayout.setVisible(false);
		toolbarLayout.setSpacing(true);
		headerLayout.addComponent(toolbarLayout);

		filterLayout.setVisible(false);
		filterLayout.setSpacing(true);
		headerLayout.addComponent(filterLayout);

		Label filterIcon = new Label();
		filterIcon.setIcon(FontAwesome.SEARCH);
		filterIcon.setDescription("Use los campos para filtrar");
		filterLayout.addComponent(filterIcon);

		mainComponentLayout.setSizeFull();
		mainLayout.addComponent(mainComponentLayout);
		mainLayout.setExpandRatio(mainComponentLayout, 1);
	}
	
	@Override
	public void setCaption(String caption) {
		if(!captionLabel.isVisible()) {
			captionLabel.setVisible(true);
			mainLayout.addComponent(captionLabel, 0);
		}
		
		captionLabel.setValue(caption);
	}

	@Override
	public void addToolbarComponent(Component component) {
		if (!headerLayout.isVisible()) {
			headerLayout.setVisible(true);
			mainLayout.addComponent(headerLayout, mainLayout.getComponentCount() - 1);
		}

		toolbarLayout.setVisible(true);
		toolbarLayout.addComponent(component);
	}

	@Override
	public void setMainComponent(Component component) {
		mainComponentLayout.removeAllComponents();
		mainComponentLayout.addComponent(component);
	}

	@Override
	public void addFilterComponent(Component component) {
		if (!headerLayout.isVisible()) {
			headerLayout.setVisible(true);
			mainLayout.addComponent(headerLayout, mainLayout.getComponentCount() - 1);
		}

		filterLayout.setVisible(true);
		filterLayout.addComponent(component);
	}

}
