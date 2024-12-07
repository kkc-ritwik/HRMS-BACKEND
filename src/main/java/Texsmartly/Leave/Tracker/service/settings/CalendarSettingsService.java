package Texsmartly.Leave.Tracker.service.settings;

import Texsmartly.Leave.Tracker.dto.settings.CalendarSettingsDTO;

import java.util.List;

public interface CalendarSettingsService {
    CalendarSettingsDTO createCalendarSettings(CalendarSettingsDTO settingsDTO);
    CalendarSettingsDTO getCalendarSettings(String id);
    List<CalendarSettingsDTO> getAllCalendarSettings();
    CalendarSettingsDTO updateCalendarSettings(String id, CalendarSettingsDTO settingsDTO);
    void deleteCalendarSettings(String id);
}
