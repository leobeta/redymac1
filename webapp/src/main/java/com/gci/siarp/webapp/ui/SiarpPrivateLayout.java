package com.gci.siarp.webapp.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gci.siarp.api.annotation.SiarpUIComponent;
import com.gci.siarp.api.ui.SiarpTheme.SiarpResources;
import com.gci.siarp.api.ui.SiarpTheme;
import com.gci.siarp.api.ui.SiarpUI;
import com.vaadin.data.Property;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Alejandro Duarte
 */
@SiarpUIComponent
@SuppressWarnings("serial")
public class SiarpPrivateLayout extends CustomComponent implements com.gci.siarp.api.ui.layout.SiarpPrivateLayout {

	private Tree tree;
	private VerticalLayout currentViewContainer;
	private Button usernameButton;

	@Autowired
	private SiarpMenuListener siarpMenuListener;

	public SiarpPrivateLayout() {

		Button logoutButton = new Button("Salir");
		logoutButton.setStyleName(SiarpTheme.BUTTON_LINK);
		logoutButton.setIcon(FontAwesome.SIGN_OUT);
		logoutButton.addClickListener(this::logout);

		usernameButton = new Button("Usuario");
		usernameButton.setStyleName(SiarpTheme.BUTTON_LINK);
		usernameButton.setIcon(FontAwesome.USER);

		HorizontalLayout sessionLayout = new HorizontalLayout(usernameButton, logoutButton);

		Image logo = new Image(null, new ThemeResource(SiarpResources.logoPrivateLayout));

		tree = new Tree();
		tree.addValueChangeListener(this::menuValueChange);
		tree.setImmediate(true);

		VerticalLayout treeLayout = new VerticalLayout(tree);
		treeLayout.setMargin(true);

		Panel menuPanel = new Panel("Menú Principal", treeLayout);
		menuPanel.setSizeFull();

		VerticalLayout menuViewContainer = new VerticalLayout();
		menuViewContainer.setSizeFull();
		menuViewContainer.setMargin(new MarginInfo(false, true, true, true));
		menuViewContainer.setSpacing(true);
		menuViewContainer.addComponent(logo);
		menuViewContainer.setComponentAlignment(logo, Alignment.TOP_CENTER);
		menuViewContainer.addComponent(sessionLayout);
		menuViewContainer.setComponentAlignment(sessionLayout, Alignment.TOP_CENTER);
		menuViewContainer.addComponent(menuPanel);
		menuViewContainer.setExpandRatio(menuPanel, 1);

		currentViewContainer = new VerticalLayout();
		currentViewContainer.setSizeFull();
		currentViewContainer.setMargin(true);
		currentViewContainer.setSpacing(true);

		HorizontalSplitPanel mainLayout = new HorizontalSplitPanel();
		mainLayout.setSizeFull();
		mainLayout.setSplitPosition(250, Unit.PIXELS);
		mainLayout.setFirstComponent(menuViewContainer);
		mainLayout.setSecondComponent(currentViewContainer);

		setCompositionRoot(mainLayout);
		setSizeFull();
	}

	@Override
	public void setUsernameButtonCaption(String username) {
		usernameButton.setCaption(username);
	}

	@Override
	public String addMenuOption(List<String> submenus) {
		String parent = null;
		String path = "";

		for (String current : submenus) {
			path += current + "/";

			if (tree.getItem(path) == null) {
				tree.addItem(path);
				tree.setParent(path, parent);
				tree.setChildrenAllowed(path, true);
				tree.setItemCaption(path, current);
				tree.setItemIcon(path, FontAwesome.FOLDER_OPEN_O);
			}

			parent = path;
		}

		tree.setChildrenAllowed(path, false);
		tree.setItemIcon(path, null);

		return path;
	}

	@Override
	public ComponentContainer getCurrentViewContainer() {
		return currentViewContainer;
	}

	private void menuValueChange(Property.ValueChangeEvent event) {
		siarpMenuListener.optionClicked("" + event.getProperty().getValue());
	}

	private void logout(ClickEvent event) {
		SiarpUI siarpUI = (SiarpUI) UI.getCurrent();
		siarpUI.showPublicLayout();
	}

}
