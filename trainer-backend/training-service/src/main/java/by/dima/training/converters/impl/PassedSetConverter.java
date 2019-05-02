package by.dima.training.converters.impl;

import by.dima.training.converters.Converter;
import by.dima.training.dto.PassedSetDTO;
import by.dima.training.model.ExerciseSet;
import by.dima.training.model.PassedSet;
import by.dima.training.model.Training;
import org.springframework.stereotype.Service;

@Service
public class PassedSetConverter implements Converter<PassedSetDTO, PassedSet> {

    @Override
    public PassedSet convert(PassedSetDTO source) {
        return new PassedSet(source.getId(), source.getIdUser(), source.getExecDate(), new ExerciseSet(source.getExerciseSetId()), new Training(source.getIdTraining()));
    }
}
