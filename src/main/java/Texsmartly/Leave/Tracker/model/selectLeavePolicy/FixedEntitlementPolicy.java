package Texsmartly.Leave.Tracker.model.selectLeavePolicy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "fixed_entitlement")
public class FixedEntitlementPolicy {
    @Id
    private String id;
    private String userId; // ID of the user requesting
    @Field(name = "leave_Id")
    private String leaveId;
//    @Field(name = "leave_Name")
    private String leaveName;
    private String image;
    private String code;
    private String type;
    private int days;
    @Field(name = "unit_Days")
    private boolean unitDays;
    @Field(name = "unit_Hours")
    private boolean unitHours;
    @Field(name = "balanced_Based_On_Fixed_Entitlement")
    private boolean balancedBasedOnFixedEntitlement;
    @Field(name = "balanced_Based_On_Leave_Grant")
    private boolean balancedBasedOnLeaveGrant;
    private String description;
    @Field(name = "validity_From")
    private String validityFrom;
    @Field(name = "validity_To")
    private String validityTo;
    private String status; //toggle find by status;

//    private List<Entitled> entitled;
//    private List<Applicable> applicable;
//    private List<Restrictions> restrictions;
//
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class Entitled {
//        private int effectiveAfterYears;
//        private String dateOfJoining;
//
//        //Accrual
//        private boolean accrual;
//        private String accrualFrequency;
//        private String accrualDay;
//        private String accrualMonth;
//        private int accrualNoOfDays;
//        private String accrualType;
//
//        //Reset
//        private boolean reset;
//        private String resetFrequency;
//        private String resetDay;
//        private String resetMonth;
//
//        private String carryForwardType;
//        private String carryForwardUnit;
//        private int carryForwardMaxLimit;
//
//        private String encashment;
//        private String encashmentUnit;
//        private int encashmentMaxLimit;
//        private String policy;
//    }
//
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class Applicable {
//        private List<String> applicableTo;
//        private List<String> exceptions;
//    }
//
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class Restrictions {
//        //Restrictions
//        private boolean allowLeaveRequest;
//        private boolean leaveRequestWithoutLimit;
//        private boolean leaveRequestUntilYearEndLimit;
//        private boolean markExcessAsLop;
//        //Sandwich Leave Policy
//        private boolean sandwichLeavePolicy;
//        //Clubbing Policy
//        private String alongWith;
//        //Reports
//        private String allowEmployeesToView;
//        private String balancedDisplayedAs;
//        //File Upload
//        private boolean uploadSupportingDocuments;
//        private int UploadDays;
//        //Record Leave Restrictions
//        private boolean allowedDurationFullDay;
//        private boolean allowedDurationHalfDay;
//        private boolean allowedDurationQuarterDay;
//        private boolean allowRequestsForPastDates;
//        private int allowRequestsForPastDays;
//        private boolean futureDates;
//        private int nextFutureDays;
//        private int futureDaysApplied;
//        private boolean allowOnlyAdministrators;
//        private boolean minimumLeaveAllowed;
//        private int minimumLeaveDays;
//        private boolean maximumLeaveAllowed;
//        private int maximumLeaveDays;
//        private boolean numberOfConsecutiveDays;
//        private int consecutiveDaysLeaveAllowed;
//        private boolean minimumGap;
//        private int minimumDaysGap;
//        private int numberOfRequests;
//        private String appliedOnlyOn;
//    }

}
