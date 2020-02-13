package com.person.restapi.hobby;

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
public class HobbyRepositoryIT {


    @Autowired
    HobbyRepository hobbyRepository;

    Hobby shopping;
    Hobby updatedShopping;
    Hobby football;
    Hobby chess;
    Hobby cricket;
    Hobby deleteMe;

    @BeforeAll
    public void setUp()
    {

        shopping = new Hobby("shopping");
        updatedShopping = new Hobby("Shopping");
        football = new Hobby("football");
        chess = new Hobby("chess");
        cricket = new Hobby("cricket");
        deleteMe = new Hobby("deleteMe");


        shopping = hobbyRepository.save(shopping);
        deleteMe = hobbyRepository.save(deleteMe);

    }

    @Test
    public void find_hobby_by_id() throws Exception {
        Hobby foundHobby = hobbyRepository.findById(shopping.getId()).get();
        Assertions.assertEquals(foundHobby,shopping);
    }
    @Test
    public void save_hobby() throws Exception {
        Hobby savedHobby = hobbyRepository.save(football);
        Assertions.assertEquals(savedHobby,football);
    }

    @Test
    public void get_list_of_all_hobbies() throws Exception {
        List<Hobby> hobbies = hobbyRepository.findAll();
        Assertions.assertTrue(hobbies.size() > 0);
    }
    @Test
    public void deletePerson() throws Exception {
        hobbyRepository.delete(deleteMe);
        Hobby foundHobby = hobbyRepository.findById(deleteMe.getId()).orElse(null);
        Assertions.assertTrue(foundHobby == null);
    }
}
