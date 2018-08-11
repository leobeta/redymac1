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

import com.gci.siarp.api.annotation.SiarpSABASSDatabase;

/**
 * Clase de configuración de conexión a la base de datos de SIARP. La
 * configuración se toma del archivo persistence.properties ubicado en el
 * directorio configurado con la variable de entorno SIARP_CONF.
 *
 * @author Alejandro Duarte
 */
@Configuration
//@PropertySource("file:${SIARP_CONF}/persistence.properties")
//@PropertySource("file:/home/sbt0/persistence.properties")
@PropertySource("file:/Users/leo/siarp_conf/persistence.properties")
@MapperScan(basePackages = "com.gci.siarp", annotationClass = SiarpSABASSDatabase.class, sqlSessionFactoryRef = "siarpSABASSSqlSessionFactoryBean")
public class SiarpSABASSDatabaseConfig {

	@Value("${SABASS.database.driver}")
	private String SABASSDatabaseDriver;

	@Value("${SABASS.database.url}")
	private String SABASSDatabaseUrl;

	@Value("${SABASS.database.username}")
	private String SABASSDatabaseUsername;

	@Value("${SABASS.database.password}")
	private String SABASSDatabasePassword;

	@Bean
	public DataSource siarpSABASSDataSource() {

		return new PooledDataSource(SABASSDatabaseDriver, SABASSDatabaseUrl, SABASSDatabaseUsername, SABASSDatabasePassword);
	}

	@Bean
	public DataSourceTransactionManager siarpSABASSTransactionManager() {

		return new DataSourceTransactionManager(siarpSABASSDataSource());
	}

	@Bean
	public SqlSessionFactoryBean siarpSABASSSqlSessionFactoryBean() {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(siarpSABASSDataSource());

		return factoryBean;
	}

	@Bean
	public SqlSession siarpSABASSSqlSession() throws Exception {
		return new SqlSessionTemplate(siarpSABASSSqlSessionFactoryBean().getObject());
	}

}
