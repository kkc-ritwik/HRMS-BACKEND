package com.example.Expense.Management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "approval-setting")
public class ApprovalSetting {
    @Id
    private String id;

    // General Settings
    private boolean allowView; // Allow record owner to view approver details and comments
    private boolean notifyIntermediate; // Notify through intermediate mails
    private boolean notifyFinal; // Notify through final mails
    private boolean skipApprover; // Skip approver if logged in user is one of the approvers
    private boolean commentApprove; // Comments required to approve request
    private boolean commentReject; // Comments required to reject request

    // Configuration
    private String approverOption; // Approver option: Based on role or specific approver
    private String specificApprover; // Specific approver
    private String allowRequestCancellationWithin; // Allow requests cancellation within
    private boolean initiateCancellation; // Initiate cancellation approval even for admin

    // TAT Settings
    private int daysAllottedForApprover; // No. of days allotted for approval for each approver
    private boolean remindApprover; // Is remind approver enabled
    private int reminderDaysBefore; // Days before reminding approver

    // When TAT has elapsed
    private ApproverAction intermediateApproverAction; // Intermediate approver settings
    private ApproverAction finalApproverAction; // Final approver settings

    // Final approver TAT settings
    private boolean remindFinalApprover; // Is remind final approver enabled
    private int remindFinalApproverDays; // Days to remind final approver when pending

    // Nested Class for Approver Actions
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApproverAction {
        private boolean moveToNextApprover; // Move to next approver
        private boolean autoReject; // Auto reject
        private String assignTo; // Assign to (e.g., Reporting to or Levels)
    }
}
