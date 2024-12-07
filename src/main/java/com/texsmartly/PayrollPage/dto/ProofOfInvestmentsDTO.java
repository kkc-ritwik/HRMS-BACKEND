package com.texsmartly.PayrollPage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProofOfInvestmentsDTO {
    @Id
    private String id;
    private String userId;
    private String cinInvestmentsName;
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
