package com.texsmartly.PayrollPage.model.payroll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "EmployeeInvestments")
public class ProofOfInvestments {
    @Id
    private String id;
    private String userId;
    private String  cinInvestmentsName;
    private String dinInvestmentsName;
    private String otherInvestmentsName;

    private Long cinInvestmentsDeclaredAmount;
    private Long dinInvestmentsDeclaredAmount;
    private Long otherInvestmentsDeclaredAmount;

    private Long cinInvestmentsActualAmount;
    private Long dinInvestmentsActualAmount;
    private Long otherInvestmentsActualAmount;

    private String cinInvestmentsProofs;
    private String dinInvestmentsProofs;
    private String otherInvestmentsProofs;

    private String cinInvestmentsComments;
    private String dinInvestmentsComments;
    private String otherInvestmentsComments;

}
