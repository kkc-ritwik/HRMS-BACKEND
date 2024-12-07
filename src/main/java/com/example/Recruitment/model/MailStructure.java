package com.example.Recruitment.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mail-structure")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailStructure {
    private String from;
    private String to;
    private String cc;
    private String bcc;
    private String language;
    private String subject;
    private byte[] attachment;
    private String emailBody;
}