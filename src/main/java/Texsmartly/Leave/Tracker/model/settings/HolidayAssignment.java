package Texsmartly.Leave.Tracker.model.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "holiday_assignments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HolidayAssignment {
    @Id
    private String id;
    private String holidayId;
    @Field(name = "holiday_Name")
    private String holidayName;
    private String userId;
    @Field(name = "holiday_Date")
    private LocalDate holidayDate;
    private String description;
    @Field(name = "created_At")
    private LocalDateTime createdAt;
}
