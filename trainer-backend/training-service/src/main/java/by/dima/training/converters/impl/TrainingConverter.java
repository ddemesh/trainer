package by.dima.training.converters.impl;

import by.dima.training.converters.Converter;
import by.dima.training.dto.PassedSetDTO;
import by.dima.training.dto.TrainingComplexDTO;
import by.dima.training.dto.TrainingDTO;
import by.dima.training.model.PassedSet;
import by.dima.training.model.Training;
import by.dima.training.model.TrainingComplex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class TrainingConverter implements Converter<TrainingDTO, Training> {

    private Converter<PassedSetDTO, PassedSet> passedSetConverter;
    private Converter<TrainingComplexDTO, TrainingComplex> complexConverter;

    public TrainingConverter(Converter<PassedSetDTO, PassedSet> passedSetConverter) {
        this.passedSetConverter = passedSetConverter;
    }

    @Autowired
    public void setComplexConverter(Converter<TrainingComplexDTO, TrainingComplex> complexConverter) {
        this.complexConverter = complexConverter;
    }

    @Override
    public Training convert(TrainingDTO source) {
        TrainingComplexDTO recursionPreventionComplex = new TrainingComplexDTO(source.getTrainingComplexDTO().getId(),
                source.getTrainingComplexDTO().getName(),
                new HashSet<>(), source.getTrainingComplexDTO().getTagDTOS());
        return new Training(source.getId(), source.getWeekDay(), complexConverter.convert(recursionPreventionComplex), source.getPassedSetDTOS().parallelStream().map(passedSetConverter::convert).collect(Collectors.toSet()));
    }
}
