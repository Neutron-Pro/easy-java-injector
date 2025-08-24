package fr.neutronstars.easy.java.injector.test.model.singleton;

import fr.neutronstars.easy.java.injector.api.annotation.Inject;
import fr.neutronstars.easy.java.injector.api.annotation.Singleton;

@Inject("test")
@Singleton
public class FooSingleton {
    private final Service service;

    public FooSingleton(Service service) {
        this.service = service;
    }

    public Service service() {
        return this.service;
    }
}
