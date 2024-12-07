package com.example.Recruitment.service.calendar;

import com.example.Recruitment.dto.calendar.AvailabilityDTO;

import java.util.List;

public interface AvailabilityService {
    AvailabilityDTO addAvailability(AvailabilityDTO availabilityDTO);
    List<AvailabilityDTO> getAllAvailabilities();
    AvailabilityDTO getAvailabilityById(String id);
    AvailabilityDTO updateAvailability(AvailabilityDTO availabilityDTO);
    void deleteAvailabilityById(String id);
}
