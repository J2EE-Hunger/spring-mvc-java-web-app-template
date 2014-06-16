package name.dargiri.data.dao;

import name.dargiri.data.model.Person;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author dionis on 14/06/14.
 */
@Repository
public class PersonRepositoryImpl implements PersonRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Person findOne(Long id) {
        Assert.notNull(id);
        return entityManager.find(Person.class, id);
    }

    @Override
    public Person save(Person entity) {
        Assert.notNull(entity);
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public void delete(Long id) {
        Assert.notNull(id);

        Person one = findOne(id);
        Assert.notNull(one);

        entityManager.remove(one);
        entityManager.flush();
    }

    @Override
    public List<Person> findAll() {
        return entityManager
                .createQuery(
                        "select p from Person p", Person.class
                )
                .getResultList();
    }
}
