package com.texsmartly.PayrollPage.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeSearchCriteriaDTO {
    @Id
    String id = UUID.randomUUID().toString();
    private String employeeId;
    private String phoneNumber;
    private String emailId;
    private String firstName;
    private String lastName;
    private String department;
}
