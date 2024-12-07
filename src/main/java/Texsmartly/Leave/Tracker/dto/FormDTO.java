package Texsmartly.Leave.Tracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "forms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormDTO {

    private String id;
    private String userId;
    @NotBlank(message = "Absence Type is mandatory")
    private String absenceType;
    @NotBlank(message = "Leave Name is mandatory")
    private String leaveName;
    @NotBlank(message = "Start date is mandatory")
    private String startDate;
    @NotBlank(message = "End date is mandatory")
    private String endDate;
    private Integer period; // Automatically Calculated
    private String reason;
    private String dateOfRequest;  // Automatically set
    private String dateOfApproval;  // New field for the date of approval
    private String status;
    
}
