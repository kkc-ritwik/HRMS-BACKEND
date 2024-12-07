package Texsmartly.Leave.Tracker.service.settings;

import Texsmartly.Leave.Tracker.model.settings.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender=javaMailSender;
    }

    public String sendMail(String email,String subject,String message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject(subject);
        mail.setText(message);
        javaMailSender.send(mail);
        return "mail send successfully.";
    }

    // public String send(){

    // }

    public void sendFollowUpEmail(EmailTemplate template) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(template.getFrom());
            message.setTo(template.getTo().split(","));
            if (StringUtils.hasText(template.getCc())) {
                message.setCc(template.getCc().split(","));
            }
            if (StringUtils.hasText(template.getBcc())) {
                message.setBcc(template.getBcc().split(","));
            }
            message.setReplyTo(template.getReplyTo());
            message.setSubject(template.getSubject());
            message.setText(template.getMessage());

            javaMailSender.send(message);
            System.out.println("Follow-up email sent successfully.");
        } catch (MailException e) {
            System.err.println("Error sending follow-up email: " + e.getMessage());
        }
    }
}
