package by.dima.nutrition.service;

import by.dima.nutrition.model.Meal;

import java.util.Collection;

public interface MealService {
    Meal getById(Integer id) throws Exception;
    Collection<Meal> getAll();
    Collection<Meal> getFavourites(Integer userId);
    void addToFavourites(Meal meal, Integer userId);
    void removeFromFavourites(Meal meal, Integer userId);
}
