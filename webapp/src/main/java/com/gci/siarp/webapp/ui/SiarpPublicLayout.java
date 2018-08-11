package com.gci.siarp.webapp.ui;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import com.gci.siarp.api.annotation.SiarpUIComponent;
import com.gci.siarp.api.ui.SiarpTheme;
import com.gci.siarp.api.ui.SiarpTheme.SiarpResources;
import com.gci.siarp.api.ui.SiarpUI;
import com.gci.siarp.auth.domain.DigestKeys;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.WebBrowser;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author DECODE IT SAS
 */
@SiarpUIComponent
//@PropertySource("file:${SIARP_CONF}/siarp.properties")
//@PropertySource("file:/home/sbt0/siarp.properties")
@PropertySource("file:/Users/leo/siarp_conf/siarp.properties")
@SuppressWarnings("serial")
public class SiarpPublicLayout extends CustomComponent implements com.gci.siarp.api.ui.layout.SiarpPublicLayout {

	@Value("${siarp.loginForm.default.username}")
	private String defaultUsername;

	@Value("${siarp.loginForm.default.password}")
	private String defaultPassword;

	private TextField username = new TextField();
	private PasswordField password = new PasswordField();
	private Window wCargarPedido;
	public Consumer<String> consumerWindowsCP = (email) -> cerrarWindowCP(email);

	@Autowired
	private SiarpLoginListener loginListener;

	public SiarpPublicLayout() {
		Image logo = new Image(null, new ThemeResource(SiarpResources.logoCliente));

		Label loginPCELlantas = new Label("PCELlantas");
		loginPCELlantas.setStyleName(SiarpTheme.LABEL_H1);
		
		Label loginLabel = new Label("Iniciar sesión");
		loginLabel.setStyleName(SiarpTheme.LABEL_H3);

		username.setInputPrompt("Usuario");
		username.setWidth("100%");

		password.setInputPrompt("Contraseña");
		password.setWidth("100%");

		Button loginButton = new Button("Entrar");
		loginButton.setWidth("100%");
		loginButton.setStyleName(SiarpTheme.BUTTON_FRIENDLY);
		loginButton.addClickListener(this::login);
		loginButton.setClickShortcut(KeyCode.ENTER);

		VerticalLayout formLayout = new VerticalLayout();
		formLayout.setSizeFull();
		formLayout.setMargin(true);
		formLayout.setSpacing(true);
		formLayout.addComponent(logo);
		formLayout.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
//		formLayout.addComponent(loginPCELlantas);
		formLayout.addComponent(loginLabel);
		formLayout.addComponent(username);
		formLayout.addComponent(password);
		formLayout.addComponent(loginButton);
		
		Button btnFormatoExcel = new Button("Descargar Formato");
		btnFormatoExcel.addStyleName(SiarpTheme.BUTTON_LINK);
		btnFormatoExcel.setIcon(FontAwesome.FILE_EXCEL_O);
		
		Button btnCargarPedido = new Button("Cargar Pedido");
		btnCargarPedido.addStyleName(SiarpTheme.BUTTON_LINK);
		btnCargarPedido.setIcon(FontAwesome.UPLOAD);
		btnCargarPedido.addClickListener(e -> cargarPedido());
		
		HorizontalLayout hlArchivos = new HorizontalLayout();
		hlArchivos.setSizeFull();
		hlArchivos.setMargin(true);
		hlArchivos.setSpacing(true);
		hlArchivos.addComponent(btnCargarPedido);
		hlArchivos.addComponent(btnFormatoExcel);
		
		formLayout.addComponent(hlArchivos);
		
		Panel panel = new Panel(formLayout);
		panel.setWidth("400px");

		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		mainLayout.addComponent(panel);
		mainLayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

		setCompositionRoot(mainLayout);
		setSizeFull();
	}

	private void cerrarWindowCP(String email) {
		UI.getCurrent().removeWindow(wCargarPedido);
	}

	private void cargarPedido() {
		wCargarPedido = new Window("Cargar Pedidos");
		wCargarPedido.setWidth("50%");
		wCargarPedido.setHeight("50%");
		wCargarPedido.setContent(new ViewCargarPedido(consumerWindowsCP));
		wCargarPedido.setModal(true);
		wCargarPedido.center();
		wCargarPedido.setClosable(true);
		wCargarPedido.setResizable(true);
		UI.getCurrent().addWindow(wCargarPedido);
	}

	@Override
	public void attach() {
		super.attach();
		resetForm();
	}

	public void resetForm() {
		username.setValue(defaultUsername);
		password.setValue(defaultPassword);
	}

	private void login(Button.ClickEvent event) {

		final WebBrowser webBrowser = UI.getCurrent().getPage().getWebBrowser();

		if (username.getValue() != null && !username.getValue().isEmpty() && password.getValue() != null
				&& !password.getValue().isEmpty()
				&& loginListener.login(username.getValue(), DigestKeys.CryptSHA1(password.getValue()), webBrowser.getAddress())) {

			SiarpUI siarpUI = (SiarpUI) UI.getCurrent();
			siarpUI.showPrivateLayout();

		} else {
			Notification.show("Credenciales incorrectas.", Notification.Type.ERROR_MESSAGE);
		}

		resetForm();
	}

}
