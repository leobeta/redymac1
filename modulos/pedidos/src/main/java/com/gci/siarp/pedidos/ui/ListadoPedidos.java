package com.gci.siarp.pedidos.ui;

import com.gci.siarp.api.annotation.SiarpUIComponent;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;

import lombok.extern.log4j.Log4j;
import ru.xpoft.vaadin.VaadinView;

@SiarpUIComponent
@VaadinView("pedidos/listadoPredidos")
@SuppressWarnings("serial")
@Log4j
public class ListadoPedidos extends CustomComponent {

	@AutoGenerated
	private AbsoluteLayout mainLayout;

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public ListadoPedidos() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	@AutoGenerated
	private void buildMainLayout() {
		// the main layout and components will be created here
		mainLayout = new AbsoluteLayout();
	}

}
