package com.texsmartly.PayrollPage.model.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "candidates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidates {
    @Id
    private String id;
    private String name;
    private String designation;
    private String department;
    private List<SalaryStructure> salaryStructures;

}
