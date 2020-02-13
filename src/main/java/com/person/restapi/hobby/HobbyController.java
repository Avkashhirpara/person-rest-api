package com.person.restapi.hobby;

import com.person.restapi.person.Person;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Hobby controller handles the http request to add,update,delete and view for Hobby.
 * Please see {@link com.person.restapi.hobby.HobbyController}
 * @author Avkashh
 * @version v1.
 */

@RestController
@RequestMapping("api/v1")
public class HobbyController {
    private HobbyService hobbyService;

    public HobbyController(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }


    /**
     * This method is responsible to handle http request to list all the hobbies in system.
     * @return @return org.springframework.http.ResponseEntity<List<Hobby>>
     */
    @ApiOperation(value = "View a list of all hobbies", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view hobbies' list"),
            @ApiResponse(code = 403, message = "Hobbies list, you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Hobbies list you were trying to reach is not found")
    })

    @GetMapping("/hobbies")
    ResponseEntity<List<Hobby>> getHobbies(){
        return new ResponseEntity<>(hobbyService.findAll(), HttpStatus.OK);
    }


    /**
     * This method responsible to handle http request ro retrieve Hobby for the provided hobbyId.
     * @param hobbyId
     * @return org.springframework.http.ResponseEntity<Hobby>
     */
    @ApiOperation(value = "Retrieve Hobby by hobbyId ", response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved a Hobby "),
            @ApiResponse(code = 401, message = "You are not authorized to view Hobby"),
            @ApiResponse(code = 403, message = "Hobby you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The Hobby you were trying to reach is not found")
    })
    @GetMapping("/hobbies/{id}")
    ResponseEntity<Hobby> getHobbyByID(@PathVariable(value = "id") Long hobbyId){
        return new ResponseEntity<>(hobbyService.findById(hobbyId),HttpStatus.OK);
    }

    /**
     * This method is responsible for creating an object of hobby.
     * @param hobby
     * @return org.springframework.http.ResponseEntity<Hobby>
     */
    @ApiOperation(value = " Create Hobby ", response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created a Hobby "),
            @ApiResponse(code = 401, message = "You are not authorized to created Hobby"),
            @ApiResponse(code = 403, message = "Hobby you were trying to create is forbidden"),
    })
    @PostMapping("/hobbies")
    ResponseEntity<Hobby> createHobby(@RequestBody Hobby hobby){
        return new ResponseEntity<>(hobbyService.save(hobby), HttpStatus.CREATED);
    }

    /**
     * This method is responsible for updating an object of hobby.
     * @param hobbyId
     * @param hobby
     * @return org.springframework.http.ResponseEntity<Hobby>
     */
    @ApiOperation(value = " Update Hobby ", response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated a Hobby "),
            @ApiResponse(code = 401, message = "You are not authorized to update a Hobby"),
            @ApiResponse(code = 403, message = "Hobby you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The Hobby you were trying to reach is not found")
    })
    @PutMapping("/hobbies/{id}")
    ResponseEntity<Hobby> udpateHobby(@PathVariable(value = "id") Long hobbyId
            , @RequestBody Hobby hobby){
        Hobby newHobby = (hobbyService.update(hobbyId,hobby));
        return new ResponseEntity<>(newHobby, HttpStatus.OK);
    }



    /**
     * This method is responsible to delete hobby by hobbyId.
     * @param hobbyId
     * @return org.springframework.http.ResponseEntity<Hobby>
     */

    @ApiOperation(value = " Remove hobby from System", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully removed a hobby from system."),
            @ApiResponse(code = 401, message = "You are not authorized to delete a hobby"),
            @ApiResponse(code = 403, message = "Hobby you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The Hobby you were trying to reach is not found")
    })

    @DeleteMapping("/hobbies/{id}")
    ResponseEntity<String> deleteHobby(@PathVariable(value = "id") Long hobbyId){
        Hobby hobby = hobbyService.findById(hobbyId);
        if(hobby.getPerson().size() > 0){
            String pesonIds = hobby.getPerson().stream()
                    .map(person -> String.valueOf(person.getId())).collect(Collectors.joining(","));
            return new ResponseEntity<>("Persons["+pesonIds+"] are associated with this hobby, Please remove reference first.",HttpStatus.BAD_REQUEST);
        }
        hobby.getPerson().forEach(person -> person.removeHobby(hobby));
        hobbyService.deleteById(hobbyId);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }



}
