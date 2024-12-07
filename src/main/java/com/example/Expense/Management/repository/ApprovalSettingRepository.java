package com.example.Expense.Management.repository;

import com.example.Expense.Management.entity.ApprovalSetting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalSettingRepository extends MongoRepository<ApprovalSetting, String> {
}