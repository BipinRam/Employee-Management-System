package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
public class AssetModel {

    private int id;

    private String serialNo;

    private String modelName;

    private Date manufacturingDate;

    private String specification;

    private LocalDate addedDate;

    private BranchModel branchModel;
    private long branchId;

    private AssetTypeModel assetTypeModel;
    private  int assetTypeId;
}
