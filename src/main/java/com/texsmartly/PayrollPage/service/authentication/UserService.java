package com.texsmartly.PayrollPage.service.authentication;

import com.texsmartly.PayrollPage.model.authentication.User;
import com.texsmartly.PayrollPage.repository.authentication.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UsersRepository userRepository;

    public User getUserByUsername(String email){
        Optional<User> user=userRepository.findByEmail(email);
        return user.orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}
