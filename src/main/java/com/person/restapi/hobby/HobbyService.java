package com.person.restapi.hobby;

import com.person.restapi.exception.HobbyNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@link com.person.restapi.hobby.HobbyService} class is responsible for performing operation of Hobby class along with
 * any required business logic, requested by {@link com.person.restapi.hobby.HobbyController}
 * Autowired dependency of {@link com.person.restapi.hobby.HobbyRepository} injected by the spring container.
 * @author Avkash
 * @version v1.
 */
@Service
public class HobbyService {
    private HobbyRepository hobbyRepository;


    public HobbyService(HobbyRepository hobbyRepository) {
        this.hobbyRepository = hobbyRepository;
    }

    /**
     * This method API is responsible for retrieving hobby object for provided hobbyId.
     * @param hobbyId
     * @return Hobby
     */
    public Hobby findById(Long hobbyId) {
        return hobbyRepository.findById(hobbyId)
                .orElseThrow(()-> new HobbyNotFoundException("Hobby not found for hobbyId"+hobbyId));
    }

    /**
     * This method API is responsible for retrieving the list of all hobby object in the system.
     * @return List of Hobby
     */
    public List<Hobby> findAll() {
        return hobbyRepository.findAll();
    }

    /**
     * This method API is responsible for saving Hobby object in system.
     * @param hobby
     * @return Hobby
     */
    public Hobby save(Hobby hobby) {
        return hobbyRepository.save(hobby);
    }

    /**
     * This method API is responsible for update an existing Hobby object in system for provided hobbyId.
     * @param hobbyId
     * @param hobby
     * @return Hobby
     */
    public Hobby update(Long hobbyId,Hobby hobby) {
        Hobby updatedHobby = hobbyRepository.findById(hobbyId).
                orElseThrow(()-> new HobbyNotFoundException("Hobby not found for this id"+hobbyId));
        updatedHobby.setName(hobby.getName());
        return hobbyRepository.save(updatedHobby);
    }

    /**
     * This method API is responsible for deleting an existing Hobby object in the system.
     * @param hobbyId
     */
    public void deleteById(Long hobbyId) {
        hobbyRepository.deleteById(hobbyId);
    }
}
