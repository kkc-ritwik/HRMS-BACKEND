package com.texsmartly.PayrollPage.service.impl;

import com.texsmartly.PayrollPage.model.payroll.LeaveDetails;
import com.texsmartly.PayrollPage.repository.payroll.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveDetailsServiceImpl {
    @Autowired
    private LeaveRepository leaveRepository;

    public LeaveDetails calculatePayableDays(String userId) {
        LeaveDetails leaveDetails = leaveRepository.findByUserId(userId).orElse(new LeaveDetails());
        int payableDays = leaveDetails.getTotalWorkingDays() - leaveDetails.getUnpaidDays();
        leaveDetails.setPayableDays(payableDays);
        return leaveRepository.save(leaveDetails);
    }
}
