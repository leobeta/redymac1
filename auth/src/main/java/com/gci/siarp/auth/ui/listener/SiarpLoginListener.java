package com.gci.siarp.auth.ui.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.gci.siarp.api.annotation.SiarpUIComponent;
import com.gci.siarp.api.ui.SiarpSession;
import com.gci.siarp.api.ui.SiarpUI;
import com.gci.siarp.auth.service.AuthService;
import com.gci.siarp.auth.ui.SiarpMenu;
import com.vaadin.ui.UI;

/**
 * 
 * @author alejandro
 *
 */
@SiarpUIComponent
public class SiarpLoginListener implements com.gci.siarp.api.ui.layout.SiarpPublicLayout.SiarpLoginListener {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private SiarpMenu menu;

	@Override
	public boolean login(String username, String password,String maquina) {
		boolean isAuthentic = authService.isAuthenticUser(username, password);
	
		if (isAuthentic) {
			SiarpSession.setUsername(username);
			SiarpSession.setMachine(maquina);
			updateUsernameButtonCaption(username);
			updateMenuOptions();
		}
		
		
		return isAuthentic;
	}

	private void updateUsernameButtonCaption(String username) {
		SiarpUI siarpUI = (SiarpUI) UI.getCurrent();
		siarpUI.getSiarpPrivateLayout().setUsernameButtonCaption(username);
	}

	private void updateMenuOptions() {
		menu.buildForCurrentUser();
	}
	
}
