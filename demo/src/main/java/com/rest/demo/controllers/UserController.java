package com.rest.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.demo.models.User;
import com.rest.demo.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String email, @RequestParam String username, @RequestParam String password) {
        userService.registerUser(email, username, password);
        return ResponseEntity.ok("ser registered successfully!");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long user) {
        return userService.getUserById(user)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
}
