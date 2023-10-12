package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class AssetAssignModel {

    private int id;

    private Date assignDate;

    private String assignRemarks;

    private Date returnDate;

    private String returnRemarks;

    private EmployeeModel employeeModel;
    private long employeeId;

    private AssetModel assetModel;
    private int assetId;
}
