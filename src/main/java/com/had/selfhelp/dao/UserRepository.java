package com.had.selfhelp.dao;

import com.had.selfhelp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*This code defines a Spring Data JPA repository interface in a Spring Boot application. */

/*Spring Data JPA repositories provide an abstraction layer on top of the JPA (Java Persistence API)
that simplifies the data access code for working with databases in Spring applications.
*/
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
