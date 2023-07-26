package com.example.demo.model;

import lombok.Data;

@Data
public class EmployeeModel {

    private Long id;
    private String employeeFullName;
    private String mobileNumber;
    private String email;
    private String bloodGroup;

    private long branchId;
    private BranchModel branch;

    private long departmentId;
    private DepartmentModel department;

    private long designationID;
    private DesignationModel designation;
}
