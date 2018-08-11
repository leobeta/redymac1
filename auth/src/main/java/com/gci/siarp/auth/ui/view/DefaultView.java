package com.gci.siarp.auth.ui.view;

import ru.xpoft.vaadin.VaadinView;

import com.gci.siarp.api.annotation.SiarpUIComponent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author alejandro
 *
 */
@SiarpUIComponent
@VaadinView("")
@SuppressWarnings("serial")
public class DefaultView extends CustomComponent implements View {

	@Override
	public void enter(ViewChangeEvent event) {
		setCompositionRoot(new VerticalLayout());
	}

}
