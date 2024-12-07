package com.example.Expense.Management.service.AuthService;

import com.example.Expense.Management.dto.PasswordChangeDto;
import com.example.Expense.Management.entity.User;
import com.example.Expense.Management.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserByUsername(String email){
        Optional<User> user = userRepository.findByEmail(email);
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

    public boolean changePassword(String email, PasswordChangeDto passwordChangeDto) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Check if the old password matches
            if (!passwordEncoder.matches(passwordChangeDto.getOldPassword(), user.getPassword())) {
                return false; // Old password doesn't match
            }

            // Check if new password matches confirm password
            if (!passwordChangeDto.getNewPassword().equals(passwordChangeDto.getConfirmPassword())) {
                return false; // New password and confirm password don't match
            }

            // Check if new password is different from old password
            if (passwordEncoder.matches(passwordChangeDto.getNewPassword(), user.getPassword())) {
                return false; // New password is the same as the old password
            }

            // Update the password
            user.setPassword(passwordEncoder.encode(passwordChangeDto.getNewPassword()));
            userRepository.save(user);
            return true;
        }
        return false; // User not found
    }
    public void changePassword(String userId, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public long getUserCount() {
        return userRepository.count();
    }

}
