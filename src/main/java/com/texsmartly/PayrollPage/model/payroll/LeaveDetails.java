package com.texsmartly.PayrollPage.model.payroll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveDetails {
    @Id
    private String id;
    private String userId;
    private int totalWorkingDays;
    private int payableDays;
    private int unpaidDays;
}
