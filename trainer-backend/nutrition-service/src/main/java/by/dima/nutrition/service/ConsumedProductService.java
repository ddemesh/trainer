package by.dima.nutrition.service;

import by.dima.nutrition.model.ConsumedProduct;

import java.util.Date;
import java.util.Map;

public interface ConsumedProductService {
    void save(ConsumedProduct consumedProduct, Integer userId);
    Map<String, Float> getNutrientsFromDate(Date date, Integer userId, String token);
}
