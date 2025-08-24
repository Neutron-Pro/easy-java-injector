package fr.neutronstars.easy.java.injector.api.injection.scanner.filter;

public interface Filter {
    boolean accept(Class<?> clazz);
}
