package Texsmartly.Leave.Tracker.model.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Approval {
    @Id
    private String id;
    private String name;
    @Field(name = "auto_Approve")
    private boolean autoApprove;
    @Field(name = "auto_Reject")
    private boolean autoReject;
    private List<ConfigureApproval> configureApprovals;
    private boolean status;

    private FollowUp followUp;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime lastFollowUpSentAt;

    private ApprovalStatus approvalStatus;

    @Data
    public static class ConfigureApproval {
        @Id
        private String id;
        private String reportingTo;
        private String level;
    }
}
