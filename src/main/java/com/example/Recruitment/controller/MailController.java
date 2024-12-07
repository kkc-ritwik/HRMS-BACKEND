package com.example.Recruitment.controller;

import com.example.Recruitment.config.ApiVersionConfig;
import com.example.Recruitment.dto.MailDTO;
import com.example.Recruitment.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/mail")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping(value = "/send", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> sendMail(
            @RequestHeader("Authorization") String authorization,
            @Valid @ModelAttribute MailDTO mailDTO, // Use @ModelAttribute to bind request params to DTO
            @RequestParam(required = false) MultipartFile attachment
    ) {
        try {
            // Validate authorization token
            if (authorization == null || !authorization.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid authorization token");
            }

            String result = mailService.sendMail(mailDTO, attachment);
            return ResponseEntity.ok(result);
        } catch (MessagingException e) {
            return ResponseEntity.badRequest().body("Failed to send email: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + e.getMessage());
        }
    }
}
