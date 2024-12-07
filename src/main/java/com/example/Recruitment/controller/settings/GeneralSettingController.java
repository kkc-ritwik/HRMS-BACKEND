package com.example.Recruitment.controller.settings;

import com.example.Recruitment.config.ApiVersionConfig;
import com.example.Recruitment.dto.settings.GeneralSettingDTO;
import com.example.Recruitment.service.settings.GeneralSettingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/general-setting")
@CrossOrigin("*")
public class GeneralSettingController {

    @Autowired
    private GeneralSettingService generalSettingService;

    @PostMapping
    public ResponseEntity<GeneralSettingDTO> createGeneralSetting(@Valid @RequestBody GeneralSettingDTO generalSettingDTO) {
        GeneralSettingDTO createdSetting = generalSettingService.createGeneralSetting(generalSettingDTO);
        return new ResponseEntity<>(createdSetting, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralSettingDTO> updateGeneralSetting(
            @PathVariable String id,
            @Valid @RequestBody GeneralSettingDTO generalSettingDTO) {
        GeneralSettingDTO updatedSetting = generalSettingService.updateGeneralSetting(id, generalSettingDTO);
        return new ResponseEntity<>(updatedSetting, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralSettingDTO> getGeneralSettingById(@PathVariable String id) {
        GeneralSettingDTO generalSettingDTO = generalSettingService.getGeneralSettingById(id);
        return new ResponseEntity<>(generalSettingDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GeneralSettingDTO>> getAllGeneralSettings() {
        List<GeneralSettingDTO> settingsList = generalSettingService.getAllGeneralSettings();
        return new ResponseEntity<>(settingsList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGeneralSetting(@PathVariable String id) {
        generalSettingService.deleteGeneralSetting(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
