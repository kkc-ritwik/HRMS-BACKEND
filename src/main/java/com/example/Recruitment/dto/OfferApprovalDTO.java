package com.example.Recruitment.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OfferApprovalDTO {

    private String id; // This may be generated and thus not require validation

    @NotBlank(message = "Candidate ID is required")
    private String candidateId;

    @NotBlank(message = "Candidate name is required")
    @Size(max = 100, message = "Candidate name should not exceed 100 characters")
    private String candidateName;

    @NotBlank(message = "Current designation is required")
    private String currentDesignation;

    @NotBlank(message = "Offered designation is required")
    private String offeredDesignation;

    @NotNull(message = "Current CTC is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Current CTC must be positive")
    private Double currentCTC;

    @NotNull(message = "Offered CTC is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Offered CTC must be positive")
    private Double offeredCTC;

    @NotNull(message = "Total experience is required")
    @Positive(message = "Total experience must be positive")
    private Double totalExperience;

    @NotBlank(message = "Job location is required")
    private String jobLocation;

    private String interviewedBy;

    @Size(max = 500, message = "Interview feedback should not exceed 500 characters")
    private String interviewFeedback;

    @NotBlank(message = "Status is required")
    private String status;
}
