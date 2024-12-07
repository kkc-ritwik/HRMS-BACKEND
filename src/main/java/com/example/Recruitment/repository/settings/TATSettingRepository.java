package com.example.Recruitment.repository.settings;

import com.example.Recruitment.model.settings.TATSetting;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TATSettingRepository extends MongoRepository<TATSetting,String> {
}
