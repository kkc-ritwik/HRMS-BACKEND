// EmailTemplate.java
package com.example.Recruitment.model.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "emailTemplates")
public class EmailTemplate {
    @Id
    private String id;
    private String name;
    private String subject;
    private String message;
    private String type;
}
