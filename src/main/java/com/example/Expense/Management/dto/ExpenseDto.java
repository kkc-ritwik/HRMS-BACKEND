package com.example.Expense.Management.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ExpenseDto {
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