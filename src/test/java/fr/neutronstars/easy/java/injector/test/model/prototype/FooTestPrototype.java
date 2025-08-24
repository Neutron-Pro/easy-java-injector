package fr.neutronstars.easy.java.injector.test.model.prototype;

import fr.neutronstars.easy.java.injector.api.annotation.Inject;
import fr.neutronstars.easy.java.injector.api.annotation.Prototype;
import fr.neutronstars.easy.java.injector.test.model.singleton.Service;

@Inject("test")
@Prototype
public class FooTestPrototype implements TestPrototype {
    private final Service service;

    public FooTestPrototype(Service service) {
        this.service = service;
    }

    @Override
    public Service service() {
        return this.service;
    }
}
