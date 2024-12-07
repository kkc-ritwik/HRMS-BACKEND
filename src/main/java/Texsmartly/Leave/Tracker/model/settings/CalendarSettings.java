package Texsmartly.Leave.Tracker.model.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "calendar-settings")
public class CalendarSettings {

    @Id
    private String id;
    private String location;
    @Field(name = "week_Start")
    private String weekStart;
    @Field(name = "work_Week_Start")
    private String workWeekStart;
    @Field(name = "work_Week_End")
    private String workWeekEnd;
    @Field(name = "halfWorkingDay_Enabled")
    private boolean halfWorkingDayEnabled;
    @NotNull(message = "Weekend definition is required")
    private Map<DayOfWeek, List<DayStatus>> weekendDefinition;
    @NotNull(message = "Calendar year information is required")
    @Field(name = "calendar_Year")
    private CalendarYear calendarYear;

    private boolean statutoryWeekendEnabled;

    public enum DayOfWeek {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }

    public enum DayStatus {
        FULL_DAY, FIRST_HALF, SECOND_HALF, OFF
    }

    @Data
    public static class CalendarYear {
        @Field(name = "current_Year")
        private boolean currentYear;
        @Field(name = "start_Date")
        private LocalDate startDate;
        @Field(name = "end_Date")
        private LocalDate endDate;
    }
}
