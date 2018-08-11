package com.gci.siarp.webapp.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.log4j.Log4j;

import com.vaadin.server.ErrorEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@SuppressWarnings("serial")
@Log4j
public class SiarpErrorHandler implements ErrorHandler {

	@Override
	public void error(ErrorEvent event) {
		log.error("Error en la petición", event.getThrowable());

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = dateFormat.format(new Date());

		Notification notification = new Notification("Error",
				"Ha ocurrido un error en la petición. Por favor, <b>tome nota de la fecha de ocurrencia</b> e informe a los administradores del sistema. <br/><br/>Fecha de ocurrencia: <b>"
						+ currentDate + "<b>.",
				Type.ERROR_MESSAGE);
		notification.setHtmlContentAllowed(true);
		notification.show(Page.getCurrent());
	}

}
