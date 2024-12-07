package com.texsmartly.PayrollPage.model.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxDeductorDetails {
    private String id;
    private String userId;
    private String deductorType; // "Employee" or "Non-Employee"
    private String deductorName;
    private String deductorFatherName;
}
