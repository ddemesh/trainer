package by.dima.training.services;

import by.dima.training.model.Training;
import by.dima.training.model.TrainingComplex;

import java.util.List;
import java.util.Set;

public interface TrainingService {
    Iterable<TrainingComplex> getAllTrainings();
    Set<TrainingComplex> getSavedTrainings(Integer userId);
    TrainingComplex saveComplex(Integer complexId, Integer userId);
    void deleteComplex(Integer complexId, Integer userId);
    TrainingComplex createComplex(TrainingComplex complex, Integer userId);
    TrainingComplex getFullInfo(Integer complexId, Integer userId);
    Integer getLastTrainingId(Integer complexId, Integer userId);
}
