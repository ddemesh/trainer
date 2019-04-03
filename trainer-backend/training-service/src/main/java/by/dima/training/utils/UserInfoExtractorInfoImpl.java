package by.dima.training.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserInfoExtractorInfoImpl implements UserInfoExtractor {

    @Override
    public Integer getUserId(Authentication authentication) {
        return (Integer)((OAuth2Authentication) authentication).getUserAuthentication().getDetails();
    }
}
