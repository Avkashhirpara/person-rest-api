package com.person.restapi;


import com.person.restapi.hobby.Hobby;
import com.person.restapi.person.Person;
import com.person.restapi.person.PersonRepository;
import com.person.restapi.person.PersonService;
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

