package upd.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import upd.persistence.dao.GenericDao;

import java.util.Collection;
import java.util.List;

public abstract class BaseService<T> {

    protected static final Logger LOG = LoggerFactory.getLogger(BaseService.class);

    protected abstract GenericDao<T> getPrimaryDao();

    public List<T> findAll() {
        return getPrimaryDao().findAll();
    }

    public T find(Integer id) {
        return getPrimaryDao().find(id);
    }

    public void persist(T instance) {
        getPrimaryDao().persist(instance);
    }

    public void persist(Collection<T> instances) {
        getPrimaryDao().persist(instances);
    }

    public void update(T instance) {
        getPrimaryDao().update(instance);
    }

    public void remove(T instance) {
        getPrimaryDao().remove(instance);
    }

    public void remove(Collection<T> instances) {
        getPrimaryDao().remove(instances);
    }

    public boolean exists(Integer id) {
        return getPrimaryDao().exists(id);
    }
}