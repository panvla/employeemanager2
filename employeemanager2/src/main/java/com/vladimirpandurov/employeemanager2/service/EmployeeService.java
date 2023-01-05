package com.vladimirpandurov.employeemanager2.service;

import com.vladimirpandurov.employeemanager2.exception.UserNotFoundException;
import com.vladimirpandurov.employeemanager2.model.Employee;
import com.vladimirpandurov.employeemanager2.repository.EmployeeRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    public Employee findEmployeeById(Long id){
        return employeeRepo.findEmployeeById(id).orElseThrow(()-> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();
    }

    public void deleteEmployee(Long id){
        employeeRepo.deleteEmployeeById(id);
    }
}
