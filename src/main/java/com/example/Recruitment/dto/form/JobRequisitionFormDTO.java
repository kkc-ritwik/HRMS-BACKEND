package com.example.Recruitment.dto.form;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobRequisitionFormDTO {

    @NotBlank(message = "Job Requisition ID cannot be blank")
    private String jobRequisitionId;

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Hiring manager is required")
    private String hiringManager;

    @NotBlank(message = "Reporting manager is required")
    private String reportingManager;

    @NotBlank(message = "Recruiter is required")
    private String recruiter;

    @NotBlank(message = "Job location is required")
    private String jobLocation;

    @NotBlank(message = "CTC offered is required")
    private String ctcOffered;

    @NotBlank(message = "Priority is required")
    private String priority;

    @Positive(message = "Number of requirements must be positive")
    private int noOfRequirements;

    @NotNull(message = "Job description file is required")
    private String jobDescription;

    private byte[] jobDescriptionFile;    // File content

    private String comment;

    @Future(message = "Due date must be a future date")
    private Date dueDate;

    @NotBlank(message = "Status is required")
    private String status;

    private List<String> candidateIds;
}
