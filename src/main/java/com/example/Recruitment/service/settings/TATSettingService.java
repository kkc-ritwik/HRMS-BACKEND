package com.example.Recruitment.service.settings;

import com.example.Recruitment.dto.settings.TATSettingDTO;

import java.util.List;

public interface TATSettingService {
    TATSettingDTO createTATSetting(TATSettingDTO tatSettingDTO);
    TATSettingDTO updateTATSetting(String id, TATSettingDTO tatSettingDTO);
    TATSettingDTO getTATSettingById(String id);
    List<TATSettingDTO> getAllTATSettings();
    void deleteTATSetting(String id);
}
