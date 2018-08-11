/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gci.siarp.auth.domain;

import java.util.ArrayList;
import lombok.Data;

/**
 *
 * @author cmoreno
 */
@Data
public class OpcionesMenuUsuario {
    Integer codigoAplicacion;
	
    Integer codigoOpcion;
    
    String action;
    
    ArrayList<String> tree;

}
