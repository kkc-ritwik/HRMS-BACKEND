package Texsmartly.Leave.Tracker.api.controller.AdminController;

import Texsmartly.Leave.Tracker.config.ApiVersionConfig;
import Texsmartly.Leave.Tracker.dto.settings.GeneralSettingsDTO;
import Texsmartly.Leave.Tracker.service.settings.GeneralSettingsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION +"/admin/general-settings")

public class GeneralSettingsController {
    private final GeneralSettingsService settingsService;

    @Autowired
    public GeneralSettingsController(GeneralSettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @PostMapping
    public ResponseEntity<GeneralSettingsDTO> addSettings(@Valid @RequestBody GeneralSettingsDTO settingsDTO) {
        GeneralSettingsDTO savedSettings = settingsService.saveSettings(settingsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSettings);
    }

    @GetMapping
    public List<GeneralSettingsDTO> getSettings() {
        return settingsService.getAllSettings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralSettingsDTO> getSettingsById(@PathVariable String id) {
        Optional<GeneralSettingsDTO> settings = settingsService.getSettingsById(id);
        return settings.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralSettingsDTO> updateSettings(@PathVariable String id, @Valid @RequestBody GeneralSettingsDTO settingsDTO) {
        GeneralSettingsDTO updatedSettings = settingsService.updateSettings(id, settingsDTO);
        if (updatedSettings != null) {
            return ResponseEntity.ok(updatedSettings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSettings(@PathVariable String id) {
        boolean isDeleted = settingsService.deleteSettings(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
