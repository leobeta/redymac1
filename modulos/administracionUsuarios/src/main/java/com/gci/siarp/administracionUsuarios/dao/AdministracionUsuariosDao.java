package com.gci.siarp.administracionUsuarios.dao;

import java.util.Collection;

import org.apache.ibatis.annotations.Param;

import com.gci.siarp.administracionUsuarios.domain.Usuarios;
import com.gci.siarp.api.annotation.SiarpDatabase;

@SiarpDatabase
public interface AdministracionUsuariosDao {

	public void crearUsuarios(@Param("idUsuario") Long idUsuario,
			  @Param("usuario") String usuario,
			  @Param("email") String email,@Param("password") String password,
			  @Param("primerNombre") String primerNombre,@Param("segundoNombre") String segundoNombre,
			  @Param("tipoDocumento") String tipoDocumento,@Param("documento") String documento,
			  @Param("estado") String estado,@Param("primerApellido") String primerApellido,
			  @Param("segundoApellido") String segundoApellido);
	
	public Collection<Usuarios> buscandoDatosUsuarios();
	
	public void editarUsuarios(@Param("idUsuario") Long idUsuario,
			  @Param("usuario") String usuario,
			  @Param("email") String email,@Param("password") String password,
			  @Param("primerNombre") String primerNombre,@Param("segundoNombre") String segundoNombre,
			  @Param("tipoDocumento") String tipoDocumento,@Param("documento") String documento,
			  @Param("estado") String estado,@Param("primerApellido") String primerApellido,
			  @Param("segundoApellido") String segundoApellido);
}
