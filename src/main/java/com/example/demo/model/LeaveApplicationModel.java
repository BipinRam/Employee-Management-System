package com.example.demo.model;

import com.example.demo.entity.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class LeaveApplicationModel {

    private Long id;

    private Date fromDate;

    private Date toDate;

    private String reason;

    private String status;

    private Date appliedDate;

    private Date actionDate;

    private String actionRemarks;

    private Long employeeId;

    private EmployeeModel employee;

    private BranchModel branchModel;
}
