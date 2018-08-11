package com.gci.siarp.webapp.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;

import com.gci.siarp.webapp.domain.BloqueoReceiver;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FailedListener;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.FinishedListener;
import com.vaadin.ui.Upload.ProgressListener;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.StartedListener;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.wcs.wcslib.vaadin.widget.recaptcha.ReCaptcha;
import com.wcs.wcslib.vaadin.widget.recaptcha.shared.ReCaptchaOptions;

public class ViewCargarPedido extends CustomComponent
		implements StartedListener, ProgressListener, FailedListener, SucceededListener, FinishedListener {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Panel panel_1;
	@AutoGenerated
	private VerticalLayout verticalLayout_1;
	@AutoGenerated
	private Button btnCargar;
	@AutoGenerated
	private VerticalLayout vlCargue;
	@AutoGenerated
	private Upload upload_1;
	@AutoGenerated
	private Label label_1;
	@AutoGenerated
	private TextField tfEmail;
	private Consumer<String> consumerWindowsCP;
	private ProgressBar progress;
	private String URLArchivo;
	private BloqueoReceiver bloqueoReceiver;
	private String nombreArchivo;
	private SimpleDateFormat sdf;
	private Date fechaActual;
	private String fechaFormato;
	private ReCaptcha captcha;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 * 
	 * @param consumerWindowsCP
	 */
	public ViewCargarPedido(Consumer<String> consumerWindowsCP) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		this.consumerWindowsCP = consumerWindowsCP;

		style();
		init();
	}

	private void init() {
		URLArchivo = "algunaruta";
		sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		fechaActual = new Date();
		fechaFormato = sdf.format(fechaActual);
		label_1.setValue("Cargar Archivo");

		progress = new ProgressBar();
		progress.setWidth("100%");

		vlCargue.addComponent(progress, 1);
		upload_1.setButtonCaption(null);
		UploadLogic();

		captcha = new ReCaptcha("6Lc8ESUTAAAAAB_8IHl5FcE8o_QCToT44hOhBa-1", new ReCaptchaOptions() {
			private static final long serialVersionUID = 1L;
			{
				theme = "light";
				sitekey = "6Lc8ESUTAAAAABAYgG11XSkPRY-YXTKKFCjGb9GJ";
			}
		});
		verticalLayout_1.addComponent(captcha);
		verticalLayout_1.setComponentAlignment(captcha, new Alignment(48));
		verticalLayout_1.addComponent(btnCargar);
		verticalLayout_1.setComponentAlignment(btnCargar, new Alignment(48));
		btnCargar.addClickListener(e -> subirPedido());
	}

	private void subirPedido() {
		try {
			validaciones();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			upload_1.submitUpload();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void validaciones() {
		// TODO Auto-generated method stub
		
	}

	private void UploadLogic() {
		bloqueoReceiver = new BloqueoReceiver(URLArchivo);
		upload_1.setReceiver(bloqueoReceiver);
		upload_1.addFinishedListener(this);
		upload_1.addFailedListener(this);
		upload_1.addProgressListener(this);
		upload_1.addSucceededListener(this);
		upload_1.addStartedListener(this);
	}

	@Override
	public void uploadFinished(FinishedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		consumerWindowsCP.accept("CerrarVentana");

	}

	@Override
	public void uploadFailed(FailedEvent event) {
		label_1.setValue("Falló la carga del archivo.");
		progress.setValue(0f);
	}

	@Override
	public void updateProgress(long readBytes, long contentLength) {
		progress.setValue(new Float(readBytes / (float) contentLength));
		label_1.setValue(Math.round(100 * progress.getValue()) + "%");
	}

	@Override
	public void uploadStarted(StartedEvent event) {
		// TODO Auto-generated method stub

	}

	private void style() {
		// TODO Auto-generated method stub

	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// panel_1
		panel_1 = buildPanel_1();
		mainLayout.addComponent(panel_1);

		return mainLayout;
	}

	@AutoGenerated
	private Panel buildPanel_1() {
		// common part: create layout
		panel_1 = new Panel();
		panel_1.setImmediate(false);
		panel_1.setWidth("100.0%");
		panel_1.setHeight("100.0%");

		// verticalLayout_1
		verticalLayout_1 = buildVerticalLayout_1();
		panel_1.setContent(verticalLayout_1);

		return panel_1;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_1() {
		// common part: create layout
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("100.0%");
		verticalLayout_1.setHeight("100.0%");
		verticalLayout_1.setMargin(true);
		verticalLayout_1.setSpacing(true);

		// tfEmail
		tfEmail = new TextField();
		tfEmail.setCaption("Correo Electrónico");
		tfEmail.setImmediate(false);
		tfEmail.setWidth("80.0%");
		tfEmail.setHeight("-1px");
		tfEmail.setRequired(true);
		verticalLayout_1.addComponent(tfEmail);

		// vlCargue
		vlCargue = buildVlCargue();
		verticalLayout_1.addComponent(vlCargue);

		// btnCargar
		btnCargar = new Button();
		btnCargar.setCaption("Cargar");
		btnCargar.setImmediate(false);
		btnCargar.setWidth("-1px");
		btnCargar.setHeight("-1px");

		return verticalLayout_1;
	}

	@AutoGenerated
	private VerticalLayout buildVlCargue() {
		// common part: create layout
		vlCargue = new VerticalLayout();
		vlCargue.setImmediate(false);
		vlCargue.setWidth("100.0%");
		vlCargue.setHeight("-1px");
		vlCargue.setMargin(false);

		// label_1
		label_1 = new Label();
		label_1.setImmediate(false);
		label_1.setWidth("-1px");
		label_1.setHeight("-1px");
		label_1.setValue("Label");
		vlCargue.addComponent(label_1);

		// upload_1
		upload_1 = new Upload();
		upload_1.setImmediate(false);
		upload_1.setWidth("-1px");
		upload_1.setHeight("-1px");
		vlCargue.addComponent(upload_1);

		return vlCargue;
	}
}