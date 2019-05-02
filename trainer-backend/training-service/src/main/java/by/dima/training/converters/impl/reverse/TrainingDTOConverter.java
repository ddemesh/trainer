package by.dima.training.converters.impl.reverse;

import by.dima.training.converters.Converter;
import by.dima.training.dto.PassedSetDTO;
import by.dima.training.dto.TrainingComplexDTO;
import by.dima.training.dto.TrainingDTO;
import by.dima.training.model.PassedSet;
import by.dima.training.model.Training;
import by.dima.training.model.TrainingComplex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TrainingDTOConverter implements Converter<Training, TrainingDTO> {

    private Converter<PassedSet, PassedSetDTO> passedSetDTOConverter;
    private Converter<TrainingComplex, TrainingComplexDTO> complexDTOConverter;

    @Autowired
    public TrainingDTOConverter(Converter<PassedSet, PassedSetDTO> passedSetDTOConverter) {
        this.passedSetDTOConverter = passedSetDTOConverter;
    }

    @Autowired
    public void setComplexDTOConverter(Converter<TrainingComplex, TrainingComplexDTO> complexDTOConverter) {
        this.complexDTOConverter = complexDTOConverter;
    }

    @Override
    public TrainingDTO convert(Training source) {
        return new TrainingDTO(source.getId(), source.getWeekDay(), source.getPassedSets().parallelStream().map(passedSetDTOConverter::convert).collect(Collectors.toSet()), complexDTOConverter.convert(source.getComplex()));
    }
}
