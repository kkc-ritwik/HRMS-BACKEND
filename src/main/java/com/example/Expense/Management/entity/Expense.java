package com.example.Expense.Management.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "expenses")
@Data
public class Expense {
    @Id
    private String id;
    private String date;
    private String projectName;
    private String expenseName;
    private String description;
    private String amount;
    private String status;
    private String paymentMethod;
    private String currency;
    private String billable;
    private String reimburse;
    private String netAmount;
    private String taxZone;
    private String tax;
    private byte[] attachmentData;
    private String attachmentName;
    private String attachmentType;
}
