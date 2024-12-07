package com.example.Recruitment.service.settings;

import com.example.Recruitment.dto.settings.ApprovalConfigDTO;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ApprovalConfigService {
    ApprovalConfigDTO saveApprovalConfig(ApprovalConfigDTO approvalConfigDTO, List<MultipartFile> attachments);
    List<ApprovalConfigDTO> getAllApprovalConfigs();
    ApprovalConfigDTO getApprovalConfigById(String id);
    void deleteApprovalConfigById(String id);
}