package fr.neutronstars.easy.java.injector.test.model.adapter;

import fr.neutronstars.easy.java.injector.api.injection.adapter.InstanceAdapter;
import fr.neutronstars.easy.java.injector.test.model.prototype.TestPrototype;
import fr.neutronstars.easy.java.injector.test.model.singleton.Service;

import java.util.HashSet;
import java.util.Set;

public class TestPrototypeAdapter implements InstanceAdapter<TestPrototype> {
    private final Set<Class<? extends TestPrototype>> adaptedClasses = new HashSet<>();
    private int called = 0;

    private final Service service;

    public TestPrototypeAdapter(Service service) {
        this.service = service;
    }

    public boolean has(Class<? extends TestPrototype> clazz) {
        return this.adaptedClasses.contains(clazz);
    }

    @Override
    public Class<TestPrototype> type() {
        return TestPrototype.class;
    }

    public int called() {
        return this.called;
    }

    @Override
    public void adapt(TestPrototype instance) {
        this.called++;
        this.service.add(instance);
        this.adaptedClasses.add(instance.getClass());
    }
}
