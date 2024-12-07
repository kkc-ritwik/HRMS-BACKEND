package Texsmartly.Leave.Tracker.model.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "holidays")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Holiday {
    @Id
    private String id;
    @Field(name = "holiday_Name")
    private String holidayName;
    @Field(name = "holiday_date")
    private LocalDate holidayDate;
    @Field(name = "holiday_type")
    private String type;
    @Field(name = "holiday_descriptions")
    private String description;
    private List<String> departments;
    private List<String> locations;
    private List<String> divisions;
    @Field(name = "notify_Applicable")
    private boolean notifyApplicable;
    @Field(name = "reprocess_Leave")
    private boolean reprocessLeave;

    private LocalDateTime createdAt;
}
