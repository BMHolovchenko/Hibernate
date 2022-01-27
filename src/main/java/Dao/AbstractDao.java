package Dao;

import Dao.interfaces.Dao;
import config.PersistenceProvider;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao <T> implements Dao<T> {

    private final  Class<T> entityType;
    protected EntityManager em = PersistenceProvider.getEntityManager();

    protected AbstractDao(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public List<T> getAll() {
        TypedQuery<T> getAll = em.createQuery("from " + entityType.getSimpleName(), entityType);
        return getAll.getResultList();
    }

    @Override
    public Optional<T> get(Long id) {
        T entity = em.find(entityType, id);
        return Optional.of(entity);
    }

    @Override
    public void create(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public void update(T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        em.getTransaction().begin();
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.getTransaction().commit();
    }
}
