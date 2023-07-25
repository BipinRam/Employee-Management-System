package com.example.demo.model;

import lombok.Data;

@Data
public class DesignationModel {

    private Long id;

    private String designationName;

    private String designationCode;

    private long companyId;

    private CompanyModel company;


}
