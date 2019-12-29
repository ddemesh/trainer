package by.dima.goal.service.impl;

import by.dima.goal.model.BodyAttribute;
import by.dima.goal.model.Goal;
import by.dima.goal.model.Progress;
import by.dima.goal.repository.BodyAttributeRepository;
import by.dima.goal.service.BodyAttributeService;
import by.dima.goal.service.GoalService;
import by.dima.goal.service.ProgressService;
import org.springframework.stereotype.Service;

@Service
public class BodyAttributeServiceImpl implements BodyAttributeService {
    private BodyAttributeRepository attributeRepository;
    private GoalService goalService;
    private ProgressService progressService;

    public BodyAttributeServiceImpl(BodyAttributeRepository attributeRepository, GoalService goalService, ProgressService progressService) {
        this.attributeRepository = attributeRepository;
        this.goalService = goalService;
        this.progressService = progressService;
    }

    @Override
    public Iterable<BodyAttribute> getAll() {
        return attributeRepository.findAll();
    }

    @Override
    public BodyAttribute get(Integer attributeId) {
        return attributeRepository.findById(attributeId).orElseThrow(RuntimeException::new);
    }

    @Override
    public void update(BodyAttribute bodyAttribute, Progress progress, Integer userId) {
        Goal goal = goalService.findByAttributeAndDateAndTargetAndUser(bodyAttribute, null, null, userId);
        if (goal == null) {
            goal = new Goal();
            goal.setBodyAttribute(bodyAttribute);
            goalService.create(goal, userId);
        }
        progressService.add(progress, goal);
    }
}
