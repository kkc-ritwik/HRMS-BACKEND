package Texsmartly.Leave.Tracker.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeHolidayDTO {
    private String userId;
    private String email;
    @NotBlank(message = "Department is required")
    @Size(max = 30, message = "Department should not exceed 30 characters")
    private String department;
    @NotBlank(message = "Location is required")
    @Size(max = 30, message = "Location should not exceed 30 characters")
    private String location;
    @NotBlank(message = "Division is required")
    @Size(max = 30, message = "Division should not exceed 30 characters")
    private String division;

    private String currentHolidayId;
    private String currentHolidayName;
    @NotNull(message = "Holiday date is required")
    private LocalDate holidayDate;
    private LocalDateTime createdAt;
}
