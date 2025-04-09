package com.example.employee_management.config;

import com.example.employee_management.model.Role;
import com.example.employee_management.model.User;
import com.example.employee_management.repository.RoleRepository;
import com.example.employee_management.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseGet(() -> roleRepository.save(new Role("ADMIN")));

            Role userRole = roleRepository.findByName("USER")
                    .orElseGet(() -> roleRepository.save(new Role("USER")));

            if (userRepository.findByUsername("admin").isEmpty()) {
                userRepository.save(new User(1L,"admin", passwordEncoder.encode("admin123"), adminRole));
            }

            if (userRepository.findByUsername("user").isEmpty()) {
                userRepository.save(new User(2L,"user", passwordEncoder.encode("user123"), userRole));
            }
        };
    }
}