package fr.neutronstars.easy.java.injector.test.model.singleton;

import fr.neutronstars.easy.java.injector.api.annotation.Inject;
import fr.neutronstars.easy.java.injector.api.annotation.Provider;
import fr.neutronstars.easy.java.injector.api.annotation.Singleton;
import fr.neutronstars.easy.java.injector.test.model.prototype.TestPrototype;

import java.util.ArrayList;
import java.util.List;

@Inject("test")
@Provider(Service.class)
@Singleton
public class DemoService implements Service {
    private final List<TestPrototype> tests = new ArrayList<>();

    @Override
    public List<TestPrototype> tests() {
        return this.tests;
    }

    @Override
    public void add(TestPrototype testPrototype) {
        this.tests.add(testPrototype);
    }
}
