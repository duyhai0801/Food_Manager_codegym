package Repository;

import java.util.List;

public interface IRepository<T> {
    List<T> readFile();

    void writeFile(List<T> list);
}
