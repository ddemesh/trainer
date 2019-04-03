package by.dima.auth.service;

import by.dima.auth.model.User;

public interface UserService {
    Object extractUserInfo(User user);
    User register(User user);
}
