package com.example.Project_1.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("admin", "adminPassword");
    }

    @Test
    void testUserConstructorWithoutRole() {
        User userWithoutRole = new User("user1", "password");
        assertNotNull(userWithoutRole);
        assertNull(userWithoutRole.getRole());  // Expect role to be null
        assertEquals("user1", userWithoutRole.getUsername());
        assertEquals("password", userWithoutRole.getPassword());
    }

    @Test
    void testUserConstructorWithRole() {
        User userWithRole = new User("admin", "user1", "password");
        assertNotNull(userWithRole);
        assertEquals("admin", userWithRole.getRole());
        assertEquals("user1", userWithRole.getUsername());
        assertEquals("password", userWithRole.getPassword());
    }

    @Test
    void testUserGettersAndSetters() {
        user.setUsername("newUser");
        user.setPassword("newPassword");
        user.setRole("user");

        assertEquals("newUser", user.getUsername());
        assertEquals("newPassword", user.getPassword());
        assertEquals("user", user.getRole());
    }

    @Test
    void testToString() {
        User userWithRole = new User("admin", "user1", "password");
        String expected = "User{userId=null, role='admin', username='user1', password='password'}";
        assertEquals(expected, userWithRole.toString());
    }

    @Test
    void testUserId() {
        user.setId(1);
        assertEquals(1, user.getId());
    }

    @Test
    void testRoleNotNull() {
        user.setRole("user");
        assertNotNull(user.getRole());
        assertEquals("user", user.getRole());
    }

    @Test
    void testPasswordNotNull() {
        user.setPassword("newPassword");
        assertNotNull(user.getPassword());
        assertEquals("newPassword", user.getPassword());
    }
}
