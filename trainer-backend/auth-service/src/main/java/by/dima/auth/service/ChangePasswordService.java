package by.dima.auth.service;

public interface ChangePasswordService {
    void change(String oldPass, String newPass, String username);
}
