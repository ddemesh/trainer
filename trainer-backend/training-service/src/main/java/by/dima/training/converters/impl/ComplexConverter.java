package by.dima.training.converters.impl;

import by.dima.training.converters.Converter;
import by.dima.training.dto.TagDTO;
import by.dima.training.dto.TrainingComplexDTO;
import by.dima.training.dto.TrainingDTO;
import by.dima.training.model.Tag;
import by.dima.training.model.Training;
import by.dima.training.model.TrainingComplex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ComplexConverter implements Converter<TrainingComplexDTO, TrainingComplex> {

    private Converter<TrainingDTO, Training> trainingConverter;
    private Converter<TagDTO, Tag> tagConverter;

    public ComplexConverter(Converter<TagDTO, Tag> tagConverter) {
        this.tagConverter = tagConverter;
    }

    @Autowired
    public void setTrainingConverter(Converter<TrainingDTO, Training> trainingConverter) {
        this.trainingConverter = trainingConverter;
    }

    @Override
    public TrainingComplex convert(TrainingComplexDTO source) {
        return new TrainingComplex(source.getId(), source.getName(), source.getTrainingDTO()
                .stream()
                .map(trainingConverter::convert).collect(Collectors.toSet()), source.getTagDTOS()
                .stream()
                .map(tagConverter::convert).collect(Collectors.toSet()));
    }
}
