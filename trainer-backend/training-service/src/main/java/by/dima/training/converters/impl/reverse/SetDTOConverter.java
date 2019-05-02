package by.dima.training.converters.impl.reverse;

import by.dima.training.converters.Converter;
import by.dima.training.dto.ExerciseRepeatingDTO;
import by.dima.training.dto.ExerciseSetDTO;
import by.dima.training.model.ExerciseRepeating;
import by.dima.training.model.ExerciseSet;
import org.springframework.stereotype.Service;

@Service
public class SetDTOConverter implements Converter<ExerciseSet, ExerciseSetDTO> {

    private Converter<ExerciseRepeating, ExerciseRepeatingDTO> repeatingDTOConverter;

    public SetDTOConverter(Converter<ExerciseRepeating, ExerciseRepeatingDTO> repeatingDTOConverter) {
        this.repeatingDTOConverter = repeatingDTOConverter;
    }

    @Override
    public ExerciseSetDTO convert(ExerciseSet source) {
        return new ExerciseSetDTO(source.getId(), source.getCount(), repeatingDTOConverter.convert(source.getRepeating()));
    }
}
