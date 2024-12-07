package com.example.Recruitment.dto.settings;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TATSettingDTO {

    @NotNull(message = "isDaysAllottedForApprover cannot be null")
    private Boolean isDaysAllottedForApprover;

    @Min(value = 1, message = "daysAllottedForApprover must be at least 1 day")
    @Max(value = 30, message = "daysAllottedForApprover cannot exceed 30 days")
    private Integer daysAllottedForApprover;

    @NotNull(message = "isRemindApprover cannot be null")
    private Boolean isRemindApprover;

    @Min(value = 1, message = "reminderDaysBefore must be at least 1 day")
    @Max(value = 10, message = "reminderDaysBefore cannot exceed 10 days")
    private Integer reminderDaysBefore;

    @Valid
    @NotNull(message = "intermediateApproverAction cannot be null")
    private ApproverActionDTO intermediateApproverAction;

    @Valid
    @NotNull(message = "finalApproverAction cannot be null")
    private ApproverActionDTO finalApproverAction;

    @NotNull(message = "remindFinalApprover cannot be null")
    private Boolean remindFinalApprover;

    @Min(value = 1, message = "remindFinalApproverDays must be at least 1 day")
    @Max(value = 10, message = "remindFinalApproverDays cannot exceed 10 days")
    private Integer remindFinalApproverDays;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApproverActionDTO {
        @NotNull(message = "moveToNextApprover cannot be null")
        private Boolean moveToNextApprover;

        @NotNull(message = "autoReject cannot be null")
        private Boolean autoReject;

        @NotBlank(message = "assignTo cannot be blank")
        private String assignTo;
    }
}
