package com.example.demo.model;

import lombok.Data;

@Data
public class DeductionModel {

    private Long id;

    private  int providentFund;

    private  int employeeStateFund;

    private long companyId;

    private CompanyModel company;
}
