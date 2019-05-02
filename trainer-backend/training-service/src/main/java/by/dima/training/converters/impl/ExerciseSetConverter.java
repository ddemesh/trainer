package by.dima.training.converters.impl;

import by.dima.training.converters.Converter;
import by.dima.training.dto.ExerciseRepeatingDTO;
import by.dima.training.dto.ExerciseSetDTO;
import by.dima.training.model.ExerciseRepeating;
import by.dima.training.model.ExerciseSet;
import org.springframework.stereotype.Service;

@Service
public class ExerciseSetConverter implements Converter<ExerciseSetDTO, ExerciseSet> {
    private Converter<ExerciseRepeatingDTO, ExerciseRepeating> repeatingConverter;

    public ExerciseSetConverter(Converter<ExerciseRepeatingDTO, ExerciseRepeating> repeatingConverter) {
        this.repeatingConverter = repeatingConverter;
    }

    @Override
    public ExerciseSet convert(ExerciseSetDTO source) {
        return new ExerciseSet(source.getId(), source.getCount(), repeatingConverter.convert(source.getRepeating()));
    }
}
