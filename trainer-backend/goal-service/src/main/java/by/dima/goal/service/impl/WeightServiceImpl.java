package by.dima.goal.service.impl;

import by.dima.goal.model.Goal;
import by.dima.goal.model.Progress;
import by.dima.goal.service.BodyAttributeService;
import by.dima.goal.service.GoalService;
import by.dima.goal.service.WeightService;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class WeightServiceImpl implements WeightService {

    private GoalService goalService;
    private BodyAttributeService attributeService;

    public WeightServiceImpl(GoalService goalService, BodyAttributeService attributeService) {
        this.goalService = goalService;
        this.attributeService = attributeService;
    }

    @Override
    public Float get(Integer userID) {
        Goal goal = goalService.findByAttributeAndDateAndTargetAndUser(attributeService.get(1), null, null, userID);
        if (goal == null) return -1.0f;
        return goal.getProgress()
                .parallelStream()
                .max(Comparator.comparing(Progress::getDate))
                .orElseThrow(() -> new IllegalArgumentException("User didn't set his weight"))
                .getValue()
                .floatValue();
    }
}
