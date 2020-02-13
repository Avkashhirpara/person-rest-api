package com.person.restapi.person;


import com.person.restapi.hobby.Hobby;
import com.person.restapi.hobby.HobbyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PersonController {

    private PersonService personService;
    private HobbyService hobbyService;

    public PersonController(PersonService personService, HobbyService hobbyService) {
        this.personService = personService;
        this.hobbyService = hobbyService;
    }
    @ApiOperation(value = "View a list of all persons", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view persons' list"),
            @ApiResponse(code = 403, message = "Accessing persons' list, you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The persons' list you were trying to reach is not found")
    })

    @GetMapping("/persons")
    ResponseEntity<List<Person>> getPersons(){
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }


    @ApiOperation(value = "Retrieve person by personId ", response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created  a Person "),
            @ApiResponse(code = 401, message = "You are not authorized to view Person"),
            @ApiResponse(code = 403, message = "Accessing Person you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The Person you were trying to reach is not found")
    })
    @GetMapping("/persons/{id}")
    ResponseEntity<Person> getPersonByID(@PathVariable(value = "id") Long personId){
        return new ResponseEntity<>(personService.findById(personId),HttpStatus.OK);
    }

    @ApiOperation(value = " Create Person ", response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created a Person "),
            @ApiResponse(code = 401, message = "You are not authorized to created Person"),
            @ApiResponse(code = 403, message = "Person you were trying to create is forbidden"),
    })
    @PostMapping("/persons")
    ResponseEntity<Person> createPerson(@RequestBody Person person){
        return new ResponseEntity<>(personService.save(person), HttpStatus.CREATED);
    }


    @ApiOperation(value = " Update Person ", response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated a Person "),
            @ApiResponse(code = 401, message = "You are not authorized to update a Person"),
            @ApiResponse(code = 403, message = "Accessing Person you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The Person you were trying to reach is not found")
    })
    @PutMapping("/persons/{id}")
    ResponseEntity<Person> udpatePerson(@PathVariable(value = "id") Long personId
            , @RequestBody Person person){
        Person newPerson = (personService.update(personId,person));
        return new ResponseEntity<>(newPerson, HttpStatus.OK);
    }

    @ApiOperation(value = " Delete Person ", response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted a Person "),
            @ApiResponse(code = 401, message = "You are not authorized to delete a Person"),
            @ApiResponse(code = 403, message = "Accessing Person you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The Person you were trying to reach is not found")
    })
    @DeleteMapping("/persons/{id}")
    ResponseEntity<Void> deletePerson(@PathVariable(value = "id") Long personId){
        Person person = personService.findById(personId);
        person.getHobby().forEach(hobby -> hobby.removePerson(person));
        personService.deleteById(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = " Add hobby for Person ", response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added a hobby for a Person "),
            @ApiResponse(code = 401, message = "You are not authorized to add a hobby for a Person"),
            @ApiResponse(code = 403, message = "Accessing Person you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The Person you were trying to reach is not found")
    })

    @PostMapping("/persons/{personId}/hobby/{hobbyId}")
    ResponseEntity<Person> addHobbyToPerson(@PathVariable Long personId, @PathVariable Long hobbyId) {
        Person person = personService.findById(personId);
        Hobby hobby = hobbyService.findById(hobbyId);
        person.addHobby(hobby);
        person = personService.save(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }












}
