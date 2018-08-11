package com.gci.siarp.config;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.gci.siarp.api.annotation.SiarpDatabase;

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
@MapperScan(basePackages = "com.gci.siarp", annotationClass = SiarpDatabase.class, sqlSessionFactoryRef = "siarpSqlSessionFactoryBean")
public class SiarpDatabaseConfig {

	@Value("${siarp.database.driver}")
	private String siarpDatabaseDriver;

	@Value("${siarp.database.url}")
	private String siarpDatabaseUrl;

	@Value("${siarp.database.username}")
	private String siarpDatabaseUsername;

	@Value("${siarp.database.password}")
	private String siarpDatabasePassword;
	
	public SiarpDatabaseConfig() {
		LogFactory.useLog4JLogging();
	}

	@Bean
	public DataSource siarpDataSource() {
		return new PooledDataSource(siarpDatabaseDriver, siarpDatabaseUrl, siarpDatabaseUsername, siarpDatabasePassword);
	}

	@Bean
	public DataSourceTransactionManager siarpTransactionManager() {

		return new DataSourceTransactionManager(siarpDataSource());
	}

	@Bean
	public SqlSessionFactoryBean siarpSqlSessionFactoryBean() {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(siarpDataSource());

		return factoryBean;
	}

	@Bean
	public SqlSession siarpSqlSession() throws Exception {
		return new SqlSessionTemplate(siarpSqlSessionFactoryBean().getObject());
	}

}
