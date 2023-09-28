package com.example.demo.repository;

import com.example.demo.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByEmployeeIdAndMonthAndYear (Long employeeId , String Month , int year);

    Payment findByEmployeeId (Long employeeId );
}
