package com.example.Expense.Management.service;

import com.example.Expense.Management.dto.ApprovalSettingDto;
import com.example.Expense.Management.entity.ApprovalSetting;
import com.example.Expense.Management.repository.ApprovalSettingRepository;
import com.example.Expense.Management.service.ApprovalSettingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovalSettingServiceImpl implements ApprovalSettingService {

    @Autowired
    private ApprovalSettingRepository approvalSettingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApprovalSetting createApprovalSetting(ApprovalSettingDto approvalSettingDto) {
        ApprovalSetting approvalSetting = modelMapper.map(approvalSettingDto, ApprovalSetting.class);
        return approvalSettingRepository.save(approvalSetting);
    }

    @Override
    public List<ApprovalSetting> getAllApprovalSettings() {
        return approvalSettingRepository.findAll();
    }

    @Override
    public ApprovalSetting getApprovalSettingById(String id) {
        return approvalSettingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Approval Setting not found"));
    }

    @Override
    public ApprovalSetting updateApprovalSetting(String id, ApprovalSettingDto approvalSettingDto) {
        ApprovalSetting existingSetting = approvalSettingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Approval Setting not found"));
        modelMapper.map(approvalSettingDto, existingSetting);
        return approvalSettingRepository.save(existingSetting);
    }

    @Override
    public void deleteApprovalSetting(String id) {
        approvalSettingRepository.deleteById(id);
    }
}