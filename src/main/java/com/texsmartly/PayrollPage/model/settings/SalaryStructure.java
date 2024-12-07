package com.texsmartly.PayrollPage.model.settings;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "salaryStructure")
public class SalaryStructure {
    @Id
    private String id;
    private String name;
    private double monthly;
    private double annual;
}

