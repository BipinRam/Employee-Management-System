package com.example.demo.model;

import lombok.Data;

@Data
public class DepartmentModel {

    private Long id;

    private String departmentName;

    private String departmentCode;

    private long companyId;

    private CompanyModel companyModel;
}
