package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "employeeSalary")
public class EmployeeSalary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name = "basic")
    private int basic;

    @Column(name = "dearness_allowance")
    private int dearnessAllowance;

    @Column(name = "house_rent_allowance")
    private int houseRentAllowance;

    @Column(name = "other_additions")
    private int otherAdditions;

    @Column(name = "total_pay")
    private  int totalPay;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
