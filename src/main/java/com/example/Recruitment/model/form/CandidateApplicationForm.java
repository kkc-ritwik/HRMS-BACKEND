package com.example.Recruitment.model.form;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor

@Document(collection = "candidate-Application-Form")
public class CandidateApplicationForm {
    @Id
    private String id;
    private String candidateId;
    private String jobRequisitionId;
    // Contact Details
    private String name;
    private String emergencyContact;
    private String emailAddress;
    private String secondaryEmailAddress;
    private String mobileNumber;

    // Personal Information
    private String dateOfBirth;
    private String passportNumber;
    private String bloodGroup;
    private String aadharAddress;
    private String panNumber;

    // Present Address
    private String presentAddressLine1;
    private String presentAddressLine2;
    private String presentCity;
    private String presentPinCode;
    private String presentState;

    // Permanent Address
    private String permanentAddressLine1;
    private String permanentAddressLine2;
    private String permanentCity;
    private String permanentPinCode;
    private String permanentState;

    // Job Details
    private String positionApplied;
    private String startDate;
    private String currentEmployer;
    private String totalYearOfExperience;
    private String jobLocation;
    private int noticePeriod;

    private String currentCtc;
    private String expectedCtc;
    private String linkedinLink;
    private String coverLetter;
    private byte[] resume;

    private Round round1;
    private Round round2;
    private Round round3;




    public CandidateApplicationForm() {
        this.candidateId = generateCandidateId();
        this.round1 = new Round("-", "-");
        this.round2 = new Round("-", "-");
        this.round3 = new Round("-", "-");
    }

    private String generateCandidateId() {
        return UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }
}