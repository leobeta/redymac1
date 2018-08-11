package com.gci.siarp.api.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Anotación usada para marcar las clases de la capa de servicios. Los servicios
 * marcados con "@SiarpService" serán adicionalmente marcados con "@Service".
 * Los métodos NO son transaccionales por defecto. Puede definir metodos
 * transaccionales usando "@Transactional(T)", donde T es el nombre del
 * TransactionalManager a usar. Por ejemplo, para la base de datos principal,
 * puede anotar el método con
 * "@Transactional(SiarpDatabase.transactionManagerBeanName)".
 *
 * Scope por defecto: singleton.
 *
 * @author Alejandro Duarte
 */
@Service
@Scope("singleton")
@Retention(RetentionPolicy.RUNTIME)
public @interface SiarpService {

}
