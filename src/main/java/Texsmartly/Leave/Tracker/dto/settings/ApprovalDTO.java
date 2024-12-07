package Texsmartly.Leave.Tracker.dto.settings;

import Texsmartly.Leave.Tracker.model.settings.ApprovalStatus;
import Texsmartly.Leave.Tracker.model.settings.FollowUp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalDTO {
    private String id;
    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[A-Za-z _-]{3,50}$", message = "Invalid name . Only letters, spaces, hyphens (-), and underscores (_) are allowed. Length must be between 3 and 50 characters.")
    private String name;

    private boolean autoApprove;
    private boolean autoReject;

    private List<ConfigureApprovalDTO> configureApprovals;
    private boolean status;

    private FollowUp followUp;

    private LocalDateTime createdAt; // Automatically set

    private LocalDateTime lastFollowUpSentAt; // Track last follow-up

    private ApprovalStatus approvalStatus;

    @Data
    public static class ConfigureApprovalDTO {
        @Id
        private String id;
        @Pattern(regexp = "^[A-Za-z _-]{3,50}$", message = "Invalid reporting To. Only letters, spaces, hyphens (-), and underscores (_) are allowed. Length must be between 3 and 50 characters.")
        private String reportingTo;
        @Pattern(
                regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com$",
                message = "level Must be a valid email format ending with .com (e.g., user@example.com)"
        )
        private String level;
    }
}
