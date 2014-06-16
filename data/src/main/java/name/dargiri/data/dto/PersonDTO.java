package name.dargiri.data.dto;

import java.util.UUID;

/**
 * Created by dionis on 2/3/14.
 */
public class PersonDTO {
    private final Long id;
    private final String username;

    public PersonDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonDTO personDTO = (PersonDTO) o;

        if (id != null ? !id.equals(personDTO.id) : personDTO.id != null) return false;
        if (username != null ? !username.equals(personDTO.username) : personDTO.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
