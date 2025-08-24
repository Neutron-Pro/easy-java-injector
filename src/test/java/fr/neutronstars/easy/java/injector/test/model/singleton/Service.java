package fr.neutronstars.easy.java.injector.test.model.singleton;

import fr.neutronstars.easy.java.injector.test.model.prototype.TestPrototype;

import java.util.List;

public interface Service {
    List<TestPrototype> tests();

    void add(TestPrototype testPrototype);
}
