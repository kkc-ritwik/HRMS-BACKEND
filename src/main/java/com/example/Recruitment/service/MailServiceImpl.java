package com.example.Recruitment.service;

import com.example.Recruitment.dto.MailDTO;
import com.example.Recruitment.model.MailStructure;
import com.example.Recruitment.repository.MailRepository;
import com.example.Recruitment.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailRepository mailRepository;

    @Override
    public String sendMail(MailDTO mailDTO, MultipartFile attachment) throws MessagingException {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(mailDTO.getFrom());
            helper.setTo(mailDTO.getTo().split(","));

            if (mailDTO.getCc() != null && !mailDTO.getCc().isEmpty()) {
                helper.setCc(mailDTO.getCc().split(","));
            }

            if (mailDTO.getBcc() != null && !mailDTO.getBcc().isEmpty()) {
                helper.setBcc(mailDTO.getBcc().split(","));
            }

            helper.setSubject(mailDTO.getSubject());
            helper.setText(mailDTO.getEmailBody(), true); // true indicates HTML content

            if (attachment != null && !attachment.isEmpty()) {
                helper.addAttachment(attachment.getOriginalFilename(), attachment);
            }

            mailSender.send(mimeMessage);

            // Save to database
            MailStructure mailStructure = new MailStructure();
            mailStructure.setFrom(mailDTO.getFrom());
            mailStructure.setTo(mailDTO.getTo());
            mailStructure.setCc(mailDTO.getCc());
            mailStructure.setBcc(mailDTO.getBcc());
            mailStructure.setLanguage(mailDTO.getLanguage());
            mailStructure.setSubject(mailDTO.getSubject());
            mailStructure.setEmailBody(mailDTO.getEmailBody());

            if (attachment != null) {
                mailStructure.setAttachment(attachment.getBytes());
            }

            mailRepository.save(mailStructure);

            return "Mail sent successfully!";
        } catch (IOException e) {
            throw new RuntimeException("Failed to process attachment", e);
        }
    }
}