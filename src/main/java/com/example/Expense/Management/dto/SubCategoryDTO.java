package com.example.Expense.Management.dto;

import lombok.Data;

@Data
public class SubCategoryDTO {
    private String id;
    private String categoryId;
    private String subcategoryName;
    private String subcategoryDescription;
    private Double taxRate;
}
