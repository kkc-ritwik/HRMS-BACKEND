package Texsmartly.Leave.Tracker.repository;

import Texsmartly.Leave.Tracker.model.Form;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FormRepository extends MongoRepository<Form, String> {
    List<Form> findByUserId(String userId);
}
