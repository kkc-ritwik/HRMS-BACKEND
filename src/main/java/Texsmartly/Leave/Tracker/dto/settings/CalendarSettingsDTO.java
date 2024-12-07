package Texsmartly.Leave.Tracker.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CalendarSettingsDTO {

    private String id;
    @NotBlank(message = "Location is required")
    @Size(max = 30, message = "Location should not exceed 30 characters")
    private String location;
    @NotBlank(message = "Week start day is required")
    @Pattern(regexp = "^(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday)$", message = "Week start must be a valid day of the week")
    private String weekStart;
    @NotBlank(message = "Work week start day is required")
    @Pattern(regexp = "^(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday)$", message = "Work week start must be a valid day of the week")
    private String workWeekStart;
    @NotBlank(message = "Work week end day is required")
    @Pattern(regexp = "^(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday)$", message = "Work week end must be a valid day of the week")
    private String workWeekEnd;

    private boolean halfWorkingDayEnabled;
    @NotNull(message = "Weekend definition is required")
    private Map<String, List<String>> weekendDefinition;
    private CalendarYearDTO calendarYear;
    private boolean statutoryWeekendEnabled;

    @Data
    public static class CalendarYearDTO {
        private boolean currentYear;
        @NotBlank(message = "Start date is required")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Start date must be in the format YYYY-MM-DD")
        private String startDate;
        @NotBlank(message = "End date is required")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "End date must be in the format YYYY-MM-DD")
        private String endDate;
    }
}
