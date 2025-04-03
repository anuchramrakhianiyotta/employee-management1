package com.example.employee_management.repository;

import com.example.employee_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
// This interface extends JpaRepository, which provides CRUD operations for the User entity.
// It also includes a method to find a user by their username, which returns an Optional<User>.