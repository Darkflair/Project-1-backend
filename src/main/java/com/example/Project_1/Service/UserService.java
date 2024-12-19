package com.example.Project_1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project_1.entity.User;
import com.example.Project_1.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) throws Exception {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be blank.");
        }
        if (user.getPassword() == null || user.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters long.");
        }
        User existingUser= userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new Exception("Username already exists.");
        }
        if(user.getRole() == null){
            user.setRole("employee");
        }
        return userRepository.save(user);
    }

    public User loginUser(User user) throws Exception{
        String password = user.getPassword();
        User existingUser = userRepository.findByUsername(user.getUsername());

        if(existingUser == null || !existingUser.getPassword().equals(password)){
              throw new Exception("Invalid username or password");
        }
        return existingUser;
    }

    
}