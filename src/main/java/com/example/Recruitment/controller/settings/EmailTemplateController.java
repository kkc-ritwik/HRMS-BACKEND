package com.example.Recruitment.controller.settings;

import com.example.Recruitment.config.ApiVersionConfig;
import com.example.Recruitment.dto.settings.EmailTemplateRequestDTO;
import com.example.Recruitment.service.settings.EmailTemplateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/email-templates")
@CrossOrigin("*")
public class EmailTemplateController {

    @Autowired
    private EmailTemplateService emailTemplateService;

    @PostMapping
    public ResponseEntity<EmailTemplateRequestDTO> createEmailTemplate(@Valid @RequestBody EmailTemplateRequestDTO emailTemplateDTO) {
        try {
            EmailTemplateRequestDTO createdTemplate = emailTemplateService.saveEmailTemplate(emailTemplateDTO);
            return new ResponseEntity<>(createdTemplate, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating email template", e);
        }
    }

    @GetMapping
    public ResponseEntity<List<EmailTemplateRequestDTO>> getAllEmailTemplates() {
        List<EmailTemplateRequestDTO> templates = emailTemplateService.getAllEmailTemplates();
        return templates.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(templates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailTemplateRequestDTO> getEmailTemplateById(@PathVariable String id) {
        EmailTemplateRequestDTO template = emailTemplateService.getEmailTemplateById(id);
        if (template == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email template not found with ID: " + id);
        }
        return ResponseEntity.ok(template);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailTemplateRequestDTO> updateEmailTemplate(
            @PathVariable String id,
            @Valid @RequestBody EmailTemplateRequestDTO emailTemplateDTO) {
        try {
            EmailTemplateRequestDTO updatedTemplate = emailTemplateService.updateEmailTemplate(id, emailTemplateDTO);
            return ResponseEntity.ok(updatedTemplate);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating email template", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmailTemplate(@PathVariable String id) {
        try {
            emailTemplateService.deleteEmailTemplateById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting email template", e);
        }
    }
}
