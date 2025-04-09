package com.example.employee_management.controller;
import com.example.employee_management.model.Role;
import com.example.employee_management.model.User;
import com.example.employee_management.repository.RoleRepository;
import com.example.employee_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

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

        // Lookup role from DB
        Role role = roleRepository.findByName(userPayload.getRole().getName())
                .orElseThrow(() -> new RuntimeException("Role not found: " + userPayload.getRole().getName()));

        // Set encrypted password and role
        userPayload.setPassword(passwordEncoder.encode(userPayload.getPassword()));
        userPayload.setRole(role);

        userRepository.save(userPayload);
        return ResponseEntity.ok("User registered successfully!");
    }

    // Optional: test
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

