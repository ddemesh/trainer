package by.dima.training.utils;

import org.springframework.security.core.Authentication;

import java.util.Map;

public interface UserInfoExtractor {
    Integer getUserId(Authentication authentication);
}
