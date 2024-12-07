package com.example.Expense.Management.repository;

import com.example.Expense.Management.entity.Tax;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaxRepository extends MongoRepository<Tax, String> {
}
