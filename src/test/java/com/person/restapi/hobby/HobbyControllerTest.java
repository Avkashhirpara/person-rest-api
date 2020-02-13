package com.person.restapi.hobby;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class HobbyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @MockBean
    private HobbyService hobbyService;


    @Test
    void get_list_of_all_hobbies() throws Exception {
        Mockito.when(hobbyService.findAll()).thenReturn(hobbies);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hobbies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(4)))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void get_hobby_by_id() throws Exception{
        Mockito.when(hobbyService.findById(1L)).thenReturn(shopping);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hobbies/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("shopping")))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    void create_new_hobby() throws Exception{
        Mockito.when(hobbyService.save(shopping)).thenReturn(shopping);
        ObjectMapper mapper = new ObjectMapper();
        String hobbyAsJson = mapper.writeValueAsString(shopping);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/hobbies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(hobbyAsJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("shopping")))
                .andDo(MockMvcResultHandlers.print());

    }




    static List<Hobby> hobbies;
    static Person aric;

    static Hobby shopping;
    static Hobby updatedShopping;
    static Hobby football;
    static Hobby chess;
    static Hobby cricket;

    @BeforeAll
    static void setUp() throws Exception {

        shopping = new Hobby("shopping");
        updatedShopping = new Hobby("Shopping");
        football = new Hobby("football");
        chess = new Hobby("chess");
        cricket = new Hobby("cricket");

        aric = new Person("Aric", "Thomas", "Red", 24, new HashSet<>(Arrays.asList(shopping)));
        hobbies = new ArrayList<>();
        hobbies.add(shopping);
        hobbies.add(football);
        hobbies.add(chess);
        hobbies.add(cricket);

        shopping.addPerson(aric);

    }


}
