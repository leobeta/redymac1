package com.gci.siarp.generales.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import lombok.extern.log4j.Log4j;

import com.gci.siarp.api.annotation.SiarpService;
import com.gci.siarp.generales.domain.sap.ArchivoAnulado;
import com.gci.siarp.generales.domain.sap.ArchivoError;
import com.gci.siarp.generales.domain.sap.ArchivoPago;
import com.gci.siarp.generales.domain.sap.ArchivoSAPF;
import com.gci.siarp.generales.domain.sap.SAPCabecera;
import com.gci.siarp.generales.domain.sap.SAPDetalle;
import com.gci.siarp.generales.domain.sap.SAPIdentificacion;


@Log4j
@SiarpService
public class SAPService {

	public enum SAPDirectorio {
		PENDIENTES, ERRONEOS, PAGADOS, CARGADOS, ANULADOS, PROCESADOS_ANULADOS, PROCESADOS_PAGADOS, PROCESADOS_ERRONEOS
	}

	public boolean crearArchivoCM(SAPIdentificacion identificacion, SAPCabecera cabecera, List<SAPDetalle> detalles, String nomFile) {

		BufferedWriter buWr = null;
		PrintWriter printwr = null;

		try {
			String directorio = obtenerDirectorioSAP(SAPDirectorio.PENDIENTES);
			File file = new File(directorio + nomFile + ".txt");
			FileWriter fw = new FileWriter(file);
			buWr = new BufferedWriter(fw);
			printwr = new PrintWriter(buWr);
			printwr.println(identificacion.toString());
			if(!detalles.isEmpty()){
				for (SAPDetalle sapDetalle : detalles) {
					
					if(sapDetalle.getTipo().equals("A") && !(sapDetalle.getCuentaCont().equals("2357102499"))){
						printwr.println(cabecera.toString());					
					}
					printwr.println(sapDetalle.toString());
				}
				return true;				
			}else{
				return false;
			}
			
		} catch (Exception e) {
			log.error("Problemas SAP CM " + e.getClass());
			return false;
		} finally {

			if (printwr != null) {
				printwr.close();
			}
			if (buWr != null) {
				try {
					buWr.close();
				} catch (IOException e) {
					log.error("Error al cerrar el Buffer del archivo!");
				}
			}

		}
	}

	public List<ArchivoError> buscarArchivosErroneos(String PrefijoIDSIARP) {
		String directorio = obtenerDirectorioSAP(SAPDirectorio.ERRONEOS);
		List<ArchivoError> archivosError = null;
		File folder = new File(directorio);
		if (folder.exists()) {
			archivosError = new ArrayList<ArchivoError>();
			File[] archivosBase = folder.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith("_ERRORES") && name.startsWith(PrefijoIDSIARP);
				}
			});

			for (File file : archivosBase) {
				ArchivoError Erroneo = new ArchivoSAPF<ArchivoError>().crear(file, ArchivoError.class);

				if (Erroneo != null) {
					archivosError.add(Erroneo);
				}else{
					log.error("SAP ARCHIVOS ERRONEOS: El archivo [" + file.getName() + "] No se puede procesar");
				}
			}
			return archivosError;
		}else{
			throw new Error("No se puede tener acceso al directorio "+directorio); 
		}

	}

	public List<ArchivoPago> buscarArchivosPagados(String PrefijoIDSIARP) {
		String directorio = obtenerDirectorioSAP(SAPDirectorio.PAGADOS);
		List<ArchivoPago> archivosPagados = null;
		File folder = new File(directorio);
		if (folder.exists()) {
			archivosPagados = new ArrayList<ArchivoPago>();
			File[] archivosBase = folder.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if(name.endsWith("_Detalle.txt")){						
						return false;
					}
					return name.endsWith(".txt") && name.startsWith(PrefijoIDSIARP);
				}
			});

			for (File file : archivosBase) {
				ArchivoPago pagado = new ArchivoSAPF<ArchivoPago>().crear(file, ArchivoPago.class);
				if (pagado != null) {
					archivosPagados.add(pagado);
				}else{
					log.error("SAP ARCHIVOS PAGADOS: El archivo [" + file.getName() + "] No se puede procesar");
				}
			}
			return archivosPagados;
		}
		else{
			throw new Error("No se puede tener acceso al directorio "+directorio); 
		}
	
	}
	
	public List<ArchivoAnulado> buscarArchivosAnulados(String PrefijoIDSIARP) {
		String directorio = obtenerDirectorioSAP(SAPDirectorio.ANULADOS);
		List<ArchivoAnulado> archivosAnulados = null;
		File folder = new File(directorio);
		if (folder.exists()) {
			archivosAnulados = new ArrayList<ArchivoAnulado>();
			File[] archivosBase = folder.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.startsWith(PrefijoIDSIARP);
				}
			});
			for (File file : archivosBase) {
				ArchivoAnulado Anulado = new ArchivoSAPF<ArchivoAnulado>().crear(file, ArchivoAnulado.class);
				if (Anulado != null) {
					archivosAnulados.add(Anulado);
				}else{
					log.error("SAP ARCHIVOS ANULADOS: El archivo [" + file.getName() + "] No se puede procesar");
				}
			}
			return archivosAnulados;
		}else{
			throw new Error("No se puede tener acceso al directorio "+directorio); 
		}
	}
	
	public void moverArchivo(SAPDirectorio Origen, SAPDirectorio Destino, String nombreArchivo) throws IOException {
		Path FROM = Paths.get(obtenerDirectorioSAP(Origen) + "/" + nombreArchivo);
		Path TO = Paths.get(obtenerDirectorioSAP(Destino) + "/" + nombreArchivo);
		CopyOption[] options = new CopyOption[] { StandardCopyOption.ATOMIC_MOVE };
		Files.move(FROM, TO, options);
	}

	private String obtenerDirectorioSAP(SAPDirectorio tipo) {
		String siarpConfDirectory = System.getenv("SIARP_CONF");
		Properties properties = new Properties();
		String retorno = null;
		try {
			properties.load(new FileInputStream(siarpConfDirectory + "/siarp.properties"));
			switch (tipo) {
			case PAGADOS:
				retorno = properties.getProperty("siarp.SAPDirectorioPagados");
				break;
			case ANULADOS:
				retorno = properties.getProperty("siarp.SAPDirectorioAnulados");
				break;
			case ERRONEOS:
				retorno = properties.getProperty("siarp.SAPDirectorioErroneos");
				break;
			case PENDIENTES:
				retorno = properties.getProperty("siarp.SAPDirectorioPendientes");
				break;
			case CARGADOS:
				retorno = properties.getProperty("siarp.SAPDirectorioCargados");
				break;
			case PROCESADOS_ANULADOS:
				retorno = properties.getProperty("siarp.SAPProcesadosAnulados");
				break;
			case PROCESADOS_PAGADOS:
				retorno = properties.getProperty("siarp.SAPProcesadosPagados");
				break;
			case PROCESADOS_ERRONEOS:
				retorno = properties.getProperty("siarp.SAPProcesadosErroneos");
				break;
			default:
				retorno = null;
				break;
			}
			return retorno;
		} catch (FileNotFoundException e) {
			log.error("No se pudo establecer el valor de la variable de entorno 'SIARP_CONF'. Verifique que la variable ha sido definida en el sistema operativo.");
			return null;
		} catch (IOException e) {
			log.error("Error cargando el archivo 'siarp.properties'. Verifique que el archivo existe en el directorio de configuración.");
			return null;
		} catch (Exception e) {
			log.error("Problemas al cargar el URLCuentasMedicasSAP.");
			return null;
		}
	}

	public File buscarArchivo(String nombreArchivo, SAPDirectorio directorio) {

		File folder = new File(obtenerDirectorioSAP(directorio));
		File[] archivosDetalle = folder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.equals(nombreArchivo);
			}
		});
		return archivosDetalle.length > 0 ? archivosDetalle[0] : null;
	}

	public Long extraerIdSiarp(String nombre) {
		int index = nombre.replace("_", ".").indexOf(".");
		if (index != -1) {
			return Long.valueOf(nombre.substring(0, index));
		}
		return null;
	}
}
