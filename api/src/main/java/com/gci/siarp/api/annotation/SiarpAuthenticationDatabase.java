package com.gci.siarp.api.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Anotación usada para marcar clases de la capa de persistencia (DAOs). Los
 * DAOs anotados con "@SiarpAuthenticationDatabase" se conectarán a la base de
 * datos de autenticación, serán marcados con "@Repository" y sus métodos serán
 * automáticamente transaccionales.
 *
 * Scope por defecto: singleton.
 *
 * @author Alejandro Duarte
 */
@Repository
@Scope("singleton")
@Transactional(SiarpAuthenticationDatabase.transactionManagerBeanName)
@Retention(RetentionPolicy.RUNTIME)
public @interface SiarpAuthenticationDatabase {

	/**
	 * Nombre del bean a usar como TransactionManager para la conexión definida
	 * en esta clase.
	 */
	public static final String transactionManagerBeanName = "siarpAuthenticationTransactionManager";

}
