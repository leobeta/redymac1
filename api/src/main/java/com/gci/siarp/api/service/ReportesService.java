package com.gci.siarp.api.service;

public interface ReportesService {

	static class EstadoSolcitudReporte {

		private boolean error;
		private String descripcion;

		public boolean isError() {
			return error;
		}

		public void setError(boolean error) {
			this.error = error;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

	}

	EstadoSolcitudReporte solicitarReporte(Integer aiCodReporte, String asUsuario, String asSql, String asTipoRep, String asUsarSqlyDw, String asArg1, String asArg2, String asArg3, String asArg4, String asArg5, String asArg6, String asArg7, String asArg8, String asArg9, String asArg10);

	String traerSQL(Integer aiIdReporte);
}
