package fr.neutronstars.easy.java.injector.api.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Scope
public @interface Singleton {
}
