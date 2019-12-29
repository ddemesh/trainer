package by.dima.training.services;

import by.dima.training.model.PassedSet;

import java.util.Date;
import java.util.List;

public interface PassedSetService {
    void save(PassedSet passedSet, Integer userId);
    PassedSet getById(Integer id);
    PassedSet pass(PassedSet set, Integer userId);
    List<PassedSet> getPassedSetsByDate(Integer userId, Date date);
    void removePassedSetsByTrainingsAndUser(Integer userId, List<Integer> trainings);
    Integer getLastSetOfTrainings(List<Integer> trainings, Integer userId);
}
