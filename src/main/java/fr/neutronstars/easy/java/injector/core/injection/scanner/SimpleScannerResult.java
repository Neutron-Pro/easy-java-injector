package fr.neutronstars.easy.java.injector.core.injection.scanner;

import fr.neutronstars.easy.java.injector.api.injection.Injector;
import fr.neutronstars.easy.java.injector.api.injection.adapter.ClassAdapter;
import fr.neutronstars.easy.java.injector.api.injection.adapter.InstanceAdapter;
import fr.neutronstars.easy.java.injector.api.injection.provider.Provider;
import fr.neutronstars.easy.java.injector.api.injection.scanner.ScannerResult;

import java.util.ArrayList;
import java.util.List;

public class SimpleScannerResult implements ScannerResult {
    private final Injector injector;
    private final List<Provider<?>> providers;

    protected SimpleScannerResult(Injector injector, List<Provider<?>> providers) {
        this.injector = injector;
        this.providers = providers;
    }

    @Override
    public List<Provider<?>> of() {
        return new ArrayList<>(this.providers);
    }

    @Override
    public void inject() {
        for (final Provider<?> provider : this.providers) {
            this.injector.adapters().find(provider.type())
                .forEach(adapter -> {
                    if (adapter instanceof ClassAdapter) {
                        ((ClassAdapter<Object>) adapter).accept(provider.implType());
                        return;
                    }
                    if (adapter instanceof InstanceAdapter) {
                        ((InstanceAdapter<Object>) adapter).adapt(provider.of());
                    }
                });
        }
    }
}
