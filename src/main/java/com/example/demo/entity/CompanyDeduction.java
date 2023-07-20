package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "companyDeductions")
public class CompanyDeduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "PF")
    private  int pfPercentage;

    @Column(name = "ESI")
    private  int esiPercentage;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
