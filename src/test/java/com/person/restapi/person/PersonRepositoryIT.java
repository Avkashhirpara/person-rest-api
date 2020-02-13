package com.person.restapi.person;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonRepositoryIT {


    @Autowired
    PersonRepository personRepository;

    Person aric;
    Person updatedAric;
    Person jason;
    Person deletePerson;

    @BeforeAll
    public void setUp()
    {
        aric = new Person("Aric","Thomas", "Red",24,null);
        updatedAric = new Person("Aricson","Thomasan", "white",22,null);
        jason = new Person("Jason","Mars", "green",26,null);
        jason = new Person("Jason","Mars", "green",26,null);
        deletePerson = new Person("Delete","Me", "black",86,null);
        jason = personRepository.save(jason);
        deletePerson = personRepository.save(deletePerson);

    }

    @Test
    public void findPersonById() throws Exception {
        Person foundPerson = personRepository.findById(jason.getId()).get();
        Assertions.assertEquals(jason,foundPerson);
    }
    @Test
    public void savePerson() throws Exception {
        Person savedPerson = personRepository.save(aric);
        Assertions.assertEquals(savedPerson,aric);
    }

    @Test
    public void getAllPerson() throws Exception {
        List<Person> persons = personRepository.findAll();
        Assertions.assertTrue(persons.size() > 0);
    }
    @Test
    public void deletePerson() throws Exception {
        personRepository.delete(deletePerson);
        Person foundPerson = personRepository.findById(deletePerson.getId()).orElse(null);
        Assertions.assertTrue(foundPerson == null);
    }



}
