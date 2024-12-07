package com.texsmartly.PayrollPage.model.settings;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtherDeductions {
    @Id
    private String id;
    private String name;
    private String otherDeductionType;
    private String calculationType;
    private double percentageOfCTC; // For calculationType as "Percentage of CTC"
    private double fixedAmount; // For calculationType as "Fixed Amount"
    private String status;
    @NotNull(message = "Earning monthly amount is required")
    private double monthlyOtherDeductionAmount;

    @NotNull(message = "Earning monthly amount is required")
    private double annualOtherDeductionAmount;



}
