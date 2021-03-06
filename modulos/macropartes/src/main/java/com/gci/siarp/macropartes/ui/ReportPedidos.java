package com.gci.siarp.macropartes.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.gci.siarp.api.ui.report.JasperReportComponent;
import com.gci.siarp.macropartes.domain.PedidoReporte;
import com.gci.siarp.macropartes.service.MacropartesService;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;
import com.vaadin.ui.Notification.Type;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportPedidos extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@AutoGenerated
	private AbsoluteLayout mainLayout;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 * @param string 
	 * @param macropartesService 
	 * @param string 
	 */
	public ReportPedidos(MacropartesService macropartesService, Long consecutivo, String preparado) {
		super();
		setWidth("80%");
		setHeight("80%");
		setModal(true);
		setResizable(false);
		center();
		List<PedidoReporte> listPedido = macropartesService.getPedidosPorIDReporte(consecutivo);
		if(listPedido.size() == 0){
			Notification.show("Atención", "No hay reporte a generar", Type.WARNING_MESSAGE);
		} else {
			JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listPedido);
			JasperReportComponent jsComponent;
			String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("nroPedido", consecutivo);
			parameters.put("path", basepath + "/VAADIN/themes/siarp/images/logo-pedido.png");
			parameters.put("preparado", preparado);
			try {
				jsComponent = new JasperReportComponent(this.getClass().getResourceAsStream("reportePedido.jrxml"), this.getClass(), jrbcds, parameters);
				jsComponent.setHeight("100%");
				jsComponent.setWidth("100%");
				setContent(jsComponent);
			} catch (JRException | IOException e) {
				e.printStackTrace();
			}
		}
	}

}
