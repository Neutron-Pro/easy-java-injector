package fr.neutronstars.easy.java.injector.api.injection.adapter;

public interface InstanceAdapter<T> extends Adapter<T>  {
    void adapt(T instance);
}
