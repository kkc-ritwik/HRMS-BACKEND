package com.example.Recruitment.service.settings;

import com.example.Recruitment.dto.settings.TATSettingDTO;
import com.example.Recruitment.model.settings.TATSetting;
import com.example.Recruitment.repository.settings.TATSettingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TATSettingServiceImpl implements TATSettingService {
    @Autowired
    private TATSettingRepository tatSettingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TATSettingDTO createTATSetting(TATSettingDTO tatSettingDTO) {
        TATSetting tatSetting = modelMapper.map(tatSettingDTO, TATSetting.class);
        TATSetting savedSetting = tatSettingRepository.save(tatSetting);
        return modelMapper.map(savedSetting, TATSettingDTO.class);
    }

    @Override
    public TATSettingDTO updateTATSetting(String id, TATSettingDTO tatSettingDTO) {
        TATSetting existingSetting = tatSettingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TAT Setting not found"));
        modelMapper.map(tatSettingDTO, existingSetting);
        TATSetting updatedSetting = tatSettingRepository.save(existingSetting);
        return modelMapper.map(updatedSetting, TATSettingDTO.class);
    }

    @Override
    public TATSettingDTO getTATSettingById(String id) {
        TATSetting tatSetting = tatSettingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TAT Setting not found"));
        return modelMapper.map(tatSetting, TATSettingDTO.class);
    }

    @Override
    public List<TATSettingDTO> getAllTATSettings() {
        List<TATSetting> tatSettings = tatSettingRepository.findAll();
        return tatSettings.stream()
                .map(tatSetting -> modelMapper.map(tatSetting, TATSettingDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTATSetting(String id) {
        TATSetting tatSetting = tatSettingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TAT Setting not found"));
        tatSettingRepository.delete(tatSetting);
    }

}
