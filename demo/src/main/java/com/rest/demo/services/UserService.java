package com.rest.demo.services;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rest.demo.enums.Role;
import com.rest.demo.models.User;
import com.rest.demo.repos.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    public User registerUser(String email, String username, String password) {
        if (userRepo.existsByEmail(email) || userRepo.existsByUsername(username)) {
            throw new RuntimeException("Email or username already exists");
        }

        User user = User.builder()
                .email(email)
                .username(username)
                .password(encoder.encode(password)) // Encrypt password
                .role(Role.USER)
                .build();

        return userRepo.save(user);
    }

    public Optional<User> getUserById(Long userId) {
        return userRepo.findById(userId);
    }
}
