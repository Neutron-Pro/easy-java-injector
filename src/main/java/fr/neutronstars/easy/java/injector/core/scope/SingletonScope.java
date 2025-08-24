package fr.neutronstars.easy.java.injector.core.scope;

import fr.neutronstars.easy.java.injector.api.annotation.Singleton;
import fr.neutronstars.easy.java.injector.api.injection.Injector;
import fr.neutronstars.easy.java.injector.api.injection.provider.Provider;
import fr.neutronstars.easy.java.injector.api.scope.Scope;

import java.lang.annotation.Annotation;

public class SingletonScope implements Scope {
    @Override
    public String name() {
        return "singleton";
    }

    @Override
    public Class<? extends Annotation> annotation() {
        return Singleton.class;
    }

    @Override
    public <T> T of(Injector injector, Provider<T> provider) {
        return injector.cache().of(provider.type(), () -> injector.create(provider.implType()));
    }
}
