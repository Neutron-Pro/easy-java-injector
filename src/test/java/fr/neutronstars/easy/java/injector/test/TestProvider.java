package fr.neutronstars.easy.java.injector.test;

import fr.neutronstars.easy.java.injector.api.injection.Injector;
import fr.neutronstars.easy.java.injector.test.extension.EasyInjectorUnitExtension;
import fr.neutronstars.easy.java.injector.test.extension.annotation.WithScan;
import fr.neutronstars.easy.java.injector.test.model.prototype.BarPrototype;
import fr.neutronstars.easy.java.injector.test.model.prototype.ErrorPrototype;
import fr.neutronstars.easy.java.injector.test.model.prototype.FooPrototype;
import fr.neutronstars.easy.java.injector.test.model.singleton.ErrorSingleton;
import fr.neutronstars.easy.java.injector.test.model.singleton.FooSingleton;
import fr.neutronstars.easy.java.injector.test.model.singleton.Service;
import fr.neutronstars.easy.java.injector.test.model.singleton.DemoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(EasyInjectorUnitExtension.class)
public class TestProvider {

    @Test
    void testSingletonProviderWithoutScan(Injector injector) {
        assertAll("Singleton providers without scan",
            () -> assertFalse(injector.providers().has(DemoService.class)),
            () -> assertNull(injector.providers().of(DemoService.class)),
            () -> assertFalse(injector.providers().has(Service.class)),
            () -> assertNull(injector.providers().of(Service.class)),
            () -> assertFalse(injector.providers().has(ErrorSingleton.class)),
            () -> assertNull(injector.providers().of(ErrorSingleton.class))
        );
    }

    @Test
    void testPrototypeProviderWithoutScan(Injector injector) {
        assertAll("Prototype providers without scan",
            () -> assertFalse(injector.providers().has(BarPrototype.class)),
            () -> assertNull(injector.providers().of(BarPrototype.class)),
            () -> assertFalse(injector.providers().has(ErrorPrototype.class)),
            () -> assertNull(injector.providers().of(ErrorPrototype.class))
        );
    }

    @Test
    @WithScan
    void testSingletonProviderWithScan(Injector injector) {
        assertAll("Singleton providers with scan",
            () -> assertFalse(injector.providers().has(DemoService.class)),
            () -> assertNull(injector.providers().of(DemoService.class)),
            () -> assertTrue(injector.providers().has(Service.class)),
            () -> assertNotNull(injector.providers().of(Service.class)),
            () -> assertFalse(injector.providers().has(ErrorSingleton.class)),
            () -> assertNull(injector.providers().of(ErrorSingleton.class))
        );
    }

    @Test
    @WithScan
    void testPrototypeProviderWithScan(Injector injector) {
        assertAll("Prototype providers with scan",
            () -> assertTrue(injector.providers().has(BarPrototype.class)),
            () -> assertNotNull(injector.providers().of(BarPrototype.class)),
            () -> assertFalse(injector.providers().has(ErrorPrototype.class)),
            () -> assertNull(injector.providers().of(ErrorPrototype.class))
        );
    }

    @Test
    @WithScan
    void testSingletonProviderInstance(Injector injector) {
        assertAll("Singleton instances",
            () -> assertTrue(injector.providers().has(Service.class)),
            () -> assertTrue(injector.providers().has(FooSingleton.class)),
            () -> assertSame(
                injector.providers().of(Service.class).of(),
                injector.providers().of(Service.class).of()
            ),
            () -> assertSame(
                injector.providers().of(FooSingleton.class).of(),
                injector.providers().of(FooSingleton.class).of()
            )
        );
    }

    @Test
    @WithScan
    void testPrototypeProviderInstance(Injector injector) {
        assertAll("Prototype instances",
            () -> assertTrue(injector.providers().has(BarPrototype.class)),
            () -> assertTrue(injector.providers().has(FooPrototype.class)),
            () -> assertNotSame(
                injector.providers().of(BarPrototype.class).of(),
                injector.providers().of(BarPrototype.class).of()
            ),
            () -> assertNotSame(
                injector.providers().of(FooPrototype.class).of(),
                injector.providers().of(FooPrototype.class).of()
            )
        );
    }
}
