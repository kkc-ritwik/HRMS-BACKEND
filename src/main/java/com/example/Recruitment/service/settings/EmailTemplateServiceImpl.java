// EmailTemplateServiceImpl.java
package com.example.Recruitment.service.settings;

import com.example.Recruitment.dto.settings.EmailTemplateRequestDTO;
import com.example.Recruitment.model.settings.EmailTemplate;
import com.example.Recruitment.repository.settings.EmailTemplateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmailTemplateRequestDTO saveEmailTemplate(EmailTemplateRequestDTO emailTemplateDTO) {
        EmailTemplate emailTemplate = modelMapper.map(emailTemplateDTO, EmailTemplate.class);
        emailTemplate = emailTemplateRepository.save(emailTemplate);
        return modelMapper.map(emailTemplate, EmailTemplateRequestDTO.class);
    }

    @Override
    public List<EmailTemplateRequestDTO> getAllEmailTemplates() {
        return emailTemplateRepository.findAll()
                .stream()
                .map(template -> modelMapper.map(template, EmailTemplateRequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmailTemplateRequestDTO getEmailTemplateById(String id) {
        EmailTemplate emailTemplate = emailTemplateRepository.findById(id).orElse(null);
        return modelMapper.map(emailTemplate, EmailTemplateRequestDTO.class);
    }

    @Override
    public EmailTemplateRequestDTO updateEmailTemplate(String id, EmailTemplateRequestDTO emailTemplateDTO) {
        EmailTemplate existingTemplate = emailTemplateRepository.findById(id).orElse(null);
        if (existingTemplate != null) {
            modelMapper.map(emailTemplateDTO, existingTemplate);
            existingTemplate.setId(id);
            EmailTemplate updatedTemplate = emailTemplateRepository.save(existingTemplate);
            return modelMapper.map(updatedTemplate, EmailTemplateRequestDTO.class);
        }
        return null;
    }

    @Override
    public void deleteEmailTemplateById(String id) {
        emailTemplateRepository.deleteById(id);
    }
}
