package com.example.Recruitment.model.calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "availabilities")
public class Availability {
    @Id
    private String id = UUID.randomUUID().toString();
    private String date;
    private String fromTime;
    private String toTime;
    private boolean recurrence;
    private String repeatFrequency;
    private String endByDate;
    private Integer occurrences;
    private List<LocalDate> recurrenceDates;
}
