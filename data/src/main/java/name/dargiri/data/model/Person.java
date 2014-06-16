package name.dargiri.data.model;

import javax.persistence.*;

/**
 * Created by dionis on 2/3/14.
 */
@Entity
@Table(name = "people")
public class Person extends AbstractModel<Long> {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "person_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "person_id_generator", sequenceName = "person_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "username")
    private String username;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
