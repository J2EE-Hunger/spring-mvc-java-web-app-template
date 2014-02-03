package name.dargiri.data.dao;

import javax.persistence.EntityManager;

/**
 * Created by dionis on 2/3/14.
 */
public interface EntityManagerAware {
    EntityManager getEntityManager();
    void setEntityManager(EntityManager entityManager);
}
