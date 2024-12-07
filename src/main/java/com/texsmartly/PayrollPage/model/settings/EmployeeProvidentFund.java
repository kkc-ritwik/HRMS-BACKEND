package com.texsmartly.PayrollPage.model.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeProvidentFund {
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
