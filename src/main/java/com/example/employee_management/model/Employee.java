package com.example.employee_management.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String department;
    private double salary;
    private String address;
    private String mobile;

    public Employee() {}

    public Employee(String firstName, String lastName, String department, double salary, String address, String mobile)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
        this.address = address;
        this.mobile = mobile;
    }
}