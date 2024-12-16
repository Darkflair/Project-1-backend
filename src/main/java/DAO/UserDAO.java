package DAO;

import org.springframework.stereotype.Repository;

import Model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO {
    private final List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return users;
    }

    public void save(User user) {
        users.add(user);
    }

    public User findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}