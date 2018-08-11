package com.gci.siarp.auth.ui.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.gci.siarp.api.annotation.SiarpUIComponent;
import com.gci.siarp.auth.ui.SiarpMenu;
import com.vaadin.ui.UI;

/**
 * 
 * @author alejandro
 *
 */
@SiarpUIComponent
public class SiarpMenuListener implements com.gci.siarp.api.ui.layout.SiarpPrivateLayout.SiarpMenuListener {

	@Autowired
	private SiarpMenu menu;

	@Override
	public void optionClicked(String optionId) {
		String url = menu.getUrl(optionId);
		
		if (url != null) {
			UI.getCurrent().getNavigator().navigateTo(url);
		}

	}

}
