package com.example.Recruitment.repository;

import com.example.Recruitment.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsersRepo extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
