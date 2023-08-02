package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private int year;

    @Column(name = "net_Pay")
    private int netPay;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "deduction_id")
    private Deduction deduction;

    @ManyToOne
    @JoinColumn(name = "employeeSalary_id")
    private  EmployeeSalary employeeSalary;

}
