package com.texsmartly.PayrollPage.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatesDTO {
    private String id;
    private String name;
    private String designation;
    private String department;
    private List<SalaryStructureDTO> salaryStructures;
}