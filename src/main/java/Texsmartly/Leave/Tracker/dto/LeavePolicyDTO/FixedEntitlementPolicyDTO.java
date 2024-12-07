package Texsmartly.Leave.Tracker.dto.LeavePolicyDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FixedEntitlementPolicyDTO {
    private String id;
    private String userId; // ID of the user requesting reimbursement
    @NotBlank(message = "Leave name is required.")
    private String leaveName;
    @NotBlank(message = "Name is required.")
    @Pattern(regexp = "^[A-Za-z _-]{3,50}$", message = "Invalid name . Only letters, spaces, hyphens (-), and underscores (_) are allowed. Length must be between 3 and 50 characters.")
    private String name;
    private String image;
    @NotBlank(message = "Code is required.")
    private String code;
    @NotBlank(message = "Type is required.")
    private String type;
    @Min(value = 0, message = "Days must be a non-negative number.")
    private int days;
    private boolean unitDays;
    private boolean unitHours;
    private boolean balancedBasedOnFixedEntitlement;
    private boolean balancedBasedOnLeaveGrant;
    @NotBlank(message = "Validity from date is required.")
    @Size(max = 255, message = "Description must not exceed 255 characters.")
    private String description;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Validity from date must be in YYYY-MM-DD format.")
    private String validityFrom;
    @NotBlank(message = "Validity to date is required.")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Validity to date must be in YYYY-MM-DD format.")
    private String validityTo;
    private String status;//toggle   find by status;


//    private List<EntitledDTO> entitled;
//    private List<ApplicableDTO> applicable;
//    private List<RestrictionsDTO> restrictions;
//
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class EntitledDTO {
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
//    public static class ApplicableDTO {
//        private List<String> applicableTo;
//        private List<String> exceptions;
//    }
//
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class RestrictionsDTO {
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
