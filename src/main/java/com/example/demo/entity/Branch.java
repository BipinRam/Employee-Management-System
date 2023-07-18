package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "branch_name")
    private String branchName;


    @Column(name = "branch_code")
    private String branchCode;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;



}
