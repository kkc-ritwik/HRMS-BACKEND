package com.texsmartly.PayrollPage.dto.request;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class ClaimRequestSearchDTO {
    @Id
    private String id;
    private LocalDate fromDate;
    private LocalDate toDate;
}
