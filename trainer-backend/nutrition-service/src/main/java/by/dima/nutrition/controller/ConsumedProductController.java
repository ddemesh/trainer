package by.dima.nutrition.controller;

import by.dima.common.utils.UserInfoExtractor;
import by.dima.nutrition.model.ConsumedProduct;
import by.dima.nutrition.service.ConsumedProductService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/consumption")
public class ConsumedProductController {
    private ConsumedProductService consumedProductService;
    private UserInfoExtractor infoExtractor;

    public ConsumedProductController(ConsumedProductService consumedProductService, UserInfoExtractor infoExtractor) {
        this.consumedProductService = consumedProductService;
        this.infoExtractor = infoExtractor;
    }

    @PostMapping
    public void save(@RequestBody ConsumedProduct consumedProduct, Authentication authentication) {
        consumedProduct.setDate(new java.util.Date());
        consumedProductService.save(consumedProduct, infoExtractor.getUserId(authentication));
    }

    @GetMapping
    public Map<String, Float> getNutrientsByDate(Authentication authentication, @RequestHeader("Authorization") String token) {
        return consumedProductService.getNutrientsFromDate(Date.valueOf(LocalDate.now()), infoExtractor.getUserId(authentication), token);
    }
}
