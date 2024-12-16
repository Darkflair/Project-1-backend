package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import Model.User;
import Service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class ReimbursementController {

    private final UserService userService;

    @Autowired
    public ReimbursementController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {
        String message = userService.register(user);
        return Map.of("message", message);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        String message = userService.login(user);
        return Map.of("message", message);
    }
}
