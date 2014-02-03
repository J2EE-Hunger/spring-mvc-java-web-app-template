package name.dargiri.data.service;

import name.dargiri.data.dto.PersonDTO;

import java.util.List;
import java.util.UUID;

/**
 * Created by dionis on 2/3/14.
 */
public interface PersonService {
    PersonDTO create(PersonDTO dto);

    PersonDTO update(PersonDTO dto);

    PersonDTO find(UUID id);

    void delete(UUID id);

    List<PersonDTO> findAll();
}
