package com.texsmartly.PayrollPage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeProvidentFundDTO {
    @Id
    private String id;
    private String userId;
    private String epfNumber;
    private String deductionCycle;
    private String employeeContributionRate;
    private String employerContributionRate;
    private boolean includeInCtc;
    private boolean includeEditContributionInCtc;
    private boolean includeAdminChargesInCtc;
    private boolean overridePfContributionRateAtEmployeeLevel;
    private boolean proRateRestrictedPfWage;
    private boolean considerSalaryComponentsOnLop;
    private boolean eligibleForAbryScheme;
    private String contributionType;
}
