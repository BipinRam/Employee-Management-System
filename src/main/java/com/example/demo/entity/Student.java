package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name =  "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "student_id")
    private long studentId;

    @Column(name = "rollNo")
    private int rollNo;

    @Column(name = "name")
    private String name;

}
