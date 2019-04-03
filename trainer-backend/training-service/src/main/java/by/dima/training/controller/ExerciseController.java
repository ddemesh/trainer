package by.dima.training.controller;

import by.dima.training.model.Exercise;
import by.dima.training.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/training")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/exercise")
    public Iterable<Exercise> getAllExercises() {
        return exerciseService.getAll();
    }

    @GetMapping("{complexId}/exercise")
    public Iterable<Exercise> getExercisesOfTraining(@PathVariable Integer complexId) {
        return exerciseService.getAll();
    }
}
