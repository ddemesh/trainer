package by.dima.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

public class UserInfoExtractorInfoImpl implements UserInfoExtractor {

    @Override
    public Integer getUserId(Authentication authentication) {
        return Integer.parseInt(((User)((OAuth2AuthenticationDetails) authentication.getDetails()).getDecodedDetails()).getUsername());
    }
}
