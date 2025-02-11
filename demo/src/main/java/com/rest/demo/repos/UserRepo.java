package com.rest.demo.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.demo.models.User;

public interface UserRepo extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
