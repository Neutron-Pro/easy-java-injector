package fr.neutronstars.easy.java.injector.test.extension;

import fr.neutronstars.easy.java.injector.api.EasyInjectorService;
import fr.neutronstars.easy.java.injector.api.annotation.Singleton;
import fr.neutronstars.easy.java.injector.api.injection.Injector;
import fr.neutronstars.easy.java.injector.core.SimpleEasyInjectorService;
import fr.neutronstars.easy.java.injector.test.extension.annotation.WithScan;
import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Method;

public class EasyInjectorUnitExtension implements BeforeEachCallback, ParameterResolver {
    @Override
    public void beforeEach(ExtensionContext context) {
        final Object testInstance = context.getRequiredTestInstance();

        final EasyInjectorService injectorService = SimpleEasyInjectorService.createDefault();
        final Injector injector = injectorService.injectors()
            .create(injectorService.configurations().of("test", Singleton.class));

        final Method testMethod = context.getRequiredTestMethod();
        if (testMethod.isAnnotationPresent(WithScan.class)) {
            injector.scanner().scan(
                testInstance.getClass().getClassLoader(),
                "fr.neutronstars.easy.java.injector.test.model"
            );
        }

        final ExtensionContext.Store store = context.getStore(ExtensionContext.Namespace.GLOBAL);
        store.put("injectorService", injectorService);
        store.put("injector", injector);
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        final Class<?> type = parameterContext.getParameter().getType();
        return type == Injector.class || type == EasyInjectorService.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext context)
        throws ParameterResolutionException {
        final ExtensionContext.Store store = context.getStore(ExtensionContext.Namespace.GLOBAL);
        final Class<?> type = parameterContext.getParameter().getType();
        if (type == Injector.class) {
            return store.get("injector");
        }
        return store.get("injectorService");
    }
}
