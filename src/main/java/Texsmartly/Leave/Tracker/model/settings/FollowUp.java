package Texsmartly.Leave.Tracker.model.settings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowUp {
    private boolean enabled;
    private String type;
    private int daysAfter;
    private int intervalDays;
    private int maxFollowUps;
    private LocalTime emailTime;
    private EmailTemplate emailTemplate;
}
