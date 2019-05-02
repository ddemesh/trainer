package by.dima.training.converters.impl.reverse;

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
public class ComplexDTOConverter implements Converter<TrainingComplex, TrainingComplexDTO> {

    private Converter<Training, TrainingDTO> trainingDTOConverter;
    private Converter<Tag, TagDTO> tagDTOConverter;

    @Autowired
    public ComplexDTOConverter(Converter<Tag, TagDTO> tagDTOConverter) {
        this.tagDTOConverter = tagDTOConverter;
    }

    @Autowired
    public void setTrainingDTOConverter(Converter<Training, TrainingDTO> trainingDTOConverter) {
        this.trainingDTOConverter = trainingDTOConverter;
    }

    @Override
    public TrainingComplexDTO convert(TrainingComplex source) {
        return new TrainingComplexDTO(source.getId(), source.getName(), source.getTraining()
                .parallelStream()
                .map(trainingDTOConverter::convert)
                .collect(Collectors.toSet()), source.getTags()
                .parallelStream()
                .map(tagDTOConverter::convert).collect(Collectors.toSet()));
    }
}
