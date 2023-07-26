package com.example.demo.model;

import lombok.Data;

@Data
public class BranchModel {

    private Long id;

    private String branchName;

    private String branchCode;

    private  long companyId;

    private CompanyModel company;
}
