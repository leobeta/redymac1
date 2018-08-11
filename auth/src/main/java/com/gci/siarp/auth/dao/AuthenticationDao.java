package com.gci.siarp.auth.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.gci.siarp.api.annotation.SiarpAuthenticationDatabase;


/**
 *
 * @author alejandro
 */
@SiarpAuthenticationDatabase
public interface AuthenticationDao {
	
//	@Select("select case when (hash(#{p},'md5') = convert(varchar(32), decrypt(password, 'arp_iss')) ) or (convert(varchar(32), decrypt(password, 'arp_iss'))=#{p}) then 1 else 0 end from autentica  where login_arp = #{u}")
	@Select("SELECT case when usuario is null then 0 else 1 end FROM dbo.USUARIOS WHERE usuario = #{u} AND password = #{p} and estado = 1")
	Boolean isAuthentic(@Param("u") String username, @Param("p") String password);
	
}
