package fr.neutronstars.easy.java.injector.test.model.scope;

import fr.neutronstars.easy.java.injector.api.annotation.Scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Scope
public @interface ErrorScope {
}
