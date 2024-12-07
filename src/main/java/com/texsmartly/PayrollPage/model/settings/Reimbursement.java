package com.texsmartly.PayrollPage.model.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Reimbursements")
public class Reimbursement {
    @Id
    private String id;

    private String reimbursementName;
    private String reimbursementType;
    private String status;
    private Long monthlyEligibleAmount;
    private Long annualEligibleAmount;
}
