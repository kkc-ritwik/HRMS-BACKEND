package com.example.Recruitment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InterviewSchedulingDTO {

    @NotBlank(message = "Interview ID cannot be blank")
    private String interviewId;

    @NotBlank(message = "Interview title cannot be blank")
    private String interviewTitle;

    @NotBlank(message = "Assigned person cannot be blank")
    private String assignedTo;

    @NotEmpty(message = "Candidates list cannot be empty")
    private List<String> candidates = new ArrayList<>();

    @NotBlank(message = "Interview type cannot be blank")
    private String interviewType;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @NotBlank(message = "Interviewer name cannot be blank")
    private String interviewerName;

    @NotBlank(message = "Interviewer role cannot be blank")
    private String interviewerRole;

    @NotBlank(message = "Duration cannot be blank")
    private String duration;

    @NotEmpty(message = "Available time slots cannot be empty")
    private List<String> availableTimeSlots;

    @NotBlank(message = "Custom slot date cannot be blank")
    private String customSlotDate;

    @NotBlank(message = "Custom slot start time cannot be blank")
    private String customSlotStartTime;

    @NotBlank(message = "Custom slot end time cannot be blank")
    private String customSlotEndTime;

    // Contact fields
    @NotBlank(message = "From email cannot be blank")
    @Email(message = "From email must be valid")
    private String from;

    @NotBlank(message = "Recipient email cannot be blank")
    @Email(message = "Recipient email must be valid")
    private String to;

    @NotBlank(message = "Subject cannot be blank")
    @Size(max = 100, message = "Subject cannot exceed 100 characters")
    private String subject;

    @NotBlank(message = "Message cannot be blank")
    private String message;

    @NotBlank(message = "Contact method cannot be blank")
    private String contactVia;

    @NotBlank(message = "Language cannot be blank")
    private String language;

    @NotBlank(message = "Email template cannot be blank")
    private String emailTemplate;
}
