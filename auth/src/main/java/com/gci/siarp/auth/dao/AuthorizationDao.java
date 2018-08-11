package com.gci.siarp.auth.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.gci.siarp.api.annotation.SiarpDatabase;

/**
 *
 * @author alejandro
 */
@SiarpDatabase
public interface AuthorizationDao {

	/**
	 * Verifica si el usuario tiene acceso a una URL.
	 * 
	 * @param username
	 *            Login o nombre de usuario
	 * @param url
	 *            URL a acceder
	 * @return true si el usuario especificado tiene autorización de acceso a la
	 *         URL especificada. false en caso contrario.
	 */
	@Select("SELECT case when count (*)> 0 then 1 else 0 end "
			+ " FROM (((pecono_reservas.dbo.ROLES_OPCIONAL ROLES_OPCIONAL INNER JOIN pecono_reservas.dbo.ROLES ROLES  ON (ROLES_OPCIONAL.ROL_CODIGO_APLICACION = ROLES.CODIGO_APLICACION) AND (ROLES_OPCIONAL.CODIGO_ROL = ROLES.CODIGO_ROL)) "
			+ " INNER JOIN pecono_reservas.dbo.OPCION_APLICACION OPCION_APLICACION  ON (ROLES_OPCIONAL.CODIGO_OPCION = OPCION_APLICACION.CODIGO_OPCION) AND (ROLES_OPCIONAL.ROL_CODIGO_APLICACION = OPCION_APLICACION.CODIGO_APLICACION)) "
			+ " INNER JOIN pecono_reservas.dbo.USUARIOS_ROLES USUARIOS_ROLES ON (USUARIOS_ROLES.CODIGO_APLICACION = ROLES.CODIGO_APLICACION) AND (USUARIOS_ROLES.CODIGO_ROL = ROLES.CODIGO_ROL)) "
			+ " INNER JOIN pecono_reservas.dbo.USUARIOS_GEN USUARIOS_GEN ON (USUARIOS_ROLES.CODIGO_USUARIO = USUARIOS_GEN.CODIGO_USUARIO) "
			+ " WHERE USUARIOS_ROLES.CODIGO_USUARIO = #{username} AND OPCION_APLICACION.LINK = #{url} AND USUARIOS_GEN.ESTADO_aud = 'A'  AND ROLES.ESTADO_aud = 'A' AND ROLES_OPCIONAL.ESTADO_aud = 'A' AND OPCION_APLICACION.ESTADO_AUD = 'A'")
	
	boolean userCanAccess(@Param("username") String username, @Param("url") String url);
}
