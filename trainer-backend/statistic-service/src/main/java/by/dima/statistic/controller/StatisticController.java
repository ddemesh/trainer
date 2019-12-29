package by.dima.statistic.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data")
@CrossOrigin(origins = "*")
public class StatisticController {

    private RestTemplate restTemplate;

    public StatisticController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public Object getData(Authentication authentication, @RequestHeader("Authorization") String token) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);

        List<Map> result = restTemplate.exchange("http://goal-service/data", HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<Map>>() {}).getBody();
        return result;
    }
}
