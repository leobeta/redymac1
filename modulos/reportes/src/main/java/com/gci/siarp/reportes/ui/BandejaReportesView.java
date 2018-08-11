package com.gci.siarp.reportes.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;

import ru.xpoft.vaadin.VaadinView;

import com.gci.siarp.api.annotation.SiarpUIComponent;
import com.gci.siarp.api.ui.SiarpSession;
import com.gci.siarp.api.ui.SiarpTheme;
import com.gci.siarp.api.ui.crud.TableBasedCrudComponent;
import com.gci.siarp.api.ui.crud.TableBasedCrudComponent.TableCrudListener;
import com.gci.siarp.reportes.domain.Reporte;
import com.gci.siarp.reportes.service.ReportesService;
import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;


/**
 * 
 * @author cmoreno
 *
 */
@SiarpUIComponent
@VaadinView("/BandejaReportes")
@SuppressWarnings("serial")
public class BandejaReportesView extends CustomComponent implements View {
	private String isAmbiente="0";//0: Intranet, 1:Internet
	
	private class LinkColumnGenerator implements Table.ColumnGenerator {

	    @Override
	    public Object generateCell( Table source, Object itemId, Object columnId ) {
	    	if (isAmbiente.equals("0")){
		    	if (source.getItem(itemId).getItemProperty("descripError").toString()!=null){
		    		return source.getItem(itemId).getItemProperty("descripError").toString();
		    	}
	    	}else{
	    		if (source.getItem(itemId).getItemProperty("descripErrorDmz").toString()!=null){
		    		return source.getItem(itemId).getItemProperty("descripErrorDmz").toString();
		    	}
	    	}
	    	Property<?> prop = source.getItem( itemId ).getItemProperty( columnId );
	    	if (prop.toString()==null) {
	    		return null;
	    	}
	    	if (prop.toString().equals("No se encuentran datos con estos argumentos")){
	    		return "No se encuentran datos con estos argumentos";
	    	}
	    	
	    	String link;
	    	link=prop.toString();
	    	if (link==null){
	    		return null;
	    	}
	    	
	    	Link ck=new Link( prop.toString(),new FileResource(new File(link)));
	        return ck;
	       
	    }
	}
	
	private class ReporteCrudListener implements TableCrudListener<Reporte>{

		@Override
		public void add(Reporte domainObjectToAdd) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void update(Reporte domainObjectToUpdate) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void delete(Reporte domainObjectToDelete) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Collection<Reporte> refreshTable() {
			return reportesService.findAllReportsUsuario(SiarpSession.getUsername(),codRep);
		}
		
	}
	
	@Autowired
	private ReportesService reportesService;
	private Integer codRep;
	private TableBasedCrudComponent<Reporte> reporteCrud;
	
	private Collection<Reporte> reportes =new ArrayList<Reporte>();
	
	@Override
	public void enter(ViewChangeEvent event) {
		try {
			String siarpConfDirectory = System.getenv("SIARP_CONF");
	
			Properties properties = new Properties();
			properties.load(new FileInputStream(siarpConfDirectory + "/siarp.properties"));
	
			String ambiente = properties.getProperty("siarp.ambiente");
	
			if (ambiente.toLowerCase().equals("internet")) {
				isAmbiente="1";
			}
		} catch (IOException e) {
			throw new RuntimeException("Error cargando el archivo 'siarp.properties'. Verifique que el archivo existe en el directorio de configuración.", e);
		}
		
		
		Panel panel = new Panel("REPORTES SOLICITADOS PREVIAMENTE");
		panel.addStyleName(SiarpTheme.PANEL_WELL);

		VerticalLayout mainLayout = new VerticalLayout();
		panel.setContent(mainLayout);
		mainLayout.setSpacing(true);
		panel.setSizeFull();
		
		reportes= reportesService.findDistinctReportUsuario(SiarpSession.getUsername());
		
		Iterator<Reporte> itrReportes = reportes.iterator();
		while(itrReportes.hasNext()){

			
			ArrayList<String> visibles = new ArrayList<String>(Arrays.asList("nroSolicitud", "fechaSolicitud"));

			Reporte reporte = itrReportes.next();

			reporteCrud = new TableBasedCrudComponent<Reporte>(Reporte.class, new ReporteCrudListener());
			reporteCrud.setCaption(reporte.getDescripReporte());
			codRep=reporte.getCodReporte();
			
			reporteCrud.doRefreshTable();
			reporteCrud.getTable().setPageLength(0);
			reporteCrud.getTable().setSizeUndefined();
			reporteCrud.getTable().setWidth("100%");
			reporteCrud.getTable().setColumnHeader("nroSolicitud","Nro Solicitud");
			reporteCrud.getTable().setColumnHeader("fechaSolicitud","Fecha Solicitud");
			
			if (isAmbiente.equals("0")){
				reporteCrud.getTable().setColumnHeader("ruta","Ruta");
				reporteCrud.getTable().addGeneratedColumn("ruta", new LinkColumnGenerator());
				visibles.add("ruta");
			}else{
				reporteCrud.getTable().setColumnHeader("rutaDmz","Ruta");
				reporteCrud.getTable().addGeneratedColumn("rutaDmz", new LinkColumnGenerator());
				visibles.add("rutaDmz");
			}
			
			if (reporte.getDescripArg1()!=null && reporte.getDescripArg1()!=""){
				visibles.add("datoArg1");
				reporteCrud.getTable().setColumnHeader("datoArg1",reporte.getDescripArg1());
			}
			if (reporte.getDescripArg2()!=null && reporte.getDescripArg2()!=""){
				visibles.add("datoArg2");
				reporteCrud.getTable().setColumnHeader("datoArg2",reporte.getDescripArg2());
			}
			if (reporte.getDescripArg3()!=null && reporte.getDescripArg3()!=""){
				visibles.add("datoArg3");
				reporteCrud.getTable().setColumnHeader("datoArg3",reporte.getDescripArg3());
			}
			if (reporte.getDescripArg4()!=null && reporte.getDescripArg4()!=""){
				visibles.add("datoArg4");
				reporteCrud.getTable().setColumnHeader("datoArg4",reporte.getDescripArg4());
			}
			if (reporte.getDescripArg5()!=null && reporte.getDescripArg5()!=""){
				visibles.add("datoArg5");
				reporteCrud.getTable().setColumnHeader("datoArg5",reporte.getDescripArg5());
			}
			if (reporte.getDescripArg6()!=null && reporte.getDescripArg6()!=""){
				visibles.add("datoArg6");
				reporteCrud.getTable().setColumnHeader("datoArg6",reporte.getDescripArg6());
			}
			if (reporte.getDescripArg7()!=null && reporte.getDescripArg7()!=""){
				visibles.add("datoArg7");
				reporteCrud.getTable().setColumnHeader("datoArg7",reporte.getDescripArg7());
			}
			if (reporte.getDescripArg8()!=null && reporte.getDescripArg8()!=""){
				visibles.add("datoArg8");
				reporteCrud.getTable().setColumnHeader("datoArg8",reporte.getDescripArg8());
			}
			if (reporte.getDescripArg9()!=null && reporte.getDescripArg9()!=""){
				visibles.add("datoArg9");
				reporteCrud.getTable().setColumnHeader("datoArg9",reporte.getDescripArg9());
			}
			if (reporte.getDescripArg10()!=null && reporte.getDescripArg10()!=""){
				visibles.add("datoArg10");
				reporteCrud.getTable().setColumnHeader("datoArg10",reporte.getDescripArg10());
			}
			reporteCrud.getTable().setVisibleColumns(visibles.toArray());
			mainLayout.addComponent(new Label(""));
			mainLayout.addComponent(reporteCrud);

        }
		
		setCompositionRoot(panel);
		
		setSizeFull();
	}

	
}
