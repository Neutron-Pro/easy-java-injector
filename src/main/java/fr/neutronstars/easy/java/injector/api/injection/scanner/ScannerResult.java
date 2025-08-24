package fr.neutronstars.easy.java.injector.api.injection.scanner;

import fr.neutronstars.easy.java.injector.api.injection.provider.Provider;

import java.util.List;

public interface ScannerResult {
    List<Provider<?>> of();

    void inject();
}
