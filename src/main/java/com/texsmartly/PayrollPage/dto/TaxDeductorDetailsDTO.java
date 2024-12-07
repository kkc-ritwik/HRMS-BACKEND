package com.texsmartly.PayrollPage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxDeductorDetailsDTO {
    private String id;
    private String userId;
    private String deductorType; // "Employee" or "Non-Employee"
    private String deductorName;
    private String deductorFatherName;
}
