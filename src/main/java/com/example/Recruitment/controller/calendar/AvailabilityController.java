package com.example.Recruitment.controller.calendar;

import com.example.Recruitment.config.ApiVersionConfig;
import com.example.Recruitment.dto.calendar.AvailabilityDTO;
import com.example.Recruitment.service.calendar.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/availabilities")
@CrossOrigin("*")
@Validated
public class AvailabilityController {
    @Autowired
    private AvailabilityService availabilityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AvailabilityDTO addAvailability(@Valid @RequestBody AvailabilityDTO availabilityDTO) {
        return availabilityService.addAvailability(availabilityDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AvailabilityDTO> getAllAvailabilities() {
        return availabilityService.getAllAvailabilities();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailabilityDTO getAvailabilityById(@PathVariable String id) {
        return availabilityService.getAvailabilityById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailabilityDTO updateAvailability(@PathVariable String id, @Valid @RequestBody AvailabilityDTO availabilityDTO) {
        availabilityDTO.setId(id);
        return availabilityService.updateAvailability(availabilityDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAvailabilityById(@PathVariable String id) {
        availabilityService.deleteAvailabilityById(id);
    }

    // Exception handler for validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
}
