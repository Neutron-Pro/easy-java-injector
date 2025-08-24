package fr.neutronstars.easy.java.injector.test.extension;

import fr.neutronstars.easy.java.injector.api.injection.Injector;
import fr.neutronstars.easy.java.injector.api.injection.adapter.Adapter;
import fr.neutronstars.easy.java.injector.test.extension.annotation.WithAdapter;
import fr.neutronstars.easy.java.injector.test.extension.annotation.WithScan;
import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Method;

public class AdapterUnitExtension implements BeforeEachCallback, ParameterResolver {
    @Override
    public void beforeEach(ExtensionContext context) {
        final Method testMethod = context.getRequiredTestMethod();
        if (!testMethod.isAnnotationPresent(WithScan.class)) {
            throw new IllegalStateException(
                "Scan must be performed before initializing adapters for method "
                    + testMethod.getName()
            );
        }
        final Injector injector = (Injector) context.getStore(ExtensionContext.Namespace.GLOBAL).get("injector");

        final ExtensionContext.Store store = context.getStore(
            ExtensionContext.Namespace.create(this.getClass(), testMethod)
        );

        if (testMethod.isAnnotationPresent(WithAdapter.class)) {
            final WithAdapter withAdapter = testMethod.getAnnotation(WithAdapter.class);
            for (final Class<? extends Adapter<?>> adapterClazz : withAdapter.value()) {
                final Adapter<?> adapter = injector.create(adapterClazz);
                injector.adapters().add(adapter);
                store.put(adapterClazz.getName(), adapter);
            }
        }

        injector.scanner().find("fr.neutronstars.easy.java.injector.test.model").inject();
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext context)
        throws ParameterResolutionException {
        final ExtensionContext.Store store = context.getStore(
            ExtensionContext.Namespace.create(this.getClass(), context.getRequiredTestMethod())
        );
        return Adapter.class.isAssignableFrom(parameterContext.getParameter().getType())
            && store.get(parameterContext.getParameter().getType().getName()) != null;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext context)
        throws ParameterResolutionException {
        return context.getStore(
            ExtensionContext.Namespace.create(this.getClass(), context.getRequiredTestMethod())
        ).get(parameterContext.getParameter().getType().getName());
    }
}
