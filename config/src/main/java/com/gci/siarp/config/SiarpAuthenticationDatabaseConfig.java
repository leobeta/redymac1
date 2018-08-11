package com.gci.siarp.config;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.gci.siarp.api.annotation.SiarpAuthenticationDatabase;

/**
 * Clase de configuración de conexión a la base de datos de SIARP. La
 * configuración se toma del archivo persistence.properties ubicado en el
 * directorio configurado con la variable de entorno SIARP_CONF.
 *
 * @author Alejandro Duarte
 */
@Configuration
//@PropertySource("file:${SIARP_CONF}/persistence.properties")
@PropertySource("file:/Users/leo/siarp_conf/persistence.properties")
@MapperScan(basePackages = "com.gci.siarp", annotationClass = SiarpAuthenticationDatabase.class, sqlSessionFactoryRef = "siarpAuthenticationSqlSessionFactoryBean")
public class SiarpAuthenticationDatabaseConfig {

	@Value("${authentication.database.driver}")
	private String authenticationDatabaseDriver;

	@Value("${authentication.database.url}")
	private String authenticationDatabaseUrl;

	@Value("${authentication.database.username}")
	private String authenticationDatabaseUsername;

	@Value("${authentication.database.password}")
	private String authenticationDatabasePassword;

	@Bean
	public DataSource siarpAuthenticationDataSource() {
		return new PooledDataSource(authenticationDatabaseDriver, authenticationDatabaseUrl, authenticationDatabaseUsername, authenticationDatabasePassword);
	}

	@Bean
	public DataSourceTransactionManager siarpAuthenticationTransactionManager() {

		return new DataSourceTransactionManager(siarpAuthenticationDataSource());
	}

	@Bean
	public SqlSessionFactoryBean siarpAuthenticationSqlSessionFactoryBean() {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(siarpAuthenticationDataSource());

		return factoryBean;
	}

	@Bean
	public SqlSession siarpAuthenticationSqlSession() throws Exception {
		return new SqlSessionTemplate(siarpAuthenticationSqlSessionFactoryBean().getObject());
	}

}
