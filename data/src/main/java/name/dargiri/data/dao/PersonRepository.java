package name.dargiri.data.dao;

import name.dargiri.data.model.Person;

import java.util.List;

/**
 * Created by dionis on 2/3/14.
 */
public interface PersonRepository extends BaseRepository<Person, Long> {
    List<Person> findAll();
}
