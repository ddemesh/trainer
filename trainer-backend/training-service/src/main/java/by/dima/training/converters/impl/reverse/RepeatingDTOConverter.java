package by.dima.training.converters.impl.reverse;

import by.dima.training.converters.Converter;
import by.dima.training.dto.ExerciseDTO;
import by.dima.training.dto.ExerciseRepeatingDTO;
import by.dima.training.model.Exercise;
import by.dima.training.model.ExerciseRepeating;
import org.springframework.stereotype.Service;

@Service
public class RepeatingDTOConverter implements Converter<ExerciseRepeating, ExerciseRepeatingDTO> {

    private Converter<Exercise, ExerciseDTO> exerciseDTOConverter;

    public RepeatingDTOConverter(Converter<Exercise, ExerciseDTO> exerciseDTOConverter) {
        this.exerciseDTOConverter = exerciseDTOConverter;
    }

    @Override
    public ExerciseRepeatingDTO convert(ExerciseRepeating source) {
        return new ExerciseRepeatingDTO(source.getId(), source.getComplexity(), source.getCount(), source.getRest(), exerciseDTOConverter.convert(source.getExercise()));
    }
}
