package fr.neutronstars.easy.java.injector.test;

import fr.neutronstars.easy.java.injector.api.exception.InjectorConstructorException;
import fr.neutronstars.easy.java.injector.api.injection.Injector;
import fr.neutronstars.easy.java.injector.test.extension.EasyInjectorUnitExtension;
import fr.neutronstars.easy.java.injector.test.extension.annotation.WithScan;
import fr.neutronstars.easy.java.injector.test.model.prototype.ErrorTestPrototype;
import fr.neutronstars.easy.java.injector.test.model.prototype.FooPrototype;
import fr.neutronstars.easy.java.injector.test.model.singleton.Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({EasyInjectorUnitExtension.class})
public class TestPrototypeInstance {
    @Test
    @WithScan
    void testPrototypeParameter(Injector injector) {
        final FooPrototype prototype = injector.providers().of(FooPrototype.class).of();
        assertAll(
            "Test Foo prototype parameter",
            () ->  {
                assertNotNull(prototype);
                assertSame(prototype.service(), injector.providers().of(Service.class).of());
            }
        );
    }

    @Test
    void testPrototypeInstanceError(Injector injector) {
        assertThrowsExactly(InjectorConstructorException.class, () -> injector.create(ErrorTestPrototype.class));
    }
}
