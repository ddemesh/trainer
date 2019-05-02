package by.dima.training.converters.impl.reverse;

import by.dima.training.converters.Converter;
import by.dima.training.dto.MuscleDTO;
import by.dima.training.model.Muscle;
import org.springframework.stereotype.Service;

@Service
public class MuscleDTOConverter implements Converter<Muscle, MuscleDTO> {
    @Override
    public MuscleDTO convert(Muscle source) {
        return new MuscleDTO(source.getId(), source.getName());
    }
}
