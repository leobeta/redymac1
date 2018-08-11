package com.gci.siarp.auth.ui.view;

import ru.xpoft.vaadin.VaadinView;

import com.gci.siarp.api.annotation.SiarpUIComponent;
import com.gci.siarp.api.ui.SiarpTheme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author alejandro
 *
 */
@SiarpUIComponent
@SuppressWarnings("serial")
@VaadinView(ErrorView.viewName)
public class ErrorView extends CustomComponent implements View {
	
	public static final String viewName = "error";
	
	public ErrorView() {
		Label label = new Label(
				"Página no encontrada o privilegios insuficientes. Verifique que la URL es correcta e intente de nuevo. Si el problema persiste contacte al administrador del sistema.");
		
		label.setStyleName(SiarpTheme.LABEL_FAILURE);
		
		VerticalLayout mainLayout = new VerticalLayout(label);
		mainLayout.setSizeFull();
		mainLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
		
		setCompositionRoot(mainLayout);
		setSizeFull();
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

}
