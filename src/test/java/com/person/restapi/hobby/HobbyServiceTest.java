package com.person.restapi.hobby;

import com.person.restapi.person.Person;
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
public class HobbyServiceTest {

    @Mock
    private HobbyRepository hobbyRepository;

    @InjectMocks
    private HobbyService hobbyServices =new HobbyService(hobbyRepository);


    @Test
    void get_list_of_all_hobbies(){
        Mockito.when(hobbyRepository.findAll()).thenReturn(hobbies);
        List<Hobby> hobbies  = hobbyServices.findAll();
        Hobby lastHobby = hobbies.get(hobbies.size()-1);
        Assertions.assertEquals(cricket,lastHobby);
    }






    List<Person> persons;
    List<Hobby> hobbies = new ArrayList<>();

    Person aric;
    Person updatedAric;

    Hobby shopping;
    Hobby updatedShopping;
    Hobby football;
    Hobby chess;
    Hobby cricket;

    @BeforeAll
    public void setUp() throws Exception {

        shopping = new Hobby("shopping");
        updatedShopping = new Hobby("Shopping");
        football = new Hobby("football");
        chess = new Hobby("chess");
        cricket = new Hobby("cricket");

        aric = new Person("Aric","Thomas", "Red",24,new HashSet<Hobby>(Arrays.asList(chess,cricket)));
        updatedAric = new Person("Arics","Thomasan", "Red",26,new HashSet<Hobby>(Arrays.asList(chess,cricket)));
        persons = new ArrayList();
        persons.add(aric);

        hobbies = new ArrayList<>();
        hobbies.add(shopping);
        hobbies.add(football);
        hobbies.add(chess);
        hobbies.add(cricket);

    }

}
