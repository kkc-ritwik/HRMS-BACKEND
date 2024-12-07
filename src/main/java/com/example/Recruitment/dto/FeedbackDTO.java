package com.example.Recruitment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO {

    @NotBlank(message = "Candidate ID cannot be blank")
    private String candidateId;

    @NotBlank(message = "Candidate name cannot be blank")
    private String candidateName;

    @NotBlank(message = "Recruiter cannot be blank")
    private String recruiter;

    @NotBlank(message = "Feedback cannot be blank")
    private String feedback;

    @NotBlank(message = "Stage cannot be blank")
    private String stage;

    @NotBlank(message = "Status cannot be blank")
    private String status;
}
