package fr.neutronstars.easy.java.injector.test;

import fr.neutronstars.easy.java.injector.api.EasyInjectorService;
import fr.neutronstars.easy.java.injector.api.annotation.Singleton;
import fr.neutronstars.easy.java.injector.api.exception.ScopeNotFoundException;
import fr.neutronstars.easy.java.injector.api.scope.Scope;
import fr.neutronstars.easy.java.injector.core.scope.SingletonScope;
import fr.neutronstars.easy.java.injector.test.extension.EasyInjectorUnitExtension;
import fr.neutronstars.easy.java.injector.test.model.scope.ErrorScope;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(EasyInjectorUnitExtension.class)
public class TestConfiguration {
    @Test
    void testScopeNotFound(EasyInjectorService injectorService) {
        assertAll(
            "Test scope not found!",
            () -> {
                assertThrows(
                    ScopeNotFoundException.class,
                    () -> injectorService.configurations().of("demo", "demo")
                );
                assertThrows(
                    ScopeNotFoundException.class,
                    () -> injectorService.configurations().of("demo", ErrorScope.class)
                );
                assertThrows(
                    ScopeNotFoundException.class,
                    () -> injectorService.configurations().of("demo", (Scope) null)
                );
            }
        );
    }

    @Test
    void testScopeFounded(EasyInjectorService injectorService) {
        assertAll(
            "Test scope founded!",
            () -> {
                assertDoesNotThrow(() -> injectorService.configurations().of("demo", "prototype"));
                assertDoesNotThrow(() -> injectorService.configurations().of("demo", Singleton.class));
                assertDoesNotThrow(() -> injectorService.configurations().of("demo", new SingletonScope()));
            }
        );
    }
}
