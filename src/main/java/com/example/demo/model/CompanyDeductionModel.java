package com.example.demo.model;

import lombok.Data;

@Data
public class CompanyDeductionModel {

    private Long id;

    private  int pfPercentage;

    private  int esiPercentage;

    private long companyId;

    private CompanyModel companyModel;
}
