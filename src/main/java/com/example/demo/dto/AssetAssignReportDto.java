package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssetAssignReportDto {
    private String type;
    private int id;
    private String serialNo;
    private String employeeFullName;
    private Date assignDate;
    private Date returnDate;
    private String assignRemarks;
    private String returnRemarks;


}
