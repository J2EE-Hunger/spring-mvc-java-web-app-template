package name.dargiri.data.dao;

import name.dargiri.data.model.Person;

import java.util.List;
import java.util.UUID;

/**
 * Created by dionis on 2/3/14.
 */
public interface PersonDAO extends BaseDAO<Person, UUID> {
    List<Person> findAll();
}
