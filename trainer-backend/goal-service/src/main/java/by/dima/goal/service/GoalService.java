package by.dima.goal.service;

import by.dima.goal.model.BodyAttribute;
import by.dima.goal.model.Goal;

import java.util.Collection;
import java.util.Date;

public interface GoalService {
    Goal find(Integer goalId);
    Goal findByAttributeAndDateAndTargetAndUser(BodyAttribute bodyAttribute, Date date, Float target, Integer userId);
    Collection<Goal> getAll(Integer userId);
    Goal createCustom(Goal goal, Integer userId);
    Goal create(Goal goal, Integer userId);
    void update(Goal goal);
    void delete(Goal goal);
}
