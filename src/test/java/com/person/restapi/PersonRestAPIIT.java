package com.person.restapi;


import com.person.restapi.hobby.Hobby;
import com.person.restapi.hobby.HobbyRepository;
import com.person.restapi.person.Person;
import com.person.restapi.person.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonRestAPIIT {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void get_person_by_id(){
        aric = personRepository.save(aric);
        String url = String.format("/api/v1/persons/%s", aric.getId());
        ResponseEntity<Person> responseEntity = testRestTemplate.getForEntity(url, Person.class);
        Person person = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(aric.getId(), person.getId());
        assertEquals(aric.getFirst_name(), person.getFirst_name());
        assertEquals(0, person.getHobby().size());
    }
    @Test
    void get_all_person_list(){
        aric = personRepository.save(jinny);
        String url = String.format("/api/v1/persons");
        ResponseEntity<Person[]> responseEntity = testRestTemplate.getForEntity(url, Person[].class);
        Person[] persons = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        Assertions.assertTrue ( persons.length > 0 );
    }
    @Test
    void test_create_person() {

        ResponseEntity<Person> responseEntity = testRestTemplate.postForEntity("/api/v1/persons", peter, Person.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getId());
        assertEquals(peter.getFirst_name(), responseEntity.getBody().getFirst_name());
        assertEquals(0, responseEntity.getBody().getHobby().size());

        Optional<Person> foundPerson = personRepository.findById(responseEntity.getBody().getId());
        assertTrue(foundPerson.isPresent());
        assertEquals(peter.getFirst_name(), foundPerson.get().getFirst_name());
    }

    @Test
    void test_update_person() {
        originalHarry = personRepository.save(originalHarry);

        HttpEntity<Person> requestUpdate = new HttpEntity<>(updatedHarry);
        String url = String.format("/api/v1/persons/%s", originalHarry.getId());
        ResponseEntity<Person> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Person.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(updatedHarry.getFirst_name(), responseEntity.getBody().getFirst_name());

        Optional<Person> foundPerson = personRepository.findById(originalHarry.getId());
        assertTrue(foundPerson.isPresent());
        assertEquals(updatedHarry.getFirst_name(), foundPerson.get().getFirst_name());
    }
    @Test
    void test_delete_person() {
        deleteMe = personRepository.save(deleteMe);
        String url = String.format("/api/v1/persons/%s", deleteMe.getId());
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(url, HttpMethod.DELETE, null,Void.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Optional<Person> foundPerson = personRepository.findById(deleteMe.getId());
        assertFalse(foundPerson.isPresent());
    }



    @Test
    void test_add_hobby_for_person() {
        hobbyPerson = personRepository.save(hobbyPerson);
        personHobby = hobbyRepository.save(personHobby);

        String url = String.format("/api/v1/persons/%s/hobby/%s", hobbyPerson.getId(), personHobby.getId());
        ResponseEntity<Person> responseEntity = testRestTemplate.postForEntity(url, null, Person.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(hobbyPerson.getId(), responseEntity.getBody().getId());
        assertEquals(hobbyPerson.getFirst_name(), responseEntity.getBody().getFirst_name());

        Optional<Person> foundPerson = personRepository.findById(hobbyPerson.getId());
        assertTrue(foundPerson.isPresent());
        assertEquals(1, foundPerson.get().getHobby().size());
        assertTrue(foundPerson.get().getHobby().contains(personHobby));

        Optional<Hobby> foundHobby = hobbyRepository.findById(personHobby.getId());
        assertTrue(foundHobby.isPresent());
        assertEquals(1, foundHobby.get().getPerson().size());
        assertTrue(foundHobby.get().getPerson().contains(hobbyPerson));
    }

    @Test
    void test_remove_hobby_of_person() {

        removeHobby = hobbyRepository.save(removeHobby);
        removePerson = personRepository.save(removePerson);
        removePerson.addHobby(removeHobby);
        removePerson = personRepository.save(removePerson);

        String url = String.format("/api/v1/persons/%s/hobby/%s", removePerson.getId(), removeHobby.getId());
        ResponseEntity<Person> responseEntity = testRestTemplate.exchange(url, HttpMethod.DELETE, null, Person.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(removePerson.getId(), responseEntity.getBody().getId());
        assertEquals(removePerson.getFirst_name(), responseEntity.getBody().getFirst_name());

        Optional<Person> foundPerson = personRepository.findById(removePerson.getId());
        assertTrue(foundPerson.isPresent());
        assertEquals(0, foundPerson.get().getHobby().size());

        Optional<Hobby> foundHobby = hobbyRepository.findById(removeHobby.getId());
        assertTrue(foundHobby.isPresent());
        assertEquals(0, foundHobby.get().getPerson().size());
    }

    @Test
    void test_get_hobby_by_id(){
        football = hobbyRepository.save(football);
        String url = String.format("/api/v1/hobbies/%s", football.getId());
        ResponseEntity<Hobby> responseEntity = testRestTemplate.getForEntity(url, Hobby.class);
        Hobby hobby = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(football.getId(), hobby.getId());
        assertEquals(football.getName(), hobby.getName());

    }
    @Test
    void test_get_list_of_all_hobbies(){
        cricket = hobbyRepository.save(cricket);
        String url = String.format("/api/v1/hobbies");
        ResponseEntity<Hobby[]> responseEntity = testRestTemplate.getForEntity(url, Hobby[].class);
        Hobby[] hobbies = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        Assertions.assertTrue ( hobbies.length > 0 );
    }
    @Test
    void test_create_hobby() {

        ResponseEntity<Hobby> responseEntity =
                testRestTemplate.postForEntity("/api/v1/hobbies", chess, Hobby.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getId());
        assertEquals(chess.getName(), responseEntity.getBody().getName());
        assertEquals(0, responseEntity.getBody().getPerson().size());

        Optional<Hobby> foundHobby = hobbyRepository.findById(responseEntity.getBody().getId());
        assertTrue(foundHobby.isPresent());
        assertEquals(chess.getName(), foundHobby.get().getName());
    }

    @Test
    void test_update_hobby() {
        orgSinging = hobbyRepository.save(orgSinging);

        HttpEntity<Hobby> requestUpdate = new HttpEntity<>(updatedSinging);
        String url = String.format("/api/v1/hobbies/%s", orgSinging.getId());
        ResponseEntity<Hobby> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Hobby.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(updatedSinging.getName(), responseEntity.getBody().getName());

        Optional<Hobby> foundHobby = hobbyRepository.findById(orgSinging.getId());
        assertTrue(foundHobby.isPresent());
        assertEquals(updatedSinging.getName(), foundHobby.get().getName());
    }
    @Test
    void test_delete_hobby() {
        deleteHobby = hobbyRepository.save(deleteHobby);
        String url = String.format("/api/v1/hobbies/%s", deleteHobby.getId());
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(url, HttpMethod.DELETE, null,String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Optional<Hobby> foundHobby = hobbyRepository.findById(deleteHobby.getId());
        assertFalse(foundHobby.isPresent());
    }

    static Person aric;
    static Person originalHarry;
    static Person updatedHarry;
    static Person deleteMe;
    static Person hobbyPerson;
    static Person jinny;
    static Person peter;
    static Person removePerson;

    static Hobby shopping;
    static Hobby football;
    static Hobby chess;
    static Hobby cricket;
    static Hobby personHobby;
    static Hobby removeHobby;

    static Hobby orgSinging;
    static Hobby updatedSinging;
    static Hobby deleteHobby;

    @BeforeAll
    static void setUp() throws Exception {

        shopping = new Hobby("shopping");
        football = new Hobby("football");
        chess = new Hobby("chess");
        cricket = new Hobby("cricket");
        orgSinging = new Hobby("singging");
        updatedSinging = new Hobby("singing");
        personHobby = new Hobby("personHobby");
        removeHobby = new Hobby("removeHobby");
        deleteHobby = new Hobby("deleteHobby");

        aric = new Person("Aric", "Thomas", "Red", 24, new HashSet<>());

        originalHarry = new Person("Herry", "Poter", "blue", 12, new HashSet<>());
        updatedHarry = new Person("Harry", "Potter", "Blue", 16, new HashSet<>());
        deleteMe = new Person("Delete", "Me", "Blue", 66, new HashSet<>());
        hobbyPerson = new Person("Hobby", "Person", "Orange", 16, new HashSet<>());

        jinny = new Person("Jinny", "Hamilton", "Green", 20, new HashSet<>());
        peter = (new Person("Peter", "Danzel", "White", 30, new HashSet<>()));
        removePerson = (new Person("Remove", "Person", "White", 30, new HashSet<>()));

    }
}
