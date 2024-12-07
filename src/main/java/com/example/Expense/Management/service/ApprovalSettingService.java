package com.example.Expense.Management.service;

import com.example.Expense.Management.dto.ApprovalSettingDto;
import com.example.Expense.Management.entity.ApprovalSetting;

import java.util.List;

public interface ApprovalSettingService {
    ApprovalSetting createApprovalSetting(ApprovalSettingDto approvalSettingDto);
    List<ApprovalSetting> getAllApprovalSettings();
    ApprovalSetting getApprovalSettingById(String id);
    ApprovalSetting updateApprovalSetting(String id, ApprovalSettingDto approvalSettingDto);
    void deleteApprovalSetting(String id);
}