package by.dima.training.services.impl;

import by.dima.training.converters.Converter;
import by.dima.training.dto.TrainingComplexDTO;
import by.dima.training.exception.IncorrectIdException;
import by.dima.training.model.Training;
import by.dima.training.model.TrainingComplex;
import by.dima.training.repository.ComplexRepository;
import by.dima.training.services.ComplexService;
import by.dima.training.services.PassedSetService;
import by.dima.training.services.SetService;
import by.dima.training.services.TrainingService;
import by.dima.training.utils.ConstantHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ComplexServiceImpl implements ComplexService {
    private ComplexRepository complexRepository;
    private Converter<TrainingComplexDTO, TrainingComplex> complexConverter;
    private Converter<TrainingComplex, TrainingComplexDTO> complexDTOConverter;

    private SetService setService;
    private PassedSetService passedSetService;
    private TrainingService trainingService;
    private ConstantHolder constants;

    @Autowired
    public ComplexServiceImpl(ComplexRepository complexRepository, Converter<TrainingComplexDTO, TrainingComplex> complexConverter, Converter<TrainingComplex, TrainingComplexDTO> complexDTOConverter, SetService setService, PassedSetService passedSetService, TrainingService trainingService, ConstantHolder constants) {
        this.complexRepository = complexRepository;
        this.complexConverter = complexConverter;
        this.complexDTOConverter = complexDTOConverter;
        this.setService = setService;
        this.passedSetService = passedSetService;
        this.trainingService = trainingService;
        this.constants = constants;
    }

    @Override
    public void addToFavourites(TrainingComplex complex, Integer userId) {
        TrainingComplexDTO complexDTO = complexDTOConverter.convert(complex);
        complexDTO.getTrainingDTO().forEach(training -> training.getPassedSetDTOS()
                .stream()
                .filter(set -> set.getExecDate() == null)
                .findFirst()
                .ifPresent(set -> {
                    training.getPassedSetDTOS().add(passedSetService.pass(set, userId));
                }));

        complexRepository.save(complexDTO);
    }

    @Override
    public void removeFromFavourites(TrainingComplex complex, Integer userId) {
        passedSetService.removePassedSetsByTrainingsAndUser(userId, complex.getTraining()
                .parallelStream()
                .map(Training::getId)
                .collect(Collectors.toList()));
    }

    @Override
    public void save(TrainingComplex complex, Integer userId) {
        complex.setId(null);

        complex.getTraining().forEach(training -> trainingService.save(training, userId));

        complexRepository.save(complexDTOConverter.convert(complex));
    }

    @Override
    public Collection<TrainingComplex> getFavourites(Integer userId) {
        Set<TrainingComplex> complexes = new HashSet<>();

        trainingService.getAllByIds(passedSetService.getPassedSetsByDate(userId, null)
                .stream()
                .map(passedSet -> passedSet.getTraining().getId())
                .collect(Collectors.toSet()))
                .forEach(training -> complexes.add(training.getComplex()));

        return complexes;
    }

    @Override
    public Collection<TrainingComplex> getAvailable() {
        return getFavourites(constants.getSystemUserId());
    }

    @Override
    public TrainingComplex getById(Integer complexId, Integer userId) {
        TrainingComplex complex = gerByIdOrThrow(complexId);

        complex.getTraining().forEach(training -> {
            training.setPassedSets(training.getPassedSets().stream().filter(set -> set.getExecDate() == null && set.getIdUser().equals(userId)).collect(Collectors.toSet()));
            training.getPassedSets().forEach(set -> set.setExerciseSet(setService.getById(set.getExerciseSet().getId())));
        });

        return complex;
    }

    @Override
    public TrainingComplex getById(Integer complexId) {
        return this.gerByIdOrThrow(complexId);
    }

    @Override
    public Integer getLastPassedTrainingId(Integer complexId, Integer userId) {
        TrainingComplex complex = gerByIdOrThrow(complexId);

        return passedSetService.getLastSetOfTrainings(complex.getTraining().parallelStream().map(Training::getId).collect(Collectors.toList()), userId);
    }

    private TrainingComplex gerByIdOrThrow(Integer complexId) {
        return complexConverter.convert(complexRepository.findById(complexId)
                .orElseThrow(() -> new IncorrectIdException("Complex with such id not ")));
    }
}
