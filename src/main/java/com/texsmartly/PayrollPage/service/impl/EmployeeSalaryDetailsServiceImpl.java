package com.texsmartly.PayrollPage.service.impl;

import com.texsmartly.PayrollPage.dto.EmployeeSalaryDetailsDTO;
import com.texsmartly.PayrollPage.model.payroll.*;
import com.texsmartly.PayrollPage.model.settings.OtherDeductions;
import com.texsmartly.PayrollPage.model.settings.Reimbursement;
import com.texsmartly.PayrollPage.repository.payroll.EmployeeSalaryDetailsRepository;

import com.texsmartly.PayrollPage.model.settings.SalaryComponent;
import com.texsmartly.PayrollPage.repository.settings.SalaryComponentRepository;
import com.texsmartly.PayrollPage.service.interf.EmployeeSalaryDetailService;
import com.texsmartly.PayrollPage.service.interf.SalaryComponentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeSalaryDetailsServiceImpl implements EmployeeSalaryDetailService {
    @Autowired
    private EmployeeSalaryDetailsRepository employeeSalaryDetailsRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SalaryComponentRepository salaryComponentRepository;
    @Autowired
    private SalaryComponentService salaryComponentService;


    @Override
    public EmployeeSalaryDetails calculateAndSaveEmployeeSalary(String userId, double annualCTC, String financialYear) {
        // Find SalaryComponent configuration for the user
        SalaryComponent salaryComponent = salaryComponentService.getSalaryComponentByUserIdOrDefault(userId);


        // Initialize EmployeeSalaryDetails
        EmployeeSalaryDetails employeeSalaryDetails = new EmployeeSalaryDetails();
        employeeSalaryDetails.setUserId(userId);
        employeeSalaryDetails.setAnnualCTC(annualCTC);
        employeeSalaryDetails.setMonthlySalary(annualCTC / 12);
        employeeSalaryDetails.setFinancialYear(financialYear);

        // Perform calculations and set values
        calculateEarnings(employeeSalaryDetails, salaryComponent, annualCTC);
        calculatePreTaxDeductions(employeeSalaryDetails, salaryComponent, annualCTC);
        calculatePostTaxDeductions(employeeSalaryDetails, salaryComponent, annualCTC);
        calculateOtherDeductions(employeeSalaryDetails, salaryComponent, annualCTC);
        calculateReimbursements(employeeSalaryDetails, salaryComponent, annualCTC);

        // Calculate totals and net pay
        calculateTotalsAndNetPay(employeeSalaryDetails);

        // Save to EmployeeSalaryDetailsRepository
        return employeeSalaryDetailsRepository.save(employeeSalaryDetails);
    }

    @Override
public void calculateEarnings(EmployeeSalaryDetails employeeSalaryDetails,
                              SalaryComponent salaryComponent,
                              double annualCTC) {
        salaryComponent.getEarnings().forEach(earning -> {
            EmployeeEarning employeeEarning = new EmployeeEarning();
            employeeEarning.setId(earning.getId());
            employeeEarning.setName(earning.getEarningName());
            employeeEarning.setEarningType(earning.getEarningType());
            employeeEarning.setCalculationType(earning.getCalculationType());
            employeeEarning.setPercentageOfCTC(earning.getPercentageOfCTC());

            if ("Percentage of CTC".equals(earning.getCalculationType())) {
                double annualAmount = (annualCTC * earning.getPercentageOfCTC()) / 100;
                employeeEarning.setAnnualAmount(annualAmount);
                employeeEarning.setMonthlyAmount(annualAmount / 12);
            } else {
                employeeEarning.setAnnualAmount(earning.getFixedAmount() * 12);
                employeeEarning.setMonthlyAmount(earning.getFixedAmount());
            }

            employeeSalaryDetails.getEarnings().add(employeeEarning);
        });
    }
@Override
public void calculatePreTaxDeductions(EmployeeSalaryDetails employeeSalaryDetails,
                                      SalaryComponent salaryComponent,
                                      double annualCTC) {
        salaryComponent.getPreTaxDeductions().forEach(preTax -> {
            EmployeePreTaxDeduction employeePreTax = new EmployeePreTaxDeduction();
            employeePreTax.setId(preTax.getId());
            employeePreTax.setName(preTax.getName());
            employeePreTax.setDeductionType(preTax.getDeductionType());
            employeePreTax.setCalculationType(preTax.getCalculationType());
            employeePreTax.setPercentageOfCTC(preTax.getPercentageOfCTC());

            if ("Percentage of CTC".equals(preTax.getCalculationType())) {
                double annualAmount = (annualCTC * preTax.getPercentageOfCTC()) / 100;
                employeePreTax.setAnnualAmount(annualAmount);
                employeePreTax.setMonthlyAmount(annualAmount / 12);
            } else {
                employeePreTax.setAnnualAmount(preTax.getFixedAmount() * 12);
                employeePreTax.setMonthlyAmount(preTax.getFixedAmount());
            }

            employeeSalaryDetails.getPreTaxDeductions().add(employeePreTax);
        });
    }
@Override
public void calculatePostTaxDeductions(EmployeeSalaryDetails employeeSalaryDetails,
                                       SalaryComponent salaryComponent,
                                       double annualCTC) {
        salaryComponent.getPostTaxDeductions().forEach(postTax -> {
            EmployeePostTaxDeduction employeePostTax = new EmployeePostTaxDeduction();
            employeePostTax.setId(postTax.getId());
            employeePostTax.setName(postTax.getName());
            employeePostTax.setDeductionType(postTax.getDeductionType());
            employeePostTax.setCalculationType(postTax.getCalculationType());
            employeePostTax.setPercentageOfCTC(postTax.getPercentageOfCTC());

            if ("Percentage of CTC".equals(postTax.getCalculationType())) {
                double annualAmount = (annualCTC * postTax.getPercentageOfCTC()) / 100;
                employeePostTax.setAnnualAmount(annualAmount);
                employeePostTax.setMonthlyAmount(annualAmount / 12);
            } else {
                if ("Monthly".equals(postTax.getDeductionFrequency())) {
                    employeePostTax.setMonthlyAmount(postTax.getFixedAmount());
                    employeePostTax.setAnnualAmount(postTax.getFixedAmount() * 12);
                } else if ("Annual".equals(postTax.getDeductionFrequency())) {
                    employeePostTax.setAnnualAmount(postTax.getFixedAmount());
                    employeePostTax.setMonthlyAmount(postTax.getFixedAmount() / 12);
                }
            }

            employeeSalaryDetails.getPostTaxDeductions().add(employeePostTax);
        });
    }
@Override

public void calculateOtherDeductions(EmployeeSalaryDetails employeeSalaryDetails,
                                     SalaryComponent salaryComponent,
                                     double annualCTC) {
    double totalMonthlyOtherDeductions = 0.0;
    double totalAnnualOtherDeductions = 0.0;

    for (OtherDeductions other : salaryComponent.getOtherDeductions()) {
        EmployeeOtherDeduction employeeOther = new EmployeeOtherDeduction();
        employeeOther.setId(other.getId());
        employeeOther.setName(other.getName());
        employeeOther.setDeductionType(other.getOtherDeductionType());
        employeeOther.setCalculationType(other.getCalculationType());

        double monthlyAmount = 0.0;
        double annualAmount = 0.0;

        if ("Fixed Amount".equals(other.getCalculationType())) {
            monthlyAmount = other.getFixedAmount();
            annualAmount = other.getFixedAmount() * 12;
        } else if ("Percentage of CTC".equals(other.getCalculationType())) {
            annualAmount = (annualCTC * other.getPercentageOfCTC()) / 100;
            monthlyAmount = annualAmount / 12;
        }

        employeeOther.setMonthlyAmount(monthlyAmount);
        employeeOther.setAnnualAmount(annualAmount);

        totalMonthlyOtherDeductions += monthlyAmount;
        totalAnnualOtherDeductions += annualAmount;

        employeeSalaryDetails.getOtherDeductions().add(employeeOther);
    }


}

    @Override
    public void calculateReimbursements(EmployeeSalaryDetails employeeSalaryDetails,
                                        SalaryComponent salaryComponent,
                                        double annualCTC) {
        double totalMonthlyReimbursements = 0.0;
        double totalAnnualReimbursements = 0.0;

        for (Reimbursement reimburse : salaryComponent.getReimbursements()) {
            EmployeeReimbursements employeeReimbursement = new EmployeeReimbursements();
            employeeReimbursement.setId(reimburse.getId()); // Add ID field if missing
            employeeReimbursement.setReimbursementName(reimburse.getReimbursementName());
            employeeReimbursement.setMonthlyEligibleAmount(reimburse.getMonthlyEligibleAmount());
            employeeReimbursement.setAnnualEligibleAmount(reimburse.getMonthlyEligibleAmount() * 12);

            totalMonthlyReimbursements += employeeReimbursement.getMonthlyEligibleAmount();
            totalAnnualReimbursements += employeeReimbursement.getAnnualEligibleAmount();
            employeeSalaryDetails.getReimbursements().add(employeeReimbursement);
        }

        employeeSalaryDetails.setTotalMonthlyReimbursements(totalMonthlyReimbursements);
        employeeSalaryDetails.setTotalAnnualReimbursements(totalAnnualReimbursements);
    }

    private void calculateTotalsAndNetPay(EmployeeSalaryDetails employeeSalaryDetails) {
        // Calculate total earnings
        double totalMonthlyEarnings = employeeSalaryDetails.getEarnings().stream()
                .mapToDouble(EmployeeEarning::getMonthlyAmount)
                .sum();
        double totalAnnualEarnings = employeeSalaryDetails.getEarnings().stream()
                .mapToDouble(EmployeeEarning::getAnnualAmount)
                .sum();

        // Calculate total deductions (including other deductions)
        double totalMonthlyDeductions =
                employeeSalaryDetails.getPreTaxDeductions().stream()
                        .mapToDouble(EmployeePreTaxDeduction::getMonthlyAmount)
                        .sum() +
                        employeeSalaryDetails.getPostTaxDeductions().stream()
                                .mapToDouble(EmployeePostTaxDeduction::getMonthlyAmount)
                                .sum() +
                        employeeSalaryDetails.getTotalMonthlyOtherDeductions();

        double totalAnnualDeductions =
                employeeSalaryDetails.getPreTaxDeductions().stream()
                        .mapToDouble(EmployeePreTaxDeduction::getAnnualAmount)
                        .sum() +
                        employeeSalaryDetails.getPostTaxDeductions().stream()
                                .mapToDouble(EmployeePostTaxDeduction::getAnnualAmount)
                                .sum() +
                        employeeSalaryDetails.getTotalAnnualOtherDeductions();

        // Set all calculated totals
        employeeSalaryDetails.setTotalMonthlyEarnings(totalMonthlyEarnings);
        employeeSalaryDetails.setTotalAnnualEarnings(totalAnnualEarnings);
        employeeSalaryDetails.setTotalMonthlyDeductions(totalMonthlyDeductions);
        employeeSalaryDetails.setTotalAnnualDeductions(totalAnnualDeductions);

        // Calculate and set net pay (including reimbursements)
        double netMonthlyPay = totalMonthlyEarnings - totalMonthlyDeductions +
                employeeSalaryDetails.getTotalMonthlyReimbursements();
        double netAnnualPay = totalAnnualEarnings - totalAnnualDeductions +
                employeeSalaryDetails.getTotalAnnualReimbursements();

        employeeSalaryDetails.setNetMonthlyPay(netMonthlyPay);
        employeeSalaryDetails.setNetAnnualPay(netAnnualPay);
    }

    public List<SalaryComponent> getSalaryComponentsByUserId(String userId) {
        return salaryComponentRepository.findByUserId(userId);
    }
@Override
public List<EmployeeSalaryDetailsDTO> getEmployeeSalaryDetails(String userId) {
    List<EmployeeSalaryDetails> employeeSalaryDetailsList = employeeSalaryDetailsRepository.findByUserId(userId);
    return employeeSalaryDetailsList.stream()
            .map(employeeSalaryDetails -> modelMapper.map(employeeSalaryDetails, EmployeeSalaryDetailsDTO.class))
            .collect(Collectors.toList());
}
    @Override
    public List<EmployeeSalaryDetailsDTO> getAllEmployeeSalaryDetails() {
        List<EmployeeSalaryDetails> allEmployeeSalaryDetails = employeeSalaryDetailsRepository.findAll();
        return allEmployeeSalaryDetails.stream()
                .map(employeeSalaryDetails -> modelMapper.map(employeeSalaryDetails, EmployeeSalaryDetailsDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public EmployeeSalaryDetailsDTO getSalaryDetailsByUserId(String userId) {
        return employeeSalaryDetailsRepository.findByUserId(userId).stream()
                .findFirst()
                .map(employeeSalaryDetails -> modelMapper.map(employeeSalaryDetails, EmployeeSalaryDetailsDTO.class))
                .orElse(null);
    }
//@Override
//    public List<EmployeeSalaryDetails> getSalaryDetailsByFinancialYearAndMonth(String financialYear) {
//        return employeeSalaryDetailsRepository.findByFinancialYearAndMonth(financialYear);
//    }


}
