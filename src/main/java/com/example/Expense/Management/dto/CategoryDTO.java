package com.example.Expense.Management.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    private String id;
    private String name;
    private String description;
    private Double taxRate;
}
