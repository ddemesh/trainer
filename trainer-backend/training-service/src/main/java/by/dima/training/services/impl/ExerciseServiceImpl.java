package by.dima.training.services.impl;

import by.dima.training.converters.Converter;
import by.dima.training.dto.ExerciseDTO;
import by.dima.training.model.Exercise;
import by.dima.training.repository.ExerciseRepository;
import by.dima.training.services.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private ExerciseRepository exerciseRepository;
    private Converter<ExerciseDTO, Exercise> exerciseConverter;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, Converter<ExerciseDTO, Exercise> exerciseConverter) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseConverter = exerciseConverter;
    }

    @Override
    public Iterable<Exercise> getAll() {
        return StreamSupport.stream(exerciseRepository.findAll().spliterator(), true).map(exerciseConverter::convert).collect(Collectors.toSet());
    }
}
