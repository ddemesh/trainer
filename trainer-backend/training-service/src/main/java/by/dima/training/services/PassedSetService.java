package by.dima.training.services;

import by.dima.training.model.PassedSet;

public interface PassedSetService {
    void pass(PassedSet set);
    PassedSet getSetsOfTraining(Integer trainingId);

}
