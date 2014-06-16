package name.dargiri.data.model;

import java.io.Serializable;

/**
 * Created by dionis on 2/3/14.
 */
public interface Model<ID extends Serializable> {
    ID getId();
    boolean isNew();
}
