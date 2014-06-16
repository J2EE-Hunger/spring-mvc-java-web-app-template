package name.dargiri.data.dao;

import java.io.Serializable;

/**
 * @author dionis on 14/06/14.
 */
public interface BaseRepository<T, ID extends Serializable> {
    T findOne(ID id);
    T save(T entity);
    void delete(ID id);
}
