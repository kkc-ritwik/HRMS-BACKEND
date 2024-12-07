package com.texsmartly.PayrollPage.model.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryComponent {
    @Id
    private String id;
    private String userId;
    private String createdDate;
    private List<Earning> earnings = new ArrayList<>();
    private List<PreTaxDeduction>preTaxDeductions = new ArrayList<>();
    private List<PostTaxDeduction>postTaxDeductions = new ArrayList<>();
    private List<OtherDeductions> otherDeductions = new ArrayList<>();
    private List<Reimbursement>reimbursements = new ArrayList<>();

}
