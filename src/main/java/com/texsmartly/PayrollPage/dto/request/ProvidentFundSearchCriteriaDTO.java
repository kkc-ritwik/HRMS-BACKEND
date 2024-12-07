package com.texsmartly.PayrollPage.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvidentFundSearchCriteriaDTO {
    String id = UUID.randomUUID().toString();
    private String employeeId;
    private LocalDate fromDate;
    private LocalDate toDate;

}
