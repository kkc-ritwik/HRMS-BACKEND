package com.example.Recruitment.service;

import com.example.Recruitment.dto.MailDTO;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

public interface MailService {
    String sendMail(MailDTO mailDTO, MultipartFile attachment) throws MessagingException;
}