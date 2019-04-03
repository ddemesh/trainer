package by.dima.training.config;

import by.dima.training.utils.CustomAccessTokenConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

import java.util.List;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @Primary
    @Bean
    public RemoteTokenServices tokenService() {
        List<ServiceInstance> services = discoveryClient.getInstances("auth-service");
        RemoteTokenServices tokenService = new RemoteTokenServices();
        if (services != null && services.size() > 0) {
            tokenService.setCheckTokenEndpointUrl(
                    services.get(0).getUri() + "/oauth/check_token");
        }
        tokenService.setClientId("trainer_client");
        tokenService.setClientSecret("trainer_password");
        tokenService.setAccessTokenConverter(accessTokenConverter());
        return tokenService;
    }

    @Bean
    public AccessTokenConverter accessTokenConverter() {
        return  new CustomAccessTokenConverter();
    }
}
