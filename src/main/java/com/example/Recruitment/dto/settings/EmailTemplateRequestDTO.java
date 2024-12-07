package com.example.Recruitment.dto.settings;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailTemplateRequestDTO {
    private String id;

    @NotBlank(message = "Template name is required")
    private String name;

    @NotBlank(message = "Template subject is required")
    private String subject;

    @NotBlank(message = "Template message content is required")
    private String message;

    @NotBlank(message = "Template type is required")
    private String type;
}
