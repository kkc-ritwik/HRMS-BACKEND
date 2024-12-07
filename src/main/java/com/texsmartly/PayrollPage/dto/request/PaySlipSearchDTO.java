package com.texsmartly.PayrollPage.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaySlipSearchDTO {
    private String employeeId;
    private LocalDate fromDate;
    private LocalDate toDate;
}
