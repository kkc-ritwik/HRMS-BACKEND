package com.example.Expense.Management.dto;

import lombok.Data;

@Data
public class ApprovalSettingDto {
    // General Settings
    private boolean allowView;
    private boolean notifyIntermediate;
    private boolean notifyFinal;
    private boolean skipApprover;
    private boolean commentApprove;
    private boolean commentReject;

    // Configuration
    private String approverOption;
    private String specificApprover;
    private String allowRequestCancellationWithin;
    private boolean initiateCancellation;

    // TAT Settings
    private int daysAllottedForApprover;
    private boolean remindApprover;
    private int reminderDaysBefore;

    // When TAT has elapsed
    private ApproverActionDto intermediateApproverAction;
    private ApproverActionDto finalApproverAction;

    // Final approver TAT settings
    private boolean remindFinalApprover;
    private int remindFinalApproverDays;

    @Data
    public static class ApproverActionDto {
        private boolean moveToNextApprover;
        private boolean autoReject;
        private String assignTo;
    }
}