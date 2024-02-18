package com.example.authService.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repository.Session;
import repository.SessionRepository;
import repository.User;
import repository.UserRepository;
import service.AuthService;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AuthServiceTest {
    @InjectMocks
    private AuthService authService;
    @Mock
    private  UserRepository userRepository;
    @Mock
    private  SessionRepository sessionRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister(){
        User user = new User();
        authService.Register(user);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void testLogin(){
        String userName = "userName";
        String password = "password";
        User user = new User();
        user.setName(userName);
        user.setPassword(password);
        Session newSession = new Session(user, userName, password);

        authService.Login(userName, password, user);
        Mockito.verify(sessionRepository, Mockito.times(1)).save(newSession);
    }

    public void testLogout(){
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setName("userName");
        user.setPassword("password");
        Session newSession = new Session(user, "userName", "password");
        authService.Login("userName", "password", user);

        authService.Logout(userId);
        Mockito.verify(sessionRepository, Mockito.times(1)).delete(newSession);
    }


}
