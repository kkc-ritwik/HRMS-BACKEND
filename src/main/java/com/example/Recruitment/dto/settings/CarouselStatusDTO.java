package com.example.Recruitment.dto.settings;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarouselStatusDTO {
    private String id; // For identification

    @NotNull(message = "New Application status is required")
    private Boolean newApplication;

    @NotNull(message = "Telephonic Round status is required")
    private Boolean telephonicRound;

    @NotNull(message = "Interview 1 status is required")
    private Boolean interview1;

    @NotNull(message = "Assessment 1 status is required")
    private Boolean assessment1;

    @NotNull(message = "Assessment 2 status is required")
    private Boolean assessment2;

    @NotNull(message = "Interview 2 status is required")
    private Boolean interview2;

    @NotNull(message = "Interview 3 status is required")
    private Boolean interview3;

    @NotNull(message = "HR Round status is required")
    private Boolean hrRound;

    @NotNull(message = "Background Verification status is required")
    private Boolean backgroundVerification;

    @NotNull(message = "Ready to Hire status is required")
    private Boolean readyToHired;

    @NotNull(message = "Rejected status is required")
    private Boolean rejected;

    @NotNull(message = "Hired status is required")
    private Boolean hired;

    @NotNull(message = "Onboarded status is required")
    private Boolean onboarded;
}
