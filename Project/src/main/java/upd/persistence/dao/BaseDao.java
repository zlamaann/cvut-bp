package upd.persistence.dao;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseDao<T> implements GenericDao<T> {

    protected static final Logger LOG = LoggerFactory.getLogger(BaseDao.class);

    @PersistenceContext
    EntityManager em;

    protected final Class<T> type;

    protected BaseDao(Class<T> type) {
        this.type = type;
    }

    @Transactional(readOnly = true)
    @Override
    public T find(Integer id) {
        if (id == null) {
            return null;
        }
        Objects.requireNonNull(id);
        return em.find(type, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<T> findAll() {
        try {
            return em.createQuery("SELECT e FROM " + type.getSimpleName() + " e", type).getResultList();
        } finally {
            em.close();
        }
    }

    @Transactional
    @Override
    public void merge(T entity) {
        Objects.requireNonNull(entity);
        try {
            em.merge(entity);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    @Transactional
    @Override
    public void persist(T entity) {
        Objects.requireNonNull(entity);
        try {
            em.persist(entity);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    @Transactional
    @Override
    public void persist(Collection<T> entities) {
        Objects.requireNonNull(entities);
        if (entities.isEmpty()) {
            return;
        }
        try {
            entities.forEach(this::persist);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    @Transactional
    @Override
    public T update(T entity) {

        Objects.requireNonNull(entity);
        if (entity == null) {
            return null;
        }
        try {
            return em.merge(entity);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    @Transactional
    @Override
    public void remove(T entity) {
        Objects.requireNonNull(entity);
        try {
            final T toRemove = em.merge(entity);
            if (toRemove != null) {
                em.remove(toRemove);
            }
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    @Transactional
    @Override
    public void remove(Collection<T> entities) {
        Objects.requireNonNull(entities);
        if (entities.isEmpty()) {
            return;
        }
        entities.forEach(this::remove);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exists(Integer id) {
        return id != null && em.find(type, id) != null;
    }
}
