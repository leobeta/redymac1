package com.gci.siarp.auth.ui.listener;

import org.springframework.beans.factory.annotation.Autowired;

import ru.xpoft.vaadin.DiscoveryNavigator;

import com.gci.siarp.api.annotation.SiarpUIComponent;
import com.gci.siarp.api.ui.SiarpSession;
import com.gci.siarp.api.ui.SiarpUI;
import com.gci.siarp.auth.ui.view.ErrorView;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.UI;

/**
 *
 * @author Alejandro Duarte
 */
@SiarpUIComponent
public class SiarpUIChangeListener implements com.gci.siarp.api.ui.SiarpUI.UIChangeListener {

	@Autowired
	private SiarpViewChangeListener siarpViewChangeListener;
	
	@Autowired
	private ErrorView errorView;

	@Override
	public void afterSwitchToPublic() {
		if(SiarpSession.getUsername() != null) {
			SiarpUI siarpUI = (SiarpUI) UI.getCurrent();
			siarpUI.getPage().setLocation("");
			VaadinService.getCurrentRequest().getWrappedSession().invalidate();
		}
	}

	@Override
	public void afterSwitchToPrivate() {
		SiarpUI siarpUI = (SiarpUI) UI.getCurrent();
		
		DiscoveryNavigator navigator = new DiscoveryNavigator((UI) siarpUI, siarpUI.getSiarpPrivateLayout().getCurrentViewContainer());
		navigator.addViewChangeListener(siarpViewChangeListener);
		navigator.setErrorView(errorView);
		siarpUI.setNavigator(navigator);
		
		siarpUI.getPage().setUriFragment(null);
	}

}
