package by.dima.training.services;

import by.dima.training.dto.PassedSetDTO;
import by.dima.training.model.PassedSet;

import java.util.Date;
import java.util.List;

public interface PassedSetService {
    void save(PassedSet passedSet, Integer userId);
    PassedSet getById(Integer id);
    PassedSetDTO pass(PassedSetDTO set, Integer userId);
    List<PassedSet> getPassedSetsByDate(Integer userId, Date date);
    void removePassedSetsByTrainingsAndUser(Integer userId, List<Integer> trainings);
    Integer getLastSetOfTrainings(List<Integer> trainings, Integer userId);
}
