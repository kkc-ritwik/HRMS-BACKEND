package Texsmartly.Leave.Tracker.service.settings;

import Texsmartly.Leave.Tracker.dto.settings.GeneralSettingsDTO;

import java.util.List;
import java.util.Optional;

public interface GeneralSettingsService {
    GeneralSettingsDTO saveSettings(GeneralSettingsDTO settingsDTO);

    List<GeneralSettingsDTO> getAllSettings();

    Optional<GeneralSettingsDTO> getSettingsById(String id);

    GeneralSettingsDTO updateSettings(String id, GeneralSettingsDTO settingsDTO);

    boolean deleteSettings(String id);
}
