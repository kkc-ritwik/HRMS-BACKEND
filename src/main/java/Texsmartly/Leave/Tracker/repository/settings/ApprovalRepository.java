package Texsmartly.Leave.Tracker.repository.settings;

import Texsmartly.Leave.Tracker.model.settings.Approval;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ApprovalRepository extends MongoRepository<Approval,String> {
    List<Approval> findByFollowUpEnabledTrue();
    List<Approval> findByCreatedAtBefore(LocalDateTime dateTime);
}
