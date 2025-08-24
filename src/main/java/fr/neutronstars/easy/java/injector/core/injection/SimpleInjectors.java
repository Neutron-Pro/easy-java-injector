package fr.neutronstars.easy.java.injector.core.injection;

import fr.neutronstars.easy.java.injector.api.EasyInjectorService;
import fr.neutronstars.easy.java.injector.api.configuration.Configuration;
import fr.neutronstars.easy.java.injector.api.injection.Injector;
import fr.neutronstars.easy.java.injector.api.injection.Injectors;

import java.util.*;

public class SimpleInjectors implements Injectors {
    private final Map<String, Injector> injectorMap = new HashMap<>();
    private final EasyInjectorService service;

    public SimpleInjectors(EasyInjectorService service) {
        this.service = service;
    }

    @Override
    public List<Injector> all() {
        return new ArrayList<>(this.injectorMap.values());
    }

    @Override
    public Injector of(String name) {
        return this.injectorMap.get(name);
    }

    @Override
    public Injector create(Configuration configuration) {
        return this.injectorMap.computeIfAbsent(
            configuration.name(),
            k -> new SimpleInjector(this.service, configuration)
        );
    }
}
