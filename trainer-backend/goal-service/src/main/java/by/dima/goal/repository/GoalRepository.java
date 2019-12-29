package by.dima.goal.repository;

import by.dima.goal.model.BodyAttribute;
import by.dima.goal.model.Goal;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Date;

public interface GoalRepository extends CrudRepository<Goal, Integer> {
    Goal findByBodyAttributeAndTargetDateAndGoalAndUserId(BodyAttribute bodyAttribute, Date targetDate, Float goal, Integer userId);
    Collection<Goal> findAllByUserIdAndAndGoalNotNullAndTargetDateNotNull(Integer userId);
    Collection<Goal> findAllByUserId(Integer userId);
}
