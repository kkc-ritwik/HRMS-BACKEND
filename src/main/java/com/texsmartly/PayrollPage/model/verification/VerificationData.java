package com.texsmartly.PayrollPage.model.verification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "verification_data")
public class VerificationData {
    private String employeeId;
    private String emailId;
    private String mobileNumber;
}
