package Texsmartly.Leave.Tracker.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document(collection = "employeeLeaves")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLeaveBalance {
    @Id
    private String id;
    private String userId;
    private String type;
    @Field(name = "leave_Name")
    private String leaveName;
    private Integer available;
    private Integer booked;
    private LocalDate dateOfUpdate;
    private String reason;
    private Integer newBalance;
}
