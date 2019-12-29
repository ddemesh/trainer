package by.dima.goal.service.impl;

import by.dima.goal.model.BodyAttribute;
import by.dima.goal.model.Goal;
import by.dima.goal.repository.GoalRepository;
import by.dima.goal.service.GoalService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Service
public class GoalServiceImpl implements GoalService {
    private GoalRepository goalRepository;

    public GoalServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public Goal find(Integer goalId) {
        return goalRepository.findById(goalId).orElseThrow(() -> new RuntimeException("Goal by id wasn\'t found"));
    }

    @Override
    public Goal findByAttributeAndDateAndTargetAndUser(BodyAttribute bodyAttribute, Date date, Float target, Integer userId) {
        return goalRepository.findByBodyAttributeAndTargetDateAndGoalAndUserId(bodyAttribute, date, target, userId);
    }

    @Override
    public Collection<Goal> getAll(Integer userId) {
        return goalRepository.findAllByUserIdAndAndGoalNotNullAndTargetDateNotNull(userId);
    }

    @Override
    public Goal createCustom(Goal goal, Integer userId) {
        if (goal.getTargetDate() == null && goal.getGoal() == null) {
            throw new IllegalArgumentException("Goal must contain either target date or goal");
        }
        return create(goal, userId);
    }

    @Override
    public Goal create(Goal goal, Integer userId) {
        goal.setId(null);
        goal.setUserId(userId);
        return goalRepository.save(goal);
    }

    @Override
    public void update(Goal goal) {
        Goal original = find(goal.getId());
        if (!Objects.equals(original.getTargetDate(), original.getTargetDate()) &&
                !Objects.equals(original.getGoal(), original.getGoal()) &&
                goal.getTargetDate() == null && goal.getGoal() == null) {
            throw new IllegalArgumentException("Goal must contain either target date or goal");
        }

        goalRepository.save(goal);
    }

    @Override
    public void delete(Goal goal) {
        goalRepository.delete(goal);
    }
}
