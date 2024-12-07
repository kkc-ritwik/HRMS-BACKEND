package com.example.Recruitment.service.settings;

import com.example.Recruitment.dto.settings.GeneralSettingDTO;

import java.util.List;

public interface GeneralSettingService {
    List<GeneralSettingDTO> getAllGeneralSetting();

    List<GeneralSettingDTO> getAllGeneralSettings();

    GeneralSettingDTO getGeneralSettingById(String id);
    GeneralSettingDTO createGeneralSetting (GeneralSettingDTO generalSettingDTO);
    GeneralSettingDTO updateGeneralSetting(String id, GeneralSettingDTO generalSettingDTO);
    void deleteGeneralSetting(String id);
}
