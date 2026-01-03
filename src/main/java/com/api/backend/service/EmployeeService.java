package com.api.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.backend.dtos.EmployeeRequest;
import com.api.backend.dtos.EmployeeResponse;
import com.api.backend.entity.Employee;
import com.api.backend.repository.EmployeeRepository;
import com.api.backend.validator.CPFValidator;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    private final CPFValidator cpfValidator;

    public EmployeeService(EmployeeRepository repository, CPFValidator cpfValidator) {
        this.repository = repository;
        this.cpfValidator = cpfValidator;
    }

    private EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse(
            employee.getCPF(), 
            employee.getEmail(), 
            employee.getName(), 
            employee.getSalary()
        );
    }

    public EmployeeResponse create(EmployeeRequest request) {

        if(!cpfValidator.isValid(request.CPF())) {
            throw new RuntimeException("Invalid CPF!");
        }

        Employee employee = new Employee(
            request.CPF(), 
            request.email(), 
            request.name(), 
            request.salary()
        );

        return toResponse(repository.save(employee));
        

    }

    
    public EmployeeResponse findById(Long id) {

        return toResponse(repository.findById(id).get());

    }
    
    public List<EmployeeResponse> list() {
        return repository.findAll()
        .stream()
        .map(this::toResponse)
        .toList();
    }


    public EmployeeResponse update(Long id, EmployeeRequest request) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Employee not found!");
        }

        Employee employee = repository.findById(id).get();

        employee.setEmail(request.email());
        employee.setSalary(request.salary());

        repository.save(employee);

        return toResponse(employee);
    }

    public void delete(Long id) {
        
        if(!repository.existsById(id)) {
            throw new RuntimeException("Employee not found!");
        }
        
        repository.deleteById(id);
    }
}
