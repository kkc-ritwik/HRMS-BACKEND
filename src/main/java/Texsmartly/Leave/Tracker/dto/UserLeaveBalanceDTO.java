package Texsmartly.Leave.Tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLeaveBalanceDTO {
    private String id;
    private String userId;
    private String leaveName;
    private String type;
    private Integer available;
    private Integer booked;
    private LocalDate dateOfUpdate;
    private String reason;
    private Integer newBalance;
}
