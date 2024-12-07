package Texsmartly.Leave.Tracker.api.controller.AdminController;

import Texsmartly.Leave.Tracker.config.ApiVersionConfig;
import Texsmartly.Leave.Tracker.dto.settings.CalendarSettingsDTO;
import Texsmartly.Leave.Tracker.exception.ResourceNotFoundException;
import Texsmartly.Leave.Tracker.service.settings.CalendarSettingsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION +"/admin/calendar-settings")
public class CalendarSettingsController {
    private final CalendarSettingsService service;

    @Autowired
    public CalendarSettingsController(CalendarSettingsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CalendarSettingsDTO> createCalendarSettings(@Valid @RequestBody CalendarSettingsDTO settingsDTO) {
        try {
            CalendarSettingsDTO createdSettings = service.createCalendarSettings(settingsDTO);
            return new ResponseEntity<>(createdSettings, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalendarSettingsDTO> getCalendarSettings(@PathVariable("id") String id) {
        try {
            CalendarSettingsDTO settings = service.getCalendarSettings(id);
            return new ResponseEntity<>(settings, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<CalendarSettingsDTO>> getAllCalendarSettings() {
        try {
            List<CalendarSettingsDTO> settings = service.getAllCalendarSettings();
            if (settings.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(settings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalendarSettingsDTO> updateCalendarSettings(@PathVariable("id") String id, @Valid @RequestBody CalendarSettingsDTO settingsDTO) {
        try {
            CalendarSettingsDTO updatedSettings = service.updateCalendarSettings(id, settingsDTO);
            return new ResponseEntity<>(updatedSettings, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCalendarSettings(@PathVariable("id") String id) {
        try {
            service.deleteCalendarSettings(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
