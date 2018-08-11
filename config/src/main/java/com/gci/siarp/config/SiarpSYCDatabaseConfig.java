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

import com.gci.siarp.api.annotation.SiarpSYCDatabase;

/**
 * Clase de configuración de conexión a la base de datos de SYC. La
 * configuración se toma del archivo persistence.properties ubicado en el
 * directorio configurado con la variable de entorno SIARP_CONF.
 *
 * @author Alejandro Duarte
 */
@Configuration
//@PropertySource("file:${SIARP_CONF}/persistence.properties")
//@PropertySource("file:/home/sbt0/persistence.properties")
@PropertySource("file:/Users/leo/siarp_conf/persistence.properties")
@MapperScan(basePackages = "com.gci.siarp", annotationClass = SiarpSYCDatabase.class, sqlSessionFactoryRef = "siarpSYCSqlSessionFactoryBean")
public class SiarpSYCDatabaseConfig {

	@Value("${SYC.database.driver}")
	private String SYCDatabaseDriver;

	@Value("${SYC.database.url}")
	private String SYCDatabaseUrl;

	@Value("${SYC.database.username}")
	private String SYCDatabaseUsername;

	@Value("${SYC.database.password}")
	private String SYCDatabasePassword;

	@Bean
	public DataSource siarpSYCDataSource() {

		return new PooledDataSource(SYCDatabaseDriver, SYCDatabaseUrl, SYCDatabaseUsername, SYCDatabasePassword);
	}

	@Bean
	public DataSourceTransactionManager siarpSYCTransactionManager() {

		return new DataSourceTransactionManager(siarpSYCDataSource());
	}

	@Bean
	public SqlSessionFactoryBean siarpSYCSqlSessionFactoryBean() {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(siarpSYCDataSource());

		return factoryBean;
	}

	@Bean
	public SqlSession siarpSYCSqlSession() throws Exception {
		return new SqlSessionTemplate(siarpSYCSqlSessionFactoryBean().getObject());
	}

}
