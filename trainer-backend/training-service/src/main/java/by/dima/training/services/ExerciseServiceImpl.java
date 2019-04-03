package by.dima.training.services;

import by.dima.training.model.Exercise;
import by.dima.training.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public Iterable<Exercise> getAll() {
        return exerciseRepository.findAll();
    }
}
