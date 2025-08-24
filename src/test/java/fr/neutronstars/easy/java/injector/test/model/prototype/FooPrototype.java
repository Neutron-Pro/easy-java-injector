package fr.neutronstars.easy.java.injector.test.model.prototype;

import fr.neutronstars.easy.java.injector.api.annotation.Inject;
import fr.neutronstars.easy.java.injector.api.annotation.Prototype;
import fr.neutronstars.easy.java.injector.test.model.singleton.Service;

@Inject("test")
@Prototype
public class FooPrototype {
    private final Service service;

    public FooPrototype(Service service) {
        this.service = service;
    }

    public Service service() {
        return this.service;
    }
}
