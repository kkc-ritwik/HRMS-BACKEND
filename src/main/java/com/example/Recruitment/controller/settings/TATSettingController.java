package com.example.Recruitment.controller.settings;

import com.example.Recruitment.config.ApiVersionConfig;
import com.example.Recruitment.dto.settings.TATSettingDTO;
import com.example.Recruitment.service.settings.TATSettingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/tat-setting")
@CrossOrigin("*")
public class TATSettingController {

    @Autowired
    private TATSettingService tatSettingService;

    @PostMapping
    public ResponseEntity<TATSettingDTO> createTATSetting(@Valid @RequestBody TATSettingDTO tatSettingDTO) {
        TATSettingDTO createdSetting = tatSettingService.createTATSetting(tatSettingDTO);
        return new ResponseEntity<>(createdSetting, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TATSettingDTO> updateTATSetting(
            @PathVariable String id,
            @Valid @RequestBody TATSettingDTO tatSettingDTO) {
        TATSettingDTO updatedSetting = tatSettingService.updateTATSetting(id, tatSettingDTO);
        return new ResponseEntity<>(updatedSetting, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TATSettingDTO> getTATSettingById(@PathVariable String id) {
        TATSettingDTO tatSettingDTO = tatSettingService.getTATSettingById(id);
        return new ResponseEntity<>(tatSettingDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TATSettingDTO>> getAllTATSettings() {
        List<TATSettingDTO> settingsList = tatSettingService.getAllTATSettings();
        return new ResponseEntity<>(settingsList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTATSetting(@PathVariable String id) {
        tatSettingService.deleteTATSetting(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
