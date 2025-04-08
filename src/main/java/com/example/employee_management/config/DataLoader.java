package com.example.employee_management.config;

import com.example.employee_management.model.User;
import com.example.employee_management.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // For older Java versions, use !isPresent() instead of isEmpty()
            if (!userRepository.findByUsername("admin").isPresent()) {
                User admin = new User("admin", passwordEncoder.encode("admin123"), "ADMIN");
                userRepository.save(admin);
            }

            if (!userRepository.findByUsername("user").isPresent()) {
                User user = new User("user", passwordEncoder.encode("user123"), "USER");
                userRepository.save(user);
            }
        };
    }
}
