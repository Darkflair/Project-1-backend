package com.example.Project_1.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.Project_1.entity.User;
import com.example.Project_1.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
void testRegisterUser_Success() throws Exception {
   
    User user = new User();
    user.setUsername("testUser");
    user.setPassword("password123");
    user.setRole(null);

    when(userRepository.findByUsername("testUser")).thenReturn(null);
    when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

    
    User registeredUser = userService.registerUser(user);

    
    assertNotNull(registeredUser);
    assertEquals("testUser", registeredUser.getUsername());
    assertEquals("employee", registeredUser.getRole());
    verify(userRepository, times(1)).save(user);
}

@Test
void testRegisterUser_Fail_BlankUsername() {
    
    User user = new User();
    user.setUsername("");
    user.setPassword("password123");

    
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> userService.registerUser(user)
    );
    assertEquals("Username cannot be blank.", exception.getMessage());
    verify(userRepository, never()).save(any(User.class));
}

@Test
void testRegisterUser_Fail_ShortPassword() {
    
    User user = new User();
    user.setUsername("testUser");
    user.setPassword("123"); 

    
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> userService.registerUser(user)
    );
    assertEquals("Password must be at least 4 characters long.", exception.getMessage());
    verify(userRepository, never()).save(any(User.class));
}

@Test
void testRegisterUser_Fail_UsernameExists() {
   
    User user = new User();
    user.setUsername("existingUser");
    user.setPassword("password123");

    User existingUser = new User();
    existingUser.setUsername("existingUser");

    when(userRepository.findByUsername("existingUser")).thenReturn(existingUser);

    
    Exception exception = assertThrows(
        Exception.class,
        () -> userService.registerUser(user)
    );
    assertEquals("Username already exists.", exception.getMessage());
    verify(userRepository, never()).save(any(User.class));
}
}