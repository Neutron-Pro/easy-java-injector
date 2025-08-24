package fr.neutronstars.easy.java.injector.test.extension.annotation;

import fr.neutronstars.easy.java.injector.api.injection.adapter.Adapter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WithAdapter {
    Class<? extends Adapter<?>>[] value();
}
