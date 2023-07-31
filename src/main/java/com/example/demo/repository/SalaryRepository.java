package com.example.demo.repository;

import com.example.demo.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary , Long> {
    Salary findByEmployeeIdAndMonthAndYear (Long employeeId , String Month , int year);
}
