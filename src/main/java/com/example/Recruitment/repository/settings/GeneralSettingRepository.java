package com.example.Recruitment.repository.settings;

import com.example.Recruitment.model.settings.GeneralSetting;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GeneralSettingRepository extends MongoRepository<GeneralSetting, String> {
}