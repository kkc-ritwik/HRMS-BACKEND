package Texsmartly.Leave.Tracker.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HolidayDTO {
    private String id;
    @NotBlank(message = "Holiday name is required")
    @Size(max = 50, message = "Holiday name should not exceed 50 characters")
    private String holidayName;
    @NotNull(message = "Holiday date is required")
    private LocalDate holidayDate;
    @NotBlank(message = "Type is required")
    private String type;
    @Size(max = 200, message = "Description should not exceed 200 characters")
    private String description;
    @NotEmpty(message = "At least one department is required")
    private List<String> departments;
    @NotEmpty(message = "At least one location is required")
    private List<String> locations;
    @NotEmpty(message = "At least one division is required")
    private List<String> divisions;
    private boolean notifyApplicable;
    private boolean reprocessLeave;
}
