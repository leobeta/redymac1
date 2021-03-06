package com.gci.siarp.generales.ui;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.function.Consumer;

import com.gci.siarp.api.ui.SiarpTheme;
import com.gci.siarp.generales.domain.IBC;
import com.gci.siarp.generales.service.IBCService;
import com.gci.siarp.generales.service.UtilidadesFechas;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Notification.Type;

public class ObjEditIBC extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private HorizontalLayout mainLayout;
	@AutoGenerated
	private TextField tF_newDias;
	@AutoGenerated
	private TextField tF_newValor;
	@AutoGenerated
	private TextField tF_dias;
	@AutoGenerated
	private TextField tF_periodo;
	@AutoGenerated
	private TextField tF_valor;
	@AutoGenerated
	private CheckBox chk_sel;
	private IBC iIbc;
	private String isItemStatus;
	ObjEditIBC iMe;
	private Consumer<ObjEditIBC> iCambiaFila;
	private IBCService iIBCService;
	private Date idFechaSini;
	private String isTipoSini;
	private UtilidadesFechas iUtilsFechas=new UtilidadesFechas();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public ObjEditIBC(IBC aIbc,Boolean abnNew,Consumer<ObjEditIBC> aCambiaFila,IBCService aIBCServ,Date adFechaSini,String asTipoSini) {
		idFechaSini=adFechaSini;
		isTipoSini=asTipoSini;
		iIBCService=aIBCServ;
		iCambiaFila=aCambiaFila;
		buildMainLayout();
		setCompositionRoot(mainLayout);
		iIbc=aIbc;
		iniciar();
		if (abnNew){
			isItemStatus="New";
		}else
			isItemStatus="NotModified";
		if (!abnNew) tF_periodo.setEnabled(false);
		iMe=this;
	}
	private void iniciar(){
		tF_dias.setStyleName(SiarpTheme.TEXTFIELD_ALIGN_CENTER);
		tF_newDias.setStyleName(SiarpTheme.TEXTFIELD_ALIGN_CENTER);
		tF_newValor.setStyleName(SiarpTheme.TEXTFIELD_ALIGN_RIGHT);
		tF_periodo.setStyleName(SiarpTheme.TEXTFIELD_ALIGN_CENTER);
		tF_valor.setStyleName(SiarpTheme.TEXTFIELD_ALIGN_RIGHT);
		
		
		tF_valor.setEnabled(false);
		tF_dias.setEnabled(false);
		
		chk_sel.addStyleName(SiarpTheme.CHECKBOX_LARGE);
		String s;
		
		if (iIbc.getSeleccionado().equals("1"))
			chk_sel.setValue(true);
		else
			chk_sel.setValue(false);
		
		if (iIbc.getValor()!=null){
			s= String.format("%(,.2f",iIbc.getValor());
			tF_valor.setValue(s);
		}
		if (iIbc.getDiasCotizados()!=null) tF_dias.setValue(iIbc.getDiasCotizados().toString());
		if (iIbc.getPeriodo()!=null) tF_periodo.setValue(new SimpleDateFormat("yyyy-MM").format(iIbc.getPeriodo()));
		if (iIbc.getValorVAP()!=null){
			s= String.format("%(,.2f",iIbc.getValorVAP());
			tF_newValor.setValue(s);
		}
		if (iIbc.getDiasNuevos()!=null) tF_newDias.setValue(iIbc.getDiasNuevos().toString());
		
		chk_sel.addValueChangeListener(this::eventCambiaChk);
		tF_newDias.addValueChangeListener(this::eventCambiaNewDias);
		tF_newValor.addValueChangeListener(this::eventCambiaNewValor);
		tF_periodo.addValueChangeListener(this::eventCambiaNewPeriodo);
		
		chk_sel.addFocusListener(new FocusListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void focus(FocusEvent event) {
				iCambiaFila.accept(iMe);
			}
			
		});
		tF_newDias.addFocusListener(new FocusListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void focus(FocusEvent event) {
				iCambiaFila.accept(iMe);
			}
		});
		tF_newValor.addFocusListener(new FocusListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void focus(FocusEvent event) {
				iCambiaFila.accept(iMe);
			}
		});
		tF_periodo.addFocusListener(new FocusListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void focus(FocusEvent event) {
				iCambiaFila.accept(iMe);
			}
		});
		
		
		
		chk_sel.setImmediate(true);
		tF_newDias.setImmediate(true);
		tF_newValor.setImmediate(true);
		tF_periodo.setImmediate(true);
	}
	
	public void update(String asOrigen){
		if (isItemStatus.equals("NotModified")) return;
		if (isItemStatus.equals("New") || isItemStatus.equals("NewModified") ){
			if (iIbc.getPeriodo()==null || iIbc.getDiasCotizados()==null){
				Notification.show("Atencion", "Debe digitar todos los datos del IBC", Type.WARNING_MESSAGE);
				tF_newDias.focus();
				return;
			}
			iIBCService.crearIBC(iIbc,asOrigen);
		}else{//Modified
			iIBCService.actualizarIBC(iIbc,asOrigen);
			//update.accept(iEscolaridad);
		}
	}
	
	private void eventCambiaChk(ValueChangeEvent event){
		if (chk_sel.getValue())
			iIbc.setSeleccionado("1");
		else
			iIbc.setSeleccionado("0");
		cambiarEstado();
	}
	
	public Boolean isValid(){
		if (iIbc.getPeriodo()==null || iIbc.getDiasCotizados()==null || (iIbc.getValor()==null && iIbc.getValorVAP()==null)) return false; 
		return true;
	}
	

	private void eventCambiaNewDias(ValueChangeEvent event){
		if (!tF_newDias.getValue().equals("")){
			try{
				iIbc.setDiasNuevos(Integer.parseInt(tF_newDias.getValue()));
				if (iIbc.getDiasNuevos()>30 || iIbc.getDiasNuevos()<0){
					Notification.show("ERROR","El valor digitado no es válido",Type.WARNING_MESSAGE);
					iIbc.setDiasNuevos(null);
					tF_newDias.focus();
					tF_newDias.setValue("");
					return;
				}
				if (isItemStatus.equals("New") || isItemStatus.equals("NewModified"))
					iIbc.setDiasCotizados(iIbc.getDiasNuevos());
			}catch (NumberFormatException nfe){
				Notification.show("ERROR","El valor digitado no es válido",Type.WARNING_MESSAGE);
				iIbc.setDiasNuevos(null);
				tF_newDias.focus();
				tF_newDias.setValue("");
				return;
			}
		}else
			iIbc.setDiasNuevos(null);
		cambiarEstado();
	}
	
	private void eventCambiaNewPeriodo(ValueChangeEvent event){
		if (!tF_periodo.getValue().equals("")){
	
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {

				Date date = formatter.parse(tF_periodo.getValue()+"-01");
				if (date.compareTo(idFechaSini)>=0){
					Notification.show("ERROR EN FECHA","El periodo no puede ser mayor a la fecha del siniestro",Type.WARNING_MESSAGE);
					tF_periodo.setValue("");
					tF_periodo.focus();
					return;
				}
				if (isTipoSini.equals("AT")){
					LocalDate ldFechaSini=iUtilsFechas.convertirDateLD(idFechaSini);
					if (date.compareTo(iUtilsFechas.convertirLD(ldFechaSini.minusMonths(7)))<0){
						Notification.show("ATENCIÓN","Para un siniestro de tipo AL no se pueden utilizar periodos anteriores a 6 meses",Type.WARNING_MESSAGE);
						tF_periodo.setValue("");
						tF_periodo.focus();
						return;
					}
				}
				
				
				iIbc.setPeriodo(date);
				tF_periodo.setValue(new SimpleDateFormat("yyyy-MM").format(date));
				
			} catch (ParseException e) {
				Notification.show("ERROR EN FECHA",e.getMessage(),Type.WARNING_MESSAGE);
				tF_periodo.setValue("");
				tF_periodo.focus();
				return;
			}
		
		}else
			iIbc.setPeriodo(null);
		cambiarEstado();
	}
	
	
	private void eventCambiaNewValor(ValueChangeEvent event){
		if (!tF_newValor.getValue().equals("")){
			try {
				iIbc.setValorVAP(new BigDecimal(tF_newValor.getValue()));
			}catch (NumberFormatException nfe){
				Notification.show("ERROR","Valor digitado no válido",Type.WARNING_MESSAGE);
				tF_newValor.setValue("");
				tF_newValor.focus();
				return;
			}
		}else
			iIbc.setValorVAP(null);
		cambiarEstado();
	}
	
	private void cambiarEstado(){
		 if (isItemStatus.equals("New") || isItemStatus.equals("NewModified")){
	        	isItemStatus="NewModified";
	        }else
	        	isItemStatus="Modified";
	}
	
	public IBC getIBC(){
		return iIbc;
	}
	
	public void focus(){
		tF_newValor.focus();
	}
	public String getItemStatus(){
		return isItemStatus;
	}
	
	public CheckBox getCheck(){
		return chk_sel;
	}

	@AutoGenerated
	private HorizontalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new HorizontalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("27px");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("27px");
		
		// chk_sel
		chk_sel = new CheckBox();
		chk_sel.setCaption("   ");
		chk_sel.setImmediate(false);
		chk_sel.setWidth("18px");
		chk_sel.setHeight("-1px");
		mainLayout.addComponent(chk_sel);
		mainLayout.setComponentAlignment(chk_sel, new Alignment(34));
		
		// tF_valor
		tF_valor = new TextField();
		tF_valor.setImmediate(false);
		tF_valor.setWidth("101px");
		tF_valor.setHeight("-1px");
		mainLayout.addComponent(tF_valor);
		mainLayout.setComponentAlignment(tF_valor, new Alignment(33));
		
		// tF_periodo
		tF_periodo = new TextField();
		tF_periodo.setImmediate(false);
		tF_periodo.setWidth("108px");
		tF_periodo.setHeight("-1px");
		mainLayout.addComponent(tF_periodo);
		mainLayout.setComponentAlignment(tF_periodo, new Alignment(33));
		
		// tF_dias
		tF_dias = new TextField();
		tF_dias.setImmediate(false);
		tF_dias.setWidth("51px");
		tF_dias.setHeight("-1px");
		mainLayout.addComponent(tF_dias);
		mainLayout.setComponentAlignment(tF_dias, new Alignment(33));
		
		// tF_newValor
		tF_newValor = new TextField();
		tF_newValor.setImmediate(false);
		tF_newValor.setWidth("101px");
		tF_newValor.setHeight("-1px");
		mainLayout.addComponent(tF_newValor);
		mainLayout.setComponentAlignment(tF_newValor, new Alignment(33));
		
		// tF_newDias
		tF_newDias = new TextField();
		tF_newDias.setImmediate(false);
		tF_newDias.setWidth("58px");
		tF_newDias.setHeight("-1px");
		mainLayout.addComponent(tF_newDias);
		mainLayout.setComponentAlignment(tF_newDias, new Alignment(33));
		
		return mainLayout;
	}

}
