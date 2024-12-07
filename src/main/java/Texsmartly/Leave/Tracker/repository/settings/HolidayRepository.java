package Texsmartly.Leave.Tracker.repository.settings;

import Texsmartly.Leave.Tracker.model.settings.Holiday;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HolidayRepository extends MongoRepository<Holiday, String> {
    List<Holiday> findByDepartmentsContainingOrLocationsContainingOrDivisionsContaining(
            String department, String location, String division
    );
}
