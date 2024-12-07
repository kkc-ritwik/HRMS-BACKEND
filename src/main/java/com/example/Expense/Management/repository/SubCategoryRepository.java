package com.example.Expense.Management.repository;

import com.example.Expense.Management.entity.SubCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubCategoryRepository extends MongoRepository<SubCategory, String> {
    List<SubCategory> findByCategoryId(String categoryId);
}
