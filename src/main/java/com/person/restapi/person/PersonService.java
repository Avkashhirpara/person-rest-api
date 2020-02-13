package com.person.restapi.person;

import com.person.restapi.exception.PersonNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found for PersonId +"+personId));
    }

    public Person save(Person aric) {
        return new Person();
    }

    public Person update(long l, Person updatedAric) {
            return new Person();
    }

    public void deleteById(Person jinny) {

    }
}
