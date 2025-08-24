package fr.neutronstars.easy.java.injector.api.injection.provider;

public interface Provider<T> {
    Class<T> type();

    String packageType();

    Class<? extends T> implType();

    T of();
}
