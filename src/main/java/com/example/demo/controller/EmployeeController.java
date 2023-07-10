package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeService empService;

    @GetMapping(value = "/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    @PostMapping( value="/employees")
    public Employee createEmployee(@RequestBody Employee emp) {
        return empService.createEmployee(emp);
    }
    @GetMapping(value="/employees")
    public List<Employee> readEmployees() {
        return empService.getEmployee();
    }

    @GetMapping(value="/employees/{id}")
    public Employee readEmployeePathVariable(@PathVariable Long id) {
        return empService.getEmployee(id);
    }

    @GetMapping(value="/employee")
    public Employee readEmployee(@RequestParam(required = false) Long id) {
        if(id == null)
            id = 2L;
        return empService.getEmployee(id);
    }

    @PutMapping(value="/employees/{empId}")
    public Employee readEmployee(@PathVariable(value = "empId") Long id, @RequestBody Employee empDetails) {
        return empService.updateEmployee(id, empDetails);
    }

    @DeleteMapping(value="/employees/{empId}")
    public void deleteEmployee(@PathVariable(value = "empId") Long id) {
        empService.deleteEmployee(id);
    }


}


