package com.person.restapi.hobby;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * HobbyRepository is class extends {@link org.springframework.data.jpa.repository.JpaRepository}
 * responsible for performing CRUD operation on Hobby entity.
 * @author Avkashh
 * @version v1
 */
public interface HobbyRepository extends JpaRepository<Hobby,Long> {

}
