package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class LeaveApplicationModel {

    private Long id;

    private Date fromDate;

    private Date toDate;

    private int numberOfLeave;

    private String reason;

    private String status;

    private Date appliedDate;

    private Date actionDate;

    private String actionRemarks;

    private Long employeeId;

    private Long branchId;

    private EmployeeModel employee;

    private BranchModel branchModel;
}
