package by.dima.auth.service;

import by.dima.auth.model.User;
import by.dima.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordServiceImpl implements ChangePasswordService {

    private PasswordEncoder encoder;
    private UserRepository userRepository;

    public ChangePasswordServiceImpl(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public void change(String oldPass, String newPass, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User is not authorised"));

        if (!encoder.matches(oldPass, user.getPassword())) {
            throw new RuntimeException("Old Pass isn't correct");
        }

        user.setPassword(encoder.encode(newPass));
        userRepository.save(user);
    }
}
