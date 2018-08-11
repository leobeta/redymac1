package com.gci.siarp.generales.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Alejandro Duarte
 */
@Data
public class IBC {

	@NotNull
	private Integer idSolicitud;
	private Date periodo;
	private Integer diasCotizados;
	private BigDecimal valor;
	private String seleccionado;
	private BigDecimal valorMensualizado;
	private BigDecimal valorVAP;
	private Integer diasNuevos;
	private String manual;
	private Date fechaPago;
}
