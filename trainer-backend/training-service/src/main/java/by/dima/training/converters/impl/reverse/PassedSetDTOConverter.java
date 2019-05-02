package by.dima.training.converters.impl.reverse;

import by.dima.training.converters.Converter;
import by.dima.training.dto.PassedSetDTO;
import by.dima.training.model.PassedSet;
import org.springframework.stereotype.Service;

@Service
public class PassedSetDTOConverter implements Converter<PassedSet, PassedSetDTO> {
    @Override
    public PassedSetDTO convert(PassedSet source) {
        return new PassedSetDTO(source.getId(), source.getIdUser(), source.getExecDate(), source.getExerciseSet().getId(), source.getTraining().getId());
    }
}
