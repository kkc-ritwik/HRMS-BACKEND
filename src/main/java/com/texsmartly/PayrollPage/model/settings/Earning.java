package com.texsmartly.PayrollPage.model.settings;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
@Data
@AllArgsConstructor

@Document(collection = "Earnings")
public class Earning {

    @Id
    private String id;
    private String earningName;
    private String earningType;
    private String calculationType; // e.g., "Fixed Amount", "Percentage of CTC"
    private double percentageOfCTC; // For calculationType as "Percentage of CTC"
    private double fixedAmount; // For calculationType as "Fixed Amount"
    @NotNull(message = "Earning monthly amount is required")
    private double monthlyEarningAmount;

    @NotNull(message = "Earning monthly amount is required")
    private double annualEarningAmount;
    private boolean status;


}
