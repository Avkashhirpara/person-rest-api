package com.person.restapi.hobby;

import com.person.restapi.exception.HobbyNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyService {
    private HobbyRepository hobbyRepository;

    public HobbyService(HobbyRepository hobbyRepository) {
        this.hobbyRepository = hobbyRepository;
    }


    public Hobby findById(Long hobbyId) {
        return hobbyRepository.findById(hobbyId)
                .orElseThrow(()-> new HobbyNotFoundException("Hobby not found for hobbyId"+hobbyId));
    }

    public List<Hobby> findAll() {
        return null;
    }
    public Hobby save(Hobby shopping) {
            return  new Hobby();
    }

    public Hobby update(long l, Hobby updatedShopping) {
        return new Hobby();
    }

    public void deleteById(long id) {

    }
}
