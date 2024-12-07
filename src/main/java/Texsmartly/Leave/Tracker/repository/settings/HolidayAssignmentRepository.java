package Texsmartly.Leave.Tracker.repository.settings;

import Texsmartly.Leave.Tracker.model.settings.HolidayAssignment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HolidayAssignmentRepository extends MongoRepository<HolidayAssignment, String> {
    List<HolidayAssignment> findByUserId(String userId);
    List<HolidayAssignment> findByHolidayId(String holidayId);
    List<HolidayAssignment> findByUserIdOrderByCreatedAtDesc(String userId);
}
