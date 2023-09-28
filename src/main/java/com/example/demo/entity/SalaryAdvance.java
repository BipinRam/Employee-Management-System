package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "salaryAdvance")
public class SalaryAdvance {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private int year;

    @Column(name = "advanceAmount")
    private Long advanceAmount;

    @Column(name = "status")
    private String status;

    @Column(name = "applied_date")
    private LocalDate appliedDate;

    @Column(name = "action_date")
    private Date actionDate;

    @Column(name = "action_remarks")
    private String actionRemarks;


    @ManyToOne
    @JoinColumn(name = "employee_id")
    private  Employee employee;

}
