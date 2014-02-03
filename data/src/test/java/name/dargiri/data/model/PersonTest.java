package name.dargiri.data.model;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Created by dionis on 2/3/14.
 */
public class PersonTest {
    @Test
    public void Person_should_be_equal_when_have_same_id() {
        Pair<Person> pair = personPairWithSameId();
        assertEquals(pair._1, pair._2);
    }

    private Pair<Person> personPairWithSameId() {
        final UUID id = UUID.randomUUID();
        Person p1 = new Person();
        p1.setId(id);
        Person p2 = new Person();
        p2.setId(id);
        return new Pair<>(p1, p2);
    }

    private class Pair<T> {
        public final T _1;
        public final T _2;

        Pair(T _1, T _2) {
            this._1 = _1;
            this._2 = _2;
        }
    }

    @Test
    public void it_should_have_the_same_hashCode_when_have_same_id() {
        Pair<Person> pair = personPairWithSameId();
        assertEquals(pair._1.hashCode(), pair._2.hashCode());
    }
}
