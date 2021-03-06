package Dao.interfaces;

import java.util.List;
import java.util.Optional;

public interface Dao <T> {

    List<T> getAll();
    Optional <T> get (Long id);
    void create (T entity);
    void update (T entity);
    void delete (T entity);

}
