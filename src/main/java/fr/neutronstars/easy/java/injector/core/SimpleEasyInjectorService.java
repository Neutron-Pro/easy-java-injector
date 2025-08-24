package fr.neutronstars.easy.java.injector.core;

import fr.neutronstars.easy.java.injector.api.EasyInjectorService;
import fr.neutronstars.easy.java.injector.api.configuration.Configurations;
import fr.neutronstars.easy.java.injector.api.injection.Injectors;
import fr.neutronstars.easy.java.injector.api.scope.Scopes;
import fr.neutronstars.easy.java.injector.core.configuration.SimpleConfigurations;
import fr.neutronstars.easy.java.injector.core.injection.SimpleInjectors;
import fr.neutronstars.easy.java.injector.core.scope.PrototypeScope;
import fr.neutronstars.easy.java.injector.core.scope.SimpleScopes;
import fr.neutronstars.easy.java.injector.core.scope.SingletonScope;

public class SimpleEasyInjectorService implements EasyInjectorService {

    public static EasyInjectorService create() {
        return new SimpleEasyInjectorService();
    }

    public static EasyInjectorService createDefault() {
        final EasyInjectorService easyInjectorService = SimpleEasyInjectorService.create();
        easyInjectorService.scopes()
            .register(new SingletonScope())
            .register(new PrototypeScope());
        return easyInjectorService;
    }

    private final Configurations configurations = new SimpleConfigurations(this);
    private final Injectors injectors = new SimpleInjectors(this);
    private final Scopes scopes = new SimpleScopes();

    private SimpleEasyInjectorService() {}

    @Override
    public Injectors injectors() {
        return this.injectors;
    }

    @Override
    public Scopes scopes() {
        return this.scopes;
    }

    @Override
    public Configurations configurations() {
        return this.configurations;
    }
}
