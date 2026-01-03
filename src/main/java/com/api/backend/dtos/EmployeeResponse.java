package com.api.backend.dtos;

public record EmployeeResponse(long id, String CPF, String email, String name, float salary) {
    
}
