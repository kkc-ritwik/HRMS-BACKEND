package com.example.Recruitment.service.settings;

import com.example.Recruitment.dto.settings.ApprovalConfigDTO;
import com.example.Recruitment.model.settings.ApprovalConfig;
import com.example.Recruitment.repository.settings.ApprovalConfigRepository;
import com.example.Recruitment.service.settings.ApprovalConfigService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApprovalConfigServiceImpl implements ApprovalConfigService {

    @Autowired
    private ApprovalConfigRepository approvalConfigRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public ApprovalConfigDTO saveApprovalConfig(ApprovalConfigDTO approvalConfigDTO, List<MultipartFile> attachments) {
        try {
            // Handle file attachments as byte[]
            List<byte[]> attachmentDataList = new ArrayList<>();
            if (attachments != null && !attachments.isEmpty()) {
                for (MultipartFile file : attachments) {
                    attachmentDataList.add(file.getBytes());
                }
            }

            // Map DTO to entity
            ApprovalConfig config = modelMapper.map(approvalConfigDTO, ApprovalConfig.class);

            // Set attachment data
            if (config.getEmailTemplate() == null) {
                config.setEmailTemplate(new ApprovalConfig.EmailTemplate());
            }
            config.getEmailTemplate().setAttachments(attachmentDataList);

            // Save to database
            config = approvalConfigRepository.save(config);

            // Map back to DTO
            return modelMapper.map(config, ApprovalConfigDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Error saving approval config: " + e.getMessage());
        }
    }

    @Override
    public List<ApprovalConfigDTO> getAllApprovalConfigs() {
        List<ApprovalConfig> configs = approvalConfigRepository.findAll();
        return configs.stream()
                .map(config -> modelMapper.map(config, ApprovalConfigDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ApprovalConfigDTO getApprovalConfigById(String id) {
        ApprovalConfig config = approvalConfigRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ApprovalConfig not found with id: " + id));
        return modelMapper.map(config, ApprovalConfigDTO.class);
    }

    @Override
    @Transactional
    public void deleteApprovalConfigById(String id) {
        ApprovalConfig config = approvalConfigRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ApprovalConfig not found with id: " + id));
        approvalConfigRepository.deleteById(id);
    }
}
