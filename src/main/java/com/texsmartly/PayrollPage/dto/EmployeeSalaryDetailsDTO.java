package com.texsmartly.PayrollPage.dto;

import com.texsmartly.PayrollPage.model.payroll.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSalaryDetailsDTO {
    @Id
    private String id;
    private String userId;
    private double monthlySalary;
    private double annualCTC;
    private String financialYear; // New field to store financial year
    private List<EmployeeEarning> earnings = new ArrayList<>();
    private List<EmployeePreTaxDeduction> preTaxDeductions = new ArrayList<>();
    private List<EmployeePostTaxDeduction> postTaxDeductions = new ArrayList<>();
    private List<EmployeeOtherDeduction> otherDeductions = new ArrayList<>();
    private List<EmployeeReimbursements> reimbursements = new ArrayList<>();
    private double totalMonthlyEarnings;
    private double totalAnnualEarnings;
    private double totalMonthlyDeductions;
    private double totalAnnualDeductions;
    private double totalMonthlyReimbursements;
    private double totalAnnualReimbursements;
    private double netMonthlyPay;
    private double netAnnualPay;

}
