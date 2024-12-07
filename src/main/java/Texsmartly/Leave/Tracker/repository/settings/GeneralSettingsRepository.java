package Texsmartly.Leave.Tracker.repository.settings;

import Texsmartly.Leave.Tracker.model.settings.GeneralSettings;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GeneralSettingsRepository extends MongoRepository<GeneralSettings, String> {
}
