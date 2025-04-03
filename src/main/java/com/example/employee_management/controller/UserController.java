package com.example.employee_management.controller;

import com.example.employee_management.model.User;
import com.example.employee_management.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a new user
    @PostMapping("/register")
public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
        return ResponseEntity.badRequest().body("Error: Username is already taken!");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    
    return ResponseEntity.ok("User registered successfully!");
}


    // Get all users (For testing)
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
