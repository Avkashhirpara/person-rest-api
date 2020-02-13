package com.person.restapi.hobby;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class HobbyController {
    private HobbyService hobbyService;

    public HobbyController(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }

    @GetMapping("/hobbies")
    ResponseEntity<List<Hobby>> getHobbies(){
        return new ResponseEntity<>(hobbyService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/hobbies/{id}")
    ResponseEntity<Hobby> getHobbyByID(@PathVariable(value = "id") Long hobbyId){
        return new ResponseEntity<>(hobbyService.findById(hobbyId),HttpStatus.OK);
    }

    @PostMapping("/hobbies")
    ResponseEntity<Hobby> createHobby(@RequestBody Hobby hobby){
        return new ResponseEntity<>(hobbyService.save(hobby), HttpStatus.CREATED);
    }



}
