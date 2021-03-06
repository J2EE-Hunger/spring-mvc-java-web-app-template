package name.dargiri.data.dao;

import name.dargiri.data.SpringDbTest;
import name.dargiri.data.model.Person;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by dionis on 2/3/14.
 */
public class PersonRepositoryImplTest extends SpringDbTest {
    @Resource
    PersonRepository dao;

    @Test
    public void Person_should_have_id_assigned_when_created() {
        Person entity = new Person();
        entity.setUsername("dionis");
        dao.save(entity);

        assertTrue(entity.getId() != null);
    }

    @Test
    public void
    it_should_be_found_by_id_after_persisting() {
        Person entity = new Person();
        entity.setUsername("dionis");
        dao.save(entity);

        assertTrue(entity.getId() != null);
        flushAndClear();

        Person found = dao.findOne(entity.getId());
        assertTrue(found != null);
        assertEquals(entity, found);
    }

    @Test
    public void it_should_not_be_found_by_id_if_not_found() {
        Person entity = new Person();
        entity.setUsername("dionis");
        dao.save(entity);

        assertTrue(entity.getId() != null);

        flushAndClear();

        Person found = dao.findOne(-1L);
        assertTrue(found == null);
    }

    @Test
    public void Find_all_should_return_all_persisted() {
        Person e1 = new Person();
        Person e2 = new Person();
        dao.save(e1);
        dao.save(e2);

        flushAndClear();

        List<Person> result = dao.findAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(e1));
        assertTrue(result.contains(e2));
    }
}
