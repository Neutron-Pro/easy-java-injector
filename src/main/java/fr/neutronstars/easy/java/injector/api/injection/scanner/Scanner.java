package fr.neutronstars.easy.java.injector.api.injection.scanner;

import fr.neutronstars.easy.java.injector.api.injection.scanner.filter.Filters;

public interface Scanner {
    Filters filters();

    void scan(ClassLoader classLoader, String packageRoot);

    ScannerResult find(String... packages);

    ScannerResult find(Class<?> assignableFrom, String... packages);
}
