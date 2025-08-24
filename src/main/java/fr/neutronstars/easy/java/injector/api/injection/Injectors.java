package fr.neutronstars.easy.java.injector.api.injection;

import fr.neutronstars.easy.java.injector.api.configuration.Configuration;

import java.util.List;

public interface Injectors {
    List<Injector> all();

    Injector of(String name);

    Injector create(Configuration configuration);
}
