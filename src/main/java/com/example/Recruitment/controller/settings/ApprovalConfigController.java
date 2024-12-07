package com.example.Recruitment.controller.settings;

import com.example.Recruitment.config.ApiVersionConfig;
import com.example.Recruitment.dto.settings.ApprovalConfigDTO;
import com.example.Recruitment.service.settings.ApprovalConfigService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/approval/config")
@CrossOrigin("*")
public class ApprovalConfigController {

    @Autowired
    private ApprovalConfigService approvalConfigService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createApprovalConfig(
            @RequestPart("data") @NotNull(message = "Approval config data is required") String approvalConfigJson,
            @RequestPart(value = "files", required = false) List<MultipartFile> attachments) {
        try {
            ApprovalConfigDTO approvalConfigDTO = objectMapper.readValue(approvalConfigJson, ApprovalConfigDTO.class);
            ApprovalConfigDTO savedConfig = approvalConfigService.saveApprovalConfig(approvalConfigDTO, attachments);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedConfig);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON format for approval config data", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing approval config", e);
        }
    }

    @GetMapping
    public ResponseEntity<List<ApprovalConfigDTO>> getAllApprovalConfigs() {
        List<ApprovalConfigDTO> configs = approvalConfigService.getAllApprovalConfigs();
        return configs.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(configs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApprovalConfigDTO> getApprovalConfigById(@PathVariable String id) {
        ApprovalConfigDTO config = approvalConfigService.getApprovalConfigById(id);
        if (config == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Approval config not found with ID: " + id);
        }
        return ResponseEntity.ok(config);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApprovalConfig(@PathVariable String id) {
        try {
            approvalConfigService.deleteApprovalConfigById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting approval config", e);
        }
    }
}
