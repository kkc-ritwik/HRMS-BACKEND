package com.texsmartly.PayrollPage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeReimbursementsDTO {
    @Id
    private String id;
    private String userId;
    private String claimNumber;
    private String status = "Pending";  // default status
    private Long claimAmount;
    private String claimDate;
    private String descriptionOfClaim;
    private String reimbursementName;
    private String eligibleAmount;
    private String approvedAmount;
    private String pendingAmount;
    private String paid;
    private String unclaimed;
    private LocalDate billDate;
    private byte[] proofs;
    private String comments;
    private Boolean receiptAttached;   // yes or no
    private LocalDate submittedDate;
    private String accountHolderName;
    private String accountNumber;
    private String accountType;
    private String ifscCode;
    private Long monthlyEligibleAmount;
    private Long annualEligibleAmount;
    private String dateOfRequest;

    // Automatically set month of request

    private String monthOfRequest;

    // Automatically set year of request

    private String yearOfRequest;


    private String dateOfApproval;

    // Method to populate fields before saving to the database
    public void setRequestDateInfo() {
        LocalDate now = LocalDate.now();
        this.dateOfRequest = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.dateOfApproval = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.monthOfRequest = now.getMonth().name(); // Fetch the month name
        this.yearOfRequest = String.valueOf(now.getYear()); // Fetch the year
    }

}
