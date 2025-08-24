package fr.neutronstars.easy.java.injector.api;

import fr.neutronstars.easy.java.injector.api.configuration.Configurations;
import fr.neutronstars.easy.java.injector.api.injection.Injectors;
import fr.neutronstars.easy.java.injector.api.scope.Scopes;

public interface EasyInjectorService {
    Injectors injectors();

    Scopes scopes();

    Configurations configurations();
}
