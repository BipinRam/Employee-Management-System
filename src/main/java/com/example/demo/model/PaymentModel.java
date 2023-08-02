package com.example.demo.model;

import lombok.Data;

@Data
public class PaymentModel {

    private Long id;

    private String month;

    private int year;

    private int netPay;

    private Long employeeId;
    private EmployeeModel employee;

    private Long deductionId;
    private DeductionModel deduction;

    private Long employeeSalaryId;
    private EmployeeSalaryModel employeeSalaryModel;


}
