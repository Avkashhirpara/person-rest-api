package com.person.restapi.person;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * HobbyRepository is class extends {@link org.springframework.data.jpa.repository.JpaRepository}
 * responsible for performing CRUD operation on Person entity.
 * @author Avkashh
 * @version v1
 */
public interface PersonRepository extends JpaRepository<Person,Long> {
}
