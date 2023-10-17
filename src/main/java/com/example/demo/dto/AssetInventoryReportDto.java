package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssetInventoryReportDto {

    private String branchName;
    private String type;
    private String serialNo;
    private String modelName;
    private Date manufacturingDate;
    private String specification;
    private LocalDate addedDate;

}
