package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "designation")
public class Designation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "designation_name")
    private String designationName;

    @Column(name = "designation_code")
    private String designationCode;

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;
}
