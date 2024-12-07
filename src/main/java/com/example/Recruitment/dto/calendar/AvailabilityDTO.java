package com.example.Recruitment.dto.calendar;

import lombok.Data;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

@Data
public class AvailabilityDTO {
    @Id
    private String id;

    @NotNull(message = "Date cannot be null")
    private String date;

    @NotBlank(message = "Start time is required")
    private String fromTime;

    @NotBlank(message = "End time is required")
    private String toTime;

    @NotNull(message = "Recurrence flag cannot be null")
    private boolean recurrence;

    @Pattern(regexp = "DAILY|WEEKLY|MONTHLY|YEARLY", message = "Repeat frequency must be one of: DAILY, WEEKLY, MONTHLY, YEARLY")
    private String repeatFrequency;

    private String endByDate;

    @PositiveOrZero(message = "Occurrences must be zero or a positive number")
    private Integer occurrences;

    private List<LocalDate> recurrenceDates;
}
