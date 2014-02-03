package name.dargiri.data.dao;

import name.dargiri.data.model.Model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * Created by dionis on 2/3/14.
 */
public abstract class AbstractBaseDAO<T extends Model<ID>, ID extends Serializable> implements BaseDAO<T, ID> {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void persist(Model entity) {
        if (entity.isNew()) {
            entity.assignId();
        }

        entityManager.persist(entity);
        entityManager.flush();
    }

    @Override
    public T find(ID id) {
        return getEntityManager().find(getEntityClass(), id);
    }

    @Override
    public void delete(T entity) {
        getEntityManager().remove(entity);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public abstract Class<T> getEntityClass();
}
