package com.gci.siarp.webapp.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.gci.siarp.api.annotation.SiarpUIComponent;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

@SiarpUIComponent
@PreserveOnRefresh
@Theme("siarp")
@SuppressWarnings("serial")
public class SiarpUI extends UI implements com.gci.siarp.api.ui.SiarpUI {

	@Autowired
	private SiarpPublicLayout siarpPublicLayout;

	@Autowired
	private SiarpPrivateLayout siarpPrivateLayout;

	@Autowired
	private UIChangeListener siarpUIChangeListener;

	@Override
	protected void init(VaadinRequest request) {
		VaadinSession.getCurrent().setErrorHandler(new SiarpErrorHandler());
		showPublicLayout();
		getPage().setTitle("Macropartes de Colombia");
	}

	@Override
	public void showPublicLayout() {
		setContent(siarpPublicLayout);
		siarpUIChangeListener.afterSwitchToPublic();
	}

	@Override
	public void showPrivateLayout() {
		setContent(siarpPrivateLayout);
		siarpUIChangeListener.afterSwitchToPrivate();
	}

	@Override
	public SiarpPrivateLayout getSiarpPrivateLayout() {
		return siarpPrivateLayout;
	}

}
