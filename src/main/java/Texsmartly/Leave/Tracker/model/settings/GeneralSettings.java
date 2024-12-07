package Texsmartly.Leave.Tracker.model.settings;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "general_settings")
public class GeneralSettings {

    @Id
    private String id;


    private int consecutiveLeavesForWeekends;


    private String applicableLeave;


    private Boolean payrollReportForAdmin;


    private Boolean includeWeekends;


    private Boolean includeHolidays;


    private String unpaidLeaveMarkedAs;


    private Boolean enablePasswordProtection;


    private Boolean pastLeavesWithCurrentPayPeriodRecordOwner;


    private Boolean pastLeavesWithCurrentPayPeriodReportingManagers;


    private Boolean pastLeavesWithCurrentPayPeriodApprovers;


    private Boolean currentDayPayRequestRecordOwner;


    private Boolean currentDayPayRequestReportingManagers;


    private Boolean currentDayPayRequestApprovers;


    private Boolean allowPartialLeaveCancel;


    private Boolean makeLeaveCancellationReasonMandatory;
    @Field(name = "reminder_Email")
    private String reminderEmail;
}
