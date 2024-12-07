package com.texsmartly.PayrollPage.service.interf;

import com.texsmartly.PayrollPage.dto.EmployeeSalaryDetailsDTO;
import com.texsmartly.PayrollPage.model.payroll.EmployeeSalaryDetails;
import com.texsmartly.PayrollPage.model.settings.SalaryComponent;

import java.util.List;

public interface EmployeeSalaryDetailService {

    EmployeeSalaryDetails calculateAndSaveEmployeeSalary(String userId, double annualCTC, String financialYear);


    void calculateEarnings(EmployeeSalaryDetails employeeSalaryDetails,
                           SalaryComponent salaryComponent,
                           double annualCTC);

    void calculatePreTaxDeductions(EmployeeSalaryDetails employeeSalaryDetails,
                                   SalaryComponent salaryComponent,
                                   double annualCTC);

    void calculatePostTaxDeductions(EmployeeSalaryDetails employeeSalaryDetails,
                                    SalaryComponent salaryComponent,
                                    double annualCTC);

    void calculateOtherDeductions(EmployeeSalaryDetails employeeSalaryDetails,
                                  SalaryComponent salaryComponent,
                                  double annualCTC);

    void calculateReimbursements(EmployeeSalaryDetails employeeSalaryDetails,
                                 SalaryComponent salaryComponent,
                                 double annualCTC);

    List<EmployeeSalaryDetailsDTO> getEmployeeSalaryDetails(String userId);

    List<EmployeeSalaryDetailsDTO> getAllEmployeeSalaryDetails();

    EmployeeSalaryDetailsDTO getSalaryDetailsByUserId(String userId);



//    List<EmployeeSalaryDetails> getSalaryDetailsByFinancialYearAndMonth(String financialYear, String month);
}
