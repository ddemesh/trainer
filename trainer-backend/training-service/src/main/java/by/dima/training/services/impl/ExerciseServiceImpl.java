package by.dima.training.services.impl;

import by.dima.training.model.Exercise;
import by.dima.training.repository.ExerciseRepository;
import by.dima.training.services.ExerciseService;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Iterable<Exercise> getAll() {
        return exerciseRepository.findAll();
    }
}
