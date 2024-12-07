// EmailTemplateRepository.java
package com.example.Recruitment.repository.settings;

import com.example.Recruitment.model.settings.EmailTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailTemplateRepository extends MongoRepository<EmailTemplate, String> {
}
