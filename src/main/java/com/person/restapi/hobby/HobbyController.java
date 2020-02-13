package com.person.restapi.hobby;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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



}
