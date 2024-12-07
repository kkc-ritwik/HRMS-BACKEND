package Texsmartly.Leave.Tracker.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Document(collection = "forms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Form {
@Id
    private String id;
    private String userId;

    @Field(name = "absence_type")
    private String absenceType;

    @Field(name="leave_Name")
    private String leaveName;

    @Field(name = "start_Date")
    private String startDate;

    @Field(name = "end_Date")
    private String endDate;
    private Integer period; // Automatically Calculated
    private String reason;
    @Field(name = "date_Of_Request")
    private String dateOfRequest;  // Automatically set
    @Field(name = "date_Of_Approval")
    private String dateOfApproval;  // New field for the date of approval
    private String status;

    // Method to calculate the period
    public void calculatePeriod() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(this.startDate, formatter);
        LocalDate end = LocalDate.parse(this.endDate, formatter);
        this.period = (int) ChronoUnit.DAYS.between(start, end) + 1; // Include both start and end dates
    }

    // Method to set the date of request automatically
    public void setDateOfRequest() {
        this.dateOfRequest = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    // Method to set the date of approval automatically
    public void setDateOfApproval() {
        this.dateOfApproval = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}


