package by.dima.nutrition.service.impl;

import by.dima.nutrition.model.ConsumedProduct;
import by.dima.nutrition.repository.ConsumedProductRepository;
import by.dima.nutrition.service.ConsumedProductService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConsumedProductServiceImpl implements ConsumedProductService {

    private ConsumedProductRepository consumedProductRepository;
    private RestTemplate restTemplate;

    public ConsumedProductServiceImpl(ConsumedProductRepository consumedProductRepository, RestTemplate restTemplate) {
        this.consumedProductRepository = consumedProductRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void save(ConsumedProduct consumedProduct, Integer userId) {
        consumedProduct.setId(null);
        consumedProduct.setUserId(userId);
        consumedProductRepository.save(consumedProduct);
    }

    @Override
    public Map<String, Float> getNutrientsFromDate(Date date, Integer userId, String token) {
        Map<String, Float> nutrients = new HashMap<>(consumedProductRepository.getNutrientsFromDateRange(date, new Date()));
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);
        Float weight = restTemplate.exchange("http://goal-service/weight", HttpMethod.GET, new HttpEntity<>(headers), Float.class).getBody();
        Float calories;
        if (weight == null || weight <= 0.0f) {
            calories = 2200f;
        } else {
            calories = weight * 34.3f;
        }
        nutrients.put("daily", calories);
        return nutrients;
    }
}
