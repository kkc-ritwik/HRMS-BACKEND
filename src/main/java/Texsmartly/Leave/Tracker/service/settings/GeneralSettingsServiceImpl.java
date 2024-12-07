package Texsmartly.Leave.Tracker.service.settings;

import Texsmartly.Leave.Tracker.dto.settings.GeneralSettingsDTO;
import Texsmartly.Leave.Tracker.model.settings.GeneralSettings;
import Texsmartly.Leave.Tracker.repository.settings.GeneralSettingsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeneralSettingsServiceImpl implements GeneralSettingsService{
    private final GeneralSettingsRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public GeneralSettingsServiceImpl(GeneralSettingsRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GeneralSettingsDTO saveSettings(GeneralSettingsDTO settingsDTO) {
        GeneralSettings settings = modelMapper.map(settingsDTO, GeneralSettings.class);
        GeneralSettings savedSettings = repository.save(settings);
        return modelMapper.map(savedSettings, GeneralSettingsDTO.class);
    }

    @Override
    public List<GeneralSettingsDTO> getAllSettings() {
        return repository.findAll().stream()
                .map(settings -> modelMapper.map(settings, GeneralSettingsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<GeneralSettingsDTO> getSettingsById(String id) {
        return repository.findById(id)
                .map(settings -> modelMapper.map(settings, GeneralSettingsDTO.class));
    }

    @Override
    public GeneralSettingsDTO updateSettings(String id, GeneralSettingsDTO settingsDTO) {
        if (repository.existsById(id)) {
            GeneralSettings settings = modelMapper.map(settingsDTO, GeneralSettings.class);
            settings.setId(id);
            GeneralSettings updatedSettings = repository.save(settings);
            return modelMapper.map(updatedSettings, GeneralSettingsDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteSettings(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
