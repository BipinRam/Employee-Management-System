package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "deductions")
public class Deduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "PF")
    private  int providentFund;

    @Column(name = "ESI")
    private  int employeeStateFund;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
