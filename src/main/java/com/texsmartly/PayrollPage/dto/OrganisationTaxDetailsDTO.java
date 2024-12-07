package com.texsmartly.PayrollPage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationTaxDetailsDTO {
    @Id
    private String id;
    private String userId;
    private String pan;
    private String tan;
    private String tdsCircle;
    private String taxPaymentFrequency;
}
