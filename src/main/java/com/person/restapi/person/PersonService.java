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

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person update(Long personId,Person person) {
        Person updatedPerson = personRepository.findById(personId).
                orElseThrow(()-> new PersonNotFoundException("Person not found for this id"+personId));
        arrangeReferences(person,updatedPerson);
        return personRepository.save(updatedPerson);
    }

    public void deleteById(Person person) {
        personRepository.delete( person);
    }

    private void arrangeReferences(Person srcPerson,Person dstPerson) {
        dstPerson.setAge(srcPerson.getAge());
        dstPerson.setFavourite_colour(srcPerson.getFavourite_colour());
        dstPerson.setFirst_name(srcPerson.getFirst_name());
        dstPerson.setLast_name(srcPerson.getLast_name());
    }
}
