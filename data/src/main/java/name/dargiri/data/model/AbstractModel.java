package name.dargiri.data.model;

import java.io.Serializable;

/**
 * Created by dionis on 2/3/14.
 */
public abstract class AbstractModel<ID extends Serializable> implements Model<ID> {

    @Override
    public boolean isNew() {
        return getId() == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractModel)) return false;

        AbstractModel that = (AbstractModel) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
