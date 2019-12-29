package by.dima.goal.controller;

import by.dima.common.utils.UserInfoExtractor;
import by.dima.goal.service.WeightService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeightController {
    private WeightService weightService;
    private UserInfoExtractor infoExtractor;

    public WeightController(WeightService weightService, UserInfoExtractor infoExtractor) {
        this.weightService = weightService;
        this.infoExtractor = infoExtractor;
    }

    @GetMapping("/weight")
    public Float getWeight(Authentication authentication) {
        return weightService.get(infoExtractor.getUserId(authentication));
    }
}
