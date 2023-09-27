package ku.cs.services;

public interface DataSource<T> {
    T readData();
    public void writeData(T t);
}
