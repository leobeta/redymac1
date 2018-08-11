package com.gci.siarp.generales.domain.sap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gci.siarp.api.annotation.SiarpService;
import com.gci.siarp.generales.domain.sap.ArchivoAnulado.CamposAnulado;
import com.gci.siarp.generales.domain.sap.ArchivoError.CamposError;
import com.gci.siarp.generales.domain.sap.ArchivoPago.CamposPago;
import com.gci.siarp.generales.domain.sap.ArchivoPagoDetalle.CamposPagoDetalle;

@SiarpService
public class ArchivoSAPF<T> {

	public ArchivoSAPF() {

	}

	@SuppressWarnings("unchecked")
	public T crear(File File, Class<T> class1) {

		if (File == null) {
			return null;
		}

		if (class1 == ArchivoAnulado.class) {
			ArchivoAnulado AnuladoSAP = new ArchivoAnulado();
			AnuladoSAP.setNombre(File.getName());
			for (String[] dato : leerArchivo(File)) {
				if (dato.length == 4) {
					HashMap<CamposAnulado, String> registro = new HashMap<ArchivoAnulado.CamposAnulado, String>();
					registro.put(CamposAnulado.ID_SIARP, dato[0]);
					registro.put(CamposAnulado.DOC_SAP, dato[1]);
					registro.put(CamposAnulado.MOT_ANULACION, dato[2]);
					registro.put(CamposAnulado.DESC_ANULACION, dato[3]);

					AnuladoSAP.getRegistros().add(registro);
				}
			}
			return AnuladoSAP.getRegistros().size() > 0 ? (T) AnuladoSAP : null;
		}
		if (class1 == ArchivoError.class) {
			ArchivoError ErrorSap = new ArchivoError();
			ErrorSap.setNombre(File.getName());
			for (String[] dato : leerArchivo(File)) {
				if (dato.length == 3) {
					HashMap<CamposError, String> registro = new HashMap<CamposError, String>();
					registro.put(CamposError.ID_SIARP, dato[0]);
					registro.put(CamposError.COD_ERROR, dato[1]);
					registro.put(CamposError.DESC_ERROR, dato[2]);

					ErrorSap.getRegistros().add(registro);
				}
			}
			return ErrorSap.getRegistros().size() > 0 ? (T) ErrorSap : null;

		}

		if (class1 == ArchivoPago.class) {
			ArchivoPago PagoSap = new ArchivoPago();
			PagoSap.setNombre(File.getName());
			for (String[] dato : leerArchivo(File)) {
				if (dato.length == 6) {
					HashMap<CamposPago, String> registro = new HashMap<CamposPago, String>();
					registro.put(CamposPago.ID_SIARP, dato[0]);
					registro.put(CamposPago.FECHA_PAGO, dato[1]);
					registro.put(CamposPago.VALOR, dato[2]);
					registro.put(CamposPago.ORDEN_PAGO, dato[3]);
					registro.put(CamposPago.SOLICITUD_PAGO, dato[4]);
					registro.put(CamposPago.ID_ORIGEN_PAGO, dato[5]);
					PagoSap.getRegistros().add(registro);
				}
			}
			return PagoSap.getRegistros().size() > 0 ? (T) PagoSap : null;

		}

		if (class1 == ArchivoPagoDetalle.class) {
			ArchivoPagoDetalle PagoDetalleSap = new ArchivoPagoDetalle();
			PagoDetalleSap.setNombre(File.getName());
			for (String[] dato : leerArchivo(File)) {
				if (dato.length == 5) {
					HashMap<CamposPagoDetalle, String> registro = new HashMap<CamposPagoDetalle, String>();
					registro.put(CamposPagoDetalle.NUM_CUENTA, dato[0]);
					registro.put(CamposPagoDetalle.TIPO_RET, dato[1]);
					registro.put(CamposPagoDetalle.IND_RET, dato[2]);
					registro.put(CamposPagoDetalle.IND_IVA, dato[3]);
					registro.put(CamposPagoDetalle.VALOR_MOV, dato[4]);
					PagoDetalleSap.getRegistros().add(registro);
				}
			}

			return PagoDetalleSap.getRegistros().size() > 0 ? (T) PagoDetalleSap : null;

		}
		return null;
	}

	public List<String[]> leerArchivo(File file) {
		FileReader fr = null;
		BufferedReader br = null;
		List<String[]> lineas = new ArrayList<String[]>();
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {

				if ((linea.trim()).length() > 0) {
					lineas.add(linea.split(";"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return lineas;
	}
}
