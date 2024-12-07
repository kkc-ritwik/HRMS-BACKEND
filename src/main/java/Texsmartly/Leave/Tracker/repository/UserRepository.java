package Texsmartly.Leave.Tracker.repository;

import Texsmartly.Leave.Tracker.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);

    List<User> findByDepartmentOrLocationOrDivision(String department, String location, String division);
}
