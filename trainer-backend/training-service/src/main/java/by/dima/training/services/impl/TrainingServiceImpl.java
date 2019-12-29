package by.dima.training.services.impl;

import by.dima.training.model.PassedSet;
import by.dima.training.model.Training;
import by.dima.training.repository.TrainingRepository;
import by.dima.training.services.PassedSetService;
import by.dima.training.services.TrainingService;
import by.dima.training.utils.ConstantHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TrainingServiceImpl implements TrainingService {
    private TrainingRepository trainingRepository;
    private ConstantHolder constants;

    private PassedSetService passedSetService;

    public TrainingServiceImpl(TrainingRepository trainingRepository, ConstantHolder constants, PassedSetService passedSetService) {
        this.trainingRepository = trainingRepository;
        this.constants = constants;
        this.passedSetService = passedSetService;
    }

    @Override
    public List<Training> getAllByIds(Set<Integer> ids) {
        return new ArrayList<>(trainingRepository.findAllByIdIn(ids));
    }

    @Override
    public List<Training> getAllByComplexId(Integer id) {
        return new ArrayList<>(trainingRepository.findAllByComplexId(id));
    }

    @Override
    public void save(Training training, Integer userId) {
        training.setId(null);
        List<PassedSet> passedSets = training.getPassedSets();
        training.setPassedSets(null);
        trainingRepository.save(training);
        passedSets.forEach(set -> {
            set.setIdTraining(training.getId());
            passedSetService.save(set, userId);
        });
    }


/*
    @Override
    public TrainingComplex getFullInfo(Integer complexId, Integer userId) {

    }*/
}
