package by.dima.auth.service;

import by.dima.auth.exception.UsernameExistException;
import by.dima.auth.model.User;
import by.dima.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Object extractUserInfo(User user) {
        HashMap<String, Object> info = new HashMap<>();
        info.put("username", user.getUsername());
        info.put("id", user.getId());
        return info;
    }

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameExistException("User with this username exist");
        }

        user.setId(null);

        return userRepository.save(user);
    }
}
