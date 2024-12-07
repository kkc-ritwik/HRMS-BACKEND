package Texsmartly.Leave.Tracker.dto.settings;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class GeneralSettingsDTO {
    private String id;
    @Min(value = 0, message = "Consecutive leaves for weekends must be zero or a positive integer")
    private int consecutiveLeavesForWeekends;

    @NotBlank(message = "Applicable leave is required")
    @Size(max = 50, message = "Applicable leave must not exceed 50 characters")
    @Field(name = "applicable_Leave")
    private String applicableLeave;

    @NotNull(message = "Payroll report for admin must be specified")
    @Field(name = "payroll_Report_For_Admin")
    private boolean payrollReportForAdmin;

    @NotNull(message = "Include weekends field must be specified")
    @Field(name = "include_Weekends")
    private boolean includeWeekends;

    @NotNull(message = "Include holidays field must be specified")
    @Field(name = "include_Holidays")
    private boolean includeHolidays;

    @NotBlank(message = "Unpaid leave marked as field is required")
    @Size(max = 20, message = "Unpaid leave marked as must not exceed 20 characters")
    @Field(name = "unpaid_Leave_Marked_As")
    private String unpaidLeaveMarkedAs;

    @NotNull(message = "Enable password protection field must be specified")
    @Field(name = "enable_Password_Protection")
    private boolean enablePasswordProtection;

    @NotNull(message = "Past leaves with current pay period record owner must be specified")
    @Field(name = "past_Leaves_With_Current_Pay_Period_RecordOwner")
    private boolean pastLeavesWithCurrentPayPeriodRecordOwner;

    @NotNull(message = "Past leaves with current pay period reporting managers must be specified")
    @Field(name = "past_Leaves_With_Current_Pay_Period_ReportingManagers")
    private boolean pastLeavesWithCurrentPayPeriodReportingManagers;

    @NotNull(message = "Past leaves with current pay period approvers must be specified")
    @Field(name = "past_Leaves_With_Current_PayPeriod_Approvers")
    private boolean pastLeavesWithCurrentPayPeriodApprovers;

    @NotNull(message = "Current day pay request record owner field must be specified")
    @Field(name = "current_DayPayRequest_RecordOwner")
    private boolean currentDayPayRequestRecordOwner;

    @NotNull(message = "Current day pay request reporting managers field must be specified")
    @Field(name = "current_DayPayRequest_ReportingManagers")
    private boolean currentDayPayRequestReportingManagers;

    @NotNull(message = "Current day pay request approvers field must be specified")
    @Field(name = "current_DayPayRequest_Approvers")
    private boolean currentDayPayRequestApprovers;

    @NotNull(message = "Allow partial leave cancel must be specified")
    @Field(name = "allow_Partial_Leave_Cancel")
    private boolean allowPartialLeaveCancel;

    @NotNull(message = "Make leave cancellation reason mandatory must be specified")
    @Field(name = "make_LeaveCancellation_ReasonMandatory")
    private boolean makeLeaveCancellationReasonMandatory;

//    @NotBlank(message = "Reminder email is required")
    @Size(max = 100, message = "Reminder email must not exceed 100 characters")

    private String reminderEmail;
}
