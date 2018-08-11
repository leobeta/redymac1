package com.gci.siarp.api.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope("singleton")
@Transactional(SiarpSABASSDatabase.transactionManagerBeanName)
@Retention(RetentionPolicy.RUNTIME)
public @interface SiarpSABASSDatabase {

	/**
	 * Nombre del bean a usar como TransactionManager para la conexión definida
	 * en esta clase.
	 */
	public static final String transactionManagerBeanName = "siarpSABASSTransactionManager";

}
