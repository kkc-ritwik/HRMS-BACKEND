package com.example.Recruitment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDTO {

    @NotBlank(message = "Sender email is required")
    @Email(message = "Sender email must be valid")
    private String from;

    @NotBlank(message = "Recipient email is required")
    @Email(message = "Recipient email must be valid")
    private String to;

    @Email(message = "CC email must be valid")
    private String cc;

    @Email(message = "BCC email must be valid")
    private String bcc;

    @NotBlank(message = "Language is required")
    @Size(max = 10, message = "Language should not exceed 10 characters")
    private String language;

    @NotBlank(message = "Subject is required")
    @Size(max = 100, message = "Subject should not exceed 100 characters")
    private String subject;

    @NotBlank(message = "Email body cannot be blank")
    private String emailBody;
}
