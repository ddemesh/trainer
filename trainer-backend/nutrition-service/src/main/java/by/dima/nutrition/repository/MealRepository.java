package by.dima.nutrition.repository;

import by.dima.nutrition.model.Meal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MealRepository extends CrudRepository<Meal, Integer> {
    List<Meal> findAllByUsersContains(Integer userId);
}
