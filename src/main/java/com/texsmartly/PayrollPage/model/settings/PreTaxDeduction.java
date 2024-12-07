package com.texsmartly.PayrollPage.model.settings;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "PreTaxDeduction")
public class PreTaxDeduction {
    @Id
    private String id;
    private String name;
    private String deductionType;
    private String calculationType;
    private double percentageOfCTC; // For calculationType as "Percentage of CTC"
    private double fixedAmount; // For calculationType as "Fixed Amount"
    @NotNull(message = "Earning monthly amount is required")
    private double monthlyPreTaxAmount;

    @NotNull(message = "Earning monthly amount is required")
    private double annualPreTaxAmount;
    private  String status;

}