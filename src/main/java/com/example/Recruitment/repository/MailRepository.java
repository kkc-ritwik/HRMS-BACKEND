package com.example.Recruitment.repository;

import com.example.Recruitment.model.MailStructure;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends MongoRepository<MailStructure, String> {
}