package fr.neutronstars.easy.java.injector.core.injection;

import fr.neutronstars.easy.java.injector.api.injection.Cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

public class SimpleCache implements Cache {
    private final ConcurrentMap<Class<?>, Object> cacheMap = new ConcurrentHashMap<>();
    @Override
    public <T> T of(Class<T> clazz) {
        return clazz.cast(this.cacheMap.get(clazz));
    }

    @Override
    public <T> T of(Class<T> clazz, Supplier<T> factory) {
        return clazz.cast(this.cacheMap.computeIfAbsent(clazz, k -> factory.get()));
    }
}
