package com.texsmartly.PayrollPage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ITDeclarationsDTO {
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
