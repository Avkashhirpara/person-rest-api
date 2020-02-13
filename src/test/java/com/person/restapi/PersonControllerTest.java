package com.person.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.person.restapi.hobby.Hobby;
import com.person.restapi.hobby.HobbyService;
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
import java.util.HashSet;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class PersonControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @MockBean
    private HobbyService hobbyService;

    @Test
    void get_list_of_all_persons() throws Exception{
        Mockito.when(personService.findAll()).thenReturn(persons);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/persons")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void get_person_by_id() throws Exception{

        Mockito.when(personService.findById(1L)).thenReturn(aric);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/persons/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.first_name",Matchers.is(aric.getFirst_name())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.last_name",Matchers.is(aric.getLast_name())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void test_create_person() throws Exception{
        Mockito.when(personService.save(aric)).thenReturn(aric);
        ObjectMapper mapper = new ObjectMapper();
        String personAsJson = mapper.writeValueAsString(aric);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personAsJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.first_name", Matchers.is(aric.getFirst_name())))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void test_update_person() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String personAsJson = mapper.writeValueAsString(updatedAric);
        Mockito.when(personService.update(1L,updatedAric)).thenReturn(updatedAric);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/persons/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personAsJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.first_name",Matchers.is(updatedAric.getFirst_name())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void test_delete_person() throws Exception{
        Mockito.when(personService.findById(1)).thenReturn((jinny));
        Mockito.doNothing().when(personService).deleteById(jinny);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/persons/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void test_add_Hobby_for_Person() throws Exception{
        Mockito.when(personService.save(aric)).thenReturn(aric);
        Mockito.when(personService.findById(1L)).thenReturn(aric);
        Mockito.when(hobbyService.findById(1L)).thenReturn(shopping);

        ObjectMapper mapper = new ObjectMapper();
        String personAsJson = mapper.writeValueAsString(aric);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/persons/1/hobby/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personAsJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.first_name", Matchers.is(aric.getFirst_name())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hobby[0].name", Matchers.is("shopping")))
                .andDo(MockMvcResultHandlers.print());

    }





    static List<Person> persons;

    static Person aric;
    static Person updatedAric;
    static Person jinny;
    static Person peter;

    static Hobby shopping;

    @BeforeAll
    static void setUp() throws Exception {

        shopping = new Hobby("shopping");

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
