package Texsmartly.Leave.Tracker.service.settings;

import Texsmartly.Leave.Tracker.dto.settings.CalendarSettingsDTO;
import Texsmartly.Leave.Tracker.exception.ResourceNotFoundException;
import Texsmartly.Leave.Tracker.model.settings.CalendarSettings;
import Texsmartly.Leave.Tracker.repository.settings.CalendarSettingsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarSettingsServiceImpl implements CalendarSettingsService{
    @Autowired
    private CalendarSettingsRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CalendarSettingsDTO createCalendarSettings(CalendarSettingsDTO settingsDTO) {
        CalendarSettings entity = modelMapper.map(settingsDTO, CalendarSettings.class);
        entity = repository.save(entity);
        return modelMapper.map(entity, CalendarSettingsDTO.class);
    }

    @Override
    public CalendarSettingsDTO getCalendarSettings(String id) {
        CalendarSettings entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calendar settings not found with id: " + id));
        return modelMapper.map(entity, CalendarSettingsDTO.class);
    }

    @Override
    public List<CalendarSettingsDTO> getAllCalendarSettings() {
        List<CalendarSettings> entities = repository.findAll();
        return entities.stream()
                .map(entity -> modelMapper.map(entity, CalendarSettingsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CalendarSettingsDTO updateCalendarSettings(String id, CalendarSettingsDTO settingsDTO) {
        CalendarSettings existingSettings = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calendar settings not found with id: " + id));

        modelMapper.map(settingsDTO, existingSettings);
        existingSettings = repository.save(existingSettings);
        return modelMapper.map(existingSettings, CalendarSettingsDTO.class);
    }

    @Override
    public void deleteCalendarSettings(String id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Calendar settings not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
