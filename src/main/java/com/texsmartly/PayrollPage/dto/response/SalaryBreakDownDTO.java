package com.texsmartly.PayrollPage.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SalaryBreakDownDTO {
    private String name;
    private double annualAmount;
    private double monthlyAmount;
}
