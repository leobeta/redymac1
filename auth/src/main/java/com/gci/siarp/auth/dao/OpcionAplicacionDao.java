/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gci.siarp.auth.dao;

import com.gci.siarp.api.annotation.SiarpDatabase;
import com.gci.siarp.auth.domain.OpcionAplicacion;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author cmoreno
 */
@SiarpDatabase
public interface OpcionAplicacionDao {
       
    @Select("select prg, cap, actcant, interfas from usuarios where usuario = @{idUsuario}")
    ArrayList<OpcionAplicacion> opcionesMenuUsuario (@Param("idUsuario") String idUsuario);
    
}
