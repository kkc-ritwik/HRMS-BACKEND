package com.example.Recruitment.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "job-Requisition-Form")
public class JobRequisitionForm {
    @Id
    private String id;

    private String jobRequisitionId;

    private String jobTitle;
    private String department;
    private String hiringManager;
    private String reportingManager;
    private String recruiter;
    private String jobLocation;
    private String ctcOffered;
    private String priority;
    private int noOfRequirements;
    private byte[] jobDescription;
    private String comment;
//    private Date dueDate;
    private String status;

    private List<String> candidateIds;

}