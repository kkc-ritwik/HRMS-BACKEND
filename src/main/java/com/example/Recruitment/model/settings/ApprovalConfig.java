package com.example.Recruitment.model.settings;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "approval_configs")
public class ApprovalConfig {
    @Id
    private String id;
    private String formName;
    private String approverName;
    private String approvalType;
    private ApprovalConfigDetails approvalConfig;
    private EmailTemplate emailTemplate;

    @Data
    public static class ApprovalConfigDetails {
        private String reportingLevel;
        private String levelType;
        private boolean enableFollowUp;
        private String followUpType;
        private int followUpDays;
        private String followUpTime;
        private boolean enableFollowUpEmail;
    }

    @Data
    public static class EmailTemplate {
        private String from;
        private String to;
        private String cc;
        private String bcc;
        private String replyTo;
        private String subject;
        private String templateType;
        private String content;
        private List<byte[]> attachments; // Storing files as binary data
    }
}
