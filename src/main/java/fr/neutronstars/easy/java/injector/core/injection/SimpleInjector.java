package fr.neutronstars.easy.java.injector.core.injection;

import fr.neutronstars.easy.java.injector.api.EasyInjectorService;
import fr.neutronstars.easy.java.injector.api.injection.Cache;
import fr.neutronstars.easy.java.injector.api.configuration.Configuration;
import fr.neutronstars.easy.java.injector.api.injection.Injector;
import fr.neutronstars.easy.java.injector.api.injection.adapter.Adapters;
import fr.neutronstars.easy.java.injector.api.injection.provider.Providers;
import fr.neutronstars.easy.java.injector.api.injection.scanner.Scanner;
import fr.neutronstars.easy.java.injector.core.injection.provider.SimpleProviders;
import fr.neutronstars.easy.java.injector.core.injection.scanner.SimpleScanner;

public class SimpleInjector implements Injector {
    private final InstanceFactory instanceFactory = new InstanceFactory(this);
    private final Scanner scanner = new SimpleScanner(this);
    private final Cache cache = new SimpleCache();
    private final Providers providers = new SimpleProviders();
    private final Adapters adapters = new SimpleAdapters();
    private final EasyInjectorService service;
    private final Configuration configuration;

    SimpleInjector(EasyInjectorService service, Configuration configuration) {
        this.service = service;
        this.configuration = configuration;
    }

    @Override
    public EasyInjectorService service() {
        return this.service;
    }

    @Override
    public Configuration configuration() {
        return this.configuration;
    }

    @Override
    public Cache cache() {
        return this.cache;
    }

    @Override
    public Providers providers() {
        return this.providers;
    }

    @Override
    public Adapters adapters() {
        return this.adapters;
    }

    @Override
    public Scanner scanner() {
        return this.scanner;
    }

    @Override
    public <T> T create(Class<T> clazz, Object... params) {
        return this.instanceFactory.create(clazz, params);
    }
}
