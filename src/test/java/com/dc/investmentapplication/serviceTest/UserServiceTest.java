package com.dc.investmentapplication.serviceTest;

import com.dc.investmentapplication.entity.User;
import com.dc.investmentapplication.repository.UserRepository;
import com.dc.investmentapplication.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setUsername("test@test.com");
        user.setPassword("password");
        user.setUsername("test");

        when(userRepository.save(user)).thenReturn(user);

        try {
            String createdUser = userService.createUser(user);
            assertEquals("User created!", createdUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
