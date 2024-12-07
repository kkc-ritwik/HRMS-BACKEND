package com.texsmartly.PayrollPage.model.payroll;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document()
public class Form16A {
    @Id
    private String id;
    private String certificateNo;
    private Date lastUpdatedOn;
    private String deductorNameAddress;
    private String deducteeNameAddress;
    private String panOfDeductor;
    private String tanOfDeductor;
    private String panOfDeductee;
    private String citTds;
    private String assessmentYear;
    private String periodFromTo;
    private String amountPaid;
    private String natureOfPayment;
    private String deducteeReferenceNo;
    private Date dateOfPaymentCredit;
    private String totalAmount;

    // Additional fields for tax deducted and deposited details
    private String taxDepositedDeductee;
    private String receiptNumberForm24G;
    private String ddoSerialNumberForm24G;
    private Date dateOfTransferVoucher;
    private String statusMatchingForm24G;

    // Additional fields for challan details
    private String bsrCode;
    private Date taxDepositDate;
    private String challanSerialNumber;
    private String statusMatchingOltas;
    private String challanTotalAmount;


}
