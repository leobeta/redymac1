package com.gci.siarp.auth.ui.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.gci.siarp.api.annotation.SiarpUIComponent;
import com.gci.siarp.api.ui.SiarpSession;
import com.gci.siarp.auth.service.AuthService;
import com.gci.siarp.auth.ui.view.ErrorView;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;

/**
 *
 * @author Alejandro Duarte
 */
@SiarpUIComponent
@SuppressWarnings("serial")
public class SiarpViewChangeListener implements ViewChangeListener {

	@Autowired
	private AuthService authService;

	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {

		boolean canAccess = authService.userCanAccess(SiarpSession.getUsername(), event.getViewName());

		if (!canAccess) {
			UI.getCurrent().getNavigator().navigateTo(ErrorView.viewName);
		}

		return canAccess;
	}

	@Override
	public void afterViewChange(ViewChangeEvent event) {
	}

}
