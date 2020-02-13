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
        return hobbyRepository.findAll();
    }

    public Hobby save(Hobby hobby) {
        return hobbyRepository.save(hobby);
    }

    public Hobby update(Long hobbyId,Hobby hobby) {
        Hobby updatedHobby = hobbyRepository.findById(hobbyId).
                orElseThrow(()-> new HobbyNotFoundException("Hobby not found for this id"+hobbyId));
        updatedHobby.setName(hobby.getName());
        return hobbyRepository.save(updatedHobby);
    }

    public void deleteById(Long hobbyId) {
        hobbyRepository.deleteById(hobbyId);
    }
}
