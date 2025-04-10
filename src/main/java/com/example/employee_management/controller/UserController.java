package com.example.employee_management.controller;

import com.example.employee_management.model.Role;
import com.example.employee_management.model.User;
import com.example.employee_management.repository.RoleRepository;
import com.example.employee_management.repository.UserRepository;
import com.example.employee_management.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User userPayload) {
        if (userRepository.findByUsername(userPayload.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: Username already exists");
        }

        Role role = roleRepository.findByName(userPayload.getRole().getName())
                .orElseThrow(() -> new RuntimeException("Role not found: " + userPayload.getRole().getName()));

        log.info("role : {}", role);

        User newUser = new User();
        newUser.setUsername(userPayload.getUsername());
        newUser.setPassword(passwordEncoder.encode(userPayload.getPassword()));
        newUser.setRole(role);

        userRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully!");
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserDTO(user.getUsername(), user.getRole().getName()))
                .toList();
    }
}
