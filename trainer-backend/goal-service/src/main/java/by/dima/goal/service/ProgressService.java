package by.dima.goal.service;

import by.dima.goal.model.Goal;
import by.dima.goal.model.Progress;

public interface ProgressService {
    void add(Progress progress, Integer goalId);
    void add(Progress progress, Goal goal);
}
