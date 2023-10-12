package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "asset")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "serial_no" , unique = true)
    private String serialNo;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "manufacturing_date")
    private Date manufacturingDate;

    @Column(name = "specification")
    private String specification;

    @Column(name = "added_date")
    private LocalDate addedDate;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "assetType_id")
    private AssetType assetType;
}
