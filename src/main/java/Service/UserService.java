package Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import DAO.UserDAO;
import Model.User;

@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String register(User user) {
        if (userDAO.findByUsername(user.getUsername()) != null) {
            return "Username already exists";
        }
        user.setRole("employee");
        userDAO.save(user);
        return "User registered successfully";
    }

    public String login(User user) {
        User foundUser = userDAO.findByUsername(user.getUsername());
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            return "Login successful";
        }
        return "Invalid username or password";
    }

     @GetMapping("/test")
    public String testEndpoint() {
        return "Test successful!";
    }
}