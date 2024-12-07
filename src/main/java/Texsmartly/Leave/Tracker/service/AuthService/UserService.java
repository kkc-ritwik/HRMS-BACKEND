package Texsmartly.Leave.Tracker.service.AuthService;

import Texsmartly.Leave.Tracker.dto.UserDTO;
import Texsmartly.Leave.Tracker.model.User;
import Texsmartly.Leave.Tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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


}
