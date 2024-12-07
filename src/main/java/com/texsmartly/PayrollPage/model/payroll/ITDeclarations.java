package com.texsmartly.PayrollPage.model.payroll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "EmployeeITDeclaration")
public class ITDeclarations {
    @Id
    String id = UUID.randomUUID().toString();
    private String userId;
    private String taxRegime;
    private Date periodStart;
    private Date periodEnd;
    private Double houseRentAmount;
    private String houseRentAddress;
    private String landLordName;
    private String houseRentLocation;
    private String investmentName;
    private String investmentAmount;
}
