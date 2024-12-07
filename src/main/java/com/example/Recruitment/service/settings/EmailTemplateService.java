// EmailTemplateService.java
package com.example.Recruitment.service.settings;

import com.example.Recruitment.dto.settings.EmailTemplateRequestDTO;
import java.util.List;

public interface EmailTemplateService {
    EmailTemplateRequestDTO saveEmailTemplate(EmailTemplateRequestDTO emailTemplateDTO);
    List<EmailTemplateRequestDTO> getAllEmailTemplates();
    EmailTemplateRequestDTO getEmailTemplateById(String id);
    EmailTemplateRequestDTO updateEmailTemplate(String id, EmailTemplateRequestDTO emailTemplateDTO);
    void deleteEmailTemplateById(String id);
}
