package com.example.Expense.Management.repository;

import com.example.Expense.Management.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
