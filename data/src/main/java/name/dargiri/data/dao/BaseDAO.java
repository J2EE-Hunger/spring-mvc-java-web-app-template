package name.dargiri.data.dao;

import name.dargiri.data.model.Model;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by dionis on 2/3/14.
 */
public interface BaseDAO<T extends Model<ID>, ID extends Serializable> extends EntityManagerAware {
    void persist(T entity);

    T find(ID id);

    Class<T> getEntityClass();

    void delete(T entity);
}
