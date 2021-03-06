package com.person.restapi.person;


import com.person.restapi.exception.PersonNotFoundException;
import com.person.restapi.hobby.Hobby;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService =new PersonService(personRepository);

    @Test
    void get_list_of_persons(){
        Mockito.when(personRepository.findAll()).thenReturn(persons);
        List<Person> persons  = personService.findAll();
        Person lastPerson = persons.get(persons.size()-1);
        Assertions.assertEquals(aric,lastPerson);
    }

    @Test
    void get_person_by_personId(){
        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(aric));
        Person foundPerson = personService.findById(1L);
        Assertions.assertEquals(aric,foundPerson);
    }

    @Test
    void test_create_Person(){
        Mockito.when(personRepository.save(aric)).thenReturn(aric);
        Person savedPerson = personService.save(aric);
        Assertions.assertEquals(aric,savedPerson);
    }

    @Test
    void test_update_person(){
        Mockito.when(personRepository.save(updatedAric)).thenReturn(updatedAric);
        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(aric));
        Person savedPerson = personService.update(1L,updatedAric);
        assert savedPerson.equals(updatedAric);
    }

    @Test
    void test_delete_person(){
        Mockito.when(personRepository.save(aric)).thenReturn(aric);
        Mockito.when(personRepository.findById(1L)).thenThrow( new PersonNotFoundException("Person Not Found"));
        Mockito.doNothing().when(personRepository).delete(aric);
        Person person = personService.save(aric);
        long personId = person.getId();
        personService.deleteById(person);
        Assertions.assertThrows(PersonNotFoundException.class, () -> {
            personService.findById(1);
        });
    }

    @BeforeAll
    public void setUp() throws Exception {
        cricket = new Hobby("cricket");
        chess = new Hobby("chess");
        aric = new Person("Aric","Thomas", "Red",24,new HashSet<Hobby>(Arrays.asList(chess,cricket)));
        updatedAric = new Person("Arics","Thomasan", "Red",26,new HashSet<Hobby>(Arrays.asList(chess,cricket)));
        persons = new ArrayList();
        persons.add(aric);
    }

    List<Person> persons;
    Person aric;
    Person updatedAric;
    Hobby chess;
    Hobby cricket;
}

