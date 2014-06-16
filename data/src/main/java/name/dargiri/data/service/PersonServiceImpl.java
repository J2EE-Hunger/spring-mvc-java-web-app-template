package name.dargiri.data.service;

import name.dargiri.data.dao.PersonRepository;
import name.dargiri.data.dto.PersonDTO;
import name.dargiri.data.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by dionis on 2/3/14.
 */
@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;

    @Transactional
    @Override
    public PersonDTO create(PersonDTO dto) {
        Person entity = toEntity(dto);
        personRepository.save(entity);
        return toDTO(entity);
    }

    @Transactional
    @Override
    public PersonDTO update(PersonDTO dto) {
        Person person = personRepository.findOne(dto.getId());
        if (person != null) {
            person.setUsername(dto.getUsername());
            personRepository.save(person);
            return toDTO(person);
        } else {
            return null;
        }
    }

    @Override
    public PersonDTO find(Long id) {
        Person person = personRepository.findOne(id);
        if (person != null) {
            return toDTO(person);
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Assert.notNull(id);
        personRepository.delete(id);
    }

    @Override
    public List<PersonDTO> findAll() {
        List<Person> all = personRepository.findAll();
        List<PersonDTO> dtos = new ArrayList<>(all.size());
        for (Person person : all) {
            dtos.add(toDTO(person));
        }
        return dtos;
    }


    private PersonDTO toDTO(Person person) {
        return new PersonDTO(person.getId(), person.getUsername());
    }

    private Person toEntity(PersonDTO dto) {
        Person person = new Person();
        person.setId(dto.getId());
        person.setUsername(dto.getUsername());
        return person;
    }
}
