package com.example.demo.model;

import lombok.Data;

@Data
public class EmployeeSalaryModel {
    private Long Id;

    private int basic;

    private int dearnessAllowance;

    private int houseRentAllowance;

    private int otherAdditions;

    private  int totalPay;

    private Long employeeId;

    private EmployeeModel employee;
}
