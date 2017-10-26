package com.example.demoApp.mvc.repository;

import com.example.demoApp.mvc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    Long countByLogin(String login);

    Long countByEmail(String email);

}
