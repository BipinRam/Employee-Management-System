package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "assetAssign")
public class AssetAssign {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "assign_date")
    private Date assignDate;

    @Column(name = "assign_remarks")
    private String assignRemarks;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "return_remarks")
    private String returnRemarks;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;
}
