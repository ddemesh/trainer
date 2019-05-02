package by.dima.training.converters.impl;

import by.dima.training.converters.Converter;
import by.dima.training.dto.ExerciseDTO;
import by.dima.training.dto.ExerciseRepeatingDTO;
import by.dima.training.model.Exercise;
import by.dima.training.model.ExerciseRepeating;
import org.springframework.stereotype.Service;

@Service
public class RepeatingConverter implements Converter<ExerciseRepeatingDTO, ExerciseRepeating> {

    private Converter<ExerciseDTO, Exercise> exerciseConverter;

    public RepeatingConverter(Converter<ExerciseDTO, Exercise> exerciseConverter) {
        this.exerciseConverter = exerciseConverter;
    }

    @Override
    public ExerciseRepeating convert(ExerciseRepeatingDTO source) {
        return new ExerciseRepeating(source.getId(), source.getComplexity(), source.getCount(), source.getRest(), exerciseConverter.convert(source.getExerciseDTO()));
    }
}
