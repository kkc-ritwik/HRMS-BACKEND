package com.texsmartly.PayrollPage.dto;

import com.texsmartly.PayrollPage.model.settings.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Salary_Components")
public class SalaryComponentDTO {
    @Id
    private String id;
    private String userId;
    private List<Earning> earnings = new ArrayList<>();
    private List<PreTaxDeduction>preTaxDeductions = new ArrayList<>();
    private List<PostTaxDeduction>postTaxDeductions = new ArrayList<>();
    private List<OtherDeductions> otherDeductions = new ArrayList<>();
    private List<Reimbursement>reimbursements = new ArrayList<>();

}
