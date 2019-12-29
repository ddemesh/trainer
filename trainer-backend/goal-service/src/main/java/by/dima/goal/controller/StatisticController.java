package by.dima.goal.controller;

import by.dima.common.utils.UserInfoExtractor;
import by.dima.goal.model.StatisticItem;
import by.dima.goal.service.StatisticService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticController {

    private StatisticService statisticService;
    private UserInfoExtractor infoExtractor;

    public StatisticController(StatisticService statisticService, UserInfoExtractor infoExtractor) {
        this.statisticService = statisticService;
        this.infoExtractor = infoExtractor;
    }

    @GetMapping("/data")
    public List<StatisticItem> getData(Authentication authentication) {
        return statisticService.getAll(infoExtractor.getUserId(authentication));
    }
}
