// Creates default roles and users at startup (admin/admin123, user/user123)
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
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   RoleRepository roleRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if ADMIN role exists, else create it
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseGet(() -> roleRepository.save(new Role("ADMIN")));

            // Check if USER role exists, else create it
            Role userRole = roleRepository.findByName("USER")
                    .orElseGet(() -> roleRepository.save(new Role("USER")));

            // Create default admin user if not exists
            if (userRepository.findByUsername("admin").isEmpty()) {
                User adminUser = new User("admin", passwordEncoder.encode("admin123"), adminRole);
                userRepository.save(adminUser);
            }

            // Create default user if not exists
            if (userRepository.findByUsername("user").isEmpty()) {
                User normalUser = new User("user", passwordEncoder.encode("user123"), userRole);
                userRepository.save(normalUser);
            }
        };
    }
}
