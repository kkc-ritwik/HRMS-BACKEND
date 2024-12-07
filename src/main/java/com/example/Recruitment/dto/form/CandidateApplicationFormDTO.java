package com.example.Recruitment.dto.form;

import com.example.Recruitment.model.form.Round;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateApplicationFormDTO {

    private String id;

    @NotBlank(message = "Candidate ID is required")
    private String candidateId;

    @NotBlank(message = "Job Requisition ID is required")
    private String jobRequisitionId;

    // Contact Details
    @NotBlank(message = "Name is required")
    private String name;

    @Pattern(regexp = "\\d{10}", message = "Emergency contact must be a 10-digit number")
    private String emergencyContact;

    @Email(message = "Invalid email format")
    private String emailAddress;

    @Email(message = "Invalid secondary email format")
    private String secondaryEmailAddress;

    @Pattern(regexp = "\\d{10}", message = "Mobile number must be a 10-digit number")
    private String mobileNumber;

    // Personal Information
    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth;

    @Pattern(regexp = "[A-Z0-9]{8}", message = "Passport number should be 8 alphanumeric characters")
    private String passportNumber;

    private String bloodGroup;
    private String aadharAddress;

    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN format")
    private String panNumber;

    // Present Address
    private String presentAddressLine1;
    private String presentAddressLine2;
    private String presentCity;

    @Pattern(regexp = "\\d{6}", message = "Invalid present pin code")
    private String presentPinCode;

    private String presentState;

    // Permanent Address
    private String permanentAddressLine1;
    private String permanentAddressLine2;
    private String permanentCity;

    @Pattern(regexp = "\\d{6}", message = "Invalid permanent pin code")
    private String permanentPinCode;

    private String permanentState;

    // Job Details
    @NotBlank(message = "Position applied is required")
    private String positionApplied;

    private String startDate;
    private String currentEmployer;

    @PositiveOrZero(message = "Total years of experience must be zero or positive")
    private String totalYearOfExperience;

    private String jobLocation;

    @Min(value = 0, message = "Notice period cannot be negative")
    private int noticePeriod;

    private String currentCtc;
    private String expectedCtc;

    @Pattern(regexp = "^(http|https)://.*$", message = "LinkedIn link must be a valid URL")
    private String linkedinLink;

    private String coverLetter;

    private byte[] resume;

    private Round round1;
    private Round round2;
    private Round round3;
}
