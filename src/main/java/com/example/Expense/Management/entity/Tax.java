package com.example.Expense.Management.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "taxes")
@Data
public class Tax {
    @Id
    private String id;
    private String taxType;
    private Double taxRate;
    private String applicableCategory;
}
