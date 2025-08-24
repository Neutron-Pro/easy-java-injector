package fr.neutronstars.easy.java.injector.api.configuration;

import fr.neutronstars.easy.java.injector.api.scope.Scope;

public interface Configuration {
    String name();

    Scope scope();
}
