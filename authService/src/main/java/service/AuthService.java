package service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.Session;
import repository.SessionRepository;
import repository.User;
import repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    public void Login(String userName, String password, User user){
        Session newSession = new Session(userName, password);
        newSession.setUser(user);
        sessionRepository.save(newSession);
    }
    public void Register(User user){
        userRepository.save(user);
    }
    public void Logout(Long userId){
        Optional<User> logoutUser = userRepository.findById(userId);
        List<Session> allSessions = sessionRepository.findAll();
        Session logoutSession = new Session();
        for (Session session: allSessions
             ) {
            if (session.getUserId().equals(userId)){
                logoutSession = session;
            }
        }
        sessionRepository.delete(logoutSession);
    }

}
