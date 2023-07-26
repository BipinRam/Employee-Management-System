package com.example.demo.model;

import lombok.Data;

@Data
public class SalaryModel {

    private Long id;

    private String month;

    private int year;

    private  int basic;

    private int dearnessAllowance;

    private int houseRentAllowance;

    private int otherAdditions;

    private int netSalary;

    private long employeeId;
    private EmployeeModel employee;

    private Long deductionId;
    private DeductionModel deduction;


}
