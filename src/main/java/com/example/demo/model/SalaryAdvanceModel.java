package com.example.demo.model;

import com.example.demo.entity.Employee;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
public class SalaryAdvanceModel {

    private Long id;

    private String month;

    private int year;

    private Long advanceAmount;

    private String status;

    private LocalDate appliedDate;

    private Date actionDate;

    private int netPay;

    private String actionRemarks;

    private Long employeeId;

    private EmployeeModel employee;
}
