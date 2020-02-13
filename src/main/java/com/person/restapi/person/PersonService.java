package com.person.restapi.person;

import com.person.restapi.exception.PersonNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@link com.person.restapi.person.PersonService} class is responsible for performing operation of Person class along with
 * any required business logic, requested by {@link com.person.restapi.person.PersonController}
 * Autowired dependency of {@link com.person.restapi.person.PersonRepository} injected by the spring container.
 * @author Avkash
 * @version v1.
 */

@Service
public class PersonService {

    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * This method API is responsible for retrieving the list of all Person object in the system.
     * @return List<Person>
     */
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    /**
     * This method API is responsible for retrieving Person object for provided hobbyId.
     * @param personId
     * @return Person
     */
    public Person findById(long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found for PersonId +"+personId));
    }

    /**
     * This method API is responsible for saving Person object in system.
     * @param person
     * @return Person
     */
    public Person save(Person person) {
        return personRepository.save(person);
    }

    /**
     * This method API is responsible for update an existing Person object in system for provided hobbyId.
     * @param personId
     * @param person
     * @return Person
     */
    public Person update(Long personId,Person person) {
        Person updatedPerson = personRepository.findById(personId).
                orElseThrow(()-> new PersonNotFoundException("Person not found for this id"+personId));
        arrangeReferences(person,updatedPerson);
        return personRepository.save(updatedPerson);
    }

    /**
     * This method API is responsible for deleting an existing Person object in the system.
     * @param person
     */
    public void deleteById(Person person) {
        personRepository.delete( person);
    }

    /**
     * This method is responsible to copy person data from source object to destination object.
     * @param srcPerson
     * @param dstPerson
     */
    private void arrangeReferences(Person srcPerson,Person dstPerson) {
        dstPerson.setAge(srcPerson.getAge());
        dstPerson.setFavourite_colour(srcPerson.getFavourite_colour());
        dstPerson.setFirst_name(srcPerson.getFirst_name());
        dstPerson.setLast_name(srcPerson.getLast_name());
    }
}
