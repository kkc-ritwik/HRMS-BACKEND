package com.texsmartly.PayrollPage.dto.response;

import jakarta.validation.constraints.NotNull;

public class ClaimResponseDTO {
    @NotNull(message = "Possible status: 'Claim Changed', 'Claim In Progress', 'Claim Rejected'")
    private String statusChanged;





}
