package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table
public class LeaveApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status")
    private  String status;

    @Column(name = "applied_date")
    private Date appliedDate;

    @Column(name = "action_date")
    private Date actionDate;

    @Column(name = "action_remarks")
    private  String actionRemarks;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

}
