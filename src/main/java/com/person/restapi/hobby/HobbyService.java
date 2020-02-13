package com.person.restapi.hobby;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyService {


    public Hobby findById(long l) {
        return new Hobby();
    }

    public List<Hobby> findAll() {
        return null;
    }
}
