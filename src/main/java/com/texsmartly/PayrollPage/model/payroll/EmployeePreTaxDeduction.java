package com.texsmartly.PayrollPage.model.payroll;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePreTaxDeduction {
    @Id
    private String id;
    private String name;
    private String deductionType;
    private String calculationType;
    private double percentageOfCTC;
    private double monthlyAmount;
    private double annualAmount;
}
