package com.texsmartly.PayrollPage.model.payroll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Personal Details")
public class PersonalDetails {
    @Id
    String id = UUID.randomUUID().toString();
    private String userId;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String workEmail;
    private  String phone;
    private String department;
    private double costToCompany;
    private String employeeDesignation;
    private String designationID;
    private String gender;
    private String dateOfJoining;
    private String location;
    private String dateOfBirth;
    private String fatherName;
    private String pan;
    private String passport;
    private String tan;
    private String tin;
    private String residentialAddress;
    private String paymentMode;
    private Boolean employeeProvidentFund;
    private Boolean stateInsurance;
    private Boolean professionalTax;
    private String status;
}
