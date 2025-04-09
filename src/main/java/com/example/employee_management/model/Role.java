package com.example.employee_management.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role {

    // Getters and setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // e.g., "ADMIN", "USER"

    @OneToMany(mappedBy = "role")
    private List<User> users = new ArrayList<>();

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

}