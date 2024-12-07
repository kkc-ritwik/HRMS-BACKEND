package com.example.Expense.Management.dto;

import lombok.Data;

@Data
public class TaxDTO {
    private String id;
    private String taxType;
    private Double taxRate;
    private String applicableCategory;
}
