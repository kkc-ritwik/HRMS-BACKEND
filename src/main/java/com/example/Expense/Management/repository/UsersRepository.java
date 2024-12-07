package com.example.Expense.Management.repository;

import com.example.Expense.Management.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsersRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

}
