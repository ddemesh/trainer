package by.dima.training.services;

import by.dima.training.model.ExerciseSet;
import by.dima.training.model.PassedSet;

public interface SetService {
    ExerciseSet getById(Integer id);
    void save(ExerciseSet set);
}
