package com.example.Recruitment.service.calendar;

import com.example.Recruitment.dto.calendar.AvailabilityDTO;
import com.example.Recruitment.model.calendar.Availability;
import com.example.Recruitment.repository.calendar.AvailabilityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {
    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AvailabilityDTO addAvailability(AvailabilityDTO availabilityDTO) {
        Availability availability = modelMapper.map(availabilityDTO, Availability.class);
        Availability savedAvailability = availabilityRepository.save(availability);
        return modelMapper.map(savedAvailability, AvailabilityDTO.class);
    }

    @Override
    public List<AvailabilityDTO> getAllAvailabilities() {
        return availabilityRepository.findAll().stream()
                .map(availability -> modelMapper.map(availability, AvailabilityDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AvailabilityDTO getAvailabilityById(String id) {
        Availability availability = availabilityRepository.findById(id).orElse(null);
        return modelMapper.map(availability, AvailabilityDTO.class);
    }

    @Override
    public AvailabilityDTO updateAvailability(AvailabilityDTO availabilityDTO) {
        Availability availability = modelMapper.map(availabilityDTO, Availability.class);
        Availability updatedAvailability = availabilityRepository.save(availability);
        return modelMapper.map(updatedAvailability, AvailabilityDTO.class);
    }

    @Override
    public void deleteAvailabilityById(String id) {
        availabilityRepository.deleteById(id);
    }
}
