package by.dima.auth.service;

import by.dima.auth.model.User;
import by.dima.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileImageServiceImpl implements ProfileImageService {

    private UserRepository userRepository;

    public ProfileImageServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void update(String image, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        user.setImageUrl(image);
        userRepository.save(user);
    }
}
