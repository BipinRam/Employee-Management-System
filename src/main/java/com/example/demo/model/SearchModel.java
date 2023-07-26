package com.example.demo.model;

import lombok.Data;

@Data
public class SearchModel {

    private Long id;

    private String month;

    private int year;

    private int netSalary;

    private Long employeeId;
}
