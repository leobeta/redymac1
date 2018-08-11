package com.gci.siarp.webapp.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.apache.log4j.Logger;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

import ru.xpoft.vaadin.SpringVaadinServlet;

public interface WebConfig {

	@WebListener
	static class SpringContextLoaderListener extends ContextLoaderListener {

		public SpringContextLoaderListener() {
			configureLog4j();
		}

		private void configureLog4j() {
			try {
//				String siarpConfDirectory = "/home/sbt0";
//				String siarpConfDirectory = System.getenv("SIARP_CONF");
				String siarpConfDirectory = "/Users/leo/siarp_conf";
				
				if (siarpConfDirectory == null) {
					throw new RuntimeException(
							"No se pudo establecer el valor de la variable de entorno 'SIARP_CONF'. Verifique que la variable ha sido definida en el sistema operativo.");
				}

				Properties properties = new Properties();
				properties.load(new FileInputStream(siarpConfDirectory + "/siarp.properties"));

				String siarpLogsDirectory = properties.getProperty("siarp.logs.directory");

				if (siarpLogsDirectory == null) {
					throw new RuntimeException(
							"No se encontró la propiedad 'siarp.logs.directory' en el archivo 'siarp.properties'. Verifique que la propiedad existe en el archivo.");
				}

				System.setProperty("siarp.logs.directory", siarpLogsDirectory);

				SLF4JBridgeHandler.install();

				Logger.getLogger(this.getClass()).info("Usando configuración en " + siarpConfDirectory
						+ ". Puede configurar este directorio usando la variable de entorno SIARP_CONF.");

			} catch (IOException e) {
				throw new RuntimeException(
						"Error cargando el archivo 'siarp.properties'. Verifique que el archivo existe en el directorio de configuración.",
						e);
			}

		}

	}

	@WebListener
	static class SpringRequestContextListener extends RequestContextListener {
	}

	// Para que funcione jmeter hay que poner disable-xsrf-protection
	/*
	 * @WebServlet( initParams = {
	 * 
	 * @WebInitParam(name = "disable-xsrf-protection", value = "true"),
	 * 
	 * @WebInitParam(name = "beanName", value = "siarpUI") }, urlPatterns = {
	 * "/*", "/VAADIN/*" })
	 */

	@WebServlet(initParams = { @WebInitParam(name = "beanName", value = "siarpUI") }, urlPatterns = { "/*",
			"/VAADIN/*" })

	@SuppressWarnings("serial")
	static class MainServlet extends SpringVaadinServlet {
	}

}
