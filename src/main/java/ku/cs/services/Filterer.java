package ku.cs.services;

public interface Filterer<T> {
    boolean filter(T t);
}