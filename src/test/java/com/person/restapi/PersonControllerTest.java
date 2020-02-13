package com.person.restapi;

import com.person.restapi.person.Person;
import com.person.restapi.person.PersonService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class PersonControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PersonService personService;



    @Test
    void getAllPerson() throws Exception{
        Mockito.when(personService.findAll()).thenReturn(persons);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/persons")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andDo(MockMvcResultHandlers.print());

    }

    static List<Person> persons;

    static Person aric;
    static Person updatedAric;
    static Person jinny;
    static Person peter;
    @BeforeAll
    static void setUp() throws Exception {


        aric = new Person("Aric", "Thomas", "Red", 24, new HashSet<>());
        updatedAric = new Person("Arics", "Thomasan", "Red", 26, new HashSet<>());
        jinny = new Person("Jinny", "Hamilton", "Green", 20, new HashSet<>());
        peter = (new Person("Peter", "Danzel", "White", 30, new HashSet<>()));

        persons = new ArrayList();
        persons.add(aric);
        persons.add(jinny);
        persons.add(peter);

    }
}
