package by.dima.training.converters.impl;

import by.dima.training.converters.Converter;
import by.dima.training.dto.MuscleDTO;
import by.dima.training.model.Muscle;
import org.springframework.stereotype.Service;

@Service
public class MuscleConverter implements Converter<MuscleDTO, Muscle> {
    @Override
    public Muscle convert(MuscleDTO source) {
        return new Muscle(source.getId(), source.getName());
    }
}
