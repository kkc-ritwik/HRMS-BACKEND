package com.example.Recruitment.dto.settings;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class ApprovalConfigDTO {
    @NotBlank(message = "ID cannot be blank")
    private String id;

    @NotBlank(message = "Form name is required")
    private String formName;

    @NotBlank(message = "Approver name is required")
    private String approverName;

    @NotBlank(message = "Approval type is required")
    private String approvalType;

    @NotNull(message = "Approval configuration details are required")
    private ApprovalConfigDetailsDTO approvalConfig;

    @NotNull(message = "Email template is required")
    private EmailTemplateDTO emailTemplate;
}

@Data
class ApprovalConfigDetailsDTO {
    @NotBlank(message = "Reporting level is required")
    private String reportingLevel;

    @NotBlank(message = "Level type is required")
    private String levelType;

    private boolean enableFollowUp;

    @NotBlank(message = "Follow-up type is required if follow-up is enabled")
    private String followUpType;

    @Positive(message = "Follow-up days must be a positive integer")
    private int followUpDays;

    private String followUpTime;

    private boolean enableFollowUpEmail;
}

@Data
class EmailTemplateDTO {
    @Email(message = "Invalid 'from' email address")
    @NotBlank(message = "From address is required")
    private String from;

    @Email(message = "Invalid 'to' email address")
    @NotBlank(message = "To address is required")
    private String to;

    @Email(message = "Invalid 'cc' email address")
    private String cc;

    @Email(message = "Invalid 'bcc' email address")
    private String bcc;

    @Email(message = "Invalid 'replyTo' email address")
    private String replyTo;

    @NotBlank(message = "Subject is required")
    private String subject;

    @NotBlank(message = "Template type is required")
    private String templateType;

    @NotBlank(message = "Email content is required")
    private String content;

    private List<byte[]> attachments;
}
