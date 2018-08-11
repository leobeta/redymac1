package com.gci.siarp.api.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Anotación usada para marcar las clases de la capa de presentación
 * (componentes Vaadin). Los componentes marcados con "@SiarpUIComponent" serán
 * adicionalmente marcados con "@Component".
 *
 * Para agregar un componente navegable, use la anotación "@VaadinView".
 *
 * Scope por defecto: prototype.
 *
 * @author Alejandro Duarte
 */
@Component
@Scope("prototype")
@Retention(RetentionPolicy.RUNTIME)
public @interface SiarpUIComponent {

}
