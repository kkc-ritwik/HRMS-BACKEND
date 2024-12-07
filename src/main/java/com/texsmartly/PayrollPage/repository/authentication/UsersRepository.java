package com.texsmartly.PayrollPage.repository.authentication;


import com.texsmartly.PayrollPage.model.authentication.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsersRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);


}