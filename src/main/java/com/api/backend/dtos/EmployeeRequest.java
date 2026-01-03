package com.api.backend.dtos;

public record EmployeeRequest(
    String CPF, String email, String name, float salary) {
    
}
