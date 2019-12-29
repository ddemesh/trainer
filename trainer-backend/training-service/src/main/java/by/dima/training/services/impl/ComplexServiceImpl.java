package by.dima.training.services.impl;

import by.dima.training.exception.IncorrectIdException;
import by.dima.training.model.PassedSet;
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
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ComplexServiceImpl implements ComplexService {
    private ComplexRepository complexRepository;

    private SetService setService;
    private PassedSetService passedSetService;
    private TrainingService trainingService;
    private ConstantHolder constants;

    @Autowired
    public ComplexServiceImpl(ComplexRepository complexRepository, SetService setService, PassedSetService passedSetService, TrainingService trainingService, ConstantHolder constants) {
        this.complexRepository = complexRepository;
        this.setService = setService;
        this.passedSetService = passedSetService;
        this.trainingService = trainingService;
        this.constants = constants;
    }

    @Override
    public void addToFavourites(TrainingComplex complex, Integer userId) {
        complex.getTraining().forEach(training -> training.getPassedSets().addAll(training.getPassedSets()
                .stream()
                .filter(set -> set.getExecDate() == null && constants.getSystemUserId().equals(set.getIdUser()))
                .map(passedSet -> passedSetService.pass(passedSet, userId)).collect(Collectors.toList())));

        complexRepository.save(complex);
    }

    @Override
    public void removeFromFavourites(Integer complexId, Integer userId) {
        passedSetService.removePassedSetsByTrainingsAndUser(userId, trainingService.getAllByComplexId(complexId)
                .parallelStream()
                .map(Training::getId)
                .collect(Collectors.toList()));
    }

    @Override
    public void save(TrainingComplex complex, Integer userId) {
        complex.setId(null);

        complex.getTraining().forEach(training -> trainingService.save(training, userId));

        complexRepository.save(complex);
    }

    @Override
    public Collection<TrainingComplex> getFavourites(Integer userId) {
        Set<TrainingComplex> complexes = new HashSet<>();

        trainingService.getAllByIds(passedSetService.getPassedSetsByDate(userId, null)
                .stream()
                .map(PassedSet::getIdTraining)
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
        TrainingComplex result = new TrainingComplex();
        result.setId(complexId);
        result.setName(complex.getName());
        result.setTags(new HashSet<>(complex.getTags()));

        result.setTraining(complex.getTraining().parallelStream().map(training -> {
            Training copyTraining = new Training();
            copyTraining.setId(training.getId());
            copyTraining.setWeekDay(training.getWeekDay());
            copyTraining.setComplex(training.getComplex());
            copyTraining.setPassedSets(training.getPassedSets().stream().filter(set -> set.getExecDate() == null && set.getIdUser().equals(userId)).collect(Collectors.toList()));
            if (copyTraining.getPassedSets().size() == 0) {
                copyTraining.setPassedSets(training.getPassedSets().stream().filter(set -> set.getExecDate() == null && set.getIdUser().equals(constants.getSystemUserId())).collect(Collectors.toList()));
            }
            copyTraining.getPassedSets().forEach(set -> set.setSet(setService.getById(set.getExerciseSet())));
            return copyTraining;
        }).collect(Collectors.toSet()));

        return result;
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
        return complexRepository.findById(complexId)
                .orElseThrow(() -> new IncorrectIdException("Complex with such id not "));
    }
}
