package by.dima.nutrition.controller;

import by.dima.common.utils.UserInfoExtractor;
import by.dima.nutrition.model.Meal;
import by.dima.nutrition.service.MealService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/meal")
public class MealController {
    private MealService mealService;
    private UserInfoExtractor infoExtractor;

    public MealController(MealService mealService, UserInfoExtractor infoExtractor) {
        this.mealService = mealService;
        this.infoExtractor = infoExtractor;
    }

    @GetMapping
    public Collection<Meal> getAll() {
        return mealService.getAll();
    }

    @GetMapping("/me")
    public Collection<Meal> getFavourites(Authentication authentication) {
        return mealService.getFavourites(infoExtractor.getUserId(authentication));
    }

    @PostMapping("/me")
    public void addToFavourites(@RequestBody Meal meal, Authentication authentication) {
        mealService.addToFavourites(meal, infoExtractor.getUserId(authentication));
    }

    @DeleteMapping("/me/{mealId}")
    public void removeFromFavourites(@PathVariable Integer mealId, Authentication authentication) throws Exception {
        Meal meal = mealService.getById(mealId);
        mealService.removeFromFavourites(meal, infoExtractor.getUserId(authentication));
    }
}
