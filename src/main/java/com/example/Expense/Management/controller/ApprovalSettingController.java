package com.example.Expense.Management.controller;

import com.example.Expense.Management.config.ApiVersionConfig;
import com.example.Expense.Management.dto.ApprovalSettingDto;
import com.example.Expense.Management.entity.ApprovalSetting;
import com.example.Expense.Management.service.ApprovalSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/approval-settings")
@CrossOrigin("*")
public class ApprovalSettingController {

    @Autowired
    private ApprovalSettingService approvalSettingService;

    @PostMapping
    public ResponseEntity<ApprovalSetting> createApprovalSetting(@RequestBody ApprovalSettingDto approvalSettingDto) {
        ApprovalSetting approvalSetting = approvalSettingService.createApprovalSetting(approvalSettingDto);
        return ResponseEntity.ok(approvalSetting);
    }

    @GetMapping
    public ResponseEntity<List<ApprovalSetting>> getAllApprovalSettings() {
        List<ApprovalSetting> settings = approvalSettingService.getAllApprovalSettings();
        return ResponseEntity.ok(settings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApprovalSetting> getApprovalSettingById(@PathVariable String id) {
        ApprovalSetting setting = approvalSettingService.getApprovalSettingById(id);
        return ResponseEntity.ok(setting);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApprovalSetting> updateApprovalSetting(
            @PathVariable String id,
            @RequestBody ApprovalSettingDto approvalSettingDto) {
        ApprovalSetting setting = approvalSettingService.updateApprovalSetting(id, approvalSettingDto);
        return ResponseEntity.ok(setting);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApprovalSetting(@PathVariable String id) {
        approvalSettingService.deleteApprovalSetting(id);
        return ResponseEntity.ok().build();
    }
}