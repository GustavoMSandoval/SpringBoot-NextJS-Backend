package com.api.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String CPF;

    @NotBlank(message = "Email cannot be blank!")
    @Email(message = "Not valid Email!")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Name cannot be blank!")
    @Size(min = 2, message = "Name must be at least 2 characters long!")
    @Column(nullable = false)
    private String name;

    @DecimalMin(value = "3.00", message = "Salary too low!")
    @Positive(message = "Salary must be greater than zero")
    @Column(nullable = false)
    private float salary;

    public Employee(){}

    public Employee(String CPF, String email, String name, float salary) {
        this.CPF = CPF;
        this.email = email;
        this.name = name;
        this.salary = salary;
    }

    public String getCPF() {
        return CPF;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public float getSalary() {
        return salary;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setSalary(float salary) {
        this.salary = salary;
    }

}