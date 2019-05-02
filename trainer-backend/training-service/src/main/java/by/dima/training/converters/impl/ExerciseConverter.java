package by.dima.training.converters.impl;

import by.dima.training.converters.Converter;
import by.dima.training.dto.ExerciseDTO;
import by.dima.training.dto.MuscleDTO;
import by.dima.training.model.Exercise;
import by.dima.training.model.Muscle;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ExerciseConverter implements Converter<ExerciseDTO, Exercise> {

    private Converter<MuscleDTO, Muscle> muscleConverter;

    public ExerciseConverter(Converter<MuscleDTO, Muscle> muscleConverter) {
        this.muscleConverter = muscleConverter;
    }

    @Override
    public Exercise convert(ExerciseDTO source) {
        return new Exercise(
                source.getId(),
                source.getName(),
                source.getMuscleDTOs()
                        .parallelStream()
                        .map(muscleConverter::convert)
                        .collect(Collectors.toSet()));
    }
}
