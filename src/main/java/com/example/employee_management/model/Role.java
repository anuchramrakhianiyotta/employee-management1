// Purpose: Defines user roles like "ADMIN" or "USER", used to control access
package com.example.employee_management.model; // This declares the package name.

import jakarta.persistence.*; // Gives you JPA annotations like @Entity, @Id, etc.
import lombok.Getter;
import lombok.Setter;
import java.util.*; // Imports List and ArrayList, used for mapping users under a role.
import com.fasterxml.jackson.annotation.JsonIgnore; // Prevents serialization of lazy-loaded fields

// Required for JPA to populate data from the DB
@Setter
@Getter
@Entity // Marks this class as a JPA entity (a table in the DB).
@Table(name = "roles") // Explicitly names the table "roles"
public class Role {

    @Id // This field is the primary key in the DB table.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementing ID (1, 2, 3, ...).
    private Long id; // Role ID

    private String name; // This field stores the name of the role. It maps to a column in the DB

    // One Role can be assigned to many Users
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonIgnore  // Prevents JSON serialization from accessing it
    private List<User> users = new ArrayList<>(); // This maps the users belonging to the role

    public Role() {
        // Required by JPA — used internally to create Role objects from the DB.
    }

    public Role(String name) {
        // A custom constructor so we can easily create roles like new Role("ADMIN")
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", usersCount=" + (users != null ? users.size() : 0) +
                '}';
    }
}
