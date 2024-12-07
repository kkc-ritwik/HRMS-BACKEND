package Texsmartly.Leave.Tracker.dto.settings;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HolidayAssignmentDTO {
    private String holidayId;
    @NotBlank(message = "Holiday name is required")
    @Size(max = 50, message = "Holiday name should not exceed 50 characters")
    private String holidayName;
    @NotNull(message = "Holiday date is required")
    private LocalDate holidayDate;
    @NotBlank(message = "Department is required")
    @Size(max = 30, message = "Department should not exceed 30 characters")
    private String department;
    @NotBlank(message = "Location is required")
    @Size(max = 30, message = "Location should not exceed 30 characters")
    private String location;
    @NotBlank(message = "Division is required")
    @Size(max = 30, message = "Division should not exceed 30 characters")
    private String division;
    @Size(max = 200, message = "Description should not exceed 200 characters")
    private String description;
}
