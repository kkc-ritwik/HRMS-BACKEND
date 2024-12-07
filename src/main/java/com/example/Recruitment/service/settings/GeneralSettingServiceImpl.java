package com.example.Recruitment.service.settings;

import com.example.Recruitment.dto.settings.GeneralSettingDTO;
import com.example.Recruitment.model.settings.GeneralSetting;
import com.example.Recruitment.repository.settings.GeneralSettingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneralSettingServiceImpl implements GeneralSettingService {
    @Autowired
    private GeneralSettingRepository generalSettingRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<GeneralSettingDTO> getAllGeneralSetting() {
        List<GeneralSetting> generalSetting = generalSettingRepository.findAll();
        return generalSetting.stream()
                .map(generalSetting1 -> modelMapper.map(generalSetting, GeneralSettingDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GeneralSettingDTO> getAllGeneralSettings() {
        return List.of();
    }


    @Override
    public GeneralSettingDTO getGeneralSettingById(String id){
        GeneralSetting generalSetting = generalSettingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GeneralSetting not Found"));
        return modelMapper.map(generalSetting,GeneralSettingDTO.class);
    }

    @Override
    public GeneralSettingDTO createGeneralSetting(GeneralSettingDTO generalSettingDTO) {
        GeneralSetting generalSetting = modelMapper.map(generalSettingDTO, GeneralSetting.class);
        GeneralSetting savedSetting = generalSettingRepository.save(generalSetting);
        return modelMapper.map(savedSetting, GeneralSettingDTO.class);
    }

    @Override
    public GeneralSettingDTO updateGeneralSetting(String id, GeneralSettingDTO generalSettingDTO){
        GeneralSetting existingSetting = generalSettingRepository.findById(id).orElseThrow(() -> new RuntimeException("GeneralSetting not found"));
        modelMapper.map(generalSettingDTO, existingSetting);
        GeneralSetting updatedSetting = generalSettingRepository.save(existingSetting);
        return modelMapper.map(updatedSetting, GeneralSettingDTO.class);
    }

    @Override
    public void deleteGeneralSetting(String id){
        GeneralSetting generalSetting = generalSettingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GeneralSetting not found"));
        generalSettingRepository.delete(generalSetting);
    }
}

