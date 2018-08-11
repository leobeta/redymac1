package com.gci.siarp.api.ui.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import com.gci.siarp.api.ui.SiarpTheme;
import com.gci.siarp.api.ui.crud.VerticalCrudLayout;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.StreamResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class JasperReportComponent extends CustomComponent {
	
	private String jasperFileName;
	private InputStream inputStream;
	private Class<?> clazz;
	private JRBeanCollectionDataSource jasperCollection; 
	private Map<String, Object> parameters;
	private JasperPrint print;
	private Button pdfButton;
	private Button wordButton;
	private Button excelButton;

	
	public Button getWordButton() {
		return wordButton;
	}
	public Button getExcelButton() {
		return excelButton;
	}
	public Button getPdfButton() {
		return pdfButton;
	}

	private interface OnDemandStreamResource extends StreamSource {
		String getFilename();
	}

	private class OnDemandFileDownloader extends FileDownloader {

		private static final long serialVersionUID = 1L;
		private final OnDemandStreamResource onDemandStreamResource;

		public OnDemandFileDownloader(OnDemandStreamResource onDemandStreamResource) {
			super(new StreamResource(onDemandStreamResource, ""));
			this.onDemandStreamResource = onDemandStreamResource;
		}

		@Override
		public boolean handleConnectorRequest(VaadinRequest request, VaadinResponse response, String path) throws IOException {
			getResource().setFilename(onDemandStreamResource.getFilename());
			return super.handleConnectorRequest(request, response, path);
		}

		private StreamResource getResource() {
			return (StreamResource) this.getResource("dl");
		}

	}



	public JasperReportComponent(InputStream inputStream, Class<?> clazz, JRBeanCollectionDataSource jasperCollection, Map<String, Object> parameters) throws JRException, IOException {
		this.inputStream=inputStream;
		this.clazz = clazz;
		this.jasperCollection=jasperCollection;
		this.parameters = parameters;
		initPrint();
		setSizeFull();

		VerticalCrudLayout mainLayout = new VerticalCrudLayout();
		//mainLayout.addToolbarComponent(new Label("Descargar: "));
		setCompositionRoot(mainLayout);
		
		
		//Button pdfButton = new Button("PDF", FontAwesome.FILE_PDF_O);
		pdfButton = new Button(null, new ThemeResource("images/A_pdf.png"));
		pdfButton.setWidth("35px");
		pdfButton.setHeight("35px");
		pdfButton.addStyleName(SiarpTheme.BUTTON_ICON_ONLY);
		pdfButton.addStyleName(SiarpTheme.BUTTON_BORDERLESS);
		mainLayout.addToolbarComponent(pdfButton);
		
		OnDemandFileDownloader pdfDownloader = new OnDemandFileDownloader(createPDFResource());
		pdfDownloader.extend(pdfButton);

		//Button wordButton = new Button("Word", FontAwesome.FILE_WORD_O);
		wordButton = new Button(null, new ThemeResource("images/A_word.png"));
		wordButton.addStyleName(SiarpTheme.BUTTON_ICON_ONLY);
		wordButton.addStyleName(SiarpTheme.BUTTON_BORDERLESS);
		wordButton.setWidth("35px");
		wordButton.setHeight("35px");
		wordButton.setVisible(false);
		mainLayout.addToolbarComponent(wordButton);
		

//		OnDemandFileDownloader wordDownloader = new OnDemandFileDownloader(createWordResource());
//		wordDownloader.extend(wordButton);

		//Button excelButton = new Button("Excel", FontAwesome.FILE_EXCEL_O);
		excelButton = new Button(null, new ThemeResource("images/A_excel.png"));
		excelButton.addStyleName(SiarpTheme.BUTTON_ICON_ONLY);
		excelButton.addStyleName(SiarpTheme.BUTTON_BORDERLESS);
		excelButton.setWidth("35px");
		excelButton.setHeight("35px");
		mainLayout.addToolbarComponent(excelButton);

		OnDemandFileDownloader excelDownloader = new OnDemandFileDownloader(createExcelResource());
		excelDownloader.extend(excelButton);
		
			
		Panel scrollablePanel = new Panel();
		scrollablePanel.setSizeFull();
		mainLayout.setMainComponent(scrollablePanel);

		VerticalLayout contentLayout = new VerticalLayout();
		scrollablePanel.setContent(contentLayout);

		Label contentLabel = new Label(getReportHtml(), ContentMode.HTML);
		contentLayout.addComponent(contentLabel);
				
				
	}
/*
	private void initPrint() throws IOException, JRException {
		try (InputStream inputStream = clazz.getResourceAsStream(jasperFileName)) {
			//Notification.show(inputStream.toString());
			print = JasperFillManager.fillReport(inputStream, parameters, connection);
		}
	}
*/	
	private void initPrint() throws IOException, JRException {
			//Notification.show(inputStream.toString());
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			print = JasperFillManager.fillReport(jasperReport, parameters, jasperCollection);
	}
	
/*	private void initPrint() throws IOException, JRException {
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperFileName);
			print = JasperFillManager.fillReport(jasperReport, parameters, connection);
			//Notification.show(print.getProperty(getCaption()));
	}
	*/

	private OnDemandStreamResource createPDFResource() throws IOException, JRException {
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.exportReport();

			return new OnDemandStreamResource() {

				@Override
				public InputStream getStream() {
					return new ByteArrayInputStream(outputStream.toByteArray());
				}

				@Override
				public String getFilename() {
					return JasperReportComponent.this.getFileName() + ".pdf";
				}
			};
		}
	}

	private OnDemandStreamResource createWordResource() throws IOException, JRException {
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

			JRDocxExporter exporter = new JRDocxExporter();
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.exportReport();

			return new OnDemandStreamResource() {

				@Override
				public InputStream getStream() {
					return new ByteArrayInputStream(outputStream.toByteArray());
				}

				@Override
				public String getFilename() {
					return JasperReportComponent.this.getFileName() + ".docx";
				}
			};

		}
	}

	private OnDemandStreamResource createExcelResource() throws IOException, JRException {
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.exportReport();
			
			return new OnDemandStreamResource() {

				@Override
				public InputStream getStream() {
					return new ByteArrayInputStream(outputStream.toByteArray());
				}

				@Override
				public String getFilename() {
					return JasperReportComponent.this.getFileName() + ".xlsx";
				}
			};

		}
	}

	private String getFileName() {
		return "siarp-report " + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(new Date());
	}

	private String getReportHtml() throws JRException, IOException {
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

			HtmlExporter exporter = new HtmlExporter();
			exporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.exportReport();

			return outputStream.toString("UTF-8");
		}
	}

}
