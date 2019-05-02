package by.dima.training.services.impl;

import by.dima.training.converters.Converter;
import by.dima.training.dto.TrainingDTO;
import by.dima.training.model.PassedSet;
import by.dima.training.model.Training;
import by.dima.training.repository.TrainingRepository;
import by.dima.training.services.PassedSetService;
import by.dima.training.services.TrainingService;
import by.dima.training.utils.ConstantHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrainingServiceImpl implements TrainingService {
    private TrainingRepository trainingRepository;
    private ConstantHolder constants;
    private Converter<TrainingDTO, Training> trainingConverter;
    private Converter<Training, TrainingDTO> trainingDTOConverter;

    private PassedSetService passedSetService;

    public TrainingServiceImpl(TrainingRepository trainingRepository, ConstantHolder constants, Converter<TrainingDTO, Training> trainingConverter, Converter<Training, TrainingDTO> trainingDTOConverter, PassedSetService passedSetService) {
        this.trainingRepository = trainingRepository;
        this.constants = constants;
        this.trainingConverter = trainingConverter;
        this.trainingDTOConverter = trainingDTOConverter;
        this.passedSetService = passedSetService;
    }

    @Override
    public List<Training> getAllByIds(Set<Integer> ids) {
        return trainingRepository.findAllByIdIn(ids)
                .stream()
                .map(trainingConverter::convert).collect(Collectors.toList());
    }

    @Override
    public void save(Training training, Integer userId) {
        training.setId(null);
        Set<PassedSet> passedSets = training.getPassedSets();
        training.setPassedSets(null);
        trainingRepository.save(trainingDTOConverter.convert(training));
        passedSets.forEach(set -> passedSetService.save(set, userId));
    }


/*
    @Override
    public TrainingComplex getFullInfo(Integer complexId, Integer userId) {

    }*/
}
