package com.gci.siarp.api.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Anotación usada para marcar clases de la capa de persistencia (DAOs). Los
 * DAOs anotados con "@SiarpDatabase" se conectarán a la base de datos principal
 * de SIARP, serán marcados con "@Repository" y sus métodos serán
 * automáticamente transaccionales.
 *
 * Scope por defecto: singleton.
 *
 * @author Alejandro Duarte
 */
@Repository
@Scope("singleton")
@Transactional(SiarpDatabase.transactionManagerBeanName)
@Retention(RetentionPolicy.RUNTIME)
public @interface SiarpDatabase {

	/**
	 * Nombre del bean a usar como TransactionManager para la conexión definida
	 * en esta clase.
	 */
	public static final String transactionManagerBeanName = "siarpTransactionManager";

}
