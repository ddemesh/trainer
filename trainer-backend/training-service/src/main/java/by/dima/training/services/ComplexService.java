package by.dima.training.services;

import by.dima.training.model.TrainingComplex;

import java.util.Collection;

public interface ComplexService {
    void addToFavourites(TrainingComplex complex, Integer userId);
    void removeFromFavourites(Integer complexId, Integer userId);
    void save(TrainingComplex complex, Integer userId);
    Collection<TrainingComplex> getFavourites(Integer userId);
    Collection<TrainingComplex> getAvailable();
    TrainingComplex getById(Integer complexId, Integer userId);
    TrainingComplex getById(Integer complexId);
    Integer getLastPassedTrainingId(Integer complexId, Integer userId);
}
