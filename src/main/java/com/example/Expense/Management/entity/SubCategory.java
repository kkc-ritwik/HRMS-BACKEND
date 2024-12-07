package com.example.Expense.Management.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subcategories")
@Data
public class SubCategory {
    @Id
    private String id;
    private String categoryId;
    private String subcategoryName;
    private String subcategoryDescription;
    private Double taxRate;
}
