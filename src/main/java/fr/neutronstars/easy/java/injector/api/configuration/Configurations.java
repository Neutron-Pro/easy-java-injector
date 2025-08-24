package fr.neutronstars.easy.java.injector.api.configuration;

import fr.neutronstars.easy.java.injector.api.scope.Scope;

import java.lang.annotation.Annotation;

public interface Configurations {
    Configuration of(String name, String scope);

    Configuration of(String name, Class<? extends Annotation> scope);

    Configuration of(String name, Scope scope);
}
