package Texsmartly.Leave.Tracker.repository.settings;

import Texsmartly.Leave.Tracker.model.settings.CalendarSettings;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CalendarSettingsRepository  extends MongoRepository<CalendarSettings, String> {
}
