package by.dima.training.converters.impl.reverse;

import by.dima.training.converters.Converter;
import by.dima.training.dto.ExerciseDTO;
import by.dima.training.dto.MuscleDTO;
import by.dima.training.model.Exercise;
import by.dima.training.model.Muscle;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ExerciseDTOConverter implements Converter<Exercise, ExerciseDTO> {

    private Converter<Muscle, MuscleDTO> muscleDTOConverter;

    public ExerciseDTOConverter(Converter<Muscle, MuscleDTO> muscleDTOConverter) {
        this.muscleDTOConverter = muscleDTOConverter;
    }

    @Override
    public ExerciseDTO convert(Exercise source) {
        return new ExerciseDTO(source.getId(), source.getName(), source.getMuscles().parallelStream().map(muscleDTOConverter::convert).collect(Collectors.toSet()));
    }
}
