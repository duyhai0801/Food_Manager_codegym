package Service;

import java.util.List;

public interface IService<T> {
    void add(T t);

    boolean delete(long id);

    boolean update(long id, T t);

    List<T> getAll();

    T findById(long id);

    int findIndexById(long id);
}
