package com.example.employee_management.controller;
import com.example.employee_management.model.Employee;
import com.example.employee_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController
{

    @Autowired
    private EmployeeService employeeService;

    // GET All Employees (Accessible by ADMIN and USER)
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<Employee>> getAllEmployees()
    {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // GET Employee by ID (Accessible by ADMIN and USER)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id)
    {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.isPresent()
                ? ResponseEntity.ok(employee.get())
                : ResponseEntity.notFound().build();
    }

    // CREATE Employee (Accessible by ADMIN and USER)
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(201).body(savedEmployee);
    }

    // UPDATE Employee (Accessible by ADMIN and USER)
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee)
    {
        try {
            Employee savedEmployee = employeeService.updateEmployee(id, updatedEmployee);
            return ResponseEntity.ok(savedEmployee);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE Employee (Accessible by ADMIN only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id)
    {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
