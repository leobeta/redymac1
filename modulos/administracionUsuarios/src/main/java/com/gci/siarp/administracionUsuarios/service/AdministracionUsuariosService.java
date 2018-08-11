package com.gci.siarp.administracionUsuarios.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.gci.siarp.administracionUsuarios.dao.AdministracionUsuariosDao;
import com.gci.siarp.administracionUsuarios.domain.Usuarios;
import com.gci.siarp.api.annotation.SiarpService;

@SiarpService
public class AdministracionUsuariosService {
	
	@Autowired
	AdministracionUsuariosDao administracionUsuariosDao;

	public void creaUsuarios(Long idUsuario,String usuario,String email, String password,String primerNombre,
			String segundoNombre, String tipoDocumento, String documento,String estado, String primerApellido,
			String segundoApellido) {
		administracionUsuariosDao.crearUsuarios(idUsuario,usuario,email,password,primerNombre,
				segundoNombre,tipoDocumento,documento,estado,primerApellido,
				segundoApellido);
	}
	
	public Collection<Usuarios> buscarDatosDeUsuarios(){
		return administracionUsuariosDao.buscandoDatosUsuarios();
	}
	
	public void editaUsuarios(Long idUsuario,String usuario,String email, String password,String primerNombre,
			String segundoNombre, String tipoDocumento, String documento,String estado, String primerApellido,
			String segundoApellido) {
		administracionUsuariosDao.editarUsuarios(idUsuario,usuario,email,password,primerNombre,
				segundoNombre,tipoDocumento,documento,estado,primerApellido,
				segundoApellido);
	}
	
}
