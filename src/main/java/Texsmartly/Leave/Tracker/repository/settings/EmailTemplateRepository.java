package Texsmartly.Leave.Tracker.repository.settings;

import Texsmartly.Leave.Tracker.model.settings.EmailTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailTemplateRepository extends MongoRepository<EmailTemplate,String> {
}
