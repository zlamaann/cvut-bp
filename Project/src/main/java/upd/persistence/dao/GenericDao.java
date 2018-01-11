package upd.persistence.dao;

import java.util.Collection;
import java.util.List;

public interface GenericDao<T> {
    T find(Integer id);

    List<T> findAll();

    void persist(T entity);

    void persist(Collection<T> entities);

    T update(T entity);

    void remove(T entity);

    void remove(Collection<T> entities);

    boolean exists(Integer id);

}
