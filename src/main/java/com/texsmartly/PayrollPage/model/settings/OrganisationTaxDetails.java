package com.texsmartly.PayrollPage.model.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationTaxDetails {
    @Id
    private String id;
    private String userId;
    private String pan;
    private String tan;
    private String tdsCircle;
    private String taxPaymentFrequency;
}
