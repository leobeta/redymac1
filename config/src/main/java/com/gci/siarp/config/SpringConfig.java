package com.gci.siarp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Configuración de Spring Framework.
 * 
 * @author alejandro
 */
@Configuration
@EnableScheduling
@EnableAsync
public class SpringConfig {

	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {

		return new PropertySourcesPlaceholderConfigurer();
	}

}
