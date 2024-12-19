package com.example.Project_1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate primary key
    private Integer userId; // Primary key field

    @Column(nullable = false)
    private String role;

    @Column(unique = true, nullable = false) // Enforce unique usernames and non-null
    private String username;

    @Column(nullable = false)
    private String password;

    // Default constructor (required for JPA)
    public User() {
    }

    // Constructor without role
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Constructor with role
    public User(String role, String username, String password) {
        this.role = role;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public Integer getId() {
        return userId;
    }

    public void setId(Integer userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", role='" + role + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
