package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Salary")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private int year;

    @Column(name = "basic")
    private  int basic;

//DA - Dearness Allowance
    @Column(name = "dearness_Allowance")
    private int dearnessAllowance;
//HRA - House Rent Allowance
    @Column(name ="House_Rent_Allowance" )
    private int houseRentAllowance;

    @Column(name = "other_Additions")
    private int otherAdditions;


    @Column(name = "net_Salary")
    private int netSalary;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "deduction_id")
    private Deduction deduction;


}
