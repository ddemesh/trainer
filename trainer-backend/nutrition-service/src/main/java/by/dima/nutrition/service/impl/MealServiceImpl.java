package by.dima.nutrition.service.impl;

import by.dima.nutrition.model.Meal;
import by.dima.nutrition.repository.MealRepository;
import by.dima.nutrition.service.MealService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository mealRepository;

    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public Meal getById(Integer id) {
        return mealRepository.findById(id).orElseThrow(() -> new RuntimeException("Meal not found"));
    }

    @Override
    public Collection<Meal> getAll() {
        return StreamSupport.stream(mealRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Meal> getFavourites(Integer userId) {
        return mealRepository.findAllByUsersContains(userId);
    }

    @Override
    public void addToFavourites(Meal meal, Integer userId) {
        if (meal.getUsers() == null) {
            meal.setUsers(new HashSet<>());
        }
        meal.getUsers().add(userId);
        mealRepository.save(meal);
    }

    @Override
    public void removeFromFavourites(Meal meal, Integer userId) {
        meal.getUsers().remove(userId);
        mealRepository.save(meal);
    }
}
