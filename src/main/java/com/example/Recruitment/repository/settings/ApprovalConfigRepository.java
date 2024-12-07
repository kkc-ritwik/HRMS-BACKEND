package com.example.Recruitment.repository.settings;

import com.example.Recruitment.model.settings.ApprovalConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApprovalConfigRepository extends MongoRepository<ApprovalConfig, String> {
}
