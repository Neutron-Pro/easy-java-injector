package fr.neutronstars.easy.java.injector.test;

import fr.neutronstars.easy.java.injector.api.injection.Injector;
import fr.neutronstars.easy.java.injector.test.extension.AdapterUnitExtension;
import fr.neutronstars.easy.java.injector.test.extension.EasyInjectorUnitExtension;
import fr.neutronstars.easy.java.injector.test.extension.annotation.WithAdapter;
import fr.neutronstars.easy.java.injector.test.extension.annotation.WithScan;
import fr.neutronstars.easy.java.injector.test.model.adapter.TestPrototypeAdapter;
import fr.neutronstars.easy.java.injector.test.model.prototype.ErrorTestPrototype;
import fr.neutronstars.easy.java.injector.test.model.prototype.FooTestPrototype;
import fr.neutronstars.easy.java.injector.test.model.singleton.Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({EasyInjectorUnitExtension.class, AdapterUnitExtension.class})
public class TestAdapter {
    @Test
    @WithScan
    void testWithoutAdapter(Injector injector) {
        final Service service = injector.providers().of(Service.class).of();
        assertAll(
            "Test without Adapter",
            () -> {
                assertNotNull(service);
                assertEquals(0, injector.adapters().all().size());
                assertEquals(0, service.tests().size());
            }
        );
    }

    @Test
    @WithScan
    @WithAdapter(TestPrototypeAdapter.class)
    void testWithAdapter(Injector injector, TestPrototypeAdapter adapter) {
        final Service service = injector.providers().of(Service.class).of();
        assertAll(
            "Test with TestPrototypeAdapter",
            () -> {
                assertNotNull(service);
                assertEquals(1, injector.adapters().all().size());
                assertTrue(adapter.called() > 0);
                assertEquals(adapter.called(), service.tests().size());
                assertTrue(adapter.has(FooTestPrototype.class));
                assertFalse(adapter.has(ErrorTestPrototype.class));
            }
        );
    }
}
