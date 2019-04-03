package by.dima.training.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;

import java.io.Serializable;
import java.util.*;

public class CustomAccessTokenConverter extends DefaultAccessTokenConverter {

    @Autowired
    private UserAuthenticationConverter userTokenConverter;

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        Map<String, String> parameters = new HashMap<String, String>();
        Set<String> scope = new LinkedHashSet(map.containsKey(SCOPE) ? (Collection) map.get(SCOPE)
                : Collections.emptySet());
        Authentication user = userTokenConverter.extractAuthentication(map);
        String clientId = (String) map.get(CLIENT_ID);
        parameters.put(CLIENT_ID, clientId);
        Set<String> resourceIds = new LinkedHashSet<String>(map.containsKey(AUD) ? (Collection) map.get(AUD)
                : Collections.emptySet());

        Map<String, Serializable> extensions = new HashMap<String, Serializable>();

        OAuth2Request request = new OAuth2Request(parameters, clientId, null, true, scope, resourceIds, null, null,
                extensions);
        return new OAuth2Authentication(request, user);
    }
}
