package fr.neutronstars.easy.java.injector.test.model.prototype;

import fr.neutronstars.easy.java.injector.api.annotation.Inject;
import fr.neutronstars.easy.java.injector.api.annotation.Prototype;

@Inject("error")
@Prototype
public class ErrorPrototype {
    public ErrorPrototype(String error) {}
}
