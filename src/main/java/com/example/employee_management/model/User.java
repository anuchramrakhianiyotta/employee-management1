package com.example.employee_management.model; // This declares the model package

import jakarta.persistence.*; // JPA annotations
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // Marks this class as a JPA entity (maps to a table)
@Table(name = "users") // Explicitly names the DB table as 'users'
public class User {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented ID
    private Long id;

    @Column(unique = true, nullable = false) // Must be unique and not null
    private String username;

    @Column(nullable = false) // Must be present
    private String password;

    // Many users can have the same role (e.g., many users with 'USER' role)
    @ManyToOne(fetch = FetchType.EAGER) // Fetch role eagerly to avoid lazy load issues
    @JoinColumn(name = "role_id", nullable = false) // Foreign key column
    private Role role;

    // Default constructor — required by JPA
    public User() {}

    // Custom constructor for easy creation
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + (role != null ? role.getName() : "null") +
                '}';
    }
}
